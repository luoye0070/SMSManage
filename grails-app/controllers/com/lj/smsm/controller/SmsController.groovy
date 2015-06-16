package com.lj.smsm.controller

import com.lj.smsm.data.MessageInfo
import lj.common.StrCheckUtil
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import java.text.ParseException
import java.text.SimpleDateFormat

class SmsController {
    def smsService;

    def index() {}


    def smsList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def model=[messageInfoInstanceList: MessageInfo.list(params), messageInfoInstanceTotal: MessageInfo.count()]
        render(view:"/manage/sms/smsList",model:model);
    }
    def delete(Long id) {
        def messageInfoInstance = MessageInfo.get(id)
        if (!messageInfoInstance) {
            flash.errors = message(code: 'default.not.found.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "smsList")
            return
        }

        try {
            messageInfoInstance.delete(flush: true)
            flash.msgs = message(code: 'default.deleted.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "smsList")
        }
        catch (DataIntegrityViolationException e) {
            flash.errors = message(code: 'default.not.deleted.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "smsList", id: id)
        }
    }

    def sendFromSection(){
        def errors=null;
        def msgs=null;
        if(request.method=="POST"){
            String phoneBegin=params.phoneBegin;
            String phoneEnd=params.phoneEnd;
            if (!StrCheckUtil.chkStrFormat(phoneBegin, "phone")||!StrCheckUtil.chkStrFormat(phoneEnd, "phone")) {
                errors="号码不正确";
                render(view:"/manage/sms/sendFromSection",model: [msgs:msgs,errors:errors]);
                return;
            }
            long phoneB_l=0;
            long phoneE_l=0;
            try {
                phoneB_l=Long.parseLong(phoneBegin);
                phoneE_l=Long.parseLong(phoneEnd);
            }catch (Exception ex){
                errors="号码不正确";
                render(view:"/manage/sms/sendFromSection",model: [msgs:msgs,errors:errors]);
                return;
            }

            int count=phoneE_l-phoneB_l;
            long beginPhone=phoneB_l;
            if(count<0){
                beginPhone=phoneE_l;
                count=0-count;
            }
            int section=count/100+1;
            for(int j=0;j<section;j++){
                int loopCount=99;
                if(j==section-1){
                    loopCount=count%100;
                }
                String phones=beginPhone+"";
                for (int i=0;i<loopCount;i++){
                    beginPhone=beginPhone+1;
                    phones+=","+(beginPhone);
                }
                log.info("phones->"+phones)
                def reInfo=smsService.send(phones,params.msgtext);
                if(reInfo.code==0){
                    msgs=reInfo.label;
                } else{
                    errors=reInfo.label;
                }
                beginPhone++;
            }
        }
        render(view:"/manage/sms/sendFromSection",model: [msgs:msgs,errors:errors]);
    }

    def sendFromExcel(){
        def errors=null;
        def msgs=null;
        if(request.method=="POST"){
            String  msgtext= params.msgtext;

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file=multiRequest.getFile("phones");
            String fileName=file.originalFilename;
            log.info("fileName->"+fileName);
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            Workbook workbook=null;
            try{
                if("xlsx".equals(fileExt)){
                    workbook=new XSSFWorkbook(file.inputStream);
                }else{
                    workbook=new HSSFWorkbook(file.inputStream);
                }
                Sheet sheet= workbook.getSheetAt(0);
                if(sheet==null){
                    errors="excel文件格式不正确";
                    render(view:"/manage/sms/sendFromExcel",model: [msgs:msgs,errors:errors]);
                    return;
                }
                //读取数据
                int count=0;
                String phones="";
                int rowNum = sheet.getLastRowNum();
                for(int i=0;i<rowNum;i++){
                    Row row=sheet.getRow(i);
                    if(row==null){
                        continue;
                    }
                    int colNum = row.getPhysicalNumberOfCells();
                    for(int j=0;j<colNum;j++){
                        Cell cell=row.getCell(j);
                        if(cell==null){
                            continue;
                        }
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String phone = cell.getStringCellValue();
                        if (!StrCheckUtil.chkStrFormat(phone, "phone")) {//丢弃
                            continue;
                        }
                        if(count%100==0){
                            phones=phone;
                        }else{
                            phones+=","+phone;
                        }
                        count++;
                        if(count%100==0){
                            log.info("phones->"+phones)
                            def reInfo=smsService.send(phones,params.msgtext);
                            if(reInfo.code==0){
                                msgs=reInfo.label;
                            } else{
                                errors=reInfo.label;
                            }
                            phones="";
                        }
                    }
                }
                if(phones){
                    log.info("phones->"+phones)
                    def reInfo=smsService.send(phones,params.msgtext);
                    if(reInfo.code==0){
                        msgs=reInfo.label;
                    } else{
                        errors=reInfo.label;
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
                errors="读取excel发生错误！";
            }finally {
                try {
                    if(workbook!=null){
                        workbook.close();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        render(view:"/manage/sms/sendFromExcel",model: [msgs:msgs,errors:errors]);
    }

    def sendFromPhones(){
        def errors=null;
        def msgs=null;

        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setPhones(params.phones);
        messageInfo.setMsgtext(params.msgtext);
        if(request.method=="POST"){
            def reInfo=smsService.send(params.phones,params.msgtext);
            if(reInfo.code==0){
                msgs=reInfo.label;
            } else{
                errors=reInfo.label;
            }
        }
        render(view:"/manage/sms/sendFromPhones",model: [msgs:msgs,errors:errors,messageInfoInstance:messageInfo]);
    }
}
