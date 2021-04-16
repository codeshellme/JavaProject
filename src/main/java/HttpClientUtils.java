import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private static final String GET = "GET";
    private static final String POST = "POST";

    public static class PostFileInner {
        // <input type="text" name="xxx" />
        // <input type="file" name="yyy" />

        public String name;     // 对应了 xxx/yyy
        public String type;     // 对应了 text/file
        public Object value;    // 当 type 为 text 时，value 为 String 类型
                                // 当 type 为 file 时，value 为 File 类型

        public PostFileInner (String name, String type, Object value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() { return name; }
        public String getType() { return type; }
        public Object getValue() { return value; }
    }

    /**
     * 打印 HTTP GET/POST 请求体
     */
    private static void printRequest(HttpRequestBase req) {
        String reqLine = req.getRequestLine().toString();
        Header[] hs = req.getAllHeaders();

        System.out.println("HttpClientUtils reqLine: " + reqLine);
        for (Header header : hs) {
            String h = header.getName() + ": " + header.getValue();
            System.out.println("HttpClientUtils reqHeaders: " + h);
        }

        if (!(req instanceof HttpPost)) {   // 如果不是 POST 请求返回
            return;
        }

        // 打印请求体 针对 POST 请求
        HttpEntity entity = ((HttpPost)req).getEntity();
        String body = null;
        Header en = entity.getContentEncoding();
        Header ty = entity.getContentType();

        try {
            body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (en != null) {
            System.out.println("HttpClientUtils reqBodyEncode: " + en.getName() +"-"+ en.getValue());
        }

        if (ty != null) {
            System.out.println("HttpClientUtils reqBodyType: " + ty.getName() +"-"+ ty.getValue());
        }

        if (body != null) {
            System.out.println("HttpClientUtils reqBodyLen: " + entity.getContentLength());
            System.out.println("HttpClientUtils reqBody: " + body);
        }

        try {
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpRequestBase getReq(String httpMethod, String url) {
        HttpRequestBase req = null;

        try {
            if (httpMethod.equals(POST)) {
                req = new HttpPost(url);
            } else {
                req = new HttpGet(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return req;
    }

    /**
     * HTTP Get 请求
     * @param url 请求 URL
     * @param headers 请求头
     * @param connTimeOut 建立连接超时时间 单位毫秒；如果为 -1，则默认设置为 10 秒
     * @param sockTimeOut 读取响应信息超时时间 单位毫秒；如果为 -1，则默认设置为 10 秒
     * @param printReq 是否打印请求体
     * @return :null 表示发生错误
     *         :""   表示响应码不是 200
     */
    public static String httpGet(String url, Map<String, String> headers,
                                 int connTimeOut, int sockTimeOut, boolean printReq) {

        /*
        // URLEncoder 编码
        URI u = URI.create(url);
        String uriParam = null;
        try {
            // URL 编码
            uriParam = URLEncoder.encode(u.getQuery(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        String port = u.getPort() == -1 ? "" : String.format(":%s", u.getPort());
        url = String.format("%s://%s%s%s?%s", u.getScheme(), u.getHost(), port, u.getPath(), uriParam);
        */

        HttpRequestBase req = getReq(GET, url); // Get 请求对象
        if (req == null) { return null; }
        return sendReq(req, headers, connTimeOut, sockTimeOut, printReq);
    }

    /**
     * HTTP POST 请求 form 表单类型 application/x-www-form-urlencoded
     *               主要使用了 UrlEncodedFormEntity
     */
    public static String httpPostForm(String url, Map<String, String> headers, Map<String, String> data,
                                      int connTimeOut, int sockTimeOut, boolean printReq) {

        HttpPost req = (HttpPost)getReq(POST, url); // Post 请求对象
        if (req == null) { return null; }

        // 给 POST 对象设置 formEntity
        // NameValuePair 接口对应 input 表单中的 name value
        // <input id="xxx" type="text" name="userName">
        // <input id="yyy" type="text" name="passWord">
        List<NameValuePair> list = new ArrayList<>();

        data.forEach((k, v) -> {
            list.add(new BasicNameValuePair(k, v));
        });

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        req.setEntity(formEntity);

        return sendReq(req, headers, connTimeOut, sockTimeOut, printReq);
    }

    /**
     * HTTP POST 请求 json 类型 application/json
     *               主要使用了 StringEntity
     */
    public static String httpPostJson(String url, Map<String, String> headers, Map<String, Object> data,
                                      int connTimeOut, int sockTimeOut, boolean printReq) {

        HttpPost req = (HttpPost)getReq(POST, url); // Post 请求对象
        if (req == null) { return null; }

        // 给 POST 对象设置 jsonEntity
        JSONObject jsonObject = new JSONObject(data);

        StringEntity jsonEntity = new StringEntity(jsonObject.toString(), Consts.UTF_8);
        jsonEntity.setContentEncoding(Consts.UTF_8.name());
        jsonEntity.setContentType(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        req.setEntity(jsonEntity);
        // req.addHeader("Content-Type", "application/json; charset=utf-8");

        return sendReq(req, headers, connTimeOut, sockTimeOut, printReq);
    }

    /**
     * HTTP POST 请求 File Input File 类型 multipart/form-data
     *               主要使用了 MultipartEntityBuilder
     *               请求格式：
     *               -----------------------------22165374713946
     *               Content-Disposition: form-data; name="localUrl"
     *
     *               yoyoketang.png
     *               -----------------------------22165374713946
     *               Content-Disposition: form-data; name="imgFile"; filename="yoyoketang.png"
     *               Content-Type: image/png
     */
    public static String httpPostFile(String url, Map<String, String> headers, List<PostFileInner> postFileList,
                                      int connTimeOut, int sockTimeOut, boolean printReq) {

        HttpPost req = (HttpPost)getReq(POST, url); // Post 请求对象
        if (req == null) { return null; }

        // 给 POST 对象设置 fileEntity
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Consts.UTF_8);
        builder.setContentType(ContentType.create("multipart/form-data", Consts.UTF_8));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);  // 浏览器上传模式

        for (PostFileInner tmp : postFileList) {
            String name = tmp.getName();
            String type = tmp.getType();
            Object value = tmp.getValue();

            ContentBody b = null;
            if (type.equals("file")) {
                // type 值为 file
                b = new FileBody((File)value);
            } else {
                // type 值为 text
                b = new StringBody((String)value, ContentType.create("text/plain", Consts.UTF_8));
            }

            builder.addPart(name, b);
        }

        /*
        builder.addPart("fileName", new FileBody(new File("e:\\a.txt")))
            .addBinaryBody("fileName", new File("e:\\a.txt"))
            .addPart("userName", new StringBody("值值值", ContentType.create("text/plain", Consts.UTF_8)))
            .addTextBody("passWord", "xxx");
         */
        req.setEntity(builder.build());

       return sendReq(req, headers, connTimeOut, sockTimeOut, printReq);
    }

    /**
     * 发送 http 请求
     */
    private static String sendReq(HttpRequestBase req, Map<String, String> headers,
                                  int connTimeOut, int sockTimeOut, boolean printReq) {
        // 添加请求头
        if (headers != null) { headers.forEach(req::addHeader); }

        // 设置超时时间
        if (connTimeOut == -1) { connTimeOut = 10000; }
        if (sockTimeOut == -1) { sockTimeOut = 10000; }

        RequestConfig build = RequestConfig.custom()
                //.setProxy()   // 设置代理
                .setConnectTimeout(connTimeOut)
                .setSocketTimeout(sockTimeOut)
                .build();
        req.setConfig(build);

        // 打印请求体
        if (printReq) { printRequest(req); }

        CloseableHttpClient http = HttpClients.createDefault(); // 客户端对象
        CloseableHttpResponse res = null;   // HTTP 响应对象
        String resStr = null;

        try {
            res = http.execute(req);    // 发送请求 抛出 IOException 异常

            // 获取响应码
            if (res.getStatusLine().getStatusCode() == 200) {
                // res.getAllHeaders();                 // 获取响应头
                HttpEntity entity = res.getEntity();    // 获取响应内容 也可以作为请求参数 是一个接口 有很多实现

                // 将响应内容转成字符串       抛出 IOException 异常
                resStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                // byte[] bytes = EntityUtils.toByteArray(entity);  // 获取字节流

                EntityUtils.consume(entity);            // 关闭流

            } else {
                resStr = "";
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // 关闭连接
            if (http != null) {
                try {
                    http.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 关闭响应
            if (res != null) {
                try {
                    res.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resStr;
    }

    private static void testGet() {
        String url = "https://www.baidu.com/s?abc=123&fff=6*66（+中+x";
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        String s = httpGet(url, headers, -1, -1, true);
    }

    private static void testPostJson() {
        String url = "https://www.baidu.com/s?abc=123&fff=6*66（+中+x";
        Map<String, Object> data = new HashMap<>();
        data.put("abc", "123");
        String s = httpPostJson(url, null, data, -1, -1, true);
    }

    private static void testPostFile() {
        String url = "https://www.baidu.com/s?abc=123&fff=6*66（+中+x";

        List<PostFileInner> postFileList = new ArrayList<>();
        PostFileInner p1 = new PostFileInner("userName", "text", "张飞");
        PostFileInner p2 = new PostFileInner("passWord", "text", "xxxyyy");
        PostFileInner p3 = new PostFileInner("fileName", "file", new File("e:\\a.txt"));

        postFileList.add(p1);
        postFileList.add(p2);
        postFileList.add(p3);

        String s = httpPostFile(url, null, postFileList, -1, -1, true);
    }

    private static void testPostForm() {
        String url = "https://www.baidu.com/s?abc=123&fff=6*66（+中+x";
        Map<String, String> data = new HashMap<>();
        data.put("userName", "123");
        data.put("passWord", "123");
        String s = httpPostForm(url, null, data, -1, -1, true);
    }

    public static void main(String[] args) {
//        testGet();
//        testPostJson();
        testPostForm();
//        testPostFile();
    }
}
