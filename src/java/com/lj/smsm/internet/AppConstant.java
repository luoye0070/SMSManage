package com.lj.smsm.internet;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-6-15
 * Time: 下午11:28
 * To change this template use File | Settings | File Templates.
 */
public interface AppConstant {
    class VisitServerResultCode
    {
        public final static int RESULT_CODE_OK=0;
        public final static int RESULT_CODE_NETWORKERROR=1;
        public final static int RESULT_CODE_OTHERERROR=2;
        public final static int RESULT_CODE_STOPBYUSER=3;
        public final static int RESULT_CODE_SERVERERRROR=4;

    }

    class HttpParamRe
    {
        public final static String PARAM_NAME="paramName";
        public final static String PARAM_VALUE="paramValue";
    }
}
