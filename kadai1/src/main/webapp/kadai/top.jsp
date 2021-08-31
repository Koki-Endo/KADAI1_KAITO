<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Product" %>
<jsp:useBean id="data" class="bean.Product" scope="page" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品検索</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<h1>商品一覧</h1>
	</header>
	<div id="contents">
		<div id="form">
			<form method="get" action="toppage">
				キーワード
				<input type="text" name="keyword">
				<button type="submit">検索</button>
				<a href="insert.html"><button type="button">新規登録</button></a>
			</form>
		</div>
		<div id="products">
			<%
			//--- データを配列aryに受け取る
			List<Product> ary = (ArrayList<Product>) request.getAttribute("data");
			//--- データが無いかを配列の大きさ（size()）で判断
			if (ary.size() == 0) {
				out.println("<p>キーワードに該当するデータはありませんでした。</p>");
			} else {
				//--- table を使って表示する
				out.println("<table>");
				//--- 項目見出しの出力
				out.println("<tr><th>ID</th><th>商品名</th><th>価格</th></tr>");
				//--- 配列から１件ずつ表示　Productのメソッドを利用
				for(Product p: ary) {
					out.println("<tr>");
					out.println("<td>" + p.getId() + "</td>");
					out.println("<td>" + p.getName() + "</td>");
					out.println("<td>" + p.getPrice() + "</td>");
					out.println("</tr>");
				}
				out.println("</table>");
			}
			%>
		</div>
	</div>
</body>
</html>