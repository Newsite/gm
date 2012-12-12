<#setting number_format="#">
<head>
<title>搜索结果</title>
<script>
$(document).ready(
function(){
	easyloader.load('pagination', function(){ 
		$('#pager').pagination({  
		    total:${page.totalCount},  
		    pageSize:${page.pageSize},
		    showPageList:false,
		    showRefresh:false,
		    pageNumber:${page.pageNo},
		    beforePageText:'第',
		    afterPageText:'页,共{pages}页',
		    displayMsg:'第{from}-{to}条,共{total}条',
		    onSelectPage:function(pageNumber,pageSize){
		    	var url =window.location.href;
		    	window.location.href=url.split('?')[0]+"?&type=&keyword=&pageNo="+pageNumber;
		    }
		});
	});
});

</script>
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
	<div id="pager" style="background:#efefef;border:1px solid #ccc;width:400px;"></div> 
	</div>
</body>
