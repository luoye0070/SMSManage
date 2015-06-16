<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-6-16
  Time: 下午11:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="manageMain"/>
  <title></title>
</head>
<body>
<div style="margin: 10px;">
<table class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <g:sortableColumn property="id" title="${message(code: 'messageInfo.id.label', default: 'Id')}" />

        <g:sortableColumn property="phones" title="${message(code: 'messageInfo.phones.label', default: 'Phones')}" />

        <g:sortableColumn property="msgtext" title="${message(code: 'messageInfo.msgtext.label', default: 'Msgtext')}" />

        <g:sortableColumn property="sendTime" title="${message(code: 'messageInfo.sendTime.label', default: 'Send Time')}" />

        <g:sortableColumn property="status" title="${message(code: 'messageInfo.status.label', default: 'Status')}" />

        <th></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${messageInfoInstanceList}" status="i" var="messageInfoInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: messageInfoInstance, field: "id")}</td>

            <td style="word-break:break-all;max-width: 100px;">${messageInfoInstance?.phones}</td>

            <td>${fieldValue(bean: messageInfoInstance, field: "msgtext")}</td>

            <td><g:formatDate date="${messageInfoInstance.sendTime}" /></td>

            <td>${fieldValue(bean: messageInfoInstance, field: "status")}</td>

            <td>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${messageInfoInstance?.id}" />
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination pull-right">
    <lj:paginateInBui action="smsList" total="${messageInfoInstanceTotal ?: 0}" prev="&larr;" next="&rarr;"
                      params="${params}"/>
</div>
    </div>
</body>
</html>