<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>HW2_practice</title>
</head>
<body>
	
			<h1>����� ������ �Է����ּ���.</h1>
	<form action="HW2_result_practice.jsp" method ="get"><!--form action �� jsp���ϵ� ����!(�������� servlet���ϸ� ���)  -->
		�̸�: <input type ="text" name ="name" placeholder="�̸�"><br>
		��ȣ: <input type ="text" name ="phone" placeholder="��ȭ��ȣ"><br>
		�̸���: <input type ="text" name ="email" placeholder="�̸���"><br>
		
		<input type ="submit" value ="����">
	</form>
	
</body>
</html>