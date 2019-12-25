
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
<link rel="stylesheet" href="WebContent/css/searchResult.css">
<title>検索結果(パソコン)</title>

<style>
</style>



</head>


<body>

	<!--ホームリンクタイトル画像-->

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>

	<br>

	<div id="block1">
		<!--検索欄-->

		<div id="kennsaku">
			<form:form action="searchResult" id="searchForm" method="get"
				modelAttribute="product">

				<form:input id="sbox" path="productName" type="text"
					placeholder="キーワードを入力" />
				<form:button vaule="search" id="sbtn" name="検索">検索</form:button>

			</form:form>
		</div>


		<!--メニュー表-->
		q1
		<div id="menu">

			<br> <br> メニュー表
			<nav>
				<ul>
					<li><a href="/ProductRegister">出品する</a></li>
					<li><a href="#">気になる</a></li>
					<li><a href="/Menu">落札中</a></li>
					<li><a href="#">落札履歴</a></li>
					<li><a href="#">出品中</a></li>
					<li><a href="#">出品履歴</a></li>
				</ul>
			</nav>

		</div>

	</div>


	<div id=block2>

		<br> ○○の検索結果<br> ☓☓件 <br> <br>



		<div>


			<!--カテゴリ-->


			<div id="kategori">
				カテゴリ
				<nav>
					<ul>

						<li><a href="#">本</a></li>
						<li><a href="#">パソコン</a></li>
						<li><a href="#">スポーツ</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>
						<li><a href="#">サンプル</a></li>

					</ul>
				</nav>

			</div>


			<!--価格 ラジオボタン-->

			表示価格
			<div class="cp_ipradio">
				<div class="box">
					<input type="radio" id="radio1" name="cpipr01" /> <label
						for="radio1">～1000</label> <input type="radio" id="radio2"
						name="cpipr01" /> <label for="radio2">～3000</label> <input
						type="radio" id="radio3" name="cpipr01" /> <label for="radio3">～10000</label>
					<input type="radio" id="radio4" name="cpipr01" /> <label
						for="radio4">10000以上</label>
				</div>
			</div>


			<!--状態　ラジオボタン-->

			商品状態
			<div class="cp_ipradio">
				<div class="box">
					<input type="radio" id="radio5" name="cpipr02" /> <label
						for="radio5">新品</label> <input type="radio" id="radio6"
						name="cpipr02" /> <label for="radio6">中古</label>
				</div>
			</div>

		</div>

	</div>


	<div id="block3">

		<h1>オークション</h1>
		<br> <br> <br>


		<!--商品並びの起点-->



		<c:forEach var="product" items="${productList}">


			<div id="q3">

				<h2>${fn:escapeXml(product.productName)}</h2>

				<br>

				<div class="q1">

					<a href="ExhibitPurchase?id=${fn:escapeXml(product.id)}"> <img
						src="${fn:escapeXml(product.productImg)}" alt="商品画像" width="200"
						height="200">
					</a>

				</div>

				<div class=q2>

					${fn:escapeXml(product.price)}<br> 入札回数△△ 残り時間○○日
				</div>

				<br> 商品情報<br>${fn:escapeXml(product.description)}

			</div>


		</c:forEach>


	</div>



</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>
</html>