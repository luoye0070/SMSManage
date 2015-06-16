
<%@ page import="com.lj.smsm.data.MessageInfo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'messageInfo.label', default: 'MessageInfo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-messageInfo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-messageInfo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list messageInfo">
			
				<g:if test="${messageInfoInstance?.phones}">
				<li class="fieldcontain">
					<span id="phones-label" class="property-label"><g:message code="messageInfo.phones.label" default="Phones" /></span>
					
						<span class="property-value" aria-labelledby="phones-label"><g:fieldValue bean="${messageInfoInstance}" field="phones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInfoInstance?.msgtext}">
				<li class="fieldcontain">
					<span id="msgtext-label" class="property-label"><g:message code="messageInfo.msgtext.label" default="Msgtext" /></span>
					
						<span class="property-value" aria-labelledby="msgtext-label"><g:fieldValue bean="${messageInfoInstance}" field="msgtext"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInfoInstance?.sendTime}">
				<li class="fieldcontain">
					<span id="sendTime-label" class="property-label"><g:message code="messageInfo.sendTime.label" default="Send Time" /></span>
					
						<span class="property-value" aria-labelledby="sendTime-label"><g:formatDate date="${messageInfoInstance?.sendTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${messageInfoInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="messageInfo.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${messageInfoInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${messageInfoInstance?.id}" />
					<g:link class="edit" action="edit" id="${messageInfoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
