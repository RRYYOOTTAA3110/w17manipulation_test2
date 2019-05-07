<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="java.util.Iterator"
import="java.util.List"
import="jp.webschool.javaweb.chapter17.bean.ItemBean"
import="jp.webschool.javaweb.chapter17.util.Cast"
import="jp.webschool.javaweb.chapter17.util.NumberFormatUtility"
import="jp.webschool.javaweb.chapter17.util.PropertyLoader"%>

<% List<ItemBean> itemList = Cast.castList(request.getAttribute("itemList"));
Iterator<ItemBean> iterator = itemList.iterator();
int bookmark = Integer.parseInt((String) session.getAttribute("page"));
int maxPage = Integer.parseInt((String) session.getAttribute("maxPage"));%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h1>商品管理</h1>

	<table>
		<caption>商品一覧</caption>
		<tr>
			<th id="id">ID</th>
			<th id="name">商品名</th>
			<th id="price">価格</th>
			<th></th>
		</tr>
		<%
			while (iterator.hasNext()) {
				ItemBean item = iterator.next();
		%>
		<tr>
			<td><%=item.getId()%></td>
			<td><a href="<%=PropertyLoader.getProperty("url.servlet.ItemPage")%>?id=<%=item.getId()%>"><%=item.getName()%></a></td>
			<td class="price"><%=NumberFormatUtility.formatCurrency(item.getPrice())%></td>
			<td><a
				href="<%=PropertyLoader.getProperty("url.servlet.ItemEditor")%>?id=<%=item.getId()%>">編集</a></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="4"><a
				href="<%=PropertyLoader.getProperty("url.servlet.ItemInsertion")%>">商品を追加</a></td>
		</tr>
		<tr id="turnOver">
			<td colspan="4">
				<p id="next">
					<%
						if (bookmark < maxPage) {
					%>
					<a
						href="<%=PropertyLoader.getProperty("url.servlet.ItemSelectorNext")%>">次へ</a>
					<%
						} else {
					%>
					<span class="off">次へ</span>
					<%
						}
					%>
				</p>
				<p>
					<%
						if (bookmark > 1) {
					%>
					<a
						href="<%=PropertyLoader.getProperty("url.servlet.ItemSelectorPrevious")%>">前へ</a>
					<%
						} else {
					%>
					<span class="off">前へ</span>
					<%
						}
					%>
				</p>
			</td>
		</tr>
	</table>

	<form method="get"
		action="<%=PropertyLoader.getProperty("url.servlet.ItemSelector")%>">
		<table>
			<caption>検索</caption>
			<tr>
				<th>IDで検索</th>
			</tr>
			<tr>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>名前で検索</th>
			</tr>
			<tr>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>金額（上限）で検索</th>
			</tr>
			<tr>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="検索"></td>
			</tr>
			<tr>
				<td><a
					href="<%=PropertyLoader.getProperty("url.servlet.ItemManager") %>">すべて表示</a></td>
			</tr>
		</table>
	</form>

</body>
</html>