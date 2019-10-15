<?php
//框架核心文件
/**
* 加载配置文件
* 加载工厂类
* 
*/
class Core
{
	
	public $Action;//需要引入的控制器
	public $Method; //需要执行该控制器下的某个方法
	public $code;// 第三个参数 id or other ues

	function __construct($config)
	{
		$this->initUrl();  //初始化路由
		$this->SafeString();
		$this->Factoryinit($config);
		$this->DoAction();//引入文件 执行对应控制器的方法
	}


	public function initUrl(){
		$list = explode("/", $_SERVER['PATH_INFO']);
		if (@$list=="") {
			$this->Action="index";
			$this->Method="index";
		}else if(@$list[1]!="" && @$list[2]==""){
			$this->Action=$list[1];
			$this->Method="index";
		}else if(@$list[1]!="" && $list[2]!="" && @$list[3]==""){
			$this->Action=$list[1];
			$this->Method=$list[2];
		}else if($list[1]!="" && $list[2]!="" && $list[3]!=""){
			$this->Action=$list[1];
			$this->Method=$list[2];
			$this->code = $list[3];
		}else{
			$this->Action="index";
			$this->Method="index";
		}

	}

	public function SafeString(){
		//判断当前 需要实例化的方法和类是否安全
		$Action = $this->Action;
		//$Method = $this->Method;
		$NotUse = array('Core','DB','APP','Factory','Controller','Model');
		if(in_array($Action,$NotUse)){
			$this->Action="index";
			$this->Method="index";
		}
	}

	public function Factoryinit($config){
		new Factory($config);
	}

	public function DoAction(){
		//开始加载控制器和执行对应的方法
		$Action = $this->Action;
		$Method = $this->Method;
		$code = $this->code;
		$Actions = $Action.'Controll';
		//引入文件
		$inc = @include_once "./Framework/Controller/".$Action."Controller.class.php";
		if($inc){
		$obj = new $Actions;
		@$obj->$Method();
		}else{
		include_once "./Framework/Controller/indexController.class.php";
		$obj = new indexControll;
		$obj->index();
		}
		
	}
}

?>