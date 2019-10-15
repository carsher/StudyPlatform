<?php
require_once './Libs/baidu/AipFace.php';

// 你的 APPID AK SK
const APP_ID = '10852026';
const API_KEY = '01zizodGwM35GNMla4lLpDkG';
const SECRET_KEY = 'nbWjkWELCB5fUsHB1WgNApqCOd4VOvmA';

$client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

$uid = "user3";

$userInfo = "user's info";

$groupId = "group01";

$image = file_get_contents('test2.jpg');

// 调用人脸注册
$client->addUser($uid, $userInfo, $groupId, $image);

// 如果有可选参数
$options = array();
$options["action_type"] = "replace";

// 带参数调用人脸注册
$ret = $client->addUser($uid, $userInfo, $groupId, $image, $options);
$data=json_encode($ret);
echo $data;
?>