<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�� �ۼ� ������</title>
</head>
<body>
	<h2>�� �ۼ� ������</h2>
	<form action="register.do" method="POST">
		<div>
			<p>����: </p>
			<input type= "text" name="boardTitle" placeholder= "���� �Է�" required>
		</div>
		<div>
			<p>�ۼ���: </p>
			<input type="text" name="memberId" value="${sessionScope.memberId }">
		</div>
		
		<div>
			<p>����: </p>
			<textarea rows="20" cols="120" name="boardContent" placeholder="���� �Է�" required></textarea>
			
		</div>
		<div>	
			<input type="submit" value="���">
		</div>
	</form>

</body>
</html>