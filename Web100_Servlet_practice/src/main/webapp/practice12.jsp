<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<%
	Cookie[]cookies= request.getCookies();

	String id="";
	String pw="";
	
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("ID")){
				id= cookie.getValue();
			}else if(cookie.getName().equals("PW")){
				pw= cookie.getValue();
			}
		}
	}
	
%>
</head>
<body>
	
		<form action="12_cookie_practice_answer.jsp" method="post">
		 ���̵� <br>  <input type="text" name="id" placeholder="���̵� �Է�" value="<%=id%>"><br>
		 ��й�ȣ <br> <input type="password"name="pw" placeholder="��й�ȣ �Է�" value="<%=pw%>"><br> 
		 			<input	type="checkbox" name="isSaveAgreed" value="agreed"> ���̵� ����<br>
		<br> <input type="submit" value="�α���">
	</form>
	
</body>
</html>