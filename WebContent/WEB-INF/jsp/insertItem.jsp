<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="jp.webschool.javaweb.chapter17.util.PropertyLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品を追加</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>商品を追加</h1>

<form method="get" action="<%=PropertyLoader.getProperty("url.servlet.ItemInsertion2") %>">
<table>
<caption>ID、名前、価格を入力してください。</caption>
<tr>
<th>ID</th>
<th>名前</th>
<th>価格</th>
<th> </th>
</tr>
<tr>
<td><input type="text" name="id"></td>
<td><input type="text" name="name"></td>
<td><input type="text" name="price"></td>
<td><input type="submit" value="追加"></td>
</tr>
<tr>
<td colspan="4"><a href="<%=PropertyLoader.getProperty("url.servlet.ItemManager") %>">キャンセル</a></td>
</tr>
</table>
</form>
</body>
</html>