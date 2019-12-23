<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>


<!--落札情報確認-->


<html lang="ja">

	<head>

		<meta charset="UTF-8">
		<link rel="stylesheet" href="../../css/kau.css">
		<title></title>


		<style>

/*検索欄　メニューのブロック*/
#block1{

}

/*画像のブロック*/
#block2-A-A{
	display: inline-block;
	width: 50%;
	padding-left: 10%;
	margin: auto;
}

/*画像横のテキストブロック*/
#block2-A-B{
    padding-top: 100px;
    vertical-align: top;
    display: inline-block
}

/*画像下　説明欄*/
#block3{
	width: 1000px;
	height: 200px;
	border: solid;
	border-radius: 10px;
}

#botan{
	padding-left: 50%;
}

h1{
	padding-left:30%;
}


/*検索ボックス*/


#kennsaku{
    display: inline-block;
    vertical-align: top;
    width: 1000px;
}

#sbox{
    display: inline-block;
	left:50px;
	outline:0;/*クリック時の青い枠線消す*/
	height:50px;/*検索ボックスの高さ*/
	width:60%;
	padding:0 10px;/*テキスト位置調整*/
	border-radius:2px 0 0 2px;/*検索ボックスの角を丸める*/
	background:#eee;/*検索ボックスの背景カラー*/
}
/*検索ボタン*/
#sbtn{
    display: inline-block;
	width:70px;/*検索ボタンの横幅*/
    height:50px;/*検索ボタンの縦幅*/
	border-radius:0 2px 2px 0;/*検索ボタンの角を丸める*/
	background:#7fbfff;/*検索ボタンの背景カラー*/
	border:none;/*検索ボタンの枠線を消す*/
	color:#fff;/*検索ボタンのテキストカラー*/
	font-weight:bold;/*検索ボタンのテキスト太字*/
	font-size:16px;/*検索ボタンのフォントサイズ*/
}

/*検索ボタンマウスオーバー時*/
#sbtn:hover{
	color:#666;/*検索ボタンマウスオーバー時のフォントカラー*/
}


/*メニュー画面*/
#menu{
    display: inline-block;
    vertical-align: top;
    clear: none;
	width: 200px;
}

nav li{

	list-style: none;/*リストの・消去*/
}


a{
	text-decoration: none;/*リストの下線消去*/
}

/*メニュー装飾*/

#menu ul {
	padding: 0;
	position: relative;
}

#menu ul li {
	line-height: 1.5;
	padding: 0.5em 0.5em 0.5em 1.7em;
	list-style-type: none!important;
	background: -webkit-linear-gradient(top, #whitesmoke 0%, whitesmoke 100%);/*グラデーション*/
	background: linear-gradient(to bottom, whitesmoke 0%, #dadada 100%);/*グラデーション*/
	text-shadow: 1px 1px 1px whitesmoke;/*文字を立体的に*/
	color: black;
}

#menu ul li:before {
	font-family: "Font Awesome 5 Free";
	content: "\f138";
	position: absolute;
	left : 0.5em; /*左端からのアイコンまでの距離*/
	color: orange; /*アイコン色*/
}

/*落札*/

.btn-square-so-pop {
    widows: 200px;
    height: 80px;
	margin: 10px 10px 10px 0px;
	position: relative;
	display: inline-block;
	padding: 0.5em 0.5em;
    text-decoration: none;
    text-align: center;
	color: #FFF;
	background: #fd9535;/*色*/
	border-radius: 4px;/*角の丸み*/
	box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), inset 0 -2px 0 rgba(0, 0, 0, 0.05);
	font-weight: bold;
	font-size: 30px;
	border: solid 2px #d27d00;/*線色*/
}

.btn-square-so-pop:active {
  /*押したとき*/
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);
}
		</style>


    </head>

	<body>


		<!--ホームリンクタイトル画像-->

		<a href="hoomPege">
		<img src="img/Title.png" alt="タイトル" width="200" height="80"></a><br>


				<div id="block1">
					<!--検索欄-->

					<div id="kennsaku">
						<form id="form1">
							<input id="sbox"  id="s" name="s" type="text" placeholder="キーワードを入力" />
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

		<br>

			<input type="hidden" value = "${fn:escapeXml(purchaseDisplay.productId)}" name = "productId">

			<br>
			<button name="action" value="delete">削除</button>
			<button name="action" value="hidden">非表示</button>

		</form>



		</c:when>

		</c:choose>

				<div id="block2">


						<div id="block2-A-A">
							<h1>${fn:escapeXml(purchaseDisplay.productName)}</h1>

							<div id="img"><img src="${fn:escapeXml(purchaseDisplay.productImg)}" alt=商品 width="300" height="300"></div>
							<br>
						</div>


						<div id="block2-A-B">
							入札回数　　　残り時間<br>
							　〇〇回数　　　〇〇時間<br>
							<br>
							価格<br>　
							${fn:escapeXml(purchaseDisplay.price)}円<br>
							送料${fn:escapeXml(purchaseDisplay.postage)}負担<br>
							<br>

							<form id="searchForm" action="Successful_did" method = post>

								<input name="contractPrice" type="text" placeholder="落札金額を入力" />
								<input type="hidden" name="userNo" value="${fn:escapeXml(purchaseDisplay.userNo)}">
								<input type="hidden" name="productId" value="${fn:escapeXml(purchaseDisplay.productId)}">

								<br>

								<button class = "btn-square-so-pop">今すぐ落札する</button>

							</form>

							<br>


							<br>

							出品者${fn:escapeXml(purchaseDisplay.userName)}さん<br>
							<br>
							出品者へのお問合せ<br>
							${fn:escapeXml(purchaseDisplay.mail)}


						</div>

						<br>



					<div id="block3">

					${fn:escapeXml(purchaseDisplay.description)}



					</div>

					<br>
					<br>

					</div>



					<div id="botan"><button onclick="location.href='../hoomPege'">戻る</button></div>

	</body>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.js">
		</script>



</html>











