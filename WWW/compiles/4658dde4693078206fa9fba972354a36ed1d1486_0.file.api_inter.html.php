<?php
/* Smarty version 3.1.30, created on 2019-10-11 12:46:58
  from "F:\phpStudy20161103\phpStudy\WWW\templates\admin\api_inter.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5da079c2ba34b4_71972182',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '4658dde4693078206fa9fba972354a36ed1d1486' => 
    array (
      0 => 'F:\\phpStudy20161103\\phpStudy\\WWW\\templates\\admin\\api_inter.html',
      1 => 1570798016,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5da079c2ba34b4_71972182 (Smarty_Internal_Template $_smarty_tpl) {
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
        <th width="130">用户名</th>
        <th width="90">问题标题</th>
        <th width="130">问题内容</th>
        <th width="130">课程</th>                 
        <th width="100">时间</th>
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
      
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['user'];?>
</td>
        <!-- <td><?php if ($_smarty_tpl->tpl_vars['val']->value['type'] == 'stu') {?>
          学生用户
          <?php } else { ?>
          教师用户
          <?php }?>
        </td> -->
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['title'];?>
</td>
        <td><?php echo $_smarty_tpl->tpl_vars['val']->value['content'];?>
</td>
        <td><?php if ($_smarty_tpl->tpl_vars['val']->value['cid'] == '2') {?>
          智能穿戴设备开发基础
          
          <?php } elseif ($_smarty_tpl->tpl_vars['val']->value['cid'] == '1') {?>

          Java程序设计          
          <?php } elseif ($_smarty_tpl->tpl_vars['val']->value['cid'] == '3') {?>
          
                    
Python3入门+进阶          
                    <?php } else { ?>
智能穿戴设备开发基础
          <?php }?></td>
          <td><?php echo $_smarty_tpl->tpl_vars['val']->value['time'];?>
</td>
          
        <td class="f-14 user-manage">
            <a href="javascript:if(confirm('确实要删除该内容吗?'))location='/index/deleteUser?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
'">删除</a>
              <!-- <a href="/index/showChangepwd?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
">修改密码</a> -->
            <a href="/index/showcommitment?id=<?php echo $_smarty_tpl->tpl_vars['val']->value['id'];?>
&content=<?php echo $_smarty_tpl->tpl_vars['val']->value['content'];?>
">回复</a>
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
