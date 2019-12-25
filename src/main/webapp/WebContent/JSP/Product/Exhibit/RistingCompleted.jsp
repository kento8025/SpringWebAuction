<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<!--出品完了画面-->

<html lang="ja">

<head>

<meta charset="UTF-8">
<title>出品完了画面</title>


</head>


<body>


	<!--ホームリンクタイトル画像-->
	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>



	<center>
		<h2>出品が完了しました。</h2>
		<br> <br> <br>

		<form:form action="homePage" method="post">
		<button>戻る</button>
		</form:form>

		</div>
		<form:form action="ProductRegister" method="post">
		<button>続けて出品する</button>
		</form:form>
		</div>

	</center>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>
</html>