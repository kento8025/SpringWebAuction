
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>





<!DOCTYPE html>

<!--登録完了-->


<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="WebContent/css/login/registration.css">
<title></title>


</head>



<body>

	<div id="width">


		<!--ホームリンクタイトル画像-->

		<a href="/homePage"> <img src="WebContent/img/Title.png"
			alt="タイトル" width="200" height="80"></a> <br>

		<div class="parent">

			<div class="children">


				<h2>ユーザーの登録が完了しました</h2>

				<br> <br> <br> <br>


				<form:form action="homePage">
					<button class="back">戻る</button>
				</form:form>

			</div>

		</div>

	</div>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>



</html>











