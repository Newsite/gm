<#macro ciyuWithPinyin ciyu>
<div class="ciyu-with-pinyin">
	<table>
		<tr class="ciyu-pinyin" align="center">
			<th>&nbsp</th>
			<#list ciyu.characters as c>
			<th align="center">
				<#if ((c.pinYinList?size)>1)>
					<#list c.pinYinList as pinYin>
						<#if pinYin_index==0><a class="default-pinyin">${pinYin}</a></#if>
					</#list>
					<select class="select-pinyin" style="display:none">
						<#list c.pinYinList as pinYin>
							<option>${pinYin}</option>
						</#list>
					</select>
				<#else>
					<#list c.pinYinList as pinYin>
							${pinYin}
					</#list>
				</#if>
			</th>
			</#list>
			<th>&nbsp</th>
		</tr>
		<tr class="hanzi" >
			<td >(</td>
			<#list ciyu.characters as c>
				<td align="center">${c.hanZi}</td>
			</#list>
			<td>)</td>
		</tr>
	</table>
</div>
</#macro>