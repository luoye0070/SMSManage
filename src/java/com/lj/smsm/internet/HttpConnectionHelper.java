package com.lj.smsm.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HttpConnectionHelper {
    private String urlStr;
    private int resultCode;//访问网络结果代码,0成功,1网络错误，2其他错误
    private ArrayList<HashMap<String, String>> paramList;
    private int outtime;

    public HttpConnectionHelper(){resultCode=AppConstant.VisitServerResultCode.RESULT_CODE_OK;outtime=5000;}
    public HttpConnectionHelper(String urlStr,
                                ArrayList<HashMap<String, String>> paramList) {
        super();
        this.urlStr = urlStr;
        this.paramList = paramList;
        this.resultCode=AppConstant.VisitServerResultCode.RESULT_CODE_OK;
        this.outtime=5000;
    }

    public int getOuttime() {
        return outtime;
    }
    public void setOuttime(int outtime) {
        this.outtime = outtime;
    }
    public String getUrlStr() {
        return urlStr;
    }
    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }
    public int getResultCode() {
        return resultCode;
    }
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    public ArrayList<HashMap<String, String>> getParamList() {
        return paramList;
    }
    public void setParamList(ArrayList<HashMap<String, String>> paramList) {
        this.paramList = paramList;
    }

    public String getResponseStr()
    {
        System.out.println(this.urlStr);
        StringBuilder sb=new StringBuilder("");
        HttpURLConnection httpConn=null;
        BufferedReader br=null;
        try {
            URL url=new URL(urlStr);
            httpConn=(HttpURLConnection) url.openConnection();
            //this.outtime=10000;
            httpConn.setReadTimeout(this.outtime);//设置超时时间为5秒
            //HttpURLConnection.setFollowRedirects(true);
            if(paramList!=null)
            {
                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("POST");

                //发送数据
                String paramsStr="";
                for (Iterator iterator = paramList.iterator(); iterator.hasNext();) {
                    HashMap<String, String> paramHash = (HashMap<String, String>) iterator.next();
                    paramsStr+=paramHash.get(AppConstant.HttpParamRe.PARAM_NAME)+"="+paramHash.get(AppConstant.HttpParamRe.PARAM_VALUE)+"&";
                }
                OutputStream out=httpConn.getOutputStream();
                out.write(paramsStr.getBytes());
                out.flush();
                out.close();
            }

            //接收返回数据
            br=new BufferedReader(new InputStreamReader(httpConn.getInputStream()),1024*512);

            //获取响应码
            int reCode=httpConn.getResponseCode();
            System.out.println("responseCode:"+reCode);
            if(reCode!=200)
            {//任何错误都定义为其他错误
                this.resultCode=AppConstant.VisitServerResultCode.RESULT_CODE_OTHERERROR;
            }

            //读取数据
            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                sb.append(line);
            }

        }
        catch(IOException e)
        {
            this.resultCode=AppConstant.VisitServerResultCode.RESULT_CODE_NETWORKERROR;
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            this.resultCode=AppConstant.VisitServerResultCode.RESULT_CODE_OTHERERROR;
            e.printStackTrace();
        }
        finally
        {
            if(httpConn!=null)
            {
                httpConn.disconnect();
            }
            if(br!=null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
    public String getResponseStr(String urlStr,
                                 ArrayList<HashMap<String, String>> paramList) {
        this.urlStr = urlStr;
        this.paramList = paramList;
        return getResponseStr();
    }
}
