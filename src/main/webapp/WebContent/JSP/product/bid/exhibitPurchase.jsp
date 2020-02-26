<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>


<!--落札情報確認-->


<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="WebContent/css/product/bid/exhibitPurchase.css">
<title></title>

</head>

<body>


	<!--ホームリンクタイトル画像-->


	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>





	<div id="block1">
		<!--検索欄-->

		<!--メニュー表-->

		<div id="menu">

			メニュー表
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

	</div>

	<c:choose>

		<c:when test="${sessionScope.user.userRank eq '1'}">

			<form:form action="searchAdminProduct" modelAttribute="tradeForm">

				<br>
				<input type="hidden"
					value="${fn:escapeXml(purchaseDisplay.primaryProductId)}"
					name="productId">
				<br>
				<button name="delete">削除</button>
				<button name="hidden">非表示</button>

			</form:form>


		</c:when>

	</c:choose>

	<div id="block2">

		<form:form action="trade" modelAttribute="tradeForm">


			<div id="block2-A-A">
				<h1>${fn:escapeXml(purchaseDisplay.productName)}</h1>

				<div id="img">
					<img src="${fn:escapeXml(purchaseDisplay.productImg)}" alt=商品
						width="300" height="300">
				</div>
				<br>
			</div>


			<div id="block2-A-B">
				入札回数 ${fn:escapeXml(purchaseDisplay.count)}回<br>
				締め切り時間${fn:escapeXml(purchaseDisplay.remainingTime)}<br> <br>
				価格<br> <input type="hidden" name="price"
					value="${fn:escapeXml(purchaseDisplay.price)}">

				${fn:escapeXml(purchaseDisplay.price)}円<br>
				送料${fn:escapeXml(purchaseDisplay.postage)}負担<br>

				<c:choose>

					<c:when test="${sessionScope.user.id eq purchaseDisplay.seller}">

						<input type="hidden"
							value="${fn:escapeXml(purchaseDisplay.primaryProductId)}"
							name="productId">
						<form:button class="btn-square-so-pop-red" name="productCancel">商品の取り消し</form:button>
						<br>

						<c:choose>

							<c:when test="${purchaseDisplay.count ne 0}">
								<form:button class="btn-square-so-pop" name="promptDecision">落札の締め切り</form:button>
							</c:when>

						</c:choose>

					</c:when>

					<c:when test="${sessionScope.user.id eq purchaseDisplay.buyer}">

						<input type="hidden"
							value="${fn:escapeXml(purchaseDisplay.trade)}" name="tradeId">

						<form:button class="btn-square-so-pop-red"
							name="successFulDidCancel">落札の取り消し</form:button>

					</c:when>


					<c:otherwise>

						<font color="red">${requestScope.priceError}</font>
						<font color="red">${requestScope.notLoginError}</font>
						<br>
						<form:errors path="contractPrice" cssStyle="color: red" />
						<br>
						<form:input path="contractPrice" placeholder="落札金額を入力" />
						<input type="hidden" name="userId" value='${sessionScope.user.id}'>
						<input type="hidden" name="productId"
							value="${fn:escapeXml(purchaseDisplay.primaryProductId)}">
						<br>

						<button class="btn-square-so-pop" name="SuccessfulDid">今すぐ落札する</button>

					</c:otherwise>

				</c:choose>

				<br> <br> 出品者 ${fn:escapeXml(purchaseDisplay.userName)}さん<br>
				<br> 出品者へのお問合せ<br> ${fn:escapeXml(purchaseDisplay.mail)}<br>

				<br>


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











