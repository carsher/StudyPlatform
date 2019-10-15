<?php
function Alert($messg){
	echo "<script>alert('{$messg}');</script>";
}
function Model($model){
	include_once "./Framework/Model/".$model."Model.class.php";
	$modelstr = $model."Model";
	$m = new $modelstr;
	return $m;
}


function AlertGoUrl($messg,$url){
	echo "<script>alert('{$messg}');
		window.location.href = '{$url}';
	</script>";
}


function GoUrl($url){
echo "<script>
		window.location.href = '{$url}';
	</script>";
}


function isLogin(){
	if($_SESSION['username'] == ""){
			AlertGoUrl("请先登录！","/");
		}
}

?>