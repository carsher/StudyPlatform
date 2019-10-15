<?php
/* Smarty version 3.1.30, created on 2019-10-12 08:52:40
  from "F:\phpStudy\WWW\templates\admin\score-analyze-list.html" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30',
  'unifunc' => 'content_5da19458b15401_74334611',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'ba17e928dbbeb46c265539b2847e85d9050ed1c2' => 
    array (
      0 => 'F:\\phpStudy\\WWW\\templates\\admin\\score-analyze-list.html',
      1 => 1570792032,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_5da19458b15401_74334611 (Smarty_Internal_Template $_smarty_tpl) {
?>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/html5shiv.js"><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/respond.min.js"><?php echo '</script'; ?>
>
<![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
<?php echo '<script'; ?>
 type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ><?php echo '</script'; ?>
>
<?php echo '<script'; ?>
>DD_belatedPNG.fix('*');<?php echo '</script'; ?>
>
<![endif]-->
    <title>成绩管理</title>
</head>

<body>

    <table class="table table-border table-bordered table-hover table-bg table-sort">
        <tbody>
            <tr class="text-c">
                <td>
                    <div class="page-container">
                        <div id="container" style="min-width:350px;height:400px"></div>
                    </div>
                </td>
                <td>
                    <div class="page-container">
                        <div id="container2" style="min-width:600px;height:550px"></div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <!--_footer 作为公共模版分离出去-->
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/layer/2.4/layer.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/static/h-ui/js/H-ui.min.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"><?php echo '</script'; ?>
>
    <!--/_footer 作为公共模版分离出去-->
    <!--请在下方写此页面业务相关的脚本-->
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/laypage/1.2/laypage.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript" src="/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"><?php echo '</script'; ?>
>
    <?php echo '<script'; ?>
 type="text/javascript">
        $(function () {
            var allStu = new Array;
            var categoriesStu = new Array;
            var j = 4;
            
            (function () {
                $.ajax({
                    type: 'GET',
                    url: '/index/showAnalyze',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data)
                        var oneArr = 0;
                        var twoArr = 0;
                        var threeArr = 0;
                        var fourArr = 0;
                        var firthArr = 0;
                        var arrer = [];
                        var type = [];
                        var people = [];
                        var seriesStu = [];

                        $.each(data, function (index, arr) {
                            arrer.push(arr.courseid)
                            people.push(arr.people);
                            if (arr.score < 60) {
                                oneArr += 1
                            } else if (arr.score >= 60 && arr.score <= 70) {
                                twoArr += 1
                            } else if (arr.score > 70 && arr.score <= 80) {
                                threeArr += 1
                            } else if (arr.score > 80 && arr.score <= 90) {
                                fourArr += 1
                            }else {
                                firthArr += 1
                            }
                            if (type.indexOf(arr.coursename)==-1) {
                                type.push(arr.coursename )
                            }
                        })
                        allStu.push(['小于60分占比', oneArr])
                        allStu.push(['60-70分占比', twoArr])
                        allStu.push(['70-80分占比', threeArr])
                        allStu.push(['80-90分占比', fourArr])
                        allStu.push(['大于90分占比', firthArr])
                        var lenCal = maxCountElement(arrer)[1]
                        for (var i = 0; i < lenCal ; i++) {
                            categoriesStu.push(data[i].version +　"，<br>考试人数：" + data[i].people)
                        }
                        for (var i = 0; i < people.length; i++) {
                            var dataer = [];
                            dataer = getDatatype(type[i], data)[0]
                            seriesStu.push({"name":type[i],"data":dataer,"options":{"user":getDatatype(type[i], data)[1] ,"people":people,"id":i}})
                        }
                        $('#container').highcharts({
                            chart: {
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false
                            },
                            title: {
                                text: '总体学生成绩统计情况'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        color: '#000000',
                                        connectorColor: '#000000',
                                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                                    }
                                }
                            },
                            series: [{
                                type: 'pie',
                                name: 'Browser share',
                                data: allStu
                            }]
                        });
                        var i =0;
                        Highcharts.chart('container2', {
                            title: {
                                text: '学生课程成绩折线图',
                                x: -20 //center
                            },
                            subtitle: {
                                text: '',
                                x: -20
                            },
                            xAxis: {
                                categories: categoriesStu
                            },
                            yAxis: {
                                title: {
                                    text: 'Score (分)'
                                },
                                plotLines: [{
                                    value: 0,
                                    width: 2,
                                    color: '#808080'
                                }]
                            },
                            tooltip: {
                                valueSuffix: '分',
                                pointFormatter: function() {
                                    console.log("................tooltip..................." + i )
                                    console.log("re:" + this.series.options.options.people[i])
                                    i++
                                    return '<span style="color: '+ this.series.color + '"></span>'+
                                    this.series.options.options.user + this.y
}
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'right',
                                verticalAlign: 'middle',
                                borderWidth: 0,
                                enabled:false,
                                
                            },
                            series: seriesStu   
                        });



                    },
                    error: function (data) {
                        console.log(data.msg);
                    },
                });
            })()

            function getDatatype(type, data) {
                var dataArr = [];
                var user = "";
                $.each(data, function (index, name) {
                    if (true) {
                        dataArr.push(parseInt(name.score))
                        console.log(name.people+"....................")
                        user =  "平均分："
                    }
                })
                return [dataArr,user];
            }

            function maxCountElement(arr) {
                var arrMap = new Map();
                var key = arr[0],
                    value = 1;
                arr.forEach((item, index) => {
                    if (arrMap.get(item) !== undefined) {
                        let num = arrMap.get(item);
                        arrMap.set(item, ++num);
                    } else {
                        arrMap.set(item, 1);
                    } if (arrMap.get(item) > value) {
                        key = item;
                        value = arrMap.get(item);
                    }
                });
                return [key, value];

            }



        });


    <?php echo '</script'; ?>
>
</body>

</html><?php }
}
