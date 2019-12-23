<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<!--メニュー画面-->

<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="../css/menu.css">
<title></title>


</head>




<body>

	<a href="../hoomPege"> <img src="../img/Title.png" alt="タイトル"
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

		<br> <br>

		<nav>
			<br> <br>
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
			<div id="text">落札中</div>
			<br> <br>
	</div>

	</div>

	<!--商品並びの起点-->

	<div id="block1">





		<h2>パソコン</h2>
		<div class="q1">
			<img src="../img/pc1.jpg" alt="タイトル" width="200" height="200"></a><br>
		</div>
		<div class=q2>
			現在○○円<br> 入札回数△△ 残り時間○○日
		</div>
		<br> 商品情報<br> サンプルサンプルサンプルサンプルサンプルサンプルサンプルサンプル



		<!--コピペで増やせる-->


		<c:forEach begin="1" end="4">


			<div id="q3">
				<h2>パソコン</h2>
				<br>
				<div class="q1">
					<img src="../img/pc1.jpg" alt="タイトル" width="200" height="200"><br>
				</div>
				<div class=q2>
					現在○○円<br> 入札回数△△ 残り時間○○日
				</div>
				<br> 商品情報<br> サンプルサンプルサンプルサンプルサンプルサンプルサンプルサンプル
			</div>
		</c:forEach>



	<div id="botan">
		<button onclick="location.href='../hoomPege'">戻る</button>
	</div>


	</div>


</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">


</script>
</html>