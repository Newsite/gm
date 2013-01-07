<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>坏狼安全网-漂亮CSS Tables</title> 
</head> 
<style type="text/css"> 
/* CSS Document */ 

body { 
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 

a { 
color: #c75f3e; 
} 

#mytable { 
width: 700px; 
padding: 0; 
margin: 0; 
} 

caption { 
padding: 0 0 5px 0; 
width: 700px; 
font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
text-align: right; 
} 

th { 
font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
border-top: 1px solid #C1DAD7; 
letter-spacing: 2px; 
text-transform: uppercase; 
text-align: left; 
padding: 6px 6px 6px 12px; 
background: #CAE8EA no-repeat; 
} 
/*power by www.winshell.cn*/ 
th.nobg { 
border-top: 0; 
border-left: 0; 
border-right: 1px solid #C1DAD7; 
background: none; 
} 

td { 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
background: #fff; 
font-size:11px; 
padding: 6px 6px 6px 12px; 
color: #4f6b72; 
} 
/*power by www.winshell.cn*/ 

td.alt { 
background: #F5FAFA; 
color: #797268; 
} 

th.spec { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #fff no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #f5fafa no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #797268; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:11px;} 
body,td,th { 
font-family: 宋体, Arial; 
font-size: 12px; 
} 
</style> 
<body> 
<table>
	<caption>IPhone信息</caption>
	<tr>
		<th>IMEI</th><td>${info.imeiNumber!}</td>
	</tr>
	<tr>
		<th>型号描述</th><td>${info.partDescription!}</td>
	</tr>
	<tr>
		<th>产品版本</th><td>${info.productVersion!}</td>
	</tr>
	<tr>
		<th>序列号</th><td>${info.serialNumber!}</td>
	</tr>
	<tr>
		<th>首次解砖时间</th><td>${info.firstUnbrickDate!}</td>
	</tr>
	<tr>
		<th>最后解砖时间</th><td>${info.lastUnbrickDate!}</td>
	</tr>
	<tr>
		<th>是否维修过</th><td>${info.hasRepair!}</td>
	</tr>
	<tr>
		<th>ICCID</th><td>${info.iccID!}</td>
	</tr>
</table>

</body> 
</html>
