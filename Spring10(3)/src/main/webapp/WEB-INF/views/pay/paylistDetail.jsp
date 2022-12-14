<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!--  fmt 는 포멧 형태 바꾸기 (날짜 같은거) -->    
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
/* ========기본 설정==============  */
body{

font-family: NanumSquareNeo;
}

table{
	text-align: center;
	margin-left:auto; 
    margin-right:auto;	
    border-collapse: collapse;
    border-radius: 16px;
}


input{
	margin-right: 10px;
}
.drink{
  display: inline-flex;
  justify-content: right;
  margin-left: 50px;
  margin-right: 40px;
  padding-right: 100px;
  
}
.shop{
  margin-left: 450px;
  margin-right: 250px;
}


@font-face {
    font-family: 'NanumSquareNeo';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/NanumSquareNeo-Variable.woff2') format('woff2');
    
}

a {
  text-decoration: none;
  active-color { color:#000; }  
}

ul{
	list-style-type: none;
}

li{
	display: inline-block;	
} 

/*===========타이틀 설정==================  */

.Title{
  margin: 0;
  padding: 0;
  font-size:20px;  
  margin-left: 550px;
  margin-right: 340px;
  text-decoration: none;/*a href 밑줄 등 글자 꾸밈 없음*/
  padding-top: 50px; 
}

/* ==============카테고리명 + 더보기 버튼 나열============= */
.Title2{
  font-size:15px;  
  margin-left: 508px;
  margin-right: 350px;
  font-weight: 580;
}

/* ==============class=".btn-3d.red" 버튼 효과 ============== */
.btn-3d {
  position: relative;
  display: inline-block;
  font-size: 10px;
  padding: 5px 18px;
  color: white;
  border-radius: 30px;
  text-align: center;
  transition: top .01s linear;
  text-shadow: 0 1px 0 rgba(0,0,0,0.15);
}
.btn-3d.red:hover    {background-color: #A52A2A;}
.btn-3d:active {
  top: 9px;
}
.btn-3d.red {
  background-color: #e74c3c;
  box-shadow: 0 0 0 1px #c63702 inset,
        0 0 0 2px rgba(255,255,255,0.15) inset,
        0 8px 0 0 #C24032,
        0 8px 0 1px rgba(0,0,0,0.4),
        0 8px 8px 1px rgba(0,0,0,0.5);
}
.btn-3d.red:active {
  box-shadow: 0 0 0 1px #c63702 inset,
        0 0 0 2px rgba(255,255,255,0.15) inset,
        0 0 0 1px rgba(0,0,0,0.4);
}
	
</style>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


<script>
$("a").click(function() {
    toggleClass(".active-color");
});
</script>


<meta charset="UTF-8">
<title>결제 목록</title>

</head>
<body>

	<%@ include file ="../header.jspf" %>

	<div class="Title">
	<h3>결제 목록</h3>
	</div>
	<br>
	
	<ul class="Title2" >
	<li style="margin-right: 15px;">
	<a href="../product/productlist" class="btn-3d red" style="color: white">상품 목록</a>
	</li>
	<li style="margin-right: 15px;">
	<a href="../event/eventlist" class="btn-3d red" style="color: white">EVENT LIST</a>		
	</li>
	<li style="margin-right: 15px;">	
	<a href="../product/productregister" class="btn-3d red">상품등록하기</a>
	</li>
	<li style="margin-right: 15px;">
	<a href="../cart/cartlist" class="btn-3d red">장바구니로 가기 </a>
	</li>
	</ul>
	
	<br>
	<hr>
	
	<br>
	
	<table id="addList" border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>주문번호</th>
			<th>상품명</th>
			<th>상품</th>
			<th>결제금액</th>
		</tr>
		<tbody id="listBody">
		<c:forEach items="${listPayDetail}" var="listPayDetail">
		
		<tr>
			<td>			
				${listPayDetail.payDetailNum }
			</td>
			<td>
				${listPayDetail.productName }
			</td>
			<td>
				<img src="display?fileName=/${listPayDetail.productUrl }" width="200px" height="150px">
			</td>
			<td>
				${listPayDetail.money }
			</td>
		</tr>		
		
		</c:forEach> 
        </tbody>
	</table>	
	
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<%@ include file ="../footer.jspf" %>	
	
</body>


</html>





