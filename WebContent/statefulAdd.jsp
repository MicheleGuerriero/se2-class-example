<%@ page import="it.polimi.classexample.beans.ListElement"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!
	private static ListElement values;

	public void jspInit() {
		try {
			InitialContext ic = new InitialContext();
			values = (ListElement) ic.lookup("java:global/se2-class-example/ListElement");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	%>
<%
	if (request.getParameter("addNum") != null && request.getParameter("t1").matches("^-?\\d+$")) {
		values.addElement(Integer.parseInt(request.getParameter("t1")));
	}

	if (request.getParameter("remNum") != null && request.getParameter("t1").matches("^-?\\d+$")) {
		values.removeElement(Integer.parseInt(request.getParameter("t1")));
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Addition list elements JSP page</title>
</head>
<body>
	<h1>Welcome</h1>
	<form name="number" method="post">
		<input type="text" name="t1"><br> <input type="submit"
			value="Add" name="addNum"><br> <input type="submit"
			value="Remove" name="remNum"><br>
		<%
			if (values != null) {
				List<Integer> nums = values.getElements();
				Integer sum = new Integer(0);
				for (int value : nums) {
					out.println("<br>" + value);
				}
				out.println("<br> Sum=" + values.sumElements());
				out.println("<br> Size=" + nums.size());
			}
		%>
	</form>
	
	<h1>Go to my HTML + Servlet version</h1>
	<form action="add" method="get">
	<input type="submit" value="Go to HTML + Servlet">
	</form><br>
</body>
</html>