<#import "../macro/exam_macro.ftl" as exam>
<head>
<script>
$(document).ready(
function() {
	$(".ciyu-pinyin").each(
		function(e){
			var x = $(this);
			var defaultPinyin = x.find(".default-pinyin");
			var selectPinyin = x.find(".select-pinyin");
			//点击多音字拼音，即出现选择框
			defaultPinyin.click(function(){
				defaultPinyin.hide();
				selectPinyin.show();
				selectPinyin.click();
			});
			//选择多音字的任意拼音，则隐藏选择框，并将默认拼音置为选择的结果
			selectPinyin.mouseleave(function(){
				defaultPinyin.show();
				selectPinyin.hide();
			});
			selectPinyin.change(function(){
				var x = selectPinyin.find("option:selected").text()
				defaultPinyin.html(x);
				$(this).mouseleave();
			});
			
		}
	);
});
</script>
</head>
<body>
<form action="generate_question" method="POST">
	<input type="radio" value='1' name="questionType" <#if questionType==1>checked</#if>/>根据拼音写单词
	<input type="radio" value='2' name="questionType" <#if questionType==2>checked</#if>/>乱词排句
	<table>
		<tr>
			<th>输入中文词语(以空格分割):</th>
			<td><textarea name="param">${param!}</textarea></td>
		</tr>
		<tr>
			<th>&nbsp</th>
			<td><input type="submit" value="确定"/></td>
		</tr>
	</table>
</form>
<br/>
<#if question??>
	<#if questionType==1>
		<#list question.body as subQuestionBody>
			<#list subQuestionBody as word>
				<@exam.ciyuWithPinyin ciyu=word/>
			</#list>
			<br/><br/><br/>
		</#list>
	</#if>
	<#if questionType==2>
		<#list question.body as subQuestionBody>
			<#list subQuestionBody as word>
				${word}
			</#list>
			<br/>
		</#list>
	</#if>
</#if>
</body>