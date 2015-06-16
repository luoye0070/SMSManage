package com.lj.smsm.data



import org.junit.*
import grails.test.mixin.*

@TestFor(MessageInfoController)
@Mock(MessageInfo)
class MessageInfoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/messageInfo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.messageInfoInstanceList.size() == 0
        assert model.messageInfoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.messageInfoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.messageInfoInstance != null
        assert view == '/messageInfo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/messageInfo/show/1'
        assert controller.flash.message != null
        assert MessageInfo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/messageInfo/list'

        populateValidParams(params)
        def messageInfo = new MessageInfo(params)

        assert messageInfo.save() != null

        params.id = messageInfo.id

        def model = controller.show()

        assert model.messageInfoInstance == messageInfo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/messageInfo/list'

        populateValidParams(params)
        def messageInfo = new MessageInfo(params)

        assert messageInfo.save() != null

        params.id = messageInfo.id

        def model = controller.edit()

        assert model.messageInfoInstance == messageInfo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/messageInfo/list'

        response.reset()

        populateValidParams(params)
        def messageInfo = new MessageInfo(params)

        assert messageInfo.save() != null

        // test invalid parameters in update
        params.id = messageInfo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/messageInfo/edit"
        assert model.messageInfoInstance != null

        messageInfo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/messageInfo/show/$messageInfo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        messageInfo.clearErrors()

        populateValidParams(params)
        params.id = messageInfo.id
        params.version = -1
        controller.update()

        assert view == "/messageInfo/edit"
        assert model.messageInfoInstance != null
        assert model.messageInfoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/messageInfo/list'

        response.reset()

        populateValidParams(params)
        def messageInfo = new MessageInfo(params)

        assert messageInfo.save() != null
        assert MessageInfo.count() == 1

        params.id = messageInfo.id

        controller.delete()

        assert MessageInfo.count() == 0
        assert MessageInfo.get(messageInfo.id) == null
        assert response.redirectedUrl == '/messageInfo/list'
    }
}
