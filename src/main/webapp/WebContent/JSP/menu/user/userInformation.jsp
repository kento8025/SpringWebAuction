<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<!--メニュー画面-->

<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="WebContent/css/menu/menu.css">
<link rel="stylesheet" href="WebContent/css/login/userRegister.css">
<link rel="stylesheet" href="WebContent/css/login/userConfirmation.css">
<title></title>


</head>

<body>

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>


	<!--メニュー表-->

	<div id="menu">

		<br> <br>
		<nav>
			<ul>
				<li><a href="userInformation">ユーザー情報</a></li>
				<li><a href="ProductRegister">出品する</a></li>
				<li><a href="menuSearch?menuCommand=successfulDid">落札中</a></li>
				<li><a
					href="menuSearch?menuCommand=productSuccessfulDidHistory">落札履歴</a></li>
				<li><a href="menuSearch?menuCommand=exhibition">出品中</a></li>
				<li><a href="menuSearch?menuCommand=exhibitionHistory">出品履歴</a></li>
			</ul>
		</nav>

	</div>


	<div id="inputInformation">

		<form:form action="Edit" modelAttribute="user">


			<h2>お客様情報</h2>

			<br> 名前 <br>

			<form:input path="userName" value="${fn:escapeXml(user.userName)}" />
			<br>
			<form:errors path="userName" cssStyle="color: red" />
			<br>

			<br>

			<br>生年月日<br>

			<form:select id="year" path="year">
				<option value="${fn:escapeXml(user.year2)}">
					${fn:escapeXml(user.year)}</option>
			</form:select>年


				<form:select id="month" path="month">
				<option value="${fn:escapeXml(user.month2)}">
					${fn:escapeXml(user.month)}</option>
			</form:select>月


				<form:select id="day" path="day">
				<option value="${fn:escapeXml(user.day2)}">
					${fn:escapeXml(user.day)}</option>
			</form:select>日
​
				<br>
			<font color="red">${requestScope.birthdayError}</font>
			<br>
			<br>


				 性別 <br>
			<form:input type="text" path="manOrWoman"
				value="${fn:escapeXml(user.manOrWoman)}" />

			<br>

			<form:errors path="manOrWoman" cssStyle="color: red" />
			<br>


			<br>
			<br>


			メールアドレス <br>

			<form:input path="mail" name="mail"
				value="${fn:escapeXml(user.mail)}" />
			<br>
			<form:errors path="mail" cssStyle="color: red" />

			<br>

			<br>
			<br>
			<br>

			<h2>会員情報</h2>

			<br> ID <br>

			半角英数字で入力してください。

			<form:input path="userId" value="${requestScope.user.userId}" />
			<br>
			<form:errors path="userId" cssStyle="color: red" />
			<br>

			<br> password <br>パスワードは半角英小文字と数字を組み合わせた8文字以上<br>

			<form:password path="passWord" value="${fn:escapeXml(user.passWord)}" />
			<br>

			<form:errors path="passWord" cssStyle="color: red" />
			<br>
			<br>
			<br>


			<div id="editButton">

				<div>
					<form:button class="btn-square-so-opo">編集する</form:button>
				</div>

			</div>

		</form:form>


		<br> <br> <br>


	</div>





</body>

<script>
	var time = new Date();
	var year = time.getFullYear();
	for (var i = year; i >= 1900; i--) {
		createOptionElements(i, 'year');
	}
	for (var i = 1; i <= 12; i++) {
		createOptionElements(i, 'month');
	}
	for (var i = 1; i <= 31; i++) {
		createOptionElements(i, 'day');
	}

	function createOptionElements(num, parentId) {
		var doc = document.createElement('option');
		doc.value = doc.innerHTML = num;
		document.getElementById(parentId).appendChild(doc);
	}
</script>

</body>

</html>