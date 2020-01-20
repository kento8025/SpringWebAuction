<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/home/homePage.css">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/WebActionParts/search.css">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/WebActionParts/menu.css">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/WebActionParts/category.css">

<title>オークション</title>

</head>

<body>


	<!--タイトル-->

	<img src="WebContent/img/Title.png" alt="タイトル" width="1500"
		height="150">

	<!--検索欄-->


	<form:form action="searchResult" id="searchForm" method="get"
		modelAttribute="product">

		<form:input id="sbox" path="productName" type="text"
			placeholder="キーワードを入力" />
		<form:button vaule="search" id="sbtn" name="検索">検索</form:button>

	</form:form>


	<!--ログイン画面-->

	<%-- 		<form:button class="btn-square" name="login">ログイン</form:button>
		<br>
		<form:button class="btn-square" name="logout">ログアウト</form:button>
 --%>




	<c:choose>

		<c:when test="${sessionScope.user.userRank eq '1'}">

			<div id="login">

				<h2>管理者${sessionScope.user.userName}さん</h2>
				<br>

				<form:form action="loginCheck" modelAttribute="user">

					ID<form:input path="userId" value='${sessionScope.user.userId}' />
					<br>
					PASSWORD<form:password path="passWord"
						value='${sessionScope.user.passWord}' />
					<br>

					<form:button class="btn-square" name="login">ログイン</form:button>
					<br>
					<form:button class="btn-square" name="logout">ログアウト</form:button>

				</form:form>

			</div>


		</c:when>
		<c:when test="${sessionScope.user.userRank eq '2'}">


			<div id="login">

				<h2>ようこそ！${sessionScope.user.userName}さん</h2>

				<br>

				<form:form action="loginCheck" modelAttribute="user">

					ID<form:input path="userId" value='${sessionScope.user.userId}' />
					<br>
					PASSWORD<form:password path="passWord"
						value='${sessionScope.user.passWord}' />
					<br>

					<button class="btn-square" name="login">ログイン</button>
					<br>
					<form:button class="btn-square" name="logout">ログアウト</form:button>


					<br>

				</form:form>

			</div>
		</c:when>
		<c:otherwise>

			<div id="login">

				<form:form action="loginCheck" modelAttribute="user">

					<font color="red">${requestScope.loginError}</font>
					<br>

					ID<form:input path="userId" value='${sessionScope.user.userId}' />
					<br>
					PASSWORD<form:password path="passWord"
						value='${sessionScope.user.passWord}' />
					<br>

					<button class="btn-square" name="login">ログイン</button>

					<br>
					<form:button class="btn-square-so-pop" name="UserRegister">新規登録</form:button>
				</form:form>

			</div>

		</c:otherwise>
	</c:choose>

	<!--メニュー表-->

	<div id="menu">

		メニュー表
		<nav>
			<ul>
				<li><a href="userInformation">ユーザー情報</a></li>
				<li><a href="ProductRegister">出品する</a></li>
				<li><a href="successfulDid">落札中</a></li>
				<li><a href="successfulDidHistory">落札履歴</a></li>
				<li><a href="exhibition">出品中</a></li>
				<li><a href="exhibitionHistory">出品履歴</a></li>
			</ul>
		</nav>

	</div>

	<!--お気に入りのタグを表示-->

	<div id="tagu">

		<nav>
			<a href="SearchResults" class="btn-circle-flat">パソコン</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a> <a href="#"
				class="btn-circle-flat">BUTTON</a>
		</nav>

	</div>

	<!--お知らせ-->

	<div id="news">

		お知らせ
		<nav>
			<ul>
				<li><a href="#">メンテナンスのお知らせ</a></li>
				<li><a href="#">通信障害</a></li>
				<li><a href="#">サイトの利用規約変更</a></li>
				<li><a href="#">サンプル</a></li>
				<li><a href="#">サンプル</a></li>
				<li><a href="#">サンプル</a></li>
				<li><a href="#">サンプル</a></li>
				<li><a href="#">サンプル</a></li>
				<li><a href="#">サンプル</a></li>
			</ul>
		</nav>

	</div>


	<!--カテゴリ一覧-->

	<div id="category">

		カテゴリ

		<c:forEach var="category" items="${categoryList}">

			<nav>
				<ul>
					<li><a href="#">${fn:escapeXml(category.category_name)}</a></li>
				</ul>
			</nav>

		</c:forEach>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br>

	</div>

	<!--目玉商品-->

	<div id="box1">


		<div id="featuredProducts">
			<h1>目玉商品</h1>

			<span class="new"><img src="WebContent/img/kinn.jpg" alt="金塊"
				width="250" height="250"></span> <span class="new"><img
				src="WebContent/img/main01.jpg" alt="ソファー" width="250" height="250"></span>
			<span class="new"><img src="WebContent/img/hamu.jpg"
				alt="ハムスター" width="250" height="250"></span>

		</div>

	</div>



	<div id="textFeaturedProducts">

		<span class="text">金塊<br> 残り時間 ○○ 現在５０万
		</span> <span class="text">ソファー<br> 残り時間 ☓☓ ３万
		</span> <span class="text">目玉焼き<br> 残り時間 △△ １０円
		</span>

	</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>

<script>
	$(function() {
		$('.btn-circle-flat').css({
			'transition' : '2s',
			'transform' : 'rotate(360deg)'
		});
		$('.btn-circle-flat').animate({
			'margin-Left' : '35px'
		});
	});
</script>


</html>