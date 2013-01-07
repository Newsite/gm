<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<#import "../views/macro/StaticFileHelper.ftl" as helper>
<@helper.tagStaticResource filename="core.css"/> 
<@helper.tagStaticResource filename="jquery-1.8.3.js"/>
<@helper.tagStaticResource filename="easyui/easyloader.js"/>
<@helper.tagStaticResource filename="exam.css"/> 
${head}
<script>
	application_root='${base}';
</script>
