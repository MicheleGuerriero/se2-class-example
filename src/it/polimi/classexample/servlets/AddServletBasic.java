package it.polimi.classexample.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class AddServletBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddServletBasic() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setBufferSize(8192);

		PrintWriter out = response.getWriter();
		String first = request.getParameter("t1");
		String second = request.getParameter("t2");

		if (first == "" || second == "" || !StringUtils.isNumeric(first) || !StringUtils.isNumeric(second)) {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} else {

			out.println("<html><body><h1>Here is the sum!</h1>" + "<br>The sum is: " + (Integer.parseInt(first) + Integer.parseInt(second)) + "<br>"
					+ "</body></html>" + "<form action=\"add\" method=\"get\">" + "Do another sum"
					+ "<input type=\"submit\">" + "</form><br></body>");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/add.jsp").forward(request, response);

	}
}
