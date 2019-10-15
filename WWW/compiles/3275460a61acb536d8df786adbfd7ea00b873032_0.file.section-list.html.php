<?php
/* Smarty version 3.1.30, created on 2019-10-08 07:41:23
  from "F:\phpStudy20161103\phpStudy\WWW\templates\admin\section-list.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5d9c3da3bde611_25755154',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '3275460a61acb536d8df786adbfd7ea00b873032' => 
    array (
      0 => 'F:\\phpStudy20161103\\phpStudy\\WWW\\templates\\admin\\section-list.html',
      1 => 1570519796,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5d9c3da3bde611_25755154 (Smarty_Internal_Template $_smarty_tpl) {
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
<title>章节管理</title>
</head>
<body>
<div class="pd-20">
  
  
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="80">ID</th>
        <th width="100">章节顺序</th>
        <th width="150">章节标题</th>
        <th width="130">小节数量</th>
        <th width="300">操作</th>
      </tr>
    </thead>
    <tbody>
    <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
    <form action="/index/changeSectionInfo" method="POST">
      <tr class="text-c">
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
</td>
        <td>
        第<?php echo $_smarty_tpl->tpl_vars['val']->value['section_sequence'];?>
章
        </td>
        <td>
        <input type="text" name="smallsectiontitle" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['section_title'];?>
" >
        </td>
        <td><input type="text" name="count" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['count'];?>
" ></td>
        <td class="">
            <a href="#">
              <input  style="padding:5px;" type="submit" name="submit" value="保存" />
              <a href="/index/setSmallItem?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
&count=<?php echo $_smarty_tpl->tpl_vars['val']->value['count'];?>
">设置小节内容</a>
            </a>
        </td>
      </tr>
      <input type="hidden" name="id" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">
      <input type="hidden" name="courseid" value="<?php echo $_smarty_tpl->tpl_vars['courseid']->value;?>
">
      </form>
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
