<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!DOCTYPE html>


<!--落札情報確認-->


<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="WebContent/css/exhibitPurchase.css">
<title></title>

</head>

<body>


	<!--ホームリンクタイトル画像-->


	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>


	<div id="block1">
		<!--検索欄-->

		<div id="kennsaku">
			<form id="form1">
				<input id="sbox" id="s" name="s" type="text" placeholder="キーワードを入力" />
				<input id="sbtn" type="submit" value="検索" />
			</form>
		</div>


		<!--メニュー表-->

		<div id="menu">

			メニュー表
			<nav>
				<ul>
					<li><a href="#">マイトップ</a></li>
					<li><a href="#">気になる</a></li>
					<li><a href="#">入札中</a></li>
					<li><a href="#">落札履歴</a></li>
					<li><a href="#">出品中</a></li>
					<li><a href="#">出品履歴</a></li>
				</ul>
			</nav>

		</div>

	</div>

	<c:choose>

		<c:when test="${sessionScope.user.userRank eq '1'}">


			<form action="searchAdminProduct" method="post">

				<br> <input type="hidden"
					value="${fn:escapeXml(purchaseDisplay.primaryProductId)}" name="productId">

				<br>
				<button name="action" value="delete">削除</button>
				<button name="action" value="hidden">非表示</button>

			</form>



		</c:when>

	</c:choose>

	<div id="block2">

		<form:form action="SuccessfulDid" modelAttribute="tradeForm">


			<div id="block2-A-A">
				<h1>${fn:escapeXml(purchaseDisplay.productName)}</h1>

				<div id="img">
					<img src="${fn:escapeXml(purchaseDisplay.productImg)}" alt=商品
						width="300" height="300">
				</div>
				<br>
			</div>


			<div id="block2-A-B">
				入札回数 残り時間<br> 〇〇回数 〇〇時間<br> <br> 価格<br>
				${fn:escapeXml(purchaseDisplay.price)}円<br>
				送料${fn:escapeXml(purchaseDisplay.postage)}負担<br> <br> <input
					name="contractPrice" type="text" placeholder="落札金額を入力" /> <input
					type="hidden" name="userId"
					value="${fn:escapeXml(purchaseDisplay.primaryUserId)}"> <input
					type="hidden" name="productId"
					value="${fn:escapeXml(purchaseDisplay.primaryProductId)}"> <br>

				<button class="btn-square-so-pop">今すぐ落札する</button>


				<br> <br> 出品者${fn:escapeXml(purchaseDisplay.userName)}さん<br>
				<br> 出品者へのお問合せ<br> ${fn:escapeXml(purchaseDisplay.mail)}


			</div>

			<br>



			<div id="block3">${fn:escapeXml(purchaseDisplay.description)}</div>
		</form:form>
		<br> <br>
	</div>






	<div id="botan">
		<button onclick="location.href='/homePage'">戻る</button>
	</div>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>



</html>











