<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String id=request.getParameter("id");
		String pw = request.getParameter("pw");
		String isSaveAgreed = request.getParameter("isSaveAgreed");
		
		if(isSaveAgreed !=null){
			
		Cookie idCookie= new Cookie("id", id);
		Cookie pwCookie= new Cookie("pw", pw);
		
		idCookie.setMaxAge(60*60);
		pwCookie.setMaxAge(60*60);
		
		response.addCookie(idCookie);
		response.addCookie(pwCookie);
		
		Cookie[]cookies= request.getCookies();
		}
		
	%>
	
		<h1>�α��� ��� ȭ��</h1>
	<p><%=request.getParameter("id") %>�� ȯ���մϴ�.</p>
	 
</body>
</html>