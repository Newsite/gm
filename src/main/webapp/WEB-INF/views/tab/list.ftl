<head>
<title>列表</title>
<!-- <link rel="stylesheet" href="/gm/static/css/core.css" type="text/css" />  -->
</head>
<body>
  <div class="tab">
	<table>
		<tr>
			<th>id</th>
			<th>曲名</th>
			<th>歌手</th>
			<th>采编</th>
			<th>源站点</th>
		</tr>
		<#list list as item>
		<tr>
			<td>${item.id?c}</td>
			<td><a href="details?tabId=${item.id?c}">${item.name}</a></td>
			<td>${item.singer}</td>
			<td>${item.source}</td>
			<td>${item.site}</td>
		</tr>
		</#list>
	</table>
	</div>
</body>
