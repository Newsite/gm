<!-- 详情中的chord -->
<#macro chordChart chartVo>
	<div class="chord-chart row">
			<div align="center"><a>${chartVo.chordName}</a></div>
			<div class="columns chord-fret-base">
				<#if (chartVo.position>1)> ${chartVo.position}</#if> 
			</div>
			<div class="columns end chord-main">
				<div class="row chord-string-group">
					<#list 1..6 as i>
						<#assign index=7-i/>
						<#assign stringFret=chartVo.stringFretList[index-1]/>
						<#list 1..6 as j>
							<#if j<6>
								<#if (j==stringFret.fretNumber)>
									<div class="chord-cell s${index} f${j}">
										<!-- <img src="${request.getContextPath()}/static/images/chord/${stringFret.finger}.gif"/> -->
										●
									</div>
								</#if>
							<#else>
								<#if stringFret.status??>
									<div class="chord-cell s${index} f6">
										<img src="${request.getContextPath()}/static/images/chord/${stringFret.status}.gif"/>
									</div>
								</#if>
							</#if>
						</#list>
					</#list>
				</div>
			</div>
	</div>
</#macro>