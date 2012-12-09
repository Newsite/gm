<#-- 加载本地的静态文件 -->
<#macro tagStaticResource filename=''>
<#if filename?matches(".+\\.js(\\?\\S+)?")>
<script src="${base}/static/js/${filename}"></script>
<#elseif filename?matches('.+\\.css(\\?\\S+)?')>
<link href="${base}/static/css/${filename}" type="text/css" rel="stylesheet"></link>
</#if>   
</#macro>
