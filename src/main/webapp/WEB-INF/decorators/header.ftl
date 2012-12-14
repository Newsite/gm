<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<#import "../views/macro/StaticFileHelper.ftl" as helper>
<@helper.tagStaticResource filename="core.css"/> 
<@helper.tagStaticResource filename="jquery-1.8.3.js"/>
<@helper.tagStaticResource filename="easyui/easyloader.js"/>
${head}
<script>
	application_root='${base}';
</script>
<@helper.tagStaticResource filename="core.js"/>
</head>
<body>
<div class="top">
	<div class="top_center">
		<h1 ><a href="${base}">GuitarMe</a></h1>
		<input id="searchBar"></input>
		<div id="searchTypeList" style='width:120px'>
		    <div data-options="name:'0',iconCls:'icon-ok'">全部</div>
		    <div data-options="name:'1'">曲名</div>
		    <div data-options="name:'2'">歌手</div>
		</div> 
	</div>
</div>
