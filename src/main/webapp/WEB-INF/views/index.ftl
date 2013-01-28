<#setting number_format="#">
<#import "macro/StaticFileHelper.ftl" as helper>
<@helper.tagStaticResource filename="index.css"/> 
<title>GuitarMe</title>
<body>
<div class="contain-to-grid">
	<nav class="top-bar">
		<ul>
	    	<li class="name"><h1><a href="${request.getContextPath()}">GuitarMe</a></h1></li>
	   	 	<li class="toggle-topbar"><a href="#"></a></li>
	  	</ul>
	</nav>
</div>
<header id="index_top">
	<div class="row">
		<div class="twelve columns">
				<div class="orbit" id="index_slider">
					<img class="twelve columns" alt="slide_1" src="${request.getContextPath()}/static/images/demo1.jpg" />
					<img class="twelve columns" alt="slide_2" src="${request.getContextPath()}/static/images/demo2.jpg" />
					<img class="twelve columns" alt="slide_3" src="${request.getContextPath()}/static/images/demo3.jpg" />
				</div>
		</div>
	</div>
	<div class="row " id='index_searchbar'>
		<div class="three columns"></div>
		<div class="six columns " >
			<form class="row collapse" action="${request.getContextPath()}/tab/list">
				<div class="ten columns"><input type="text" placeholder="曲名/歌手/歌词" name="keyword"/></div>
				<div class="two columns"><input type="submit"  value="搜索" class=" button expand postfix"/></div>
			</form>
		</div>
		<div class="three columns"></div>
	</div>
</header>
	<div class="row collapse" id="index_main">
		<div class="twelve columns"></div>
		<div class="one columns"></div>
		<div class="ten columns">
				<table class="six columns">
					<caption><kbd>热门曲谱</kbd></caption>
						<tr>
							<th>曲名</th>
							<th>歌手</th>
						</tr>
						<#list hotTabList as tab>
						<tr>
							<td><a href="${request.getContextPath()}/tab/details?tabId=${tab.id?c}">${tab.name}</a></td>
							<td>${tab.singer}</td>
						</tr>
						</#list>
				</table>
				<table class="six columns">
					<caption><kbd>最新曲谱</kbd></caption>
						<tr>
							<th>曲名</th>
							<th>歌手</th>
						</tr>
						<#list lastTabList as tab>
						<tr>
							<td><a href="${request.getContextPath()}/tab/details?tabId=${tab.id?c}">${tab.name}</a></td>
							<td>${tab.singer}</td>
						</tr>
						</#list>
				</table>
		</div>
		<div class="one columns"></div>
	</div>
	<script type="text/javascript">
   $(window).load(function() {
       $("#index_slider").orbit({fluid:'16x4' });
   });
</script>
</body>
