package com.example;

import com.sobte.cqp.jcq.entity.Anonymous;
import com.sobte.cqp.jcq.entity.GroupFile;
import com.sobte.cqp.jcq.entity.ICQVer;
import com.sobte.cqp.jcq.entity.IMsg;
import com.sobte.cqp.jcq.entity.IRequest;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import com.util.*;

/**
 * 本文件是JCQ插件的主类<br>
 * <br>
 * <p>
 * 注意修改json中的class来加载主类，如不设置则利用appid加载，最后一个单词自动大写查找<br>
 * 例：appid(com.example.demo) 则加载类 com.example.Demo<br>
 * 文档地址： https://gitee.com/Sobte/JCQ-CoolQ <br>
 * 帖子：https://cqp.cc/t/37318 <br>
 * 辅助开发变量: {@link JcqAppAbstract#CQ CQ}({@link com.sobte.cqp.jcq.entity.CoolQ 酷Q核心操作类}),
 * {@link JcqAppAbstract#CC CC}({@link com.sobte.cqp.jcq.entity.CQCode 酷Q码操作类}),
 * 具体功能可以查看文档
 */
public class Demo extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    public static  int jinseInit = 1;
    jinseInfoScheduler jinseInfoScheduler;
    String allowusdt = "BTC,BCH,ETH,ETC,LTC,EOS,XRP,OMG,DASH,ZEC,STEEM,ADA,CTXC,ACT,BTM," +
            "BTS,ONT,IOST,HT,TRX,DTA,NEO,QTUM,ELA,VEN,THETA,SNT,ZIL.XEM,NAS,RUFF,HC,LET," +
            "MDS,STORJ,ELF,ITC,CVC,GNT,SMT";
    String alloweth = "WAN,BFT,ZRX,AST,KNC,ONT,BTM,IOST,ELA,TRX,ABT,NAS,OCN,WICC,ZIL,CTXC,ZLA," +
            "WPR,DTA,MTX,THETA,SRN,VEN,BTS,EDU,WAX,HC,ICX,MTN,ACT,BLZ,QASH,RUFF,CMT,ELF,MEET,SOC," +
            "QTUM,ITC,SWFTC,YEE,LSK,LUN,LET,GNX,CHAT,EKO,TOPC,DGD,STK,MDS,DBC,SNC,PAY,QUN,AIDOC,TNB," +
            "APPC,RDN,UTK,POWR,BAT,PROPY,MANA,REQ,CVC,QSP,EVX,DAT,MCO,GNT,GAS,OST,LINK,RCN,TNT,ENG,SALT,ADX";
    String allowbtc="BCH,ETH,LTC,ETC,EOS,OMG,XRP,DASH,ZEC,IOTA,ADA,STEEM,WAN,BFT,BTM,ONT,IOST,HT,TRX," +
            "ELA,WICC,OCN,ZLA,ABT,MTX,NAS,VEN,DTA,NEO,WAX,BTS,ZIL,THETA,CTXC,SRN,XEM,EDU,ICX,DGD,CHAT" +
            ",WPR,LUN,SWFTC,SNT,MEET,YEE,ELF,LET,QTUM,LSK,ITC,SOC,QASH,MDS,EKO,TOPC,MTN,ACT,HC,STK,STORJ," +
            "GNX,DBC,SNC,CMT,TNB,RUFF,QUN,ZRX,KNC,BLZ,PROPY,RPX,APPC,AIDOC,POWR,CVC,PAY,QSP,DAT,RDN," +
            "MCO,RCN,MANA,UTK,TNT,GAS,BAT,OST,LINK,GNT,MTL,EVX,REQ,ADX,AST,ENG,SALT,BIFI,BCX,BCD,SBTC," +
            "BTG";
    String okexusdt = "OKB,1ST,AAC,ABT,ACE,ACT,AIDOC,AMM,ARK,AST,AUTO,AVT,BCD,BKX,BNT,BTG,BTM,CAN,CBT" +
            ",CHAT,CIC,CMT,CTXC,CVC,DADI,DASH,DENT,DGB,DGD,DNA,DNT,DPY,EDO,ELF,ENG,ENJ,EOS,EVX,FUN,GAS" +
            ",GNT,GNX,GTC,HOT,HC,ICN,ICX,INS,INSUR,INT,IOST,IOTA,IPC,ITC,KCASH,KNC,LIGHT,LINK,LRC," +
            "MANA,MCO,MDA,MDT,MITH,MKR,MOF,MTH,MTL,NANO,NAS,NEO,NULS,OAX,OF,OMG,ONT,OST,PAY,POE,PPT," +
            "PRA,PST,QTUM,QUN,R,RCN,RDN,REN,RFR,RNT,SALT,SAN,SNC,SPF,SSC,STORJ,SUB,SWFTC,THETA,TIO," +
            "TNB,TOPC,TRA,TRIO,TRUE,TRX,UCT,UGC,VEE,VIB,WTC,XEM,XLM,XMR,XRP,XUC,YEE,YOYO,ZEC,ZEN,ZIP,ZRX";
    String hadaxeth="GET,MAN,HOT,GTC,PORTAL,DATX,18T,BUT,LXT,CDC,UUU,AAC,CNN,UIP,UC,GSC,IIC,MEX,EGCC,SHE";
    String zbqc = "ZB,BTC,BCC,LTC,ETH,ETC,BTS,EOS,QTUM,HC,XRP,DASH,USDT,UBTC,BCD,SBTC,INK,TV,BCX,BTH,LBTC" +
            ",CHAT,HLC,BCW,BTP,BITCNY,TOPC,ENT,BAT,1ST,SAFE,QUN,BTN,TRUE,CDC,DDM,HOTC,XUC,EPC,BDS,GRAM,DOGE" +
            ",NEO,OMG,HPY,BTM,SNT,AE,ICX,ZRX,EDO,FUN,MANA,RCN,XLM,MCO,MITH,KNC,GNT,MTL,SUB,XEM,EOSDAC,KAN," +
            "ADA";
    String cexusdt="SMT,THETA,DTA,MDS,CTXC,ACT,DASH,ZEC,ITC,SNT,IOST,WICC,TRX,ZIL,GNT,RUFF,STORJ,CVC,OMG,HOLO," +
            "LRN,VNS,SNET,DDD,AE,BTM,BCD,LRC,TSL,CEX,SHOW,LOOM,INF,CDT,AMB,STX,XUC,LEND";
    String cexeth="DAT,YEE,VEN,SMT,QUN,DTA,MDS,CTXC,KNC,AST,ZRX,RCN,BTM,ACT,REQ,RDN,PROPY,TNT,MANA,ITC,PAY,TNB," +
            "BLZ,EDU,ENG,TRX,MTN,MTX,WPR,NAS,STK,SNC,LUN,OCN,ZLA,SRN,ZIL,UTK,ADX,EVX,LINK,EKO,MEET,GNT,RUFF,SOC,SWFIC," +
            "APPC,AIDOC,OST,TOPC,POWR,CVC,ABT,GNX,DGD,QASH,MCO,CMT,BAT,QSP,SALT,ICX,ETP,DATA,YYW,BNT,PPT,EDO," +
            "MOD,MDA,RLC,BQX,ICN,GVT,ARN,BRD,WINGS,WABI,QION,DLT,INS,SNM,POA,SNGLS,BCPT,MTH,FUEL,VIBE,DNT,SUB," +
            "VIB,ENJ,CHAT,CND,FUN,GTO,POE,STORM,NCASH,AMB,CDT,MKR";
    String bnbusdt = "BNB,ICX,TUSD";
    /**
     * 打包后将不会调用 请不要在此事件中写其他代码
     *
     * @return 返回应用的ApiVer、Appid
     */
    public String appInfo() {
        // 应用AppID,规则见 http://d.cqp.me/Pro/开发/基础信息#appid
        String AppID = "com.example.demo";// 记住编译后的文件和json也要使用appid做文件名
        /**
         * 本函数【禁止】处理其他任何代码，以免发生异常情况。
         * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。
         */
        return CQAPIVER + "," + AppID;
    }

    /**
     * 酷Q启动 (Type=1001)<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 请在这里执行插件初始化代码。<br>
     * 请务必尽快返回本子程序，否则会卡住其他插件以及主程序的加载。
     *
     * @return 请固定返回0
     */
    public int startup() {
        // 获取应用数据目录(无需储存数据时，请将此行注释)
        String appDirectory = CQ.getAppDirectory();
        // 返回如：D:\CoolQ\app\com.sobte.cqp.jcq\app\com.example.demo\
        // 应用的所有数据、配置【必须】存放于此目录，避免给用户带来困扰。
        return 0;
    }

    /**
     * 酷Q退出 (Type=1002)<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 无论本应用是否被启用，本函数都会在酷Q退出前执行一次，请在这里执行插件关闭代码。
     *
     * @return 请固定返回0，返回后酷Q将很快关闭，请不要再通过线程等方式执行其他代码。
     */
    public int exit() {
        return 0;
    }

    /**
     * 应用已被启用 (Type=1003)<br>
     * 当应用被启用后，将收到此事件。<br>
     * 如果酷Q载入时应用已被启用，则在 {@link #startup startup}(Type=1001,酷Q启动) 被调用后，本函数也将被调用一次。<br>
     * 如非必要，不建议在这里加载窗口。
     *
     * @return 请固定返回0。
     */
    public int enable() {
        enable = true;
        return 0;
    }

    /**
     * 应用将被停用 (Type=1004)<br>
     * 当应用被停用前，将收到此事件。<br>
     * 如果酷Q载入时应用已被停用，则本函数【不会】被调用。<br>
     * 无论本应用是否被启用，酷Q关闭前本函数都【不会】被调用。
     *
     * @return 请固定返回0。
     */
    public int disable() {
        enable = false;
        return 0;
    }

    /**
     * 私聊消息 (Type=21)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType 子类型，11/来自好友 1/来自在线状态 2/来自群 3/来自讨论组
     * @param msgId   消息ID
     * @param fromQQ  来源QQ
     * @param msg     消息内容
     * @param font    字体
     * @return 返回值*不能*直接返回文本 如果要回复消息，请调用api发送<br>
     * 这里 返回  {@link IMsg#MSG_INTERCEPT MSG_INTERCEPT} - 截断本条消息，不再继续处理<br>
     * 注意：应用优先级设置为"最高"(10000)时，不得使用本返回值<br>
     * 如果不回复消息，交由之后的应用/过滤器处理，这里 返回  {@link IMsg#MSG_IGNORE MSG_IGNORE} - 忽略本条消息
     */
    public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
        // 这里处理消息
