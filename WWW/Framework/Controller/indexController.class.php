<?php 

class indexControll extends Controller
{
	
	public function index(){
		$this->display("admin/login.html");
	}

	public function login(){
		$username = $_POST['username'];
		$pwd = $_POST['pwd'];
		$model = new Model();
		$re = $model->findHaveCodition("api_teacher",['name'=>$username,'password'=>$pwd]);
		if($re == null){
			AlertGoUrl("对不起您输入的账号或者密码错误","/");
		}else{
			$_SESSION['name'] = $username;
			$_SESSION['username'] = $username;
			$_SESSION['class'] = $re['class'];
			GoUrl("/index/home");
		}
	}

	public function home(){
		if($_SESSION['username'] == ""){
			AlertGoUrl("请先登录！","/");
		}

		$this->display("admin/indexcopy.html");
	}

	public function showUserList(){
		//显示用户的列表
		isLogin();
		$model = new Model();
		$classname = $_SESSION['class'];
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_user",['1'=>'1']);
		}else{
			$list = $model->findHaveCoditions("api_user",['class'=>"'{$classname}'"]);
		}
		for($i = 0; $i < count($list); $i++){
			$nos = $list[$i]['no'];
			$info = $model->findHaveCodition('api_sutdentinfo',['no'=>$nos]);
			$list[$i]['name'] = $info['name'];
			$list[$i]['classname'] = $info['classname'];
		}
		
