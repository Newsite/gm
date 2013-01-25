<#macro select source dicName="" checked="" width=200 selectorWidth=27>
	<select name=${dicName} style="display:none">
		<#list source?keys as key>
			<option <#if key==checked>selected <#assign currentValue=source[key]/></#if> value='${key}'>
				${source[key]}
			</option>
		</#list>
	</select> 
	<div class="custom dropdown">
		<a href="#" class="current" style="width:${width-selectorWidth}px;">${currentValue!}</a>
		<a href="#" class="selector" style="width:${selectorWidth}px;"></a>
		<ul>
			<#list source?keys as key>
				<li>${source[key]}</li>
			</#list>
		</ul>
	</div>
</#macro>


