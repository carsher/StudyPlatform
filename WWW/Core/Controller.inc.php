<?php
//控制器的父类
/**
* 
*/           
class Controller
{
	
	function __construct()
	{
		
	}

	public function display($teamplate){
		Factory::$View->display($teamplate);
	}

	public function assign($key,$value){
		Factory::$View->assign($key,$value);
	}

	public function haspost(){
		if ($_POST['submit']=="") {
			Alert("服务器忙，请返回重试！");
			exit;
		}
	}

}


?>