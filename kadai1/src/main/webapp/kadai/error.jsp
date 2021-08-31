<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<h1>エラー</h1>
	</header>
	<div id="contents">
		<%
		//--- エラーメッセージを取得して表示する
		String mess = (String) request.getAttribute("error");
		out.println(mess);		
		%>
		<p>
			<a href="toppage">トップページへ戻る</a>
		</p>
	</div>
</body>