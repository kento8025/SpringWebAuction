
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="../css/kennsakuhonn.css">
<title>検索結果(パソコン)</title>

<style>


/*検索欄　メニューのブロック*/
#block1 {

}

/*カテゴリ　メニュー等のブロック*/
#block2 {
	display: inline-block;
}

/*オークションのブロック*/
#block3 {
	vertical-align: top;
	padding-left: 200px;
	display: inline-block;
}

/*商品画像のブロック*/
.q1 {
	float: left;
	padding-right: 200px;
}

/*商品のテキストボックス*/
.q2 {
	float: left;
	padding-top: 20px;
	width: 300px;


}
/*商品二つ目以降ブロック*/
#q3 {
	display: block;
	padding-top: 100px;
	width: 80％;

}

/*検索ボックス*/
#kennsaku {
	display: inline-block;
	vertical-align: top;
	width: 1000px;
}

#sbox {
	display: inline-block;
	left: 50px;
	outline: 0; /*クリック時の青い枠線消す*/
	height: 50px; /*検索ボックスの高さ*/
	width: 60%;
	padding: 0 10px; /*テキスト位置調整*/
	border-radius: 2px 0 0 2px; /*検索ボックスの角を丸める*/
	background: #eee; /*検索ボックスの背景カラー*/
}
/*検索ボタン*/
#sbtn {
	display: inline-block;
	width: 70px; /*検索ボタンの横幅*/
	height: 50px; /*検索ボタンの縦幅*/
	border-radius: 0 2px 2px 0; /*検索ボタンの角を丸める*/
	background: #7fbfff; /*検索ボタンの背景カラー*/
	border: none; /*検索ボタンの枠線を消す*/
	color: #fff; /*検索ボタンのテキストカラー*/
	font-weight: bold; /*検索ボタンのテキスト太字*/
	font-size: 16px; /*検索ボタンのフォントサイズ*/
}

/*検索ボタンマウスオーバー時*/
#sbtn:hover {
	color: #666; /*検索ボタンマウスオーバー時のフォントカラー*/
}

/*メニュー画面*/
#menu {
	display: inline-block;
	vertical-align: top;
	clear: none;
	width: 200px;
}

nav li {
	list-style: none; /*リストの・消去*/
}

a {
	text-decoration: none; /*リストの下線消去*/
}

/*メニュー装飾*/
#menu ul {
	padding: 0;
	position: relative;
}

#menu ul li {
	line-height: 1.5;
	padding: 0.5em 0.5em 0.5em 1.7em;
	list-style-type: none !important;
	background: -webkit-linear-gradient(top, #whitesmoke 0%, whitesmoke 100%);
	/*グラデーション*/
	background: linear-gradient(to bottom, whitesmoke 0%, #dadada 100%);
	/*グラデーション*/
	text-shadow: 1px 1px 1px whitesmoke; /*文字を立体的に*/
	color: black;
}

#menu ul li:before {
	font-family: "Font Awesome 5 Free";
	content: "\f138";
	position: absolute;
	left: 0.5em; /*左端からのアイコンまでの距離*/
	color: orange; /*アイコン色*/
}

/*対象商品のチェックボックス装飾*/
.cp_ipcheck {
	width: 250px;
	text-align: left;
}

.cp_ipcheck ul {
	margin: 0.5rem 0.5rem 2rem 0.5rem;
	padding: 0.5rem 1rem;
	list-style: none;
	border: 1px solid #cccccc;
}

.cp_ipcheck .list_item {
	margin: 0 0 0.5rem 0;
	padding: 0;
}

.cp_ipcheck label {
	line-height: 135%;
	position: relative;
	margin: 0.5rem;
	cursor: pointer;
}

.cp_ipcheck .option-input05 {
	position: relative;
	margin: 0 1rem 0 0;
	cursor: pointer;
}

