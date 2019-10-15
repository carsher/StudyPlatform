<?php
//Model父类
/**
* 
*/
class DB
{
	
	function __construct($config)
	{
		$this->init($config);
	}

	public function init($config){
		$con = mysql_connect($config['HOST'],$config['USERNAME'],$config['PASSWORD']);
		if(!$con){
			echo 'Connect Mysql is Die'.mysql_errno();exit;
		}
		mysql_set_charset($config['CHARSET']);
		mysql_select_db($config['DBNAME'],$con);
	}
}

?>