//        CQ.sendPrivateMsg(fromQQ, CC.at(fromQQ) + "你发送了这样的消息：" + msg + "\n来自Java插件");
        CQ.sendPrivateMsg(fromQQ, CC.at(fromQQ) + "我只是个机器人，有事请联系QQ535528121");
        return MSG_IGNORE;
    }

    /**
     * 群消息 (Type=2)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType       子类型，目前固定为1
     * @param msgId         消息ID
     * @param fromGroup     来源群号
     * @param fromQQ        来源QQ号
     * @param fromAnonymous 来源匿名者
     * @param msg           消息内容
     * @param font          字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg,
                        int font) {
        // 如果消息来自匿名者
        // 解析CQ码案例 如：[CQ:at,qq=100000]
        // CC.analysis();// 此方法将CQ码解析为可直接读取的对象
        // 此方法为简便方法，获取第一个CQ:At里的QQ号，错误时为：-1000
        // 这里处理消息

        if (fromGroup == 591162730)
        {
        	
//            if (jinseInit == 1)
//            {
//                jinseInfoScheduler jinseInfoScheduler = new jinseInfoScheduler();
//                jinseInfoScheduler.jinseCQ = CQ;
//                jinseInfoScheduler.jinseSchedule();
//                jinseInit = 2;
//            }

            long qqId = CC.getAt(msg);
            
            String returnStr = "";
            if (allowusdt.contains(msg.toUpperCase()))
            {
                returnStr = getHuobiPrice.getPrice(msg.toLowerCase());
            }else if (alloweth.contains(msg.toUpperCase()))
            {
                returnStr = getHuobiPrice.getPrice4ETH(msg.toLowerCase(),"eth");
            }else if (allowbtc.contains(msg.toUpperCase()))
            {
                returnStr = getHuobiPrice.getPrice4ETH(msg.toLowerCase(),"btc");
            }
            else if(okexusdt.contains(msg.toUpperCase()))
            {
                returnStr = getOkexPrice.getPrice(msg.toLowerCase());
            }else if(hadaxeth.contains(msg.toUpperCase()))
            {
                returnStr = getHuobiPrice.getHadaxPrice4ETH(msg.toLowerCase(),"eth");
            }else if(zbqc.contains(msg.toUpperCase()))
            {
                returnStr = getZBPrice.getzbprice(msg.toLowerCase(), "qc");
            }
            else if(cexusdt.contains(msg.toUpperCase()))
            {
                returnStr = getCexPrice.getPriceUsdt(msg.toLowerCase());
            }else if(cexeth.contains(msg.toUpperCase()))
            {
                returnStr = getCexPrice.getPriceETH(msg.toLowerCase(),"eth");
            }else if(bnbusdt.contains(msg.toUpperCase()))
            {
            	returnStr = getBinancePrice.getPriceUsdt(msg);
            }
            
            if (fromQQ == 535528121) {
				if (msg.equals("开启金色推送")) {
					if(jinseInfoScheduler == null)
					{
						jinseInfoScheduler = new jinseInfoScheduler();
					}
	                jinseInfoScheduler.jinseCQ = CQ;
	                jinseInfoScheduler.jinseSchedule();
	                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) + "开启");
				}
				if (msg.equals("关闭金色推送")) {
					if (jinseInfoScheduler != null) {
						jinseInfoScheduler.closeJinseInfo();
					}
					CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) + "关闭");
				}
				if(msg.contains("添加火币usdt"))
				{
					allowusdt += "," + msg.substring(msg.indexOf("t")+1).toUpperCase();
					CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) + "已添加");
				}
				if (msg.contains("添加火币hadax")) {
					hadaxeth += "," + msg.substring(msg.indexOf("t")+1).toUpperCase();
					CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) + "已添加");
				}
			}
            if (returnStr.length() > 10)
            {
                CQ.sendGroupMsg(fromGroup, CC.at(fromQQ) + returnStr);
            }

        }

        return MSG_IGNORE;
    }

    /**
     * 讨论组消息 (Type=4)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype     子类型，目前固定为1
     * @param msgId       消息ID
     * @param fromDiscuss 来源讨论组
     * @param fromQQ      来源QQ号
     * @param msg         消息内容
     * @param font        字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群文件上传事件 (Type=11)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType   子类型，目前固定为1
     * @param sendTime  发送时间(时间戳)// 10位时间戳
     * @param fromGroup 来源群号
     * @param fromQQ    来源QQ号
     * @param file      上传文件信息
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
        GroupFile groupFile = CQ.getGroupFile(file);
        if (groupFile == null) { // 解析群文件信息，如果失败直接忽略该消息
            return MSG_IGNORE;
        }
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群事件-管理员变动 (Type=101)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/被取消管理员 2/被设置管理员
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群事件-群成员减少 (Type=102)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/群员离开 2/群员被踢
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(仅子类型为2时存在)
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 群事件-群成员增加 (Type=103)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/管理员已同意 2/管理员邀请
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(即管理员QQ)
     * @param beingOperateQQ 被操作QQ(即加群的QQ)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 好友事件-好友已添加 (Type=201)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype  子类型，目前固定为1
     * @param sendTime 发送时间(时间戳)
     * @param fromQQ   来源QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int friendAdd(int subtype, int sendTime, long fromQQ) {
        // 这里处理消息

        return MSG_IGNORE;
    }

    /**
     * 请求-好友添加 (Type=301)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，目前固定为1
     * @param sendTime     发送时间(时间戳)
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
        // 这里处理消息

        /**
         * REQUEST_ADOPT 通过
         * REQUEST_REFUSE 拒绝
         */

        // CQ.setFriendAddRequest(responseFlag, REQUEST_ADOPT, null); // 同意好友添加请求
        return MSG_IGNORE;
    }

    /**
     * 请求-群添加 (Type=302)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，1/他人申请入群 2/自己(即登录号)受邀入群
     * @param sendTime     发送时间(时间戳)
     * @param fromGroup    来源群号
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg,
                               String responseFlag) {
        // 这里处理消息

        /**
         * REQUEST_ADOPT 通过
         * REQUEST_REFUSE 拒绝
         * REQUEST_GROUP_ADD 群添加
         * REQUEST_GROUP_INVITE 群邀请
         */
		/*if(subtype == 1){ // 本号为群管理，判断是否为他人申请入群
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_ADD, REQUEST_ADOPT, null);// 同意入群
		}
		if(subtype == 2){
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_INVITE, REQUEST_ADOPT, null);// 同意进受邀群
		}*/

        return MSG_IGNORE;
    }

}
