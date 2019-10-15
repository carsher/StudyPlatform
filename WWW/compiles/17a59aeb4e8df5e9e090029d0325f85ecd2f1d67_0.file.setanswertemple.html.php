<?php
/* Smarty version 3.1.30, created on 2019-10-07 06:41:07
  from "F:\phpStudy20161103\phpStudy\WWW\templates\admin\setanswertemple.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5d9ade0317e3d2_27006472',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '17a59aeb4e8df5e9e090029d0325f85ecd2f1d67' => 
    array (
      0 => 'F:\\phpStudy20161103\\phpStudy\\WWW\\templates\\admin\\setanswertemple.html',
      1 => 1532148402,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5d9ade0317e3d2_27006472 (Smarty_Internal_Template $_smarty_tpl) {
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 课程管理 <span class="c-gray en">&gt;</span> 题目设置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
<form action="/index/changeSubject" method="post" class="form form-horizontal" id="form-user-add" >
  <?php $_smarty_tpl->_assignInScope('i', 1);
?>
    <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
    <h3>第<?php echo $_smarty_tpl->tpl_vars['i']->value;?>
题</h3>
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="200">题目名称</th>
        <th width="150">选项A</th>
        <th width="150">选项B</th>
        <th width="150">选项C</th>
        <th width="150">选项D</th>
        <th width="120">正确答案</th>
      </tr>
    </thead>
    <tbody>
   
      <tr class="text-c">
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['title'];?>
" name="title[]"></td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['a'];?>
" name="a[]"></td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['b'];?>
" name="b[]"></td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['c'];?>
" name="c[]"></td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['d'];?>
" name="d[]"></td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['trueoption'];?>
" name="trueoption[]"></td>
      </tr>
      
      <input type="hidden" name="id[]" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">

    </tbody>
  </table>
<?php $_smarty_tpl->_assignInScope('i', $_smarty_tpl->tpl_vars['i']->value+1);
?>
  <?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl);
?>


  <div id="pageNav" class="pageNav"></div>

<div class="row cl">
      <div class="col-9 col-offset-2">
        <input class="btn btn-primary radius" style="width: 300px; height: 35px; margin: 0 auto; font-size: 17px;" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
      </div>
    </div>
</form>
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
