<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>管理者画面</h1>

	<br>
	<br>

	<h2>${fn:escapeXml(result)}</h2>

	<br>
	<br>

	<form action="AdminProduct" method="post">

	商品

		<br>

		<input type="text" placeholder="商品IDを入力" name = "productId">

		<br>
		<button name="action" value="delete">削除</button>
		<button name="action" value="hidden">非表示</button>
		<button name="action" value="hiddenCancel">再表示</button>


	</form>



		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>

	カテゴリ


		<input type="text" placeholder="カテゴリ名を入力" name =categoryName>


		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>


	管理者の追加

		<input type="text" placeholder="管理者にするIDを入力" name = "">



</body>
</html>