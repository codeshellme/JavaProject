import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.stream.Collectors;


public class NetAddrTest {
    public static List<String> getMacList() throws Exception {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        List<String> macList = new ArrayList<>();

        while (en.hasMoreElements()) {
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();

            for (InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if (network == null) {
                    continue;
                }

                byte[] mac = network.getHardwareAddress();
                if (mac == null) {
                    continue;
                }

                sb.delete(0, sb.length());
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }

                // 除去 00-00-00-00-00-00-00-E0
                if (sb.toString().startsWith("00-00-00-00-00-00-00-E0")) {
                    continue;
                }

                macList.add(sb.toString());
            }
        }

        if (macList.size() <= 1) {
            return macList;
        } else {
            // 去重
            // 同一个网卡的ipv4, ipv6得到的 mac都是一样的，肯定有重复
            List<String> unique = macList.stream().distinct().collect(Collectors.toList());
            return unique;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("进行 multi net address 测试===》");

        List<String> macs = getMacList();
        System.out.println("本机的mac网卡的地址有："+macs);
    }
}
