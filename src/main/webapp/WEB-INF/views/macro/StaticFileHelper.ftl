<#-- 加载本地的静态文件 -->
<#macro tagStaticResource filename=''>
<#if !(base??)><#assign base=rc.getContextPath()/></#if>
<#if filename?matches(".+\\.js(\\?\\S+)?")>
<script src="${base}/static/javascripts/${filename}"></script>
<#elseif filename?matches('.+\\.css(\\?\\S+)?')>
<link href="${base}/static/stylesheets/${filename}" type="text/css" rel="stylesheet"></link>
</#if>   
</#macro>
