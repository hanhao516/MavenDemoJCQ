package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class getCexPrice {

    public static String getPriceUsdt(String excoin)
    {
        String remoURL = "http://47.52.137.185:8088/test/cex/cex";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("symbol",excoin+"_usdt");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("ticker").toString();
            JSONObject dataObj = JSONObject.parseObject(dataJ);
            String highM = dataObj.get("high").toString();
            String lowM = dataObj.get("low").toString();
            String closeM = dataObj.get("last").toString();
            String returnStr = excoin + "(CEX)现价: " + get2PointUtil(closeM) +"$\n"+
                    "24小时最高价: " + get2PointUtil(highM) + "$\n"+
                    "24小时最低价: " + get2PointUtil(lowM) + "$";
            return returnStr;

        }catch (Exception e)
        {
            return "";
        }
    }

    public static String getPriceETH(String excoin, String channl)
    {
        String remoURL = "http://47.52.137.185:8088/test/cex/cex";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("symbol",excoin+"_eth");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("ticker").toString();
            JSONObject dataObj =JSONObject.parseObject(dataJ);
            Double closeETH = Double.parseDouble(dataObj.get("last").toString());
            Double low =Double.parseDouble(dataObj.get("low").toString());
            Double high =Double.parseDouble(dataObj.get("high").toString());
            BigDecimal closeMETH = new BigDecimal(closeETH);
            BigDecimal lowETH = new BigDecimal(low);
            BigDecimal highETH = new BigDecimal(high);
//            return getHuobiPriceWithEth(excoin,ethdec);
            String returnStr = excoin + "(CEX)现价: " + closeMETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+"\n"+
                    "24小时最高价: " + highETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString()+channl + "\n"+
                    "24小时最低价: " + lowETH.setScale(10,BigDecimal.ROUND_HALF_UP).toPlainString() +channl+ "";
            return returnStr;

        }catch (Exception e)
        {
            return "error";
        }
    }

    private static String get2PointUtil(String str)
    {
        double d = Double.parseDouble(str);
        return String.format("%.5f",d);
    }
}
