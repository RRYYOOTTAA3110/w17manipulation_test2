<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="jp.webschool.javaweb.chapter17.util.PropertyLoader"
import="jp.webschool.javaweb.chapter17.util.Encoder"%>

<% request.setCharacterEncoding("utf-8"); %>

<%
String id = request.getParameter("id");
String name = request.getParameter("name");
String price = request.getParameter("price");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><%=name %></title>
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h1><%=name %></h1>

	<table>
		<tr>
			<th id="id">ID</th>
			<th>商品名</th>
			<th id="price">価格</th>
		</tr>
		<tr>
			<td><%=id %></td>
			<td><%=name %></td>
			<td><%=price %>円</td>
		</tr>
		<tr>
			<td colspan="3"><a href="<%=PropertyLoader.getProperty("url.servlet.ItemManager") %>">一覧へ戻る</a></td>
		</tr>
	</table>

</body>
</html>