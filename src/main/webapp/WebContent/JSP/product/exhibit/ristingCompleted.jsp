<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<!--出品完了画面-->

<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="WebContent/css/product/exhibit/ristingCompleted.css">
<title>出品完了画面</title>


</head>


<body>


	<!--ホームリンクタイトル画像-->
	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>


	<div class="parent">

		<div class="children">


			<h2>&emsp;&emsp;出品が完了しました。</h2>

			<br> <br> <br> <br>

			<div id="button">

				<form:form action="homePage">
					<button class="back">戻る</button>
				</form:form>




				<form:form action="ProductRegister" method="get">

					<button class="next">続けて出品する</button>

				</form:form>

			</div>

		</div>

	</div>


</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>
</html>