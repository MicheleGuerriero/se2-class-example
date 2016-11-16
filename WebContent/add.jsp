<!DOCTYPE html>
<%!private static Integer k;%>
<%
	String first = request.getParameter("t1");

	String second = request.getParameter("t2");

	if (Integer.valueOf(first) != null || Integer.valueOf(second) != null) {
		int i = Integer.parseInt(first);
		int j = Integer.parseInt(second);
		k = i + j;
	}
%>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h1>Insert two number to add.</h1>
	<form>
		Enter 1st number : <input type="text" name="t1"> <br>
		Enter 2st number : <input type="text" name="t2"> <br> <input type="submit">
		<%
			if (k != null) {
				out.println("<br> The sum is: " + k + "<br>");
			}
		%>
	</form>
	<br>
	
	<h1>Go to my HTML + Servlet version</h1>
	<form action="add" method="get">
	<input type="submit" value="Go to HTML + Servlet">
	</form><br>
	
</body>
</html>


