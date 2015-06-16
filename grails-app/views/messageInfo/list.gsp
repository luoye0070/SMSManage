
<%@ page import="com.lj.smsm.data.MessageInfo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'messageInfo.label', default: 'MessageInfo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-messageInfo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-messageInfo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="phones" title="${message(code: 'messageInfo.phones.label', default: 'Phones')}" />
					
						<g:sortableColumn property="msgtext" title="${message(code: 'messageInfo.msgtext.label', default: 'Msgtext')}" />
					
						<g:sortableColumn property="sendTime" title="${message(code: 'messageInfo.sendTime.label', default: 'Send Time')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'messageInfo.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${messageInfoInstanceList}" status="i" var="messageInfoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${messageInfoInstance.id}">${fieldValue(bean: messageInfoInstance, field: "phones")}</g:link></td>
					
						<td>${fieldValue(bean: messageInfoInstance, field: "msgtext")}</td>
					
						<td><g:formatDate date="${messageInfoInstance.sendTime}" /></td>
					
						<td>${fieldValue(bean: messageInfoInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${messageInfoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
