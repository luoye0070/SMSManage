<!DOCTYPE HTML>
<html>
<head>
    <title>短信发送管理平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="Shortcut Icon" href="${resource(dir: 'images', file: 'favicon_picc.ico')}"/>
    <link href="${resource(dir: 'js/bui/css', file: 'dpl-min.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'js/bui/css', file: 'bui-min.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'js/bui/css', file: 'main.css')}" rel="stylesheet" type="text/css"/>
</head>

<body>
%{--<sec:ifLoggedIn>--}%
<div class="header">
    <div class="dl-title"><span class="">短信发送管理平台</span></div>

        %{--<div class="dl-log">欢迎您，<span class="dl-log-user"><sec:loggedInUserInfo field="username"/></span>--}%
            %{--<a href="${createLink(controller:'logout',action:'index')}" title="退出系统" class="dl-log-quit">[退出]</a>--}%
        %{--</div>--}%

</div>

<div class="content">
    <div class="dl-main-nav">
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-storage">短信管理</div></li>
            %{--<li class="nav-item dl-selected"><div class="nav-item-inner nav-storage">用户</div></li>--}%
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.8.1.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js/bui', file: 'bui-min.js')}"></script>
<script>
    BUI.use('common/main', function () {
        var config = [
            {
                id: 'smsManage',
                menu: [
                    {
                        text: '短信管理',
                        items: [
                            {id: 'sms_list_menu', text: '短信列表', href: '${createLink(controller:"sms",action:'smsList')}'},
                            {id: 'send_from_section_menu', text: '从号段发送短信', href: '${createLink(controller:"sms",action:'sendFromSection')}'},
                            {id: 'send_from_excel_menu', text: '从excel发送短信', href: '${createLink(controller:"sms",action:'sendFromExcel')}'},
                            {id: 'send_from_phones_menu', text: '从指定号码发送短信', href: '${createLink(controller:"sms",action:'sendFromPhones')}'}
                        ]
                    }
                ]
            }
            %{--,--}%
            %{--{--}%
                %{--id: 'user',--}%
                %{--menu: [--}%
                    %{--{--}%
                        %{--text: '用户',--}%
                        %{--items: [--}%
                            %{--{id: 'user_menu', text: '用户管理', href: '${createLink(controller:"userApi",action:'member')}'}--}%
                            %{--<sec:ifAnyGranted roles="R_DEV">,--}%
                            %{--{id: 'roles_menu', text: '角色管理', href: '${createLink(controller:"userApi",action:'roles')}'},--}%
                            %{--{id: 'authority_menu', text: 'Authority', href: '${createLink(controller:"userApi",action:'authority')}'},--}%
                            %{--{id: 'requestmap_menu', text: 'Requestmap', href: '${createLink(controller:"userApi",action:'requestmap')}'}--}%
                            %{--</sec:ifAnyGranted>,--}%
                            %{--{id: 'updPass_menu', text: '更新密码', href: '${createLink(controller:"userApi",action:'updPass')}'}--}%
                        %{--]--}%
                    %{--}--}%
                %{--]--}%
            %{--}--}%
        ];
        new PageUtil.MainPage({
            modulesConfig: config
        });
    });

</script>
%{--</sec:ifLoggedIn>--}%
</body>
</html>