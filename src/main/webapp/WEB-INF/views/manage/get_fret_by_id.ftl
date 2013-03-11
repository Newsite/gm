<head>
<!--29x33 -->
<style>
.chord-cell{z-index:4;width:29px;height:33px;position: absolute;float:left;}
.fret-number{border:1px solid #ccc;text-align:center;width:29px;height:29px;z-index:5;}

.s6{left:-15px;}
.s5{left:15px;}
.s4{left:44px;}
.s3{left:73px;}
.s2{left:102px;}
.s1{left:131px;}

.f1{top:6px;}
.f2{top:38px;}
.f3{top:72px;}
.f4{top:105px;}
.f5{top:138px;}

.string-group{
	background-image:url(${request.getContextPath()}/static/images/chord-grid.gif);
	background-repeat: no-repeat;
	width:146px;
	height:166px;
	position:relative;
}
.main{
width:146px;
}
.status{
height:15px;
width:146px;
}

.note{
width:146px;
}
.status-cell{border:1px solid #ccc;text-align:center;width:16px;height:16px;z-index:5;float:left;position:absolute;}
.note-cell{border:1px solid #ccc;text-align:center;width:16px;height:16px;z-index:5;float:left;position:absolute;}

.fret-base{
height:224px;
}
</style>
</head>
<body>

<div class="row">
<#if chordChart??>
	<div class="chord-chart row">
			<div class="columns fret-base">
				<br/>
				<br/>
				<div class="">
					<#if chordChart.capo!=0>${chordChart.capo+1}</#if>
				</div>
			</div>
			<div class="columns end main">
				<div class="row status">
					<#list 1..6 as i>
							<#assign index=7-i/><!--第${index}弦-->
							<#assign stringFret=chordChart.stringFretList[index-1]/>
							<#if stringFret.status??>
								<div class="status-cell s${index}">
									${stringFret.status}
								</div>
							</#if>
					</#list>
				</div>
				<div class="row string-group">
					<#list 1..6 as i>
						<#assign index=7-i/><!--第${index}弦-->
						<#assign stringFret=chordChart.stringFretList[index-1]/>
						<#list 1..5 as j>
							<#assign fretBase=(chordChart.capo+j) />
							<#if (fretBase==stringFret.fretNumber)>
								<div class="chord-cell s${index} f${j}">
									<img src="${request.getContextPath()}/static/images/${stringFret.finger}.png"/>
								</div>
							</#if>
						</#list>
					</#list>
				</div>
				<div class="row note">
					<#list 1..6 as i>
							<#assign index=7-i/><!--第${index}弦-->
							<#assign stringFret=chordChart.stringFretList[index-1]/>
							<div class="note-cell s${index}">
								${stringFret.note.getName()}
							</div>
					</#list>
				</div>
			</div>
	</div>
</#if>
</div>
</body>
