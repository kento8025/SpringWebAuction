<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!--落札確認画面-->

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="">
<title></title>


</head>


	<h2>商品を落札しました。</h2>

	<br> <br> <br> <br>


	<form:form action="homePage" >
		<button>戻る</button>
	</form:form>


	<form:form action="searchResult" method="get" >

		<button>続けて落札する</button>

	</form:form>

<body>






</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>



</html>











