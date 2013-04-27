<#setting number_format="#">
<#import "../macro/foundation_macro.ftl" as foundation>
<head>
<title>搜索结果</title>
</head>
<body>
  <div class="container">
  	<div class="row">
  		<br/>
		<table class="eight columns centered">
			<tr>
				<th>id</th>
				<th>曲名</th>
				<th>歌手</th>
			</tr>
			<#list list as item>
			<tr>
				<td>${item.id?c}</td>
				<td><a href="details?tabId=${item.id?c}">${item.name}</a></td>
				<td>${item.singer}</td>
			</tr>
			</#list>
		</table>
	</div>
	<div class="row pagination-centered">
		<@foundation.pagination macro_id="pagination" url='${request.getContextPath()}/tab/list?keyword=${keyword!}' current=page.pageNo total=page.totalPageCount/>
	</div> 
  </div>
</body>
