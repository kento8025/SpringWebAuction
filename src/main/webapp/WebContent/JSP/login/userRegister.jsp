<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!DOCTYPE html>

<!--新規登録-->

<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="WebContent/css/login/userRegister.css">
<title>新規登録</title>

</head>


<body>

	<!--ホームリンクタイトル画像-->

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>

	<div id="box1">

		<form:form action="UserConfirmation" modelAttribute="user">



			<h2>お客様情報</h2>

			<br> 名前 <br>

			<form:input path="userName" value='${requestScope.user.userName}' />
			<br>
			<form:errors path="userName" cssStyle="color: red" />
			<br>


			<br>

			<br>生年月日<br>

			<form:select id="year" path="year">
				<option value="${requestScope.user.year}">
					${requestScope.user.year}</option>
			</form:select>年


				<form:select id="month" path="month">
				<option value="${requestScope.user.month}">
					${requestScope.user.month}</option>
			</form:select>月


				<form:select id="day" path="day">
				<option value="${requestScope.user.day}">
					${requestScope.user.day}</option>
			</form:select>日

				​
				<br>
				<font color="red">${requestScope.birthdayError}</font>
			<br>
			<br>

			<br>
			<br>

			<c:choose>
				<c:when test="${requestScope.user.manOrWoman eq '男'}">

					性別 <br>

					<form:radiobutton path="manOrWoman" value="男" checked="checked" />男
					<form:radiobutton path="manOrWoman" value="女" />女

				</c:when>

				<c:when test="${requestScope.user.manOrWoman eq '女'}">

					性別 <br>

					<form:radiobutton path="manOrWoman" value="男" />男
						<form:radiobutton path="manOrWoman" value="女" checked="checked" />女

					<br>
				</c:when>

				<c:otherwise>

					性別 <br>

					<form:radiobutton path="manOrWoman" value="男" />男
						<form:radiobutton path="manOrWoman" value="女" />女

					<br>

				</c:otherwise>

			</c:choose>

			<form:errors path="manOrWoman" cssStyle="color: red" />
			<br>





			<br>


			メールアドレス <br>

			<form:input path="mail" value='${requestScope.user.mail}' />
			<br>

			<form:errors path="mail" cssStyle="color: red" />
			<br>

			<br>
			<br>

			<h2>会員情報</h2>

			<br> ID <br>

			<form:input path="userId" value='${requestScope.user.userId}' />
			<br>
			<form:errors path="userId" cssStyle="color: red" />
			<font color="red">${requestScope.userIdError}</font>
			<br>
			<br>


			<br> password <br>パスワードは半角英小文字と数字を組み合わせた8文字以上<br>

			<form:password path="passWord" value='${requestScope.user.passWord}' />
			<br>
			<form:errors path="passWord" cssStyle="color: red" />
			<br>

			<br>
			<br>

			<button class="btn-square-so-pop">新規登録</button>

		</form:form>

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



</html>











