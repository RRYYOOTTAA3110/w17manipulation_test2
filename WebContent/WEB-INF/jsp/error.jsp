<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="jp.webschool.javaweb.chapter17.util.PropertyLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>エラー</h1>

<table id="error">
<caption>${errorMessage }</caption>
<tr>
<td><a href="<%=PropertyLoader.getProperty("url.servlet.ItemManager") %>">商品管理</a></td>
</tr>
</table>

</body>
</html>