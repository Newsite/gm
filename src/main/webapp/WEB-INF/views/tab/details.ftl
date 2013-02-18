<#setting number_format="#">
<div class="row">
	<div class="six columns radius">
		<div class="row panel ">
			<div class="twelve columns">
				<h5>曲名:${tab.name}</h5>
				<h6>歌手:${tab.singer!''}</h6>
				<h6>原调:${tab.keyOrigin!''}</h6>
				<h6>选调:${tab.keyChosen!''}</h6>
			</div>
			<div class="one columns postfix">
			</div>
		</div>
		<div class="row panel ">
			<dl>
				<#list tab.contentVo as s>
					<dt>
						<#list s.chordList as chord>
							<a>${chord.getStringName()}</a>
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
			<h6 align="center">变调器</h6>
			<#list keyList as key>
				<div class="one columns">
					<#if key_index!=offset>
						<kbd><a href="${request.getContextPath()}/tab/details?tabId=${tab.id}&offset=${key_index}">${key}</a></kbd>
					<#else>
						<code><a>${key}</a></code>
					</#if>
				</div>
			</#list>
			</div>
		</div>
		<div class="row">
			<div class="panel">
				<h6 align="center">和弦图解</h6>
				<#if chordList??>
					<ul class="block-grid four-up">
					<#list chordList as chord>
						<li><a>${chord.stringName}</a>[${chord}]</li>
					</#list>
					</ul>
				</#if>
			</div>
		</div>
	</div>
	
</div>
