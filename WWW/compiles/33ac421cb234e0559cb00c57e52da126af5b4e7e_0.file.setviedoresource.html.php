<?php
/* Smarty version 3.1.30, created on 2018-10-12 10:04:21
  from "D:\phpStudy\WWW\templates\admin\setviedoresource.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5bc00125108135_43583235',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '33ac421cb234e0559cb00c57e52da126af5b4e7e' => 
    array (
      0 => 'D:\\phpStudy\\WWW\\templates\\admin\\setviedoresource.html',
      1 => 1532168102,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5bc00125108135_43583235 (Smarty_Internal_Template $_smarty_tpl) {
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 课程管理 <span class="c-gray en">&gt;</span> 章节资源设置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

   <?php $_smarty_tpl->_assignInScope('i', 1);
?>
    
    <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
    <h3>第<?php echo $_smarty_tpl->tpl_vars['i']->value;?>
章</h3>
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="300">章节标题</th>
        <th width="300">视频资源</th>
        <th width="300">上传资源</th>
      </tr>
    </thead>
    <tbody>
    <form action="/index/saveViedo" method="post" class="form form-horizontal" id="form-user-add" enctype="multipart/form-data"  >
      <tr class="text-c">
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['sectiontitle'];?>
" style="width: 200px; height: 30px; font-size: 14px;  " name="title"></td>
        <td>
        <?php if ($_smarty_tpl->tpl_vars['val']->value['path'] == '') {?>
          <input type="file" name="file" value="选择图片" >
         <?php } else { ?>
          <input type="file" name="file" value="选择图片" ><br>
          已经上传了视频文件：<?php echo $_smarty_tpl->tpl_vars['val']->value['path'];?>

          <?php }?>
        </td>
        <td>
         <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;上传&nbsp;&nbsp;">
        </td>
      </tr>
      <input type="hidden" name="id" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">
      <input type="hidden" name="courseid" value="<?php echo $_smarty_tpl->tpl_vars['courseid']->value;?>
">
</form>
<?php $_smarty_tpl->_assignInScope('i', $_smarty_tpl->tpl_vars['i']->value+1);
?>

</tbody>
  </table>

       <?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl);
?>

     

    
 

  <div id="pageNav" class="pageNav"></div>

<div class="row cl">
      <div class="col-9 col-offset-2">
       
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
