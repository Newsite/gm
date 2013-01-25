<#setting number_format="#">
<head>
<title>曲谱列表</title>
</head>
<body>
  <div class="container">
  	<div class="row">
		<table class="twelve columns">
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
	<div class="row">
		<div id="pagination" class="pagination"></div>
	</div> 
  </div>
<script>
var pageNumber=${page.pageNo};
var pageIndex=pageNumber-1;
var pageSize=${page.pageSize};

function pageSelectCallBack(index,pageSize){
	num=index+1;
	var url =window.location.href;
	if(num!=pageNumber)
	window.location.href=url.split('?')[0]+"?pageNo="+num;
}

$(document).ready(
function(){
		$('#pagination').pagination(${page.totalCount},{
			'current_page':pageIndex,
		    'items_per_page':pageSize,
		    'num_display_entries' : 3, 
		    'num_edge_entries'    : 2,
		    'prev_text': "上一页", 
		    'next_text': "下一页",
		    'callback':pageSelectCallBack
		});
});

</script>
</body>
