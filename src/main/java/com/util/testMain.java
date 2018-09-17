package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.sun.tools.javah.resources.l10n;

import java.util.HashMap;
import java.util.Map;

public class testMain {
    String allowEX = "btc,bch,eth,etc,ltc,eos,xrp,omg,dash,zec,steem,ada,ctxc,act,btm," +
            "bts,ont,iost,ht,trx,dta,neo,qtum,ela,ven,theta,snt,zil,xem,nas,ruff,hsr,let," +
            "mds,storj,elf,itc,cvc,gnt,smt";

    public static void main(String[] args) {
//        String returnStr;
//        returnStr = getBinancePrice.getPriceUsdt("btc");
//        String ss="";
    	String ssString ="添加火币usdtbtc";
    	String relString = ssString.substring(ssString.indexOf("t")+1);
    	String deString = "";
//        jinseInfoScheduler jinseInfoScheduler = new jinseInfoScheduler();
//
//        jinseInfoScheduler.jinseSchedule();
//        getOkexPrice okex = new getOkexPrice();
//        String ss = okex.getPrice("okb");8
//        String sss="";
//        String remoURL = "http://47.52.137.185:8088/test/jisne/getJinseFastApi";
//        Map<String,String> paraMap = new HashMap<String, String>();
//        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
//        paraMap.put("limit","1");
//
//        try
//        {u
//            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
//            String dataJ = jsonObject.get("list").toString();
//            JSONObject dataObj =(JSONObject)JSONArray.parseArray(dataJ).get(0);
//            String dataFastList = dataObj.get("lives").toString();
//            JSONObject fastInfo = (JSONObject)JSONArray.parseArray(dataFastList).get(0);
//
//            String content = fastInfo.get("content").toString();
//            String link = fastInfo.get("link").toString();
//            String infoID = fastInfo.get("id").toString();
//            String importID = fastInfo.get("grade").toString();
//            String returnStr = importID;
//        }catch (Exception e)
//        {
//        }
    }

    private static String get2PointUtil(String str)
    {
        double d = Double.parseDouble(str);
        return String.format("%.2f",d);
    }

}
