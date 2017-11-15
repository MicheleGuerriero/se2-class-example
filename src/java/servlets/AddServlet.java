package servlets;

import beans.AdditionBean;
import entities.IntPair;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jms.Queue;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author micheleguerriero
 */
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private AdditionBean addBean;

	@Resource(mappedName = "jms/testQueue")
	private Queue queue;

	@Resource(mappedName = "jms/QueueConnectionFactory")
	private QueueConnectionFactory queueConnectionFactory;

	public AddServlet() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setBufferSize(8192);

		PrintWriter out = response.getWriter();
		String first = request.getParameter("t1");
		String second = request.getParameter("t2");

		if (	!(first.matches("^-?\\d+$") && second.matches("^-?\\d+$"))) {
			request.getRequestDispatcher("/error.html").forward(request, response);
		} else {
			
			QueueConnection queueConnection = null;
			try {
				queueConnection = queueConnectionFactory.createQueueConnection();
				queueConnection.start();
				QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				QueueSender sender = queueSession.createSender(queue);

				TextMessage msg = queueSession.createTextMessage();
				msg.setText("Received valid POST request for AddServlet");
				msg.setStringProperty("method", "POST");
				msg.setIntProperty("first", Integer.parseInt(first));
				msg.setIntProperty("second", Integer.parseInt(second));


				sender.send(msg);
			} catch (JMSException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (queueConnection != null) {
						queueConnection.close();
					}
				} catch (JMSException e) { // ignore
				}
			}
			
			addBean.setI(Integer.parseInt(first));
			addBean.setJ(Integer.parseInt(second));
			addBean.add();

			List<IntPair> storedPairs = addBean.getPairs();

			out.println("<html><body><h1>Here is the sum!</h1>" + "<br>The sum is: " + addBean.getK() + "<br>"
					+ "<form action=\"add\" method=\"get\">" + "Do another sum" + "<input type=\"submit\">"
					+ "</form><br><h1>Stored Pairs</h1>");

			for (IntPair p : storedPairs) {
				out.println("<br>" + p.getFirstOperand() + "," + p.getSecondOperand());
			}

			out.println("</body></html>");

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QueueConnection queueConnection = null;
		try {
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueConnection.start();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = queueSession.createSender(queue);

			TextMessage msg = queueSession.createTextMessage();
			msg.setText("Received request for AddServlet");
			msg.setStringProperty("method", "GET");

			sender.send(msg);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (queueConnection != null) {
					queueConnection.close();
				}
			} catch (JMSException e) { // ignore
			}
		}
		request.getRequestDispatcher("/add.html").forward(request, response);

	}
}
