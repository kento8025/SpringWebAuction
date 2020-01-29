
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="WebContent/css/searchResult/searchResult.css">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/WebActionParts/menu.css">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/WebActionParts/category.css">


<title>検索結果</title>

</head>
<body>

	<!--ホームリンクタイトル画像-->

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>

	<br>


	<form:form action="searchResult" method="get" modelAttribute="product">

		<!--検索欄-->

		<div id="searchForm">

			<form:input id="sbox" path="productName" type="text"
				placeholder="キーワードを入力" />
			<form:button vaule="search" id="sbtn" name="検索">検索</form:button>

			<!--  お気に入りに追加する -->


			<div id="favorite">
				<br> <font color="blue">${requestScope.registrationSuccessful}
				</font> <font color="red">${requestScope.notLoginError} </font><br> <br>
				8文字以内<br> <input name="favoriteName" type="text"
					placeholder="登録したいお気に入り名" /> <input type="hidden"
					value="${sessionScope.user.id}" name="productId"> <input
					type="hidden" name="priceBetweenResult"
					value="${requestScope.priceBetweenResult}"> <input
					type="hidden" name="productStatusResult"
					value="${requestScope.productStatusResult}" /> <select
					name="registrNumber">
					<option value="1">${requestScope.favoriteList1.favoriteName}</option>
					<option value="2">${requestScope.favoriteList2.favoriteName}</option>
					<option value="3">${requestScope.favoriteList3.favoriteName}</option>
					<option value="4">${requestScope.favoriteList4.favoriteName}</option>
					<option value="5">${requestScope.favoriteList5.favoriteName}</option>
					<option value="6">${requestScope.favoriteList6.favoriteName}</option>
					<option value="7">${requestScope.favoriteList7.favoriteName}</option>
					<option value="8">${requestScope.favoriteList8.favoriteName}</option>

				</select>


				<form:button name="favoriteRegister">現在の検索結果の保存</form:button>


			</div>



		</div>


		<!--メニュー表-->

		<div id="menu">

			<br> <br> メニュー表
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

		<div id="searchCondition">
			<br> ○○の検索結果<br> ☓☓件 <br> <br>

			<div>


				<!--カテゴリ-->


				<div id="category">

					カテゴリ

					<c:forEach var="category" items="${categoryList}">

						<nav>
							<ul>
								<li><a
									href="searchResult?category=${fn:escapeXml(category.category_name)}">
										${fn:escapeXml(category.category_name)}</a></li>
							</ul>
						</nav>

					</c:forEach>


					<br> <br> <br> <br> <br> <br> <br>

				</div>


				<!--価格 ラジオボタン-->

				表示価格
				<div class="cp_ipradio">
					<div class="box">
						<input type="radio" id="radio1" name="priceBetween" value="1" />
						<label for="radio1">0～1000</label> <input type="radio" id="radio2"
							name="priceBetween" value="2" /> <label for="radio2">1000～5000</label>
						<input type="radio" id="radio3" name="priceBetween" value="3" />
						<label for="radio3">5000～10000</label> <input type="radio"
							id="radio4" name="priceBetween" value="4" /> <label for="radio4">10000以上</label>
					</div>
				</div>


				<!--状態 ラジオボタン-->

				商品状態
				<div class="cp_ipradio">
					<div class="box">
						<input type="radio" id="radio5" name="productStatus" value="新品" />
						<label for="radio5">新品</label> <input type="radio" id="radio6"
							name="productStatus" value="中古" /> <label for="radio6">中古</label>
					</div>
				</div>

			</div>

		</div>

		<div id="productArea">

			<h1>オークション</h1>


			<c:forEach var="product" items="${productList}">

				<div id="product">

					<h2>${fn:escapeXml(product.productName)}</h2>

					<br>

					<div class="imageArea">

						<a href="ExhibitPurchase?id=${fn:escapeXml(product.id)}"> <img
							src="${fn:escapeXml(product.productImg)}" alt="商品画像" width="200"
							height="200">
						</a>

					</div>

					<div class="productInformation">

						出品者 ${fn:escapeXml(product.userName)}さん <br> 現在価格
						${fn:escapeXml(product.price)} 円<br> 入札回数
						${fn:escapeXml(product.count)} 回

						<p>締め切り ${fn:escapeXml(product.remainingTime)}
					</div>

					<br> 商品情報<br>${fn:escapeXml(product.description)}

				</div>

				<br>

			</c:forEach>

		</div>

	</form:form>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>
</html>