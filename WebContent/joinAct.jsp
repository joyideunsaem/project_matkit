<%@page import="home.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="home.member.MemberDTO" />
<jsp:setProperty property="*" name="user" />

<%
	MemberDAO dao = new MemberDAO();

	int row = dao.insertMember(user);
	// 실패시 0 , 성공시 0 이 아닌 수 반환
	
	 out.println("<script>");
	
	if(row !=0 ){
		out.println("alert('회원가입 성공')");
		out.println("location.href='login.jsp'");
	}
	else{
		out.println("alert('회원가입 실패')");
		out.println("location.href='join.jsp'");
	}
	
	out.println("</script>");
%>

</body>
</html>