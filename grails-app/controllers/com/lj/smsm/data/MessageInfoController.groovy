package com.lj.smsm.data

import org.springframework.dao.DataIntegrityViolationException

class MessageInfoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [messageInfoInstanceList: MessageInfo.list(params), messageInfoInstanceTotal: MessageInfo.count()]
    }

    def create() {
        [messageInfoInstance: new MessageInfo(params)]
    }

    def save() {
        def messageInfoInstance = new MessageInfo(params)
        if (!messageInfoInstance.save(flush: true)) {
            render(view: "create", model: [messageInfoInstance: messageInfoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), messageInfoInstance.id])
        redirect(action: "show", id: messageInfoInstance.id)
    }

    def show(Long id) {
        def messageInfoInstance = MessageInfo.get(id)
        if (!messageInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "list")
            return
        }

        [messageInfoInstance: messageInfoInstance]
    }

    def edit(Long id) {
        def messageInfoInstance = MessageInfo.get(id)
        if (!messageInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "list")
            return
        }

        [messageInfoInstance: messageInfoInstance]
    }

    def update(Long id, Long version) {
        def messageInfoInstance = MessageInfo.get(id)
        if (!messageInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (messageInfoInstance.version > version) {
                messageInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'messageInfo.label', default: 'MessageInfo')] as Object[],
                          "Another user has updated this MessageInfo while you were editing")
                render(view: "edit", model: [messageInfoInstance: messageInfoInstance])
                return
            }
        }

        messageInfoInstance.properties = params

        if (!messageInfoInstance.save(flush: true)) {
            render(view: "edit", model: [messageInfoInstance: messageInfoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), messageInfoInstance.id])
        redirect(action: "show", id: messageInfoInstance.id)
    }

    def delete(Long id) {
        def messageInfoInstance = MessageInfo.get(id)
        if (!messageInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "list")
            return
        }

        try {
            messageInfoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'messageInfo.label', default: 'MessageInfo'), id])
            redirect(action: "show", id: id)
        }
    }
}
