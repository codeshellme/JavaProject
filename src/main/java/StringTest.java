import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringTest {

    public static boolean isNull(Object... objs) {
        for (Object o: objs) {
            if (o == null) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//
//        s = s.replaceAll("\\\\", "\\\\\\\\");
//        System.out.println(s);
//
//        System.out.println(params.toString());

//        if (sex.equals("男")) {
////            sex = "M";
////        } else {
////            sex = "W";
////        }

//        sex = "M" if sex.equals("男") else "W";
//        sex = sex.equals("男") ? "M" : "W";
//
//        System.out.println(sex);

//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("abc", null);
//        String s = params.get("abc").toString();
//        System.out.println(s);

//        Random r = new Random();
//        System.out.println(r.nextInt(10000));

//        int max=9999, min=1000;
////        int ran2 = (int) (Math.random()*(max-min)+min);
//        System.out.println((int)(Math.random()*(max-min)+min));

//        String s = "20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||,0|||^";
//        s = s.split("\\^")[2].split("\\|")[0];
//        System.out.println(s);

//        String s = "26024|0|邹克志|19531021|01|342123195310218770|1|01|20201107110305|20210414100937|12|骨一科|542|于淼|26024||0|0|0|0|0|0|0|0|0||0|0|0|0&T00.901|胸腰部、右腕软组织挫伤|1&||&1852582|1|3|0|202104120006|20210412090912|7763|Z-A09BA-F0284|阿胶|阿胶||0.8500|6.00||||0||||1||542|于淼|||";
//        s = s.split("\\|")[41];
//        System.out.println(s);

//        String s = "abc<>kkk";
//        s = s.replaceAll("<", "%3C");
//        System.out.println(s);
//
//        Map<String, String> m = new HashMap<String, String>(){{
//            put("name", "test");
//            put("age", "20");
//        }};
//
//        String x = m.toString();
//        System.out.println(x);
//        System.out.println(m.get("name"));
//        System.out.println(m.get("name"));

//        String s = "腰痛待查,zzz,null,null,null,null,null,null,null,瘤";
//        s = deleteFromString(s, "null");
//        System.out.println(s);

        // 合格数据   20210412102339-00200002-1265^1^0^0|||^
        // 不合格数据 20210414092800-00200002-1689^1^1|1000000045116588&&03&&费用构成主要为药品费用（排除肿瘤化疗）占比过高，实际占比：100.0%大于阈值设置：80.0%.&&202104120006^0|||^
//        String s = "20210412102339-00200002-1265^1^0^0|||^";
//        s = "20210414092800-00200002-1689^1^1|1000000045116588&&03&&费用构成主要为药品费用（排除肿瘤化疗）占比过高，实际占比：100.0%大于阈值设置：80.0%.&&202104120006^0|||^";
//
//        s = "26221|0|张俊英|19610318|01|342123196103181041|2|01|20201110193251|20210419093552|12|骨一科|542|于淼|26221||0|0|0|0|0|0|0|0|0||0|0|0|1&T00.901|全身多处软组织挫伤|1&||&1852553|1|3|0|202104070020|20210407103152|7763|Z-A09BA-F0284|阿胶|阿胶||0.8500|5.00||||0||||1||542|于淼|||";
////        s = s.split("\\|")[41];
//        System.out.println(s.split("\\|")[33].substring(1));

//        String retInfo = "20210422113903-00222007-4055^1^2|处方数量输入格式错误。^0|||^";
//
//        String s = "parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\n" +
//                "\" paraValue=\"20210422165147-00222007-9927^1^1|1000000045158923&&01&&太和药品中药\n" +
//                "饮片限复方，具体明细如下：\n" +
//                "1.药品【T001300477，昆布】不能单独使用。\n" +
//                "&&202104220209^0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></paramete\n" +
//                "rs><dataStores></dataStores></body></reponseEnvelope>parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\n" +
//                "\" paraValue=\"20210422165147-00222007-9927^1^1|1000000045158923&&01&&太和药品中药\n" +
//                "饮片限复方，具体明细如下：\n" +
//                "1.药品【T001300477，昆布】不能单独使用。\n" +
//                "&&202104220209^0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></paramete\n" +
//                "rs><dataStores></dataStores></body></reponseEnvelope>";
//
//        s = s.replaceAll("\n", "").replaceAll("\r\n", "");
//
//        // 获取返回信息
//        String pattern = "outputData\" paraValue=\"(.*?)\"";
//        retInfo = RegexTest.regexSearch(s, pattern);
//        if (retInfo == null) {	// 没有得到有用信息
//            System.out.println("没有得到有用信息");
//            System.exit(0);
//        }
//
//        String flag = retInfo.split("\\^")[2];
//        if (flag.startsWith("0")) {
//            // 合格 不用处理
//            System.out.println("合格 不用处理");
//            System.exit(0);
//        }
//
//        if (!flag.startsWith("1")) {
//            System.out.println("门诊，医保监控接口返回不合格信息：" + retInfo);
//            System.exit(0);
//        }
//
//        // 药品不合格，获取错误信息
//        String errInfo = flag.split("&&")[2];
//        System.out.println("errInfo:" + errInfo);

//        String s = "1995-11-12 14:00:00.0";
//        int l = s.split("\\|").length;
//        System.out.println(l);
//        System.out.println(s.split("\\|")[0]);
        String regist_id = "166961";
        String sql = "select id, billcode, regist_id, recivefee, realfee, retfee, insurefee, insuraccountfee, bas_paytype_id, cheque, settledep_id, settledby, settledate, hisOrderNo, payfee from clinic_account where regist_id = %s;\n" +
                "select id, account_id, regist_id, isregist, sickname, rcpdep_id, rcpdoctor_id, exedep_id, fee, discnt, realfee, bas_patienttype_id, bas_patienttype1_id, insurcode, healthcard, invoice, billcode, depart_id, chargedate, chargeby, charged, insurstat, insurefee, insurotherfee, Insurbillcode, insuraccountfee, clinic_invoice_id, clinic_tab_id, payfee, hisorderno, bas_paytype_id, netpay_store_id, netpayret,invoice_billcode,real_invoice from clinic_invoice where regist_id = %s;\n" +
                "select id, clinic_invoice_id, payfee, bas_paysumby_id, bas_paytype_id, cheque, isfirst from clinic_invoicedet where clinic_invoice_id in (select id from clinic_invoice where regist_id = %s);\n" +
                "select id, netcode, sync, regist_id, clinic_invoice_id, clinicInvoice, billcode, rcptype, clinic_rcp_id, executed, dispensing, drugcalled, depart_id, doctor_id, rcpdate, ischarged, chargedate, chargeby, recipelfee, realfee, unlocked, retappstat from clinic_cost where regist_id = %s;\n" +
                "select id, netcode, sync, clinic_Invoice_id, bas_patienttype_id, clinic_cost_id, regist_id, standcode, item_id, drug_factyitem_id, itemfrom, rcptype, clinic_rcpdetail_id, depart_id, doctor_id, rcpdate, exedep_id, exedoctor_id, executed, packsole, drug_packsole_id, name, spec, unit, num, prc, fee, discnt, realfee, itemtype_id, itemtype1_id, groupid, groupnum, charged, chargedate, chargeby, unlocked, retappstat, clinic_costdet_id, settled, member_rechargedet_id, drugprc, insursync, insurefee, selffee, insurclass, dosageform_id, createdate, insurno, clinic_rcp_id from clinic_costdet where regist_id = %s;\n" +
                "select id, emr_record_id, emr_tempoutline_id, keyname, keyval, region, length, htmlvalue from emr_outlinedata where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "select id, ihsp_id, clinic_record_id, registkind, emr_temptype_id, emr_temp_id, depart_id, doc_id, name, recodeurl, ismust, createdt, iscomplete, completedate, startpoint, endpoint, recorddate, emr_neonate_id, attached, isprinted, isneonage, clipth_exec_id, imageurl, oper_record_id, blood_app_id, isfirstprint, printer_id, save_count, ordersn, isopened, isabnormal, abnormalmsg, attribute, isreason_type, reject_reason, infect_chkflag, infect_mgbl, infectcrb_mgbl from emr_record where ihsp_id = %s and registkind='CLIN';\n" +
                "select id, emr_record_id, emr_element_id, keyname, keyvalue, docvalue, region, position, length, attached, timestampkeyname, timestamp from emr_data where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "select id, ihsp_id, ihspcode, sickname, sicksex, depart_id, doctor_id, temp_keyname, tempname, iswrite, writetime, emr_record_id, qclen, status, lockedtime, unlocktime from emr_lockrecord where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "select id, emr_record_id, doc_id, doc_name, operate, createdt, soucont, descont, dataregion, depart_id from emr_revision where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "select id, emr_record_id, position, doc_id, printtime from emr_conprint where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "select id, doctor_id, depart_id, log_time, log_kind, emr_record_id, isarchive from emr_access_log where emr_record_id in (select id from emr_record where ihsp_id = %s and registkind='CLIN');\n" +
                "SELECT o.id, o.iokind, o.billcode, o.actdept_id, o.objdept_id, o.admindept_id,o.provider_id, o.opstat, o.depstock, o.appprint, o.amount, o.chkoper,o.chkdate, o.iostocker_id, o.procurer_id, o.shipper, o.invoice,o.invoicedate, o.payer, o.paymark, o.paycheque, o.drug_app_id, o.auditor,o.auditdate, o.auditemark, o.reason, o.drug_reason_id, o.createdby,o.createdate, o.dispense_id, o.dispensetime, o.ihsp_dispdrug_record_id,o.dispensing, o.offline_status, o.dispense FROM clinic_rcp LEFT JOIN clinic_rcpdetail ON clinic_rcpdetail.clinic_rcp_id = clinic_rcp.id LEFT JOIN clinic_costdet ON clinic_costdet.clinic_rcpdetail_id = clinic_rcpdetail.id LEFT JOIN drug_iodetail ON drug_iodetail.costdet_id = clinic_costdet.id LEFT JOIN drug_io o ON o.id = drug_iodetail.drugio_id WHERE clinic_rcp.regist_id = %s and o.id is not null GROUP BY o.id;\n" +
                "SELECT o.id, o.drugio_id, o.opstat, o.depstock, o.item_id, o.objdept_id, o.iokind,o.costdet_id, o.drug_stockdet_id, o.name, o.spec, o.pkgunit, o.pkgprc, o.pkgrealprc,o.pkgqty, o.pkgcount, o.unit, o.qty, o.qty_bak, o.baspkgprc, o.realprc, o.packsole,o.drug_packsole_id, o.packsoleprc, o.packsoleunit, o.packsoleqty, o.batch, o.enddate,o.entrydate, o.statenum, o.drug_provider_id, o.drug_factory_id, o.drug_factyitem_id,o.addrate, o.drug_origin_id, o.pincode, o.iotype, o.code, o.memo FROM clinic_rcp LEFT JOIN clinic_rcpdetail ON clinic_rcpdetail.clinic_rcp_id = clinic_rcp.id LEFT JOIN clinic_costdet ON clinic_costdet.clinic_rcpdetail_id = clinic_rcpdetail.id LEFT JOIN drug_iodetail o ON o.costdet_id = clinic_costdet.id WHERE clinic_rcp.regist_id = %s and o.id is not null GROUP BY o.id;\n" +
                "select id, regist_id, registkind, billcode, recorddate, infect_diagn_id, depart_id, infect_fallillrelation_id, appdep_id, appdoc_id, appDate, chker_id, chkdate, infectkind, chkstat, warnstat, oper_record_id, oper_incision_id, oper_asa_id, isurgent, isendoscope, oper_hocusType_id, opdate, implant, perioperiod, inquirer_id, warninfo, warnkind from infect_record where regist_id = %s and registkind='CLIN';\n" +
                "select id, infect_record_id, keyname, keyvalue, valuetype from infect_disease where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN');\n" +
                "select id, infect_record_id, infect_diagn_id, recorddate from infect_opredisp where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN');\n" +
                "select id, infect_record_id, chk_sampletype_id, chk_app_id, appdate from infect_recsampletype where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN');\n" +
                "select id, infect_recsampletype_id, diagngerms_id, infect_germs_id, name, enname, resStr from infect_appgerms where infect_recsampletype_id in (select id from infect_recsampletype where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN'));\n" +
                "select id, appgerms_id, infect_drug_id, group_id, name, enname, testStr, allergy from infect_appallergy where appgerms_id in (select id from infect_appgerms where infect_recsampletype_id in (select id from infect_recsampletype where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN')));\n" +
                "select id, infect_record_id, infect_predisp_id from infect_recpredisp where infect_record_id in (select id from infect_record where regist_id = %s and registkind='CLIN');\n" +
                "select id, blood_app_id, regist_id, registkind, sickname, ihspcode, usedate, weight, needstat_id, blooditem_id, number, uom, approve, approvedate, purpose, history, pregnant, childbirth, sickaddr, bloodtype, rh, pt, tt, aptt, fib, hgb, hct, plt, alt, hbsag, anti_hcv, anti_hiv, syphilis, mark1, mark2, samper_id, sampdate, sendsign_id, senddate, recvsign_id, recvdate, writeby, writedate, aim, bloodcount from blood_appinfo where regist_id = %s and  registkind='CLIN';\n" +
                "select id, billcode, depart_id, doctor_id, doctgrp_id, appdate, matchedate, appstat, appprint, barcode, gather, amount, stock_id, chkoper_id, chkdate, createdby, createdate, retstat, retinfo, issended, provider_no, appissended, ta_id from blood_app where id in (select blood_app_id from blood_appinfo where regist_id = %s and  registkind='CLIN');\n" +
                "select id, blood_app_id, bloodcode, blood_stockdet_id, testtype_id, chkbloodtype, chkrh, mainres, secondres, chkres, chkIrregular, chkoth, matcher_id, chkmatcher_id, matchedate, issended, bi_id from blood_matche where blood_app_id in (SELECT id from blood_app where id in (SELECT blood_app_id from  blood_appinfo where regist_id = %s and registkind='CLIN'));\n" +
                "select id, blood_app_id, begintime, beginfreq, normalfreq, endtime, operator_id, regist_id, registkind, number, uom, isreaction, reactionkindids, reactionothmsg, transfsn, opcontstat, opsoberstat, normalstat, rescuestat, temperature, druginfo, lots, timelenth, chker_id, chkdate, chktime, dealwith, opstat, submittime, submitby from blood_transfusion where blood_app_id in (SELECT id from blood_app where id in (SELECT blood_app_id from  blood_appinfo where regist_id = %s and registkind='CLIN'));\n" +
                "select id, blood_transchkdet_id, blood_transmonitorseq_id, temperature, pulse, breathe, highpress, lowpress, adverse from blood_transmonitor where blood_transchkdet_id in (select id from blood_transchkdet where blood_transfusion_id in (select id from blood_transfusion where blood_app_id in (SELECT id from blood_app where id in (SELECT blood_app_id from  blood_appinfo where regist_id = %s and registkind='CLIN'))));\n" +
                "select id, blood_ioitem_id, bloodcode, blood_transfusion_id, beforopter_id, beforchker_id, operator_id, opchker_id, begintime, endtime from blood_transchkdet where blood_transfusion_id in (select id from blood_transfusion where blood_app_id in (SELECT id from blood_app where id in (SELECT blood_app_id from  blood_appinfo where regist_id = %s and registkind='CLIN')));\n" +
                "select id, blood_transfusion_id, blood_reactionkind_id from blood_reactioninfo where blood_transfusion_id in (select id from blood_transfusion where blood_app_id in (SELECT id from blood_app where id in (SELECT blood_app_id from  blood_appinfo where regist_id = %s and registkind='CLIN')));";
        String sql0 = String.format(sql,
                regist_id, regist_id, regist_id, regist_id, regist_id, regist_id, regist_id,
                regist_id, regist_id, regist_id, regist_id, regist_id, regist_id, regist_id,
                regist_id, regist_id, regist_id, regist_id, regist_id, regist_id, regist_id,
                regist_id, regist_id, regist_id, regist_id, regist_id, regist_id, regist_id
        );

        System.out.println(sql0);

        String s = "100zz";
    }

    private static String deleteFromString(String srcStr, String del) {
        StringBuilder ret = new StringBuilder();
        String[] split = srcStr.split(",");

        for (String value : split) {
            if (value.equals(del)) {
                continue;
            }

            ret.append(",").append(value);
        }

        return ret.substring(1);
    }

    public static String getVal(Map<String, Object> m, String key) {
        if (m.get(key) == null) {
            return "";
        } else {
            return m.get(key).toString();
        }
    }
}