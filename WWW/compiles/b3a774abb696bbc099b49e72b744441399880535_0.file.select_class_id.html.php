<?php
/* Smarty version 3.1.30, created on 2019-10-10 03:23:00
  from "F:\phpStudy20161103\phpStudy\WWW\templates\admin\select_class_id.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5d9ea414922108_42381986',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'b3a774abb696bbc099b49e72b744441399880535' => 
    array (
      0 => 'F:\\phpStudy20161103\\phpStudy\\WWW\\templates\\admin\\select_class_id.html',
      1 => 1570677761,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5d9ea414922108_42381986 (Smarty_Internal_Template $_smarty_tpl) {
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
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
>DD_belatedPNG.fix('*');<?php echo '</script'; ?>
>
<![endif]-->
<title>选择课程与班级</title>
</head>
<body>

	

<div class="page-container">
	<form action="/index/drawClassId" method="GET" class="form form-horizontal" id="form-user-add" enctype="multipart/form-data" >
		<div>	
		<label class="">
				请选择课程：</label>
				<select name="courseider">
					<?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
					<option value="<?php echo $_smarty_tpl->tpl_vars['val']->value['coursename'];?>
"><?php echo $_smarty_tpl->tpl_vars['val']->value['coursename'];?>
</option>
					<?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl);
?>

				</select>
			</div>
			<div>				<label class="">
					请选择用户：</label>
					<select name="classnameer">
						<?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['cl']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
						<option value="<?php echo '';?>
"></option>
						<option value="<?php echo $_smarty_tpl->tpl_vars['val']->value['username'];?>
"><?php echo $_smarty_tpl->tpl_vars['val']->value['username'];?>
</option>
						<?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl);
?>

					</select>
				</div>
				<div>
					<label><input name="typer" type="radio" value="0" checked/>按课程 </label> 
					<label><input name="typer" type="radio" value="1" />按课程+学生 </label> 
				</div>
				
		
		     

		<div class="row cl">
			<div class="col-9 col-offset-2">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;下一步&nbsp;&nbsp;">
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
> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript">
$(function(){
	
});
<?php echo '</script'; ?>
>
</body>
</html><?php }
}