.cp_ipcheck .option-input05:before {
	position: absolute;
	z-index: 1;
	top: 0.125rem;
	left: 0.1875rem;
	width: 0.75rem;
	height: 0.375rem;
	content: '';
	-webkit-transition: -webkit-transform 0.4s
		cubic-bezier(0.45, 1.8, 0.5, 0.75);
	transition: transform 0.4s cubic-bezier(0.45, 1.8, 0.5, 0.75);
	-webkit-transform: rotate(-45deg) scale(0, 0);
	transform: rotate(-45deg) scale(0, 0);
	border: 2px solid #da3c41;
	border-top-style: none;
	border-right-style: none;
}

.cp_ipcheck .option-input05:checked:before {
	-webkit-transform: rotate(-45deg) scale(1, 1);
	transform: rotate(-45deg) scale(1, 1);
}

.cp_ipcheck .option-input05:after {
	position: absolute;
	top: -0.125rem;
	left: 0;
	width: 1rem;
	height: 1rem;
	content: '';
	cursor: pointer;
	border: 2px solid #f2f2f2;
	background: #ffffff;
}

/*カテゴリ*/
#kategori {
	width: 200px;
}

#kategori ul {
	padding: 0;
	position: relative;
}

#kategori ul li {
	color: black;
	border-left: solid 8px orange; /*左側の線*/
	background: whitesmoke; /*背景色*/
	margin-bottom: 5px; /*下のバーとの余白*/
	line-height: 1.5;
	border-radius: 0 15px 15px 0; /*右側の角だけ丸く*/
	padding: 0.5em;
	list-style-type: none !important;
}

.cp_ipradio {
	width: 300px;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.cp_ipradio:before, .cp_ipradio:after {
	-webkit-box-sizing: inherit;
	box-sizing: inherit;
}

.cp_ipradio .box {
	width: 90%;
	margin: 2em auto;
	text-align: left;
	border: 1px solid white;
	border-radius: 3px;
	background: #ffffff;
}

.cp_ipradio input[type=radio] {
	display: none;
}

.cp_ipradio label:focus, .cp_ipradio label:hover, .cp_ipradio label:active,
	.cp_ipradio input:checked+label {
	color: #4400ff;
}

.cp_ipradio label:focus:before, .cp_ipradio label:hover:before,
	.cp_ipradio label:active:before, .cp_ipradio input:checked+label:before
	{
	border-color: #4400ff;
	background: #ffffff;
}

.cp_ipradio label {
	font-size: 1em;
	font-weight: bold;
	line-height: 1;
	position: relative;
	display: block;
	overflow: hidden;
	padding: 1em 1em 1em 3em;
	cursor: pointer;
	-webkit-transition: all 0.15s ease;
	transition: all 0.15s ease;
	white-space: nowrap;
	text-overflow: ellipsis;
	background: #ffffff;
}

.cp_ipradio label:before {
	position: absolute;
	top: 1em;
	left: 1em;
	width: 10px;
	height: 10px;
	content: '';
	border: 0.2em solid #cccccc;
	border-radius: 50%;
}

.cp_ipradio input:checked+label:before {
	border-color: #4400ff;
	background: #4400ff;
}

.cp_ipradio input:disabled+label {
	cursor: not-allowed;
	color: rgba(0, 0, 0, 0.5);
	background: #efefef;
}

.cp_ipradio input:disabled+label:hover {
	border-color: rgba(0, 0, 0, 0.1);
}

.cp_ipradio input:disabled+label:before {
	border-color: #ffffff;
	background: #ffffff;
}
</style>



</head>


<body>

	<!--ホームリンクタイトル画像-->

	<a href="hoomPege"> <img src="img/Title.png" alt="タイトル"
		width="200" height="80"></a>
	<br>


14229000

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

		<br>
		<br>

			メニュー表
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

					<a href="ExhibitPurchase?productId=${fn:escapeXml(product.productId)}"> <img src="${fn:escapeXml(product.productImg)}" alt="商品画像" width="200" height="200"></a>

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