		//$list = array(array('name'=>"asdnhjaksd"));
		$this->assign("list",$list);
		$this->display("admin/user-list.html");
	}


	public function showCourseList(){
		//显示课程管理
		isLogin();
		$model = new Model();
		$tname = $_SESSION['name'];
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_course_delegat",['1'=>"1"]);
		}else{
			$list = $model->findHaveCoditions("api_course_delegat",['belong'=>"'{$tname}'"]);
		}
		$this->assign("list",$list);
		$this->display("admin/course-list.html");
	}


	public function showaddCourse(){
		//添加课程
		isLogin();
		$this->display("admin/addcourse.html");
	}

	public function addCourse(){
		//处理保存课程的逻辑
		isLogin();
		$imgname = $_FILES['file']['name'];
		$tmp = $_FILES['file']['tmp_name'];
		$filename = time().rand(999999,9999999);
		
		
		 if(move_uploaded_file($tmp,BASE_PATH."courseimg/".$filename.".jpg")){
		       
		 	$_POST['imgpath'] = "/courseimg/".$filename.".jpg";
		 	$_POST['belong'] = '管理员';
		 	$_POST['submit'] = 'submit';
		 	$model = new Model();
		 	$id = $model->save($_POST,"api_course_delegat");
		 	if($id>=0){
		 		AlertGoUrl("添加成功！","/index/showaddCourse");
		 	}else{
		 		AlertGoUrl("添加失败","/index/showaddCourse");
		 	}

		    }else{
		        echo "上传失败";
		    }

	}


	public function showanswer(){
		//显示所有的答题模板answertemple-list.html
		$model = new Model();
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_answertemple",['1'=>'1']);
			
		}else{
			$tname = $_SESSION['name'];
			$list = $model->findHaveCoditions("api_answertemple",['belong'=>"'{$tname}'"]);
		}
		$this->assign("list",$list);
		$this->display("admin/answertemple-list.html");
	}

	public function showaddanswer(){
		//显示添加答题模板、
		isLogin();
		$this->display("admin/addanswertemple.html");
	}

	public function addTemple(){
			isLogin();
			//存入数据库
			$_POST['belong'] = $_SESSION['name'];
			$_POST['submit'] = 'submit';
			$model = new Model();
			$id = $model->save($_POST,"api_answertemple");
		 	if($id>=0){
		 		
		 		for($i=0; $i<$_POST['cout']; $i++){
		 			//循环添加
					 $key['pid'] = $id;
		 			$key['submit'] = "submit";
		 			$model->save($key,"api_subject");
		 		}
				AlertGoUrl("添加成功！","/index/showaddanswer");
		 	}else{
		 		AlertGoUrl("添加失败","/index/showaddanswer");
		 	}
	}


	public function setMatching(){
		//设置题目setanswertemple.html
		isLogin();
		$id = $_GET['id'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_subject",['pid'=>$id]);
		$this->assign("list",$list);
		$this->display("admin/setanswertemple.html");
	}
	public function showCouresforClassId(){
		//..
		$model = new Model();
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_course_delegat",["1"=>"1"]);
			
		}else{
			$tname = $_SESSION['name'];
			$list = $model->findHaveCoditions("api_course_delegat",["belong"=>"'{$tname}'"]);
		
		}
		$this->assign("list",$list);
		$classname = $_SESSION['class'];
		if($_SESSION['name'] == 'admin'){
			$data = $model->findHaveCoditions("api_user",['1'=>'1']);
		}else{
			$data = $model->findHaveCoditions("api_user",['class'=>"'{$classname}'"]);
		}
		for($i = 0; $i < count($data); $i++){
			$nos = $data[$i]['no'];
			$info = $model->findHaveCodition('api_sutdentinfo',['no'=>$nos]);
			$data[$i]['name'] = $info['name'];
			$data[$i]['classname'] = $info['classname'];
		}
		// $sql = "SELECT username
		// FROM `api_user`";
		// $result = mysql_query($sql);
		// while($r = mysql_fetch_assoc($result)){
		// 	$data[] = $r;
		// }
		//select_bat
		$this->assign("cl",$data);
		$this->display("admin/select_class_id.html");
	}
	public function drawClassId(){
		$_SESSION['courseider'] = $_GET['courseider'];
		$_SESSION['classnameer'] = $_GET['classnameer'];
		$_SESSION['typer'] = $_GET['typer'];
		$typer = $_GET['typer'];
		if($_GET['typer']==1){
		$courseid = $_GET['courseider'];
		$classname =  $_GET['classnameer'];
		$sql = "SELECT * FROM api_score WHERE coursename='{$courseid}' AND user='{$classname}'";
		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;

		}
		$this->assign("list",$list);
		$this->display("admin/score-list.html");
	}else{
		$courseid = $_GET['courseider'];
		$sql = "SELECT * FROM api_score WHERE coursename='{$courseid}'";
		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;

		}
		$this->assign("list",$list);
		$this->display("admin/score-list.html");
	}	}
	public function changeSubject(){
		//修改题目
		isLogin();
		//var_dump($_POST);
		$size = count($_POST['title']);
		for($i=0; $i<$size; $i++){
			//存入数据库中
			$title = $_POST['title'][$i];
			$a = $_POST['a'][$i];
			$b = $_POST['b'][$i];
			$c = $_POST['c'][$i];
			$d = $_POST['d'][$i];
			$trueoption = $_POST['trueoption'][$i];
			$id = $_POST['id'][$i];

			$sql = "update api_subject set title='{$title}',a='{$a}',b='{$b}',c='{$c}',d='{$d}',trueoption='{$trueoption}' where id='{$id}'";
			
			mysql_query($sql);
		}

		AlertGoUrl("保存成功","/index/showanswer");
	}

	public function setBindTemple(){
		//给课程绑定考试题目setbindtemple.html
		isLogin();

		$id = $_GET['id'];

		$model = new Model();
		$mytemplename = $model->findHaveCodition("api_small_sction_item",['id'=>$id]);
		$list = $model->findHaveCoditions("api_answertemple",['1'=>'1']);		

		$name = "未设置";
		for($i = 0; $i< count($list); $i++){
			if($mytemplename['testid'] == $list[$i]['id']){
				$name = $list[$i]['name'];
			}
		}

		$this->assign("name",$name);
		$this->assign("id",$id);
		$this->assign("list",$list);
		$this->display("admin/setbindtemple.html");
	}

	public function setBind(){
		//绑定课程 和考试题目
		isLogin();
		$testid = $_POST['temple'];
		$id = $_POST['myid'];

		$sql = "update api_small_sction_item set testid='{$testid}' where id='{$id}'";
		
		mysql_query($sql);

		echo "<script>alert('设置成功！'); history.go(-1);</script>";
	}

	public function showsetCourseResource(){
		//setcoursereource.html  设置课程资源【文字 视频】
		isLogin();
		$model = new Model();

		$list = $model->findHaveCoditions("api_course",['1'=>'1']);
		$this->assign("list",$list);
		$this->display("admin/setcoursereource.html");
	}


	public function setTextCourseResource(){
		//设置文字类的课程资源[api_coursetextresource]数据表settextresource.html
		isLogin();
		$id = $_GET['id'];
		$section = $_GET['section'];
		$model = new Model();
		
		$list = $model->findHaveCoditions("api_coursetextresource",['pid'=>$id]);
		//var_dump($list);
		if($list == null){
			//创建数据
			for($i = 0; $i<$section; $i++){
				$model->save(['pid'=>$id,'content'=>'null','section'=>($i+1),'submit'=>'submit'],"api_coursetextresource");
			}

		$list = $model->findHaveCoditions("api_coursetextresource",['pid'=>$id]);

		}


		$this->assign("list",$list);
		$this->display("admin/settextresource.html");
	}

	public function setViedoCourseResource(){
		//设置视频类课程资源
		isLogin();
		$id = $_GET['id'];
		$section = $_GET['section'];
		$model = new Model();
		
		$list = $model->findHaveCoditions("api_courseviedoresource",['pid'=>$id]);
		//var_dump($list);
		if($list == null){
			//创建数据
			for($i = 0; $i<$section; $i++){
				$model->save(['pid'=>$id,'section'=>($i+1),'submit'=>'submit'],"api_courseviedoresource");
			}

		$list = $model->findHaveCoditions("api_courseviedoresource",['pid'=>$id]);

		}

		$this->assign("courseid",$id);
		$this->assign("list",$list);
		$this->display("admin/setviedoresource.html");
	}


	public function changeTextResource(){
		//修改【保存】资源
		isLogin();
		$size = count($_POST['title']);

		for ($i=0; $i < $size; $i++) { 
			$id = $_POST['id'][$i];
			$title = $_POST['title'][$i];
			$content = $_POST['content'][$i];
			$sql = "update api_coursetextresource set sectiontitle='{$title}',content='{$content}' where id='{$id}'";
			mysql_query($sql);
		}

		AlertGoUrl("保存成功！","/index/showsetCourseResource");

	}

	public function saveViedo(){
		$imgname = $_FILES['file']['name'];
		
		$tmp = $_FILES['file']['tmp_name'];
		$filename = time().rand(999999,9999999).".mp4";
		
		 if(move_uploaded_file($tmp,BASE_PATH."viedeo/".$filename)){
		 	$id = $_POST['id'];
		 	$title = $_POST['title'];

		 	$sql = "update api_courseviedoresource set sectiontitle='{$title}',path='{$filename}' where id='{$id}'";
		 	mysql_query($sql);	


		 	AlertGoUrl("上传成功！","/index/setViedoCourseResource?id=".$_POST['courseid']);
		 }else{
		 	AlertGoUrl("视频上传失败，请检查网络，或者视频文件","/index/setViedoCourseResource?id=".$_POST['courseid']);
		 }

	}

	public function showCoures(){
		//..
		$model = new Model();
		$list = $model->findHaveCoditions("api_course_delegat",["1"=>"1"]);
		$this->assign("list",$list);
		$sql = "SELECT DISTINCT class
		FROM `api_user`";
		$result = mysql_query($sql);
		while($r = mysql_fetch_assoc($result)){
			$data[] = $r;
		}
		//select_bat
		$this->assign("cl",$data);
		$this->display("admin/select_info.html");
	}
	public function showbat(){
		//..
		$model = new Model();
		$sql = "SELECT DISTINCT class
		FROM `api_score`";
		$result = mysql_query($sql);
		while($r = mysql_fetch_assoc($result)){
			$data[] = $r;
		}
		//select_bat
		$this->assign("cl",$data);
		$this->display("admin/select_bat.html");
	}
	public function showSelect(){
		//..
		$model = new Model();
		$list = $model->findHaveCoditions("api_course_delegat",["1"=>"1"]);
		$this->assign("list",$list);
		$sql = "SELECT DISTINCT class
		FROM `api_user`";
		$result = mysql_query($sql);
		while($r = mysql_fetch_assoc($result)){
			$data[] = $r;
		}
		
		$this->assign("cl",$data);
		$this->display("admin/select_info.html");
	}
	public function showscore(){
		//查看考试成绩
		isLogin();
		$model = new Model();
		if($_SESSION['name'] =='admin'){
			$list = $model->findHaveCoditions("api_score",['1'=>"1"]);
		}else{
			$tname = $_SESSION['name'];
			$sql = "select * from api_score,api_course_delegat where api_score.courseid=api_course_delegat.id and api_course_delegat.belong='{$tname}'";
			//echo $sql;
			$res = mysql_query($sql);
			while($r = mysql_fetch_array($res)){
				$list[] = $r;
			}
			//var_dump($list);
		}
		$this->assign("list",$list);
		$this->display("admin/score-list.html");
	}
	public function showScoreAnalyze(){
		//查看考试成绩分析
		isLogin();
		$model = new Model();
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_course_delegat",["1"=>"1"]);
			
		}else{
			$tname = $_SESSION['name'];
			$list = $model->findHaveCoditions("api_course_delegat",["belong"=>"'{$tname}'"]);
		}
		$this->assign("list",$list);
		$sql = "SELECT DISTINCT class
		FROM `api_user`";
		$result = mysql_query($sql);
		while($r = mysql_fetch_assoc($result)){
			$data[] = $r;
		}
		
		$this->assign("cl",$data);
		$this->display("admin/select_info.html");
	}

	public function drawGraph(){
		$_SESSION['courseid'] = $_GET['courseid'];
		$_SESSION['classname'] = $_GET['classname'];
		$this->display("admin/score-analyze-list.html");
		
	}


	public function showAnalyze(){
		//查看考试成绩分析
		isLogin();
		$courseid = $_SESSION['courseid'];
		$classname = $_SESSION['classname'];
		//$sql = "select * from api_score, api_user where api_score.courseid='{$coursid}' and api_score.user=api_user.username and api_user.class='{$classname}'";
		$sql = "select avg(score) as score,count(*) 
		as people,api_score.version,api_score.coursename,api_score.courseid from api_score, api_user
		 where api_score.courseid='{$courseid}' and api_score.user=api_user.username and api_user.class='{$classname}'
		  group by api_score.sid";
	
		$row = mysql_query($sql);
		while ($arr = mysql_fetch_array($row)) {
			$list[] = $arr;
		}
		echo json_encode($list,JSON_UNESCAPED_UNICODE);
	}

	public function deleteUser(){
		//删除用户
		isLogin();
		$id = $_GET['id'];
		$sql = "delete from api_user where id='{$id}'";
		mysql_query($sql);
		AlertGoUrl("删除成功","/index/showUserList");
	}

	public function showUsertype(){
		//查看用户类型列usertype-list.html
		isLogin();
		$model = new Model();
		$list = $model->findHaveCoditions("api_usertype",['1'=>"1"]);
		$this->assign("list",$list);
		$this->display("admin/usertype-list.html");
	}

	public function deleteUsertype(){
		//删除用户类型
		isLogin();
		$id = $_GET['id'];
		$sql = "delete from api_usertype where id='{$id}'";
		mysql_query($sql);
		AlertGoUrl("删除成功","/index/showUsertype");
	}

	public function showAddUsertype(){
		//显示添加用户类型adduserytpe.html
		isLogin();
		$this->display("admin/adduserytpe.html");
	}

	public function addUsertype(){
		//添加用户类型
		isLogin();
		$model = new Model();
		$_POST['time'] = date('Y-m-d h:i:s',time());
		$_POST['submit'] = 'submit';
		$model->save($_POST,"api_usertype");
		AlertGoUrl("添加成功！","/index/showUsertype");
	}

	public function showChangepwd(){
		//显示修改用户密码changepwd.html
		isLogin();
		$id = $_GET['id'];
		$model = new Model();
		$list = $model->findHaveCodition("api_user",['id'=>$id]);
		$this->assign("user",$list['username']);
		$this->assign("pwd",$list['pwd']);
		$this->display("admin/changepwd.html");

	}

	public function changePWD(){
		//修改密码
		isLogin();
		$user = $_POST['user'];
		$newpwd = $_POST['pwd'];
		$sql = "update api_user set pwd='{$newpwd}' where username='{$user}'";
		mysql_query($sql);
		AlertGoUrl("修改用户密码成功","/index/showUserList");
	}

	public function showChioesCourse(){
		//设置课程小节。首先选择 课程
		//chioes-course.html
		$model = new Model();
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_course_delegat",["1"=>"1"]);
			
		}else{
			$tname = $_SESSION['name'];
			$list = $model->findHaveCoditions("api_course_delegat",["belong"=>"'{$tname}'"]);
		
		}
		$this->assign("list",$list);
		$this->display("admin/chioes-course.html");

	}
	public function showApiInterlocution(){
		//交流讨论
		$model = new Model();
		if($_SESSION['name'] == 'admin'){
			$list = $model->findHaveCoditions("api_interlocution",["1"=>"1"]);		
			
		}else{
			$tname = $_SESSION['name'];
			$sql = "select * from api_interlocution,api_course_delegat where api_interlocution.cid=api_course_delegat.id and api_course_delegat.belong='{$tname}'";
			$res = mysql_query($sql);
			while($row = mysql_fetch_array($res)){
				$list[] = $row;
			}
		}
		$this->assign("list",$list);
		$this->display("admin/api_inter.html");

	}

	public function showcommitment(){
		//显示回复某条消息的页面
		$id = $_GET['id'];
		$content = $_GET['content'];
		$this->assign("id",$id);
		$this->assign("content",$content);
		$this->display("admin/teacher-mark.html");
		
	}

	public function addMark(){
		$user = "admin";
		$content = $_POST['content'];
		$pid = $_POST['id'];
		$time = date("Y:m:d H:m:i",time());
		$sql = "INSERT INTO api_interlocution_reply(`content`,`time`,`user`,`pid`) VALUE('{$content}','{$time}','{$user}','{$pid}')";
		mysql_query($sql);
		//echo $sql;
		AlertGoUrl("评论成功","/index/showApiInterlocution");
	}

	public function showCourseSctionTitle(){
		//根据课程，显示各个章节对应的标题，小节数量 [可执行 修改操作]
		$id = $_GET['courseid'];
		$model = new Model();
		$l = $model->findHaveCodition("api_course_delegat",['id'=>$id]);
		//根据id  查询  [若不存在 就创建]
		$size = $l['seciton'];
		$sectionlist = $model->findHaveCoditions("api_section_item",['belong_course'=>$id]);
		if($sectionlist == null){
			for($i = 1; $i <= $size; $i++){
				//逐个进行创建
				$model->save(['belong_course'=>$id,'section_title'=>"",'section_sequence'=>$i,"learning"=>0,'submit'=>"submit"],"api_section_item");
			}
			$sectionlist = $model->findHaveCoditions("api_section_item",['belong_course'=>$id]);
		}
		
		//将数据展示
		$this->assign("list",$sectionlist);
		$this->assign("courseid",$id);
		$this->display("admin/section-list.html");

	}

	public function changeSectionInfo(){
		//修改标题 和小节数量
		$id = $_POST['id'];
		$courseid = $_POST['courseid'];
		$title = $_POST['smallsectiontitle'];
		$count = $_POST['count'];

		$sql = "update api_section_item set section_title='{$title}',count='{$count}' where id='{$id}'";
		mysql_query($sql);

		AlertGoUrl("修改成功！", "/index/showCourseSctionTitle?courseid=".$courseid);
	}

	public function setSmallItem(){
		$id = $_GET['id'];
		$count = $_GET['count'];
		//查询small表
		//api_small_sction_item
		$model = new Model();
		$list = $model->findHaveCoditions("api_small_sction_item",['sid'=>$id]);
		if($list == null){
			//创建小节内容表
			for($i = 1; $i <= $count; $i++){
				$model->save(['sid'=>$id,'videopath'=>"null",'title'=>"",'testid'=>0,'viedo_length'=>0,'submit'=>"submit"],"api_small_sction_item");
			}
			$list = $model->findHaveCoditions("api_small_sction_item",['sid'=>$id]);
		}
		$this->assign("list",$list);
		$this->assign("sid",$id);
		$this->display("admin/smallsection-list.html");

	}

	public function setSmallItemResoure(){
		//设置小节的资源内容
		$imgname = $_FILES['file']['name'];
		$tmp = $_FILES['file']['tmp_name'];
		$filename = time().rand(999999,9999999).".mp4";

		$tsource = $_FILES['txtsource']['name'];
		$txtsourcetemp = $_FILES['txtsource']['tmp_name'];
		$txtsource_filename = time().rand(999999,9999999).".rar";

		$sid = $_POST['sid'];
		 if(move_uploaded_file($tmp,BASE_PATH."viedeo/".$filename)){
			 if(move_uploaded_file($txtsourcetemp,BASE_PATH."txtsource/".$txtsource_filename)){
				$id = $_POST['id'];
				$title = $_POST['title'];
				$viedo_length = $_POST['viedo_length'];
				$paths = "/viedeo/".$filename;
			   	$user = $_SESSION['username'];
				$sql = "update api_small_sction_item set title='{$title}',videopath='{$paths}',user='常亚萍',viedo_length='{$viedo_length}',txtsource='{$txtsource_filename}' where id='{$id}'";
				mysql_query($sql);	
			   
			    $this->addMessage($id);
				AlertGoUrl("上传成功！","/index/setSmallItem?id=".$sid);
			 }else{
				AlertGoUrl("文件资源上传失败！","/index/home");
			 }
		 	
		 }else{
		 	AlertGoUrl("视频上传失败，请检查网络，或者视频文件","/index/home");
		 }
	}

	public function addMessage($id){
		$sql = "select * from api_small_sction_item where id='{$id}'";
		$r = mysql_query($sql);
		$data = mysql_fetch_array($r);
		$name = $data['user'];
		$classinfo = $data['title'];
		$sql = "select * from api_teacher where name='{$name}'";
		$r = mysql_query($sql);
		$data = mysql_fetch_array($r);
		$classname = $data['class'];
		$sql = "select * from api_user where class='{$classname}'";
		$r = mysql_query($sql);
		while($result = mysql_fetch_array($r)){
			$trage = $result['id'];
			$sql = "insert into api_message(target,teacher,coursename,message) value('{$trage}','{$name}','{$classinfo}','同学们快来听课哟！')";
			mysql_query($sql);
		}
	}
	
	public function vedioCheck(){
		//check-list.html
		if($_SESSION['type'] != 0){
			AlertGoUrl("您不是最高管理员，不可以查看此处！","/index/home");
			exit;
		}
		$model = new Model();
		$list = $model->findHaveCoditions("api_small_sction_item",['1'=>"1"]);
		$this->assign("list",$list);
		$this->display("admin/check-list.html");
	}
	
	public function pass(){
		//通过审核
		$id = $_GET['id'];
		$sql = "update api_small_sction_item set status=1 where id='{$id}'";
		mysql_query($sql);
		AlertGoUrl("状态更新成功","/index/vedioCheck".$sid);

	
	}
	
	public function importstu(){
		//批量导入学生
		$this->display("admin/upload.html");
	}
	
	public function receverInfo(){
		//接受上传的文件
		include './Excel/reader.php';
		$data = new Spreadsheet_Excel_Reader();

		$dir=dirname(__FILE__);                       //获取当前脚本的绝对路径
		$dir=str_replace("//","/",$dir)."/";
		$model = new Model();
		$filename='uploadFile.xls'; //可以定义一个上传后的文件名称
		$result= move_uploaded_file($_FILES['file']['tmp_name'],$dir.$filename);//假如上传到当前目录下
		if($result){
			echo "文件上传成功！<br>";
			$data->setOutputEncoding('utf-8');//设置在页面中输出的编码方式,而不是utf8
			$data->read("C:\www\Framework\Controller\uploadFile.xls");  //读取上传到当前目录下名叫$filename的
			error_reporting(E_ALL ^ E_NOTICE);
			for ($i = 1; $i <= $data->sheets[0]['numRows']; $i++)
			{
				$no = $data->sheets[0]['cells'][$i][1];
				$name = $data->sheets[0]['cells'][$i][2];
				$classinfo =  $data->sheets[0]['cells'][$i][3];
				echo "导入学生：";
				echo $name."，学号：";
				echo $no."，班级：";
				echo $classinfo;
				
				$id = $model->save(['name'=>$name,'no'=>$no,'classname'=>$classinfo,'submit'=>"submit"],'api_sutdentinfo');
				if($id > 0 ){
					echo "------------------导入数据库成功！";
				}else{
					echo "------------------导入数据库失败！";
				}
				echo "<br>";
			}
		}
	}
	
	public function showCourseInfo(){
		//显示当前全部课程  showSmallSection
		$model = new Model();
		$list = $model->findHaveCoditions("api_course_delegat",['1'=>'1']);
		$this->assign("list",$list);
		
		$this->display("admin/learning_info.html");
	}
	
	public function showSmallSection(){
		//显示本课程的全部小节 belong_course
		$model = new Model();
		$list = $model->findHaveCoditions("api_section_item",['belong_course'=>$_GET['id']]);
		//查找小节数 api_small_sction_item
		for($i = 0; $i < count($list); $i++){
			$id = $list[$i]['id'];
			$smallitem = $model->findHaveCoditions('api_small_sction_item',['sid'=>$id]);
			$list[$i]['smalltitle'] = $smallitem;			
		}
		
		//small_leanring.html
		$this->assign("list",$list);

		$this->display("admin/small_leanring.html");
		
	}
	
	//showSmallContents
	
	public function showSmallContents(){
		//显示某一个小节学习的具体的人是谁  learning_count.html 统计
		$id = $_GET['id'];
		$model = new Model();
		$list = $model->findHaveCoditions("api_leaning_infos",['sid'=>$id]);
		
		$size = count($list);
		$this->assign("list",$list);
		$this->assign("size",$size);

		$this->display("admin/learning_count.html");
	
	}
	
	public function showSmallTestCount(){
		$id = $_GET['id'];
		//api_small_test   test_cout
		$model = new Model();
		$list = $model->findHaveCoditions("api_small_test",['sid'=>$id]);
		$size = 0;
		for($i = 0; $i < count($list); $i++){
			if($list[$i]['score'] >= 60){
				$size = $size+1;
			}		
		}
		$rat = $size / count($list);
		
		$this->assign("list",$list);
		$this->assign("count",count($list));
		$this->assign("size",$size);
		$this->assign("rate",($rat*100)."%");

		$this->display("admin/test_cout.html");

	}
	public  function addSore()
	{
		$model = new Model();
		$user = $model->findHaveCoditions("api_user",['1'=>"1"]);
		$sql = "select * from api_small_sction_item where sid=104 or sid=106 or sid=107 or sid=108";
		$r = mysql_query($sql);
		while($row = mysql_fetch_array($r)){
			$course[] = $row;
		}
		for($i = 0; $i<2; $i++){
				$ul = count($user);
				$cl = count($course);
				$uindex = mt_rand(0,$ul) + 1;
				$cindex =  mt_rand(3,$cl) + 1;

				$u = $user[$uindex-1]['username'];
				$title = $course[$cindex - 1]['title'];
				if($title == ""){
					$title = "ListView控件";
				}
				if($u == ""){
					$u = "13672660891";
				}
				$t = mt_rand(280,350);
				$score = mt_rand(95,100);
				$sql = "insert into api_score(coursename,score,user,time,usertype,courseid,times,version,sid) 
				value('智能穿戴设备开发基础','{$score}','{$u}',
				'2018-10-11 15:32:11','stu','5','{$t}','XML序列化和解析','1062')";
				mysql_query($sql);
		}
	}

}

?>