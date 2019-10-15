<?php
//Model父类
/**
* Model 
*/
class Model
{
	function __construct($model){

		
	}

	public function Exquery($table,$action,$order){
		//基础的sql命令执行
		switch ($action) {
			case 'select':
				$sql = "select * from ".$table." ".$order;
				$row = mysql_query($sql);
				return $row;
				break;
			case 'update':
				$sql = "update ".$table." set ".$order;
				mysql_query($sql);
				return mysql_affected_rows();
				break;
			case 'delete':
				$sql = "delete from ".$table." where ".$order;
				mysql_query($sql);
				return mysql_affected_rows();
				break;
			case 'insert':
				$sql = "insert into ".$table." ".$order;
				mysql_query($sql);
				return mysql_insert_id();
				break;
		}

	}

	public function save($list,$table){
		//根据数组存入数据
		$sql = "insert into ".$table." (";
		$keys = "";
		$values = "";
		$i=count($list);
		$k=0;
		foreach ($list as $key => $value) {
			if($key!="submit"){
				if($k==$i-2){	
					$keys .= "`".$key."`";
					$values .= "'".$value."'"."";	
				}else{
				$keys .= "`".$key."`,";
				$values .= "'".$value."'".",";		
				}
			}
			$k++;
		}
		$sql .= $keys.") values(".$values.")";
		//echo $sql;
		mysql_query($sql);
		return mysql_insert_id();

	}

	public function find($tablename,$page,$offsize=12){
		//拿到所有数据， 自带分页，偏移量12
		$cout = $page*$offsize;
		$sql = "select * from ".$tablename." limit {$page},{$cout}";
		exit;
		if($row = mysql_query($sql)){
			while ($rest = mysql_fetch_array($row)) {
				$list[] = $rest;
			}
			return $list;
		}else{
			return null;
		}
	}

	public function findHaveCodition($tablename,$codition){
		$sql = "select * from ".$tablename." where ";
		//遍历条件 [二维数组][单条返回]
		$cdt = "";
		$cdtN = count($codition);
		$index=0;
		foreach ($codition as $key => $value) {
			if($index==$cdtN-1){
				$cdt .= $key."='".$value."'";
			}else{
				$cdt .= $key."='".$value."'"." and ";
			}
			$index++;
		}

		$sql .=$cdt;
		if($row = mysql_query($sql)){
			$result = mysql_fetch_array($row);
			return $result;
		}

		return null;

	}


	public function findHaveCoditions($tablename,$codition){
		//遍历条件 [二维数组][多条数据返回][应用场景:用户进入个人中心，取个人数据时]
		$sql = "select * from ".$tablename." where ";
		$cdt = "";
		$cdtN = count($codition);
		$index=0;
		$isHas = false;

		foreach ($codition as $key => $value) {
			if($index==$cdtN-1 || $key=="order-by-id"){
				//最后一个条件，或者遇到order [2种情况，第一前面需要and连接 第二 不需要and[limit,order by id desc]]
				 switch ($key) {
				 	case 'limit':
					 	if(!$isHas){
					 		$cdt = substr($cdt, 0,-4);
					 	}
				 		$cdt .=" limit ".$value;
				 		break;
				 	case 'order-by-id':
				 		$cdt = substr($cdt, 0,-4);
				 		$cdt .="order by id ".$value;
				 		$isHas = true;
				 		break;
				 	default:
				 		$cdt .=$key."=".$value;
				 		break;
				 }

			}else{
				$cdt .=$key."=".$value." and ";
			}
			$index++;
		}
		$sql .=$cdt;
		//echo $sql;
		if($row = mysql_query($sql)){
			//$result = mysql_fetch_array($row);

			while ($res = mysql_fetch_array($row)) {
				$result[] = $res;
			}

			return $result;
		}else{
			return null;
		}
	}


}


?>