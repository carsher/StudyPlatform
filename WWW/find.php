<?php
header("Content-type: text/html; charset=utf-8");  

require_once './Libs/baidu/AipFace.php';

// 你的 APPID AK SK
const APP_ID = '10852026';
const API_KEY = '01zizodGwM35GNMla4lLpDkG';
const SECRET_KEY = 'nbWjkWELCB5fUsHB1WgNApqCOd4VOvmA';

$client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

$groupId = "01,group01";

$image = file_get_contents('test2.jpg');

// 调用人脸识别
$client->identifyUser($groupId, $image);

// 如果有可选参数
$options = array();
$options["ext_fields"] = "faceliveness";
$options["user_top_num"] = 3;

// 带参数调用人脸识别
$array = $client->identifyUser($groupId, $image, $options);
foreach ($array as $key => $value) {
	echo "最外面的key值：".$key."Vulaer值：".$value."<br>";
	foreach ($value as $keys => $values) {
		foreach ($values as $keyss => $valuess) {
			echo "当前的key值：";
			echo $keyss;
			echo "当前的value值：".$valuess;
			echo "<br>";		
		}

	}
	
}
?>