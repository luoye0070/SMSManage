package com.lj.smsm.service

import com.lj.smsm.data.MessageInfo
import com.lj.smsm.internet.AppConstant
import com.lj.smsm.internet.HttpConnectionHelper

class SmsService {
    def grailsApplication;

    static reMessages=[
            "0":"操作成功",
            "-1":"失败、用户或密码错误",
            "-2":"缺少源号码",
            "-3":"缺少密码",
            "-4":"缺少短信内容",
            "-5":"缺少目标号码",
            "-6":"企业账号登陆失败",
            "-7":"内容过长",
            "-8":"超过100个号码",
            "-9":"账户无效或暂停使用",
            "-10":"余额不足",
            "-11":"含有敏感词句",
            "-12":"本小时流量到",
            "-13":"本日流量到",
            "-14":"扩展号错误",
            "-15":"操作频率过快",
            "-16":"目标号码不正确",
            "-17":"主机无响应",
            "-110":"系统错误，请联系业务员"

    ];

    def send(String phones,String msgtext) {
        String cpid=grailsApplication.config.grails.sms.cpid;//"2026128";
        String cppwd=grailsApplication.config.grails.sms.cppwd;//"20223313";
        String cppwdMd5=cppwd.encodeAsMD5().toUpperCase();
        String sendUrl=grailsApplication.config.grails.sms.sendurl;
        log.info("cpid"+cpid+";cppwdMd5->"+cppwdMd5+"sendUrl"+sendUrl);

        ArrayList<HashMap<String, String>> paramList=new ArrayList<HashMap<String, String>>();
        HashMap<String,String> p1=new HashMap<String,String>();
        p1.put(AppConstant.HttpParamRe.PARAM_NAME,"cpid");
        p1.put(AppConstant.HttpParamRe.PARAM_VALUE,cpid);
        paramList.add(p1);
        HashMap<String,String> p2=new HashMap<String,String>();
        p2.put(AppConstant.HttpParamRe.PARAM_NAME,"cppwd");
        p2.put(AppConstant.HttpParamRe.PARAM_VALUE,cppwdMd5);
        paramList.add(p2);
        HashMap<String,String> p3=new HashMap<String,String>();
        p3.put(AppConstant.HttpParamRe.PARAM_NAME,"phone");
        p3.put(AppConstant.HttpParamRe.PARAM_VALUE,phones);
        paramList.add(p3);
        HashMap<String,String> p4=new HashMap<String,String>();
        p4.put(AppConstant.HttpParamRe.PARAM_NAME,"msgtext");
        p4.put(AppConstant.HttpParamRe.PARAM_VALUE,msgtext);
        paramList.add(p4);

        HttpConnectionHelper hch=new HttpConnectionHelper();
        String responseStr=hch.getResponseStr(sendUrl,paramList);
        log.info("responseStr->"+responseStr);
        int recode=-200;
        if(responseStr!=null){
            try {
                recode=Integer.parseInt(responseStr.trim());
            }
            catch (Exception ex){
                log.warn(ex.getMessage());
            }
        }
        String label=reMessages.get(recode+"");
        if(label==null){
            label="未知错误";
        }

        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setPhones(phones);
        messageInfo.setMsgtext(msgtext);
        messageInfo.setStatus(label);
        if(!messageInfo.save(flush: true)){
            log.error("保存消息失败："+messageInfo);
        }

        return [code:recode,label:label];
    }
}
