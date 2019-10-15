<?php
/* Smarty version 3.1.30, created on 2018-11-09 15:00:49
  from "D:\phpStudy\WWW\templates\admin\smallsection-list.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5be530a1e0ae62_41894589',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'd56ca9539a7b7f0373bf0def5301d87e4cd6c943' => 
    array (
      0 => 'D:\\phpStudy\\WWW\\templates\\admin\\smallsection-list.html',
      1 => 1541746732,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5be530a1e0ae62_41894589 (Smarty_Internal_Template $_smarty_tpl) {
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
<title>小节内容设置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 课程管理 <span class="c-gray en">&gt;</span> 小节内容设置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  
  
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">ID</th>
        <th width="100">小节标题</th>
        <th width="150">视频资源</th>
        <th width="130">小节测试设定</th>
        <th width="130">视频长度</th>
        <th width="100">操作</th>
      </tr>
    </thead>
    <tbody>
    <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['list']->value, 'val');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['val']->value) {
?>
    <form action="/index/setSmallItemResoure" method="post" class="form form-horizontal" id="form-user-add" enctype="multipart/form-data" >
      <tr class="text-c">
        <td><input type="checkbox" value="1" name=""></td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
</td>
        <td><input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['title'];?>
" name="title"></td>
        <td>
         <?php if ($_smarty_tpl->tpl_vars['val']->value['videopath'] == 'null') {?>
          <input type="file" name="file" value="选择图片" >
         <?php } else { ?>
          <input type="file" name="file" value="选择图片" ><br>
          已经上传了视频文件：<?php echo $_smarty_tpl->tpl_vars['val']->value['videopath'];?>

          <?php }?>
        </td>
        <td>
          <?php if ($_smarty_tpl->tpl_vars['val']->value['testid'] == '0') {?>
            <a href="/index/setBindTemple?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">设置考试模板</a>
            <?php } else { ?>
             <a href="/index/setBindTemple?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">已经设置</a>
            <?php }?>
        </td>
        <td>
          <input type="text" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['viedo_length'];?>
" name="viedo_length">
        </td>
        <input type="hidden" name="id" value="<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
" >
        <input type="hidden" name="sid" value="<?php echo $_smarty_tpl->tpl_vars['sid']->value;?>
" >
        <td class="f-14 user-manage">
           <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
        </td>
      </tr>
      
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
