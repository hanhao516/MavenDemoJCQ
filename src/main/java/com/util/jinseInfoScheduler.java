package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sobte.cqp.jcq.entity.CoolQ;

import java.util.HashMap;
import java.util.Map;

public class jinseInfoScheduler {
    public static long jinseInfoOldID = 0l;
    public static long jinseInfoNewID = 0l;
    public CoolQ jinseCQ;
    Thread thread;
    Boolean continuethreadBoolean = true;

    public void jinseSchedule()
    {
        final long timeInterval = 1000 * 60;
        Runnable runnable = new Runnable() {
            public void run() {
                while (continuethreadBoolean) {
                    // ------- code for task to run
                    String jsInfo = getJinseInfo();
                    if (jsInfo.length() > 1)
                    jinseCQ.sendGroupMsg(142562984, jsInfo);
                    if (jinseInfoOldID == 0)
                    {
                        jinseInfoOldID = jinseInfoNewID;
                    }else if((jinseInfoNewID - jinseInfoOldID) > 1)
                    {
                        for (int i = 0;i < (jinseInfoNewID - jinseInfoOldID -1);i++)
                        {
                            String jinseLoseInfo = getBaseJinseInfo(jinseInfoOldID+1+i);
                            if(jinseLoseInfo.length()>2)
                            {
                                jinseCQ.sendGroupMsg(142562984, jinseLoseInfo);
                            }
                        }
                    }
                    jinseInfoOldID = jinseInfoNewID;
                    // ------- ends herei
                    try {
                        Thread.sleep(timeInterval);
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
    
    public void closeJinseInfo() {
		continuethreadBoolean = !continuethreadBoolean;
	}

    public String getJinseInfo()
    {
        String remoURL = "http://47.52.137.185:8088/test/jisne/getJinseFastApi";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("limit","1");

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("list").toString();
            JSONObject dataObj =(JSONObject) JSONArray.parseArray(dataJ).get(0);
            String dataFastList = dataObj.get("lives").toString();
            JSONObject fastInfo = (JSONObject)JSONArray.parseArray(dataFastList).get(0);

            String content = fastInfo.get("content").toString();
            content = validateContent(content);
            String link = fastInfo.get("link").toString();
            String infoID = fastInfo.get("id").toString();
            jinseInfoNewID = Long.parseLong(infoID);
            if (jinseInfoNewID == jinseInfoOldID)
            {
                return "";
            }
            String importID = fastInfo.get("grade").toString();
            if(Integer.parseInt(importID) < 5)
            {
                return "";
            }
            String returnStr = "【重要指数" + importID + "星】\n"+ content + "\n" +link;
            return returnStr;
        }catch (Exception e)
        {
            return "";
        }
    }

    public String getBaseJinseInfo(Long infoIdl)
    {
        String remoURL = "http://47.52.137.185:8088/test/jisne/getJinseFastApi";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("limit","1");
        paraMap.put("id",(infoIdl+1)+"");
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            String dataJ = jsonObject.get("list").toString();
            JSONObject dataObj =(JSONObject) JSONArray.parseArray(dataJ).get(0);
            String dataFastList = dataObj.get("lives").toString();
            JSONObject fastInfo = (JSONObject)JSONArray.parseArray(dataFastList).get(0);

            String content = fastInfo.get("content").toString();
            content = validateContent(content);
            String link = fastInfo.get("link").toString();
            String infoID = fastInfo.get("id").toString();
            String importID = fastInfo.get("grade").toString();
            if(Integer.parseInt(importID) < 5)
            {
                return "";
            }

            String returnStr = "【重要程度" + importID + "星】\n"+ content + "\n" +link;
            return returnStr;
        }catch (Exception e)
        {
            return "";
        }
    }

    public String validateContent(String contentInfo)
    {
        if (contentInfo.contains("金色财经独家分析"))
        {
            contentInfo = contentInfo.replace("金色财经独家分析","分析");
        }
        return  contentInfo;
    }

}
