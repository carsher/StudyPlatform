<?php
//php 工厂模式

/**
* 加载各种 数据库引擎 模板引擎
*/
class Factory
{
	static public $View;
	static public $DB;

	function __construct($config)
	{
		self::$View = $this->View();
		self::$DB = $this->DB($config);
	}

	public function View(){
		//实例化 smarty
		$smarty = new smarty();
		$smarty->cache_dir="caches";//缓存文件夹可选为减轻压力  
		$smarty->caching=false;//关闭缓存，调试中建议关闭，默认为关闭，在实际运行的时候请打开，减轻服务器压力  
		$smarty->template_dir="templates";//设置模版目录  
		$smarty->compile_dir="compiles";//设置编译目录必选  
		$smarty->left_delimiter = "<{"; //左边定界符
		$smarty->right_delimiter = "}>";
		return $smarty;
	}

	public function DB($config){
		//数据库实例化
		 $db = new DB($config);
		 return $db;
	}

}

?>