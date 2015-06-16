<%@ page import="com.lj.smsm.data.MessageInfo" %>



<div class="fieldcontain ${hasErrors(bean: messageInfoInstance, field: 'phones', 'error')} ">
	<label for="phones">
		<g:message code="messageInfo.phones.label" default="Phones" />
		
	</label>
	<g:textField name="phones" value="${messageInfoInstance?.phones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: messageInfoInstance, field: 'msgtext', 'error')} ">
	<label for="msgtext">
		<g:message code="messageInfo.msgtext.label" default="Msgtext" />
		
	</label>
	<g:textField name="msgtext" value="${messageInfoInstance?.msgtext}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: messageInfoInstance, field: 'sendTime', 'error')} required">
	<label for="sendTime">
		<g:message code="messageInfo.sendTime.label" default="Send Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="sendTime" precision="day"  value="${messageInfoInstance?.sendTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: messageInfoInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="messageInfo.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${messageInfoInstance?.status}"/>
</div>

