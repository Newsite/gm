<#setting number_format="#">
<#import "macro/StaticFileHelper.ftl" as helper>
<@helper.tagStaticResource filename="index.css"/> 
<title>GuitarMe</title>
<body>
<div class="contain-to-grid">
	<nav class="top-bar">
		<ul>
	    	<li class="name"><h1><a href="${rc.getContextPath()}">GuitarMe</a></h1></li>
	   	 	<li class="toggle-topbar"><a href="#"></a></li>
	  	</ul>
	  	<section>
	  		<ul class="left">
	  			<li class="divider"></li>
	  			<li class="has-dropdown">
	  				<a class="active" href="">找谱</a>
	  				<ul class="dropdown">
	  					<li><label>文本谱</label></li>
	  					<li><a href="#">新谱</a></li>
	  					<li><a href="#">热谱</a></li>
	  					<li class="divider"></li>
	  					<li><label>歌手</label></li>
	  					<li><a href="#">热门歌手</a></li>
	  					<li><a href="#">歌手列表</a></li>
	  				</ul>
	  			</li>
	  			<li class="divider"></li>
	  			<li class="has-dropdown">
	  				<a href="">乐理</a>
	  				<ul class="dropdown">
	  					<li><a href="#">基础乐理</a></li>
	  					<li><a href="#">吉他学习</a></li>
	  				</ul>
	  			</li>
	  			<li class="divider"></li>
	  		</ul>
	  	</section>
	</nav>
</div>
<header id="index_top">
	<div class="row">
		<div class="twelve columns">
				<div class="orbit" id="index_slider">
					<img class="twelve columns" alt="slide_1" src="${rc.getContextPath()}/static/images/demo1.jpg" />
					<img class="twelve columns" alt="slide_2" src="${rc.getContextPath()}/static/images/demo2.jpg" />
					<img class="twelve columns" alt="slide_3" src="${rc.getContextPath()}/static/images/demo3.jpg" />
				</div>
		</div>
	</div>
	<div class="row " id='index_searchbar'>
		<div class="three columns"></div>
		<div class="six columns " >
			<form class="row collapse" action="${rc.getContextPath()}/tab/list">
				<div class="ten columns"><input type="text" placeholder="曲名/歌手/歌词" name="keyword"/></div>
				<div class="two columns"><input type="submit"  value="搜索" class=" button expand postfix"/></div>
			</form>
		</div>
		<div class="three columns"></div>
	</div>
</header>
	<div class="row" id="index_main">
				<table class="six columns">
					<caption><kbd>热门曲谱</kbd></caption>
						<tr>
							<th>曲名</th>
							<th>歌手</th>
						</tr>
						<#list hotTabList as tab>
						<tr>
							<td><a href="${rc.getContextPath()}/tab/details?tabId=${tab.id?c}">${tab.name}</a></td>
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
							<td><a href="${rc.getContextPath()}/tab/details?tabId=${tab.id?c}">${tab.name}</a></td>
							<td>${tab.singer}</td>
						</tr>
						</#list>
				</table>
	</div>
	<script type="text/javascript">
   $(window).load(function() {
       $("#index_slider").orbit({fluid:'16x4' });
   });
</script>
</body>
