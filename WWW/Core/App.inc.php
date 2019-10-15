<?php
//项目启动引擎
/**
* 
*/
class App
{
	
	public function Run($List,$Config){
		$this->init($List);
		$this->coreinit($Config);
	}	
	public function init($List){
		//引入全部项目文件
		foreach ($List as $value) {
			include_once $value;
		}
	}


	public function coreinit($Config){
		new Core($Config);
	}

}

?>