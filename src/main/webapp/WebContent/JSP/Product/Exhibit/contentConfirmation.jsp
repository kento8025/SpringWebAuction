<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ja">

	<!--出品商品情報確認-->

	<head>

		<meta charset="UTF-8">
		<link rel="stylesheet" href="../../css/Confirmation.css">
		<title></title>

		<style>

	#block1{
    width: 40%;
    margin: auto;
	}

	#img{
	}

	#text{
	}


	/*出品する*/

	.btn-square-so-pop {
	    margin: auto;
	    text-align: center;
		width:80px;
		position: relative;
		display: inline-block;
		padding: 0.25em 0.5em;
		text-decoration: none;
		color: #FFF;
		background: #fd9535;/*色*/
		border-radius: 4px;/*角の丸み*/
		box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), inset 0 -2px 0 rgba(0, 0, 0, 0.05);
		font-weight: bold;
	    border: solid 2px #d27d00;/*線色*/
	    }


	    /*編集する*/

	.btn-square-so-opo {
	    margin: auto;
	    text-align: center;
		width:80px;
		position: relative;
		display: inline-block;
		padding: 0.25em 0.5em;
		text-decoration: none;
		color: #FFF;
		background: #2548e4;/*色*/
		border-radius: 4px;/*角の丸み*/
		box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), inset 0 -2px 0 rgba(0, 0, 0, 0.05);
		font-weight: bold;
	    border: solid 2px #2548e4;/*線色*/
	   }

		</style>


    </head>

	<body>



		<!--ホームリンクタイトル画像-->

		<a href="../hoomPege">
		<img src="img/Title.png" alt="タイトル" width="200" height="80"></a><br>

		<div id="block1">


			<form action="RistingCompleted" method="post">


				<h2>内容確認</h2>
				<br>
				<br>
				出品者： <input type="text" name="userId" value="${fn:escapeXml(user.userName)}" readonly><br>
				商品名： <input type="text" name="productName" value="${fn:escapeXml(product.productName)}" readonly><br>
			 	カテゴリ： <input type="text" name="category" value="${fn:escapeXml(product.categoryName)}" readonly><br>
				商品の状態  <input type="text" name="productStatus" value="${fn:escapeXml(product.productStatus)}" readonly><br>
				<div id="img">
				<img src="${fn:escapeXml(product.productImg)}" alt=商品 width="300" height="300"><br>
				<input type="hidden" name="productImg" value="${fn:escapeXml(product.productImg)}">
				価格：<input type="text" name="price" value="${fn:escapeXml(product.price)}" readonly><br>

				</div>
				<br>
				<br>

				 説明<br>
				 <div id="text">
				 <textarea cols="50" rows="10" name = "description" readonly>${fn:escapeXml(product.description)}
				 </textarea>
				<br>
				<br>
						発送元：<input type="text" name="shippingOrigin" value="${fn:escapeXml(product.shippingOrigin)}" readonly><br>
						送料負担：<input type="text" name="postage" value="${fn:escapeXml(product.postage)}" readonly><br>
						配送方法：<input type="text" name="shipping_method" value="${fn:escapeXml(product.shipping_method)}" readonly><br>
						出品期間：<input type="text" name="exhibition_period" value="${fn:escapeXml(product.exhibition_period)}" readonly><br>
						価格：<input type="text" name="price" value="${fn:escapeXml(product.price)}" readonly><br>
				<br>
				<br>

				<button class="btn-square-so-pop">出品する</button>

				</div>


			</form>

	</div>




	</body>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">
		</script>



</html>











