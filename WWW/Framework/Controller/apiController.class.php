<?php
class apiControll extends Controller
{

	//接口文件
	public function login(){
		//登录接口
		$user = $_POST['user'];
		$pwd = $_POST['pwd'];
		$model = new Model();
		$re = $model->findHaveCodition("api_user",['username'=>$user,'pwd'=>$pwd]);
		if(!$re){
			$re['isfind'] = "false";
		}else{
			$re['isfind'] = "true";
		}
		
		//查询用户对应的，姓名，班级
		$nos = $re['no'];
		
		$res = $model->findHaveCodition('api_sutdentinfo',['no'=>$nos]);
		
		$re['name'] = $res['name'];
		$re['classname'] = $res['classname'];
		
		echo json_encode($re,JSON_UNESCAPED_UNICODE);
	}

	public function getHomeInfo(){
		//获取到首页的 文字类课程相关信息
		$model = new Model();
		$list = $model->findHaveCoditions("api_course",['type'=>"'文字类'"]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getViedoCourseList(){
		//获取到首页的 文字类课程相关信息
		$model = new Model();
		$list = $model->findHaveCoditions("api_course",['type'=>"'视频类'"]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getTextCourse(){
		$id = $_POST['id'];
		$user = $_POST['user'];
		//查询到文字课程的详细信息[章节,章节标题]
		$model = new Model();
		$list = $model->findHaveCoditions("api_course",['id'=>$id]);

		$sql = "select sectiontitle,section from api_coursetextresource where pid='{$id}'";
		$row = mysql_query($sql);

		while ($arr = mysql_fetch_array($row)) {
			$section[] = $arr;
		}
		$json['course'] = $list;
		$json['section'] = $section;

		/*查询用户是否已经选了课，如果有，那么学到了那一节*/

		$stucou = $model->findHaveCodition("api_stucou",['user'=>$user,'courseid'=>$id]);

		if($stucou == null){
			$json['is'] = "no";
			$json['sctionpro'] = 1;

			//更新订阅量
			$sql = "update api_course set hot=hot+1 where id='{$id}'";
			mysql_query($sql);

		}else{
			$json['is'] = "yes";
			if($stucou['pro'] == 0){
				$json['sctionpro'] = 1;
			}else{
				$json['sctionpro'] = $stucou['pro'];
			}
			
		}

		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}

	public function getViedoCourse(){
		$id = $_POST['id'];
		$user = $_POST['user'];
		//查询到视频课程的详细信息[章节,章节标题]
		$model = new Model();
		$list = $model->findHaveCodition("api_course",['id'=>$id]);

		/*查询用户是否已经选了课，如果有，那么学到了那一节*/

		$stucou = $model->findHaveCodition("api_stucou",['user'=>$user,'courseid'=>$id]);

		if($stucou == null){
			$list['is'] = "no";
			$list['sctionpro'] = 1;

			//更新订阅量
			$sql = "update api_course set hot=hot+1 where id='{$id}'";
			mysql_query($sql);
			/*插入到选课表中*/
			$model->save(['user'=>$user,'courseid'=>$id,'pro'=>"1",'submit'=>'submit'],"api_stucou");

		}else{
			$list['is'] = "yes";
			if($stucou['pro'] == 0){
				$list['sctionpro'] = 1;
			}else{
				$list['sctionpro'] = $stucou['pro'];
			}
			
		}


		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}


	public function getViedoCourseInfo(){
		//获取到我选课的视频课程的详情，包含了课程章节，课程介绍，评论，课程资料
		$id = $_POST['id'];
		$model = new Model();
		$sql = "select sectiontitle,section,path from api_courseviedoresource where pid='{$id}'";
		$row = mysql_query($sql);

		while ($arr = mysql_fetch_array($row)) {
			$section[] = $arr;
		}


		//获取评论数据
		$list = $model->findHaveCoditions("api_disscuz",['pid'=>$id]);
		//获取课件资料api_text
		$text = $model->findHaveCodition("api_text",['pid'=>$id]);

		$course = $model->findHaveCodition("api_course",['id'=>$id]);

		$json['disscuz'] = $list;
		$json['section'] = $section;
		$json['text'] = $text;

		$json['course'] = $course;

		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}

	public function getSectionContent(){
		//得到某一个课程某一章的具体的内容[文字类]
		$model = new Model();
		$courseid = $_POST['courseid'];
		$section = $_POST['section'];//章节
		$user = $_POST['user'];//用户名
		$list = $model->findHaveCodition("api_coursetextresource",['pid'=>$courseid,'section'=>$section]);
		
		/*判断用户是否选课了，如果选了那么 更新进度，如果没有 那么加入进去*/

		$stucou = $model->findHaveCodition("api_stucou",['user'=>$user,'courseid'=>$courseid]);

		if($stucou == null){
			//没有选课，加入进去
			$model->save(['user'=>$user,'courseid'=>$courseid,'pro'=>$section,'submit'=>'submit'],"api_stucou");
		}else{
			//选了，更新进度
			$id = $stucou['id'];
			$sql = "update api_stucou set pro='{$section}' where id='{$id}'";
			mysql_query($sql);
		}

		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getMyChioesCourse(){
		//查看到用户的个人选课信息
		$user = $_POST['user'];
		$sql = "select * from api_stucou,api_course where api_stucou.user='{$user}' and api_stucou.courseid=api_course.id";
		$row = mysql_query($sql);

		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}

		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getScoreInfo(){
		//获取到我的考试记录
		$sql = "select * from api_score";
		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}


	public function getMyScoreInfo(){
		//获取到我的考试记录
		$user = $_GET['user'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_score",['user'=>$user]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getMyCourseAndScore(){
		//课程考试页面的数据获取
		$user = $_POST['user'];
		 $model = new Model();
		// $list = $model->findHaveCoditions("api_stucou",['user'=>$user]);
		$sql = "select api_stucou.courseid,api_course.name,api_course.img,api_course.type from api_stucou,api_course where api_stucou.user='{$user}' and api_stucou.courseid=api_course.id";
		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}

		for($i = 0; $i<count($list); $i++){
			//遍历每一个课程里面是否考试
			$is = $model->findHaveCodition("api_score",['courseid'=>$list[$i]['courseid'],'user'=>$user]);
			if($is != null){
				$list[$i]['istest'] = "yes";
			}else{
				$list[$i]['istest'] = "no";
			}
		}

		echo json_encode($list,JSON_UNESCAPED_UNICODE);

	}

	public function getTextVideoList(){
		//获取到文字类和视频类的课程列表
		$model = new Model();

		$list = $model->findHaveCoditions("api_course",['1'=>'1']);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}


	public function sendMessage(){
		//发布评论
		//$data = data("Y-m-d H:i:s",time());
		$_POST['time'] = date("Y-m-d H:i:s",time());
		$_POST['submit'] = 'submit';
		$model = new Model();
		$id = $model->save($_POST,"api_disscuz");
		echo $id;
	}

	public function pullDisscuz(){
		$id = $_POST['id'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_disscuz",['pid'=>$id]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getTestSubject(){
		$id = $_POST['id'];
		$model = new Model();
		$course = $model->findHaveCodition("api_course",['id'=>$id]);
		//echo $course['test'];
		$list = $model->findHaveCoditions("api_subject",['pid'=>$course['test']]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function saveScore(){
		$model = new Model();
		//查询用户类型
		$type = $model->findHaveCodition("api_user",['username'=>$_POST['user']]);
		$_POST['usertype'] = $type['type'];
		$_POST['time'] = date("Y-m-d H:i:s",time());
		$_POST['submit'] = "submit";
		$id = $model->save($_POST,'api_score');
		if($id>0){
			echo "suc";
		}else{
			echo "fail";
		}
	}


	public function getMyScoreRecoreList(){
		//获取我的考试记录
		$user = $_POST['user'];
		$sql = "select api_score.score,api_course.name,api_course.type,api_course.img from api_score,api_course where api_score.user='{$user}' and api_score.courseid=api_course.id";

		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function searchScore(){
		//搜索课程
		$key = $_POST['key'];

		$sql = "select * from api_course where name like '%{$key}%'";
		
		$row = mysql_query($sql);

	while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}
		echo json_encode($list,JSON_UNESCAPED_UNICODE);

	}

	public function getUserType(){
		//获取用户类型
		$model = new Model();
		//查询用户类型
		$list = $model->findHaveCoditions("api_usertype",['1'=>'1']);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);

	}


	public function sendCode(){
		//接收用户的phone 生成验证码
		$code = rand(1000,9999);
		$phone = $_POST['phone'];
		$model = new Model();
		$mycode = $model->findHaveCodition("api_code",['phone'=>$phone]);

		if($mycode == null){
			//创建
			$model->save(['phone'=>$phone,'code'=>$code,'submit'=>'submit'],'api_code');
		}else{
			$sql = "update api_code set code='{$code}' where phone='{$phone}'";
			mysql_query($sql);
		}

		$sendUrl = 'http://v.juhe.cn/sms/send'; //短信接口的URL
  
		$smsConf = array(
		    'key'   => '35303516127e13e907475aa19edfcf29', //您申请的APPKEY
		    'mobile'    => $phone, //接受短信的用户手机号码
		    'tpl_id'    => '92778', //您申请的短信模板ID，根据实际情况修改
		    'tpl_value' =>"#code#=".$code //您设置的模板变量，根据实际情况修改
		);
		$content = $this->juhecurl($sendUrl,$smsConf,1); //请求发送短信
		 
		if($content){
		    $result = json_decode($content,true);
		    $error_code = $result['error_code'];
		    if($error_code == 0){
		        //状态为0，说明短信发送成功
		        $json['send_statu'] = "success";
		    }else{
		        //状态非0，说明失败
		        $msg = $result['reason'];
				echo $msg;
		        $json['send_statu'] = "error";
		    }
		}else{
		    //返回内容异常，以下可根据业务逻辑自行修改
		    $json['send_statu'] = "error";
		}
			echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}


		/**
	 * 请求接口返回内容
	 * @param  string $url [请求的URL地址]
	 * @param  string $params [请求的参数]
	 * @param  int $ipost [是否采用POST形式]
	 * @return  string
	 */
  	public function juhecurl($url,$params=false,$ispost=0){

	    $httpInfo = array();
	    $ch = curl_init();
	    curl_setopt( $ch, CURLOPT_HTTP_VERSION , CURL_HTTP_VERSION_1_1 );
	    curl_setopt( $ch, CURLOPT_USERAGENT , 'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22' );
	    curl_setopt( $ch, CURLOPT_CONNECTTIMEOUT , 30 );
	    curl_setopt( $ch, CURLOPT_TIMEOUT , 30);
	    curl_setopt( $ch, CURLOPT_RETURNTRANSFER , true );
	    if( $ispost )
	    {
	        curl_setopt( $ch , CURLOPT_POST , true );
	        curl_setopt( $ch , CURLOPT_POSTFIELDS , $params );
	        curl_setopt( $ch , CURLOPT_URL , $url );
	    }
	    else
	    {
	        if($params){
	            curl_setopt( $ch , CURLOPT_URL , $url.'?'.$params );
	        }else{
	            curl_setopt( $ch , CURLOPT_URL , $url);
	        }
	    }
	    $response = curl_exec( $ch );
	    if ($response === FALSE) {
	        //echo "cURL Error: " . curl_error($ch);
	        return false;
	    }
	    $httpCode = curl_getinfo( $ch , CURLINFO_HTTP_CODE );
	    $httpInfo = array_merge( $httpInfo , curl_getinfo( $ch ) );
	    curl_close( $ch );
	    return $response;
	}

	public function reigster(){
		//注册
		$code = $_POST['code'];
		$phone = $_POST['phone'];

		//判断验证码是否正确
		$model = new Model();
		$re = $model->findHaveCodition("api_code",['code'=>$code,'phone'=>$phone]);
		
		//判断用户输入的学号是否正确
		
		$nore = $model->findHaveCodition("api_sutdentinfo",['no'=>$_POST['no']]);
		
		if($nore == null){
			$json['reigster_is_suc'] = 'success';
			$json['error'] = "noerror";
			echo json_encode($json,JSON_UNESCAPED_UNICODE);
			exit;
		}

		if($re == null){
			$json['reigster_is_suc'] = 'error';
			$json['error'] = "code_wring";
		}else{
			//存入
			$times = date("Y-m-d H:i:s",time());
			$save = $model->save(['username'=>$_POST['phone'],'pwd'=>$_POST['pwd'],'type'=>$_POST['type'],'addtime'=>$times,'no'=>$_POST['no'],'submit'=>'submit'],'api_user');
			$json['reigster_is_suc'] = 'success';
			$json['error'] = "null";
		}

		echo json_encode($json,JSON_UNESCAPED_UNICODE);

	}

	public function bindStuInfo(){
		//绑定学生的个人信息
		$user = $_GET['user'];
		$no = $_GET['no'];
		$model = new Model();
		$student = $model->findHaveCodition("api_student",['no'=>$no]);
		if(!$student){
			$json['statu'] = "error";
			$json['error'] = "notfind";
		}else if($student['isbind'] != "0"){
			$json['statu'] = "error";
			$json['error'] = "alreadbind";
		}else{
			//进行绑定
			$sql = "update api_student set isbind=1,binduser='{$user}' where no='{$no}'";
			mysql_query($sql);
			$json['statu'] = "success";
			$json['error'] = "null";
		}
		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}

	public function getCourseList(){
		//进入首页，获取课程信息
		$model = new Model();
		$list = $model->findHaveCoditions("api_course_delegat",["type"=>"'计算机'"]);
		$json['computer'] = $list;
		$list = $model->findHaveCoditions("api_course_delegat",["type"=>"'文史类'"]);
		$json['history'] = $list;
		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}

	public function getContentCourse(){
		//进入某一门课程，
		$user = $_POST['user'];
		$courseid = $_POST['courseid'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_section_item",['belong_course'=>$courseid]);
		for($i = 0; $i<count($list); $i++){
			$sectionid = $list[$i]['id'];
			$smallItem = $model->findHaveCoditions("api_small_sction_item",['sid'=>$sectionid,'status' => '1']);

			
			// 获取 章节下对应的小节
			for($j = 0; $j<count($smallItem); $j++){
				$read = $model->findHaveCodition("api_learning_recorder",['user'=>$user,'belong_section'=>$smallItem[$j]['id']]);
				if($read){
					$smallItem[$j]['isread'] = "read";
				}else{
					$smallItem[$j]['isread'] = "notread";
				}	
			}
			

			$list[$i]['smallsction'] = $smallItem;
			
		}

		$coursename = $model->findHaveCodition("api_course_delegat",['id'=>$courseid]);
		$json['coursename'] = $coursename['coursename'];
		$json['section'] = $list;
		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}

	public function getContentCourseA(){
		//进入某一门课程，
		$courseid = $_GET['courseid'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_section_item",['belong_course'=>$courseid]);
		for($i = 0; $i<count($list); $i++){
			$sectionid = $list[$i]['id'];
			$smallItem[] = $model->findHaveCoditions("api_small_sction_item",['sid'=>$sectionid]);
		}

		echo json_encode($smallItem,JSON_UNESCAPED_UNICODE);
	}

	public function changeLearning(){
		//更新学习人数标记用户已读该小节
		$user = $_POST['user'];
		$sectionid = $_POST['id'];
		$sql = "update api_section_item set learning=learning+1 where id='{$sectionid}'";
		mysql_query($sql);
		$model = new Model();
		$model->save(['belong_section'=>$sectionid,'user'=>$user,'submit'=>"submit"],"api_learning_recorder");
		$json['statu'] = "success";
		echo json_encode($json,JSON_UNESCAPED_UNICODE);
	}


	public function getSubject(){
		//获取每个小节的测试题目
		$id = $_POST['id'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_subject",['pid'=>$id]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getInterlocutionList(){
		//获取问答列表
		$id = $_POST['id']; //课程id
		$model = new Model();
		$list = $model->findHaveCoditions("api_interlocution",['cid'=>$id]);
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function getInterlocutionDetile(){
		//获取某一条问答的详情
		$id = $_POST['id']; //问题id
		$model = new Model();
		$header = $model->findHaveCodition("api_interlocution",['id'=>$id]);

		//查询回答列表
		$list = $model->findHaveCoditions("api_interlocution_reply",['pid'=>$id]);

		$json['header'] = $header;
		$json['list'] = $list;
		echo json_encode($json,JSON_UNESCAPED_UNICODE);	
	}


	public function putQuestion(){
		//发出一个提问
		$model = new Model();
		$_POST['submit'] = "submit";
		$id = $model->save($_POST,"api_interlocution");
		if($id>0){
			$json['statu'] = 'success';
		}else{
			$json['statu'] = 'error';
		}
		echo json_encode($json,JSON_UNESCAPED_UNICODE);	
	}

	public function replyQuestion(){
		//回答某一个问题
		$model = new Model();
		$_POST['submit'] = "submit";
		$id = $model->save($_POST,"api_interlocution_reply");
		if($id>0){
			$json['statu'] = 'success';
		}else{
			$json['statu'] = 'error';
		}
		echo json_encode($json,JSON_UNESCAPED_UNICODE);	
	}

	public function updateLearningInfo(){
		//更新某一个小节具体的学习用户是谁  api_leaning_infos
		$model = new Model();
		$re = $model->findHaveCodition('api_leaning_infos',['user'=>$_POST['user']]);
		$times = date("Y-m-d h:i:s",time());
		$_POST['times'] = $times;
		$_POST['submit'] = "submit";
		if($re != null){
			$json['statu'] = 'success';
		}else{
			$insert = $model->save($_POST,'api_leaning_infos');
			if($insert >0){
					$json['statu'] = 'success';
			}else{
				$json['statu'] = 'error';	
			}
		}
		
		echo json_encode($json,JSON_UNESCAPED_UNICODE);	
	
	}
	
	public function recovderSmallItemTest(){
			//记录每一个小节测试的学生信息  api_small_test
		$sid = $_POST['sid'];
		$user = $_POST['user'];
		$times = date("Y-m-d h:i:s",time());
		//判断用户是否已经记录
		$_POST['time'] = $times;
		$_POST['submit'] = "submit";

		$model = new Model();
		$re = $model->findHaveCodition('api_small_test',['user'=>$user,'sid'=>$sid]);
		if($re != null){
			$json['statu'] = 'success';
		}else{
			$insert = $model->save($_POST,'api_small_test');
			if($insert >0){
					$json['statu'] = 'success';
			}else{
				$json['statu'] = 'error';	
			}
		}
	
		echo json_encode($json,JSON_UNESCAPED_UNICODE);	

	}

	public function getnewMessage(){
		//1.user id
		$id = $_GET['id'];
		$sql = "select * from api_message where target='{$id}' and isread=0 limit 1";
		$result = mysql_query($sql);
		$data = mysql_fetch_array($result);
		$sql = "update api_message set isread=1 where target='{$id}'";
		mysql_query($sql);
		echo json_encode($data,JSON_UNESCAPED_UNICODE);	
		
	}
	public function getUserScord(){
		//学生测试成绩
		$user = $_GET['user'];
		$sql = "select avg(score) as score, AVG( times ) as times,count(*) as people,version ,coursename,sid FROM api_score WHERE user = '{$user}' group by version";
		$result = mysql_query($sql);
		while ($arr = mysql_fetch_array($result)) {
		   $list[] = $arr;
	   }
		echo json_encode($list,JSON_UNESCAPED_UNICODE);	

	}

	 public function getUserScordDetail(){
		//学生测试成绩
		$course = $_GET['sid'];
		$sql = "select * FROM api_score WHERE user = '13058352164' and sid='{$course}'";
		$result = mysql_query($sql);
		while ($arr = mysql_fetch_array($result)) {
		   $list[] = $arr;
	   }
		echo json_encode($list,JSON_UNESCAPED_UNICODE);	

	}
	public function getAllScordDetail(){
	   //学生测试成绩
	   $sql = "select * FROM api_score WHERE user = '13058352164' ";
	   $result = mysql_query($sql);
	   while ($arr = mysql_fetch_array($result)) {
		  $list[] = $arr;
	  }
	   echo json_encode($list,JSON_UNESCAPED_UNICODE);	

   }
}

?>