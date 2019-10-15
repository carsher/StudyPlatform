<?php
/* Smarty version 3.1.30, created on 2019-10-11 02:30:28
  from "F:\phpStudy20161103\phpStudy\WWW\templates\admin\indexcopy.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5d9fe944a12ce8_77014468',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '3e738cd7a9121428ec14ee94621674f3e359498a' => 
    array (
      0 => 'F:\\phpStudy20161103\\phpStudy\\WWW\\templates\\admin\\indexcopy.html',
      1 => 1570761026,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5d9fe944a12ce8_77014468 (Smarty_Internal_Template $_smarty_tpl) {
?>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/static/new/css/base.css" />
<link rel="stylesheet" type="text/css" href="/static/new/css/jquery.dialog.css" />
<link rel="stylesheet" href="/static/new/css/index.css" />
    <style>
        .layui-layer-title{background:url(images/righttitlebig.png) repeat-x;font-weight:bold;color:#46647e; border:1px solid #c1d3de;height: 33px;line-height: 33px;}
    </style>
<title>后台管理系统</title>
</head>
<body>
<div id="container">
	<div id="hd">
    	<div class="hd-wrap ue-clear">
        	
            <div class="login-info ue-clear">
                <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:void(0)" class="user-name">Admin</a></div>
                <div class="login-msg ue-clear">
                    <a href="javascript:void(0)" class="msg-txt">消息</a>
                    <a href="javascript:void(0)" class="msg-num">10</a>
                </div>
            </div>
            <div class="toolbar ue-clear">
                <!-- <a href="javascript:void(0)" class="home-btn1" target="right" onclick="openlayer()">修改密码</a> -->
                <a href="javascript:void(0)" class="quit-btn exit home-btn">退出</a>
            </div>
        </div>
    </div>
    <div id="bd">
    	<div class="wrap ue-clear">
        	<div class="sidebar">
            	<h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                    <li class="land"><div class="nav-header"><a href="JavaScript:;" class="ue-clear" ><span>系统管理</span><i class="icon hasChild"></i></a></div>
                        <ul class="subnav">
                            <li><a href="/index/showUserList" target="right">用户管理</a></li>
                            <li><a href="/index/showUsertype" target="right">添加用户类型</a></li>
                            <li><a href="/index/showCourseList" target="right">课程管理</a></li>
                            <li><a href="/index/showChioesCourse" target="right">知识树</a></li>
                            <li><a href="/index/showaddanswer" target="right">添加测试试题</a></li>                            
                            <li><a href="/index/showanswer" target="right">测试题管理</a></li>
                            <li><a href="/index/showApiInterlocution" target="right">交流讨论</a></li>                            
         <!-- <li><a href="/index/showscore" target="right">测试成绩查看</a></li>                             -->
         <li><a href="/index/showCouresforClassId" target="right">测试成绩查看</a></li>                            
         <li><a href="/index/showScoreAnalyze" target="right">学情分析</a></li>
         <!-- <li><a href="/index/addSore" target="right">+++++++</a></li> -->
         
                        </ul>
                    </li>

                </ul>
            </div>
            <div class="content">
            	<iframe src="" id="iframe" width="100%" height="100%" frameborder="0" name="right" style="min-width: 1100px"></iframe>
            </div>
        </div>
    </div>
    <div id="ft" class="foot_div">

        <div class="ft-right">
        </div>
    </div>
</div>
<div class="exitDialog">
	<div class="dialog-content">
    	<div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
        	<p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>
            
            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" />
                <input type="button" class="button long2 normal" value="取消" />
            </div>
        </div>
        
    </div>
</div>
</body>
<?php echo '<script'; ?>
 type="text/javascript" src="/static/new/js/jquery.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/static/new/js/core.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/static/new/js/jquery.dialog.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/static/new/js/index.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 src="/static/new/js/layer_v2.1/layer/layer.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript">
    function openlayer(id){
        layer.open({
            type: 2,
            title: '修改密码',
            shadeClose: false,
            shade: 0.5,
            skin: 'layui-layer-rim',
//            maxmin: true,
            closeBtn:2,
            area: ['35%', '40%'],
            content: 'password.html'
            //iframe的url
        });
    }
<?php echo '</script'; ?>
>
</html>
<?php }
}
