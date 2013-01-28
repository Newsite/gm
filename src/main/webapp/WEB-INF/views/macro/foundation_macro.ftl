<!-- 为foundation提供的宏 -->
<#macro pagination url current total macro_id="" >
<ul id=${macro_id} class="pagination">
	<li class="arrow <#if current==1>unavailable</#if>" ><a href="<#if current!=1>${url}?pageNo=${current-1}</#if>">&laquo;</a></li>
	
	<#list 1..total as t>
		<#assign offset=(current-t)/>
		<#assign lefthellip=false/>
		<#assign righthellip=false/>
		<#if t==1>
			<li <#if t==current>class="current"</#if>><a href="${url}?pageNo=${t}">${t}</a></li>
			<#if (current>2)><li class="unavailable"><a href="">&hellip;</a></li></#if>
		<#elseif ((offset<3)&&(offset>-3)) >
			<li <#if t==current>class="current"</#if>><a href="${url}?pageNo=${t}">${t}</a></li>
		<#elseif t==total>
			<#if (current<(total-1)) ><li class="unavailable"><a href="">&hellip;</a></li></#if>
			<li <#if t==current>class="current"</#if>><a href="${url}?pageNo=${t}">${t}</a></li>
		</#if>
	</#list>
  	<li class="arrow  <#if current==total>unavailable</#if>"><a href="<#if current!=total>${url}?pageNo=${current+1}</#if>">&raquo;</a></li>
</ul>
</#macro>