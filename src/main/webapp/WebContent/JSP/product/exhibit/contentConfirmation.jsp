<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="ja">

<!--出品商品情報確認-->

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="WebContent/css/exhibit/productConfirmation.css">
<title></title>

</head>

<body>



	<!--ホームリンクタイトル画像-->

	<a href="/homePage"> <img src="WebContent/img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>

	<br>

	<div id="block1">


		<form:form  action="RistingCompleted" method="post" modelAttribute="product">


			<h2>内容確認</h2>
			<br> <br> 出品者：<input type="text" name="userId"
				value="${fn:escapeXml(user.userName)}" ><br>
			商品名：
			<form:input path="productName"
				value="${fn:escapeXml(product.productName)}"  />
			<br> カテゴリ：
			<form:input path="categoryName"
				value="${fn:escapeXml(product.categoryName)}"  />
			<br> 商品の状態
			<form:input path="productStatus"
				value="${fn:escapeXml(product.productStatus)}"  />
			<br>
			<div id="img">
				<img src="${fn:escapeXml(product.productImg)}" alt=商品 width="300"
					height="300"><br>
				<input type= "hidden" name="productImg" value="${fn:escapeXml(product.productImg)}" />
				価格：

				<form:input path="price" value="${fn:escapeXml(product.price)}"/>
				<br>
			</div>
			<br> <br> 説明<br>
			<div id="text">
				<form:textarea cols="50" rows="10" path="description" value="${fn:escapeXml(product.description)}" />
				<br> <br> 発送元：<form:input path="shippingOrigin"
					value="${fn:escapeXml(product.shippingOrigin)}" /><br>
				送料負担：<form:input  path="postage"
					value="${fn:escapeXml(product.postage)}" /><br>
				配送方法：<form:input path="shipping_method"
					value="${fn:escapeXml(product.shipping_method)}" /><br>
				出品期間：<form:input path="exhibition_period"
					value="${fn:escapeXml(product.exhibition_period)}" /><br>
				価格：<form:input path="price"
					value="${fn:escapeXml(product.price)}" /><br> <br>
				<br>

				<button class="btn-square-so-pop">出品する</button>

			</div>
		</form:form>

	</div>




</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">

</script>



</html>











