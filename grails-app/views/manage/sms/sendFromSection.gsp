<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-6-16
  Time: 下午11:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta name="layout" content="manageMain"/>
    <title></title>
</head>
<body>
<div class="">
    <g:form action="sendFromSection" name="form1" method="post" class="form-horizontal">
        <div class="control-group">
            <label class="control-label"><g:message code="messageInfo.phones.label" default="Phones" />：</label>
            <div class="controls control-row-auto">
                %{--<g:textArea name="phones" style="width: 500px;" cols="40" rows="2" maxlength="1200" required=""
                            value="${messageInfoInstance?.phones}" placeholder="输入手机号，多个手机号用','隔开"/>--}%
                <g:textField name="phoneBegin" value="${params?.phoneBegin}" placeholder="输入手机号段开始号码" required=""/> -
                <g:textField name="phoneEnd" value="${params?.phoneEnd}" placeholder="输入手机号段结束号码" required=""/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><g:message code="messageInfo.msgtext.label" default="Msgtext" />：</label>
            <div class="controls control-row-auto">
                <g:textArea name="msgtext" style="width: 500px;" cols="40" rows="2" maxlength="402" required=""
                            value="${params?.msgtext}"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="button">
                    ${message(code: 'default.button.send.label', default: 'Send')}
                </button>
            </div>
        </div>

    </g:form>
</div>
</body>
</html>