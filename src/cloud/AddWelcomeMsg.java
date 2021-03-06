package cloud;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AddWelcomeMsg extends HttpServlet {
	public static final String WELCOME_MSG_ENTITY_KEY = "WelcomeMsg";
	public static final String WELCOME_MSG_MSG_ENTITY_PROPERTY = "your training";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world from AddWelcomeMsg ");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity welcomeMsg = new Entity(WELCOME_MSG_ENTITY_KEY);
		welcomeMsg.setProperty(WELCOME_MSG_MSG_ENTITY_PROPERTY, req.getParameter(WELCOME_MSG_MSG_ENTITY_PROPERTY));

		datastore.put(welcomeMsg);

	}
}
