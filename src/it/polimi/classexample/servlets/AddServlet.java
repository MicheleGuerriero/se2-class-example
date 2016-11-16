package it.polimi.classexample.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.classexample.beans.AdditionBean;
import it.polimi.classexample.entities.IntPair;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private AdditionBean addBean;

	public AddServlet() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setBufferSize(8192);

		PrintWriter out = response.getWriter();
		String first = request.getParameter("t1");
		String second = request.getParameter("t2");

		if (Integer.valueOf(first) == null || Integer.valueOf(second) == null) {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} else {
			addBean.setI(Integer.parseInt(first));
			addBean.setJ(Integer.parseInt(second));
			addBean.add();
			
			List<IntPair> storedPairs = addBean.getPairs();
			

			out.println("<html><body><h1>Here is the sum!</h1>" + "<br>The sum is: " + addBean.getK() + "<br>"
					+"<form action=\"add\" method=\"get\">" + "Do another sum"
					+ "<input type=\"submit\">" + "</form><br><h1>Stored Pairs</h1>");
			
			for(IntPair p: storedPairs){
				out.println("<br>" + p.getFirstOperand() + "," + p.getSecondOperand());
			}
			
			out.println("</body></html>");

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/add.html").forward(request, response);

	}
}
