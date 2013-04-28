<#setting number_format="#">
<#import "../macro/chord_macro.ftl" as chordMacro>
<body>
<br/>
<div class="row">
	<div class="six columns radius">
		<div class="row panel ">
			<div class="twelve columns">
				<h5>曲名:${tab.name}</h5>
				<h6>歌手:${tab.singer!''}</h6>
				<h6>原调:${tab.keyOrigin!''}</h6>
				<div class="row">
					<ul class="tabs">
						<#list keyList as key>
								<#if key_index!=offset>
									<li><a href="${rc.getContextPath()}/tab/details?tabId=${tab.id}&offset=${key_index}">${key}</a></li>
								<#else>
									<li class="active"><a>${key}</a></li>
								</#if>
						</#list>
					</ul>
				</div>
			</div>
		</div>
		<!-- content -->
		<div class="row panel">
			<dl>
				<#list tab.contentVo as s>
					<dt>
						<#list s.chordList as chord>
							<a>${chord.getName()}</a>
							&nbsp;&nbsp; 
						</#list>
					</dt>
					<dd>${s.lyric}</dd>
				</#list>
			</dl>
		</div>
	</div>
	<div class="five columns">
		<div class="row">
			<div class="panel">
				<h6 align="center">和弦图解</h6>
				<#if chordChartList??>
					<ul class="block-grid four-up">
						<#list chordChartList as chord>
							<li>
								<@chordMacro.chordChart chartVo=chord/>
							</li>
						</#list>
					</ul>
				</#if>
			</div>
		</div>
	</div>
</div>
</body>