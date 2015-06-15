package com.lj.smsm.controller

class TestController {
    def smsService;
    def index() {

    }

    def sendSms(){

        if(request.method=="POST"){
            String cpid="2026128";
            String cppwd="20223313";
            String cppwdMd5=cppwd.encodeAsMD5().toUpperCase();
            log.info("cppwdMd5->"+cppwdMd5);
            def reInfo=smsService.send("18699178734","test");
            render(text: reInfo.label,contentType: "text/html");
        }
    }
}
