easyloader.base = '../static/js/easyui/';// TODO 需要标准化此地址
easyloader.theme = 'gray';
$(document).ready(
		function() {
			easyloader.load('searchbox', function() {
				$('#searchBar').searchbox(
						{
							searcher : function(keyword, type) {
								var url = application_root+'/tab/search?type=' + type//TODO
										+ '&keyword=' + keyword;
								window.location.href = url;
							},
							menu : '#searchTypeList',
							prompt : '曲谱搜索'
						});
			});
		});