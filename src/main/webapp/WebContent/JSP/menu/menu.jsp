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
<title></title>


</head>

<body>

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>



	<!--検索欄-->

	<div id="kennsaku">
		<form id="form1">
			<input id="sbox" id="s" name="s" type="text" placeholder="キーワードを入力" />
			<input id="sbtn" type="submit" value="検索" />
		</form>
	</div>


	<!--メニュー表-->

	<div id="menu">

		<br> <br> <br> <br>
		<nav>
			<ul>
				<li><a href="../ProductInput">出品する</a></li>
				<li><a href="#">気になる</a></li>
				<li><a href="../Menu">落札中</a></li>
				<li><a href="#">落札履歴</a></li>
				<li><a href="#">出品中</a></li>
				<li><a href="#">出品履歴</a></li>
			</ul>
		</nav>

		<br> <br>
		<div id="text">${requestScope.menu}</div>
	</div>


	<!--商品並びの起点-->

	<div id="block1">

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

						出品者　　${fn:escapeXml(product.userName)}さん <br>

						現在価格　${fn:escapeXml(product.price)} 円<br>
						入札回数　${fn:escapeXml(product.count)} 回

						<p>締め切り　${fn:escapeXml(product.remainingTime)}

				</div>

				<br> 商品情報<br>${fn:escapeXml(product.description)}

			</div>


		</c:forEach>

	</div>

	<div id="botan">
		<button onclick="location.href='/homePege'">戻る</button>
	</div>


</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>
</html>