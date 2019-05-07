<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="jp.webschool.javaweb.chapter17.bean.ItemBean"
import="jp.webschool.javaweb.chapter17.util.NumberFormatUtility"
import="jp.webschool.javaweb.chapter17.util.PropertyLoader"%>

<% ItemBean item = (ItemBean) request.getAttribute("item"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編集完了</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>編集完了</h1>

<table>
<caption>${message }</caption>
<tr>
<th id="id">ID</th>
<th id="name">名前</th>
<th id="price">価格</th>
</tr>
<tr>
<td><%=item.getId() %></td>
<td><%=item.getName() %></td>
<td class="price"><%=NumberFormatUtility.formatCurrency(item.getPrice()) %></td>
</tr>
<tr>
<td colspan="3"><a href="<%=PropertyLoader.getProperty("url.servlet.ItemManager") %>">商品管理</a></td>
</tr>
</table>
</body>
</html>