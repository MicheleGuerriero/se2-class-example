package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author micheleguerriero
 */
@WebServlet(name = "AddServletBasic", urlPatterns = {"/AddServletBasic"})
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

        if (first.matches("^-?\\d+$") && second.matches("^-?\\d+$")) {
            out.println("<html><body><h1>Here is the sum!</h1>"
                    + "<br>The sum is: "
                    + (Integer.parseInt(first) + Integer.parseInt(second))
                    + "<br>"
                    + "</body></html>"
                    + "<form action=\"add\" method=\"get\">"
                    + "Do another sum"
                    + "<input type=\"submit\">"
                    + "</form><br></body>");
        } else {
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/add.html").forward(request, response);

    }
}
