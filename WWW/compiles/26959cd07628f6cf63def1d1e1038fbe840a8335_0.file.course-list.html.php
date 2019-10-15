<?php
/* Smarty version 3.1.30, created on 2019-10-12 06:12:35
  from "F:\phpStudy\WWW\templates\admin\course-list.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5da16ed3cd9778_60632703',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '26959cd07628f6cf63def1d1e1038fbe840a8335' => 
    array (
      0 => 'F:\\phpStudy\\WWW\\templates\\admin\\course-list.html',
      1 => 1570688928,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5da16ed3cd9778_60632703 (Smarty_Internal_Template $_smarty_tpl) {
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
<title>课程管理</title>
</head>
<body>
<div class="pd-20">
  <nav class="breadcrumb">
    <button><a class="addcourse" href="/index/showaddCourse" target="right">添加课程</a>
    </button>
  </nav>  
  
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">ID</th>
        <th width="100">预览图</th>
        <th width="150">课程名称</th>
        <th width="130">学习人数</th>
        <th width="120">所属教师</th>
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
        <td><input type="checkbox" value="1" name=""></td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
</td>
        <td><img width="150px" height="120px" src="<?php echo $_smarty_tpl->tpl_vars['val']->value['imgpath'];?>
" /></td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['coursename'];?>
</td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['learning'];?>
人</td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['belong'];?>
</td>
        <td class="f-14 user-manage">
            <a href="#">删除</a>
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
