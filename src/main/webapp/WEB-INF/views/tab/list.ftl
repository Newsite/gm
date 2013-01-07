<#setting number_format="#">
<head>
<title>曲谱列表</title>
</head>
<body>
  <div class="tab">
	<table class="common-table">
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
	<div id="pagination" class="pagination"></div> 
  </div>
	<hr/>
	<hr/>
	
<script>
var pageNumber=${page.pageNo};
var pageIndex=pageNumber-1;
var pageSize=${page.pageSize};

function pageSelectCallBack(index,pageSize){
	num=index+1;
	var url =window.location.href;
	console.info(num);
	if(num!=pageNumber)
	window.location.href=url.split('?')[0]+"?pageNo="+num;
}

$(document).ready(
function(){
		$('#pagination').pagination(${page.totalCount},{
			'current_page':pageIndex,
		    'items_per_page':pageSize,
		    'num_display_entries' : 5, 
		    'num_edge_entries'    : 2,
		    'prev_text': "上一页", 
		    'next_text': "下一页",
		    'callback':pageSelectCallBack
		});
});

</script>
</body>
