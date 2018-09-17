package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public  class getHuobiPrice {

    public static String jinseId = "0";

    public static String getPrice(String excoin)
    {
        String remoURL = "http://47.52.137.185:8088/test/api/hello";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("changKey",excoin+"usdt");
        paraMap.put("timeKey","1day");
        paraMap.put("countKey","1");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("data").toString();
            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
            String highM = dataObj.get("high").toString();
            String lowM = dataObj.get("low").toString();
            String closeM = dataObj.get("close").toString();
            String returnStr = excoin + "(Huobi)现价: " + get2PointUtil(closeM) +"$\n"+
                               "24小时最高价: " + get2PointUtil(highM) + "$\n"+
                               "24小时最低价: " + get2PointUtil(lowM) + "$";
            return returnStr;

        }catch (Exception e)
        {
            return "";
        }
    }

    public static String getHadaxPrice4ETH(String excoin,String channl)
    {
        String remoURL = "http://47.52.137.185:8088/test/api/hadax";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("changKey",excoin+channl);
        paraMap.put("timeKey","1day");
        paraMap.put("countKey","1");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("data").toString();
            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
            Double closeETH = Double.parseDouble(dataObj.get("close").toString());
            Double low =Double.parseDouble(dataObj.get("low").toString());
            Double high =Double.parseDouble(dataObj.get("high").toString());
            BigDecimal closeMETH = new BigDecimal(closeETH);
            BigDecimal lowETH = new BigDecimal(low);
            BigDecimal highETH = new BigDecimal(high);
//            return getHuobiPriceWithEth(excoin,ethdec);
            String returnStr = excoin + "(Huobi)现价: " + closeMETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+"\n"+
                    "24小时最高价: " + highETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString()+channl + "\n"+
                    "24小时最低价: " + lowETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+ "";
            return returnStr;

        }catch (Exception e)
        {
            return "error";
        }
    }

    public static String getPrice4ETH(String excoin,String channl)
    {
        String remoURL = "http://47.52.137.185:8088/test/api/hello";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("changKey",excoin+channl);
        paraMap.put("timeKey","1day");
        paraMap.put("countKey","1");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("data").toString();
            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
            Double closeETH = Double.parseDouble(dataObj.get("close").toString());
            Double low =Double.parseDouble(dataObj.get("low").toString());
            Double high =Double.parseDouble(dataObj.get("high").toString());
            BigDecimal closeMETH = new BigDecimal(closeETH);
            BigDecimal lowETH = new BigDecimal(low);
            BigDecimal highETH = new BigDecimal(high);
//            return getHuobiPriceWithEth(excoin,ethdec);
            String returnStr = excoin + "(Huobi)现价: " + closeMETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+"\n"+
                    "24小时最高价: " + highETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString()+channl + "\n"+
                    "24小时最低价: " + lowETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+ "";
            return returnStr;

        }catch (Exception e)
        {
            return "error";
        }
    }

//    public static String getHuobiPriceWithEth(String coinstr,BigDecimal ethdec)
//    {
//        String remoURL = "http://47.52.137.185:8088/test/api/hello";
//        Map<String,String> paraMap = new HashMap<String, String>();
//        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
//        paraMap.put("changKey",coinstr+"eth");
//        paraMap.put("timeKey","1day");
//        paraMap.put("countKey","1");
//
//        try
//        {
//            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
//            String dataJ = jsonObject.get("data").toString();
//            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
//            Double closeM =Double.parseDouble(dataObj.get("close").toString());
//            Double low =Double.parseDouble(dataObj.get("low").toString());
//            Double high =Double.parseDouble(dataObj.get("high").toString());
//            BigDecimal closeMdec = new BigDecimal(closeM);
//            BigDecimal lowMdec = new BigDecimal(low);
//            BigDecimal highMdec = new BigDecimal(high);
//            String returnStr = coinstr + "现价: " + get2PointUtil(closeMdec.multiply(ethdec).toString()) +"$\n"+
//                    "24小时最高价: " + get2PointUtil(highMdec.multiply(ethdec).toString()) + "$\n"+
//                    "24小时最低价: " + get2PointUtil(lowMdec.multiply(ethdec).toString()) + "$";
//
//            return returnStr;
//        }catch (Exception e)
//        {
//            return "error";
//        }
//    }


    private static String get2PointUtil(String str)
    {
        double d = Double.parseDouble(str);
        return String.format("%.4f",d);
    }

//    public static String getJinseFastInfo()
//    {
//        String remoURL = "http://47.52.137.185:8088/test/jisne/getJinseFastApi";
//        Map<String,String> paraMap = new HashMap<String, String>();
//        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
//        paraMap.put("limit","1");
//
//        try
//        {
//            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
//            String dataJ = jsonObject.get("data").toString();
//            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
//            String highM = dataObj.get("high").toString();
//            String lowM = dataObj.get("low").toString();
//            String closeM = dataObj.get("close").toString();
//            String returnStr = excoin + "现价: " + get2PointUtil(closeM) +"$\n"+
//                    "24小时最高价: " + get2PointUtil(highM) + "$\n"+
//                    "24小时最低价: " + get2PointUtil(lowM) + "$";
//            return returnStr;
//
//        }catch (Exception e)
//        {
//            return "";
//        }
//    }

}
