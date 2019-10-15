<?php
/* Smarty version 3.1.30, created on 2019-10-12 06:12:19
  from "F:\phpStudy\WWW\templates\admin\user-list.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5da16ec338ebe8_49550899',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '2c1f0ec0a865fb3bb63f0152c9de222ed17600eb' => 
    array (
      0 => 'F:\\phpStudy\\WWW\\templates\\admin\\user-list.html',
      1 => 1570707808,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5da16ec338ebe8_49550899 (Smarty_Internal_Template $_smarty_tpl) {
?>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/html5shiv.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/respond.min.js"><?php echo '</script'; ?>
>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
>DD_belatedPNG.fix('*');<?php echo '</script'; ?>
>
<![endif]-->
<title>用户管理</title>
</head> 
<body>
<div class="pd-20">
  
  
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="80">序号</th>
        <th width="100">用户名</th>
        <th width="90">学号</th>
        <th width="130">用户类型</th>
        <th width="130">加入时间</th> 
        <th width="130">学院</th>        
        <th width="130">班级</th>                
        <th width="100">操作</th>
      </tr>
    </thead>
    <tbody>
    <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
      <tr class="text-c">
        <td><?php echo count($_smarty_tpl->tpl_vars['list']->value)-(count($_smarty_tpl->tpl_vars['list']->value)-$_smarty_tpl->tpl_vars['i']->value++)+1;?>
</td>
       
        <td><u style="cursor:pointer" class="text-primary" onclick="user_show('10001','360','','张三','user-show.html')"><?php echo $_smarty_tpl->tpl_vars['val']->value['username'];?>
</u></td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['no'];?>
</td>
        <td><?php if ($_smarty_tpl->tpl_vars['val']->value['type'] == 'stu') {?>
          学生用户
          <?php } else { ?>
          教师用户
          <?php }?>
        </td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['addtime'];?>
</td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['departement'];?>
</td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['class'];?>
</td>
        
        <td class="f-14 user-manage">
            <a href="javascript:if(confirm('确实要删除该内容吗?'))location='/index/deleteUser?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
'">删除</a>  <a href="/index/showChangepwd?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">修改密码</a>
        </td>
      </tr>
      <?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl);
?>

    </tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"><?php echo '</script'; ?>
> 
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/layer/2.4/layer.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/static/h-ui/js/H-ui.min.js"><?php echo '</script'; ?>
> 
<?php echo '<script'; ?>
 type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"><?php echo '</script'; ?>
>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"><?php echo '</script'; ?>
> 
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"><?php echo '</script'; ?>
> 
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/laypage/1.2/laypage.js"><?php echo '</script'; ?>
>

</body>
</html>
<?php }
}
