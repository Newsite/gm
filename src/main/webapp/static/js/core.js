easyloader.base = '../static/js/easyui/';// TODO 需要标准化此地址
easyloader.theme = 'gray';
$(document).ready(
		function() {
			easyloader.load('searchbox', function() {
				$('#searchBar').searchbox(
						{
							searcher : function(keyword, type) {
								var url = '/gm/tab/search?type=' + type
										+ '&keyword=' + keyword;
								window.location.href = url;
							},
							menu : '#searchTypeList',
							prompt : '曲谱搜索'
						});
			});
		});