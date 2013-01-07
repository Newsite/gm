<head>
<title>生成器入口</title>
</head>
<body>
	<form action="generate_question">
		<input type="radio" value='1' name="questionType" />根据拼音写单词
		<input type="radio" value='2' name="questionType" />乱词排句
		<table>
			<tr>
				<th>输入中文词语(以空格分割):</th>
				<td><textarea name="param"></textarea></td>
			</tr>
			<tr>
				<th>&nbsp</th>
				<td><input type="submit" value="确定" class="small button" /></td>
			</tr>
		</table>
	</form>
</body>