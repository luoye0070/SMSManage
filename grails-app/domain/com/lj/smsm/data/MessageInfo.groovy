package com.lj.smsm.data

class MessageInfo {
    String phones;
    String msgtext;
    Date sendTime=new Date();
    String status;

    static constraints = {
        phones(nullable:false,maxsize:12*1000);
        msgtext(nullable:false,maxsize:134*3);
        sendTime(nullable:false);
        status(nullable:false,maxsize:256);
    }

    @Override
    public java.lang.String toString() {
        return "MessageInfo{" +
                "id=" + id +
                ", phones='" + phones + '\'' +
                ", msgtext='" + msgtext + '\'' +
                ", sendTime=" + sendTime +
                ", status='" + status + '\'' +
                ", version=" + version +
                '}';
    }
}
