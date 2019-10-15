<?php
header("Content-type: text/html; charset=utf-8");  
session_start();
//路由引入
define('BASE_PATH',str_replace('\\','/',realpath(dirname(__FILE__).'/'))."/");
 include_once "/Core/List.inc.php";
 include_once './Core/App.inc.php';
 include_once './Function/Comm.inc.php';
 include_once './Config/Config.inc.php';
 $list = explode("/", $_SERVER['PATH_INFO']);
 $app = new App;
 $app->Run($List,$Config);



?>