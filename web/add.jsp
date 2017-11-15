<%-- 
    Document   : add
    Created on : Nov 15, 2017, 11:32:04 PM
    Author     : micheleguerriero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Insert two number to add.</h1>
        <form>
            Enter 1st number : <input type="text" name="t1"> <br>
            Enter 2st number : <input type="text" name="t2"> <br> <input type="submit">
            <%
                String first = request.getParameter("t1");
                String second = request.getParameter("t2");

                if (first != null && second != null && !first.equals("") && !second.equals("")) {
                    if (first.matches("^-?\\d+$") && second.matches("^-?\\d+$")) {
                        out.println("<br>The sum is: " + (Integer.parseInt(first) + Integer.parseInt(second)) + "<br>");
                    } else {
                        out.println("<br>Error, wrong input format. Should be an integer.<br>");
                    }
                } else {
                    out.println("<br>Insert two valid input integers.<br>");
                }
            %>
        </form>
        <br>

        <h1>Go to my HTML + Servlet version</h1>
        <form action="add" method="get">
            <input type="submit" value="Go to HTML + Servlet">
        </form><br>    </body>
</html>
