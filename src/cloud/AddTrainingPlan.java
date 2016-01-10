package cloud;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class AddTrainingPlan extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatastoreService datastore;
	private final static String CMD_LABEL = "cmd";
	private final static String TITLE_LABEL = "title";
	private final static String DESCRIPTION_LABEL = "description";
	private final static String DOMAIN_LABEL = "domain";
	@Override
	public void init() throws ServletException {
		super.init();
		// Create a connection to the datastore ONETIME at the init servlet
		// process
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cmdValue = req.getParameter(CMD_LABEL);
		if ("addTrainingPlan".equals(cmdValue)) {
			// Retreive Parameter to HTTP request
			String titleValue = req.getParameter(TITLE_LABEL);
			String descriptionValue = req.getParameter(DESCRIPTION_LABEL);
			String domainValue = req.getParameter(DOMAIN_LABEL);
			
			System.out.println("title " + titleValue);
			System.out.println("description " + descriptionValue);
			System.out.println("domain " + domainValue);

			Entity training = new Entity("training");
			training.setProperty(TITLE_LABEL, titleValue);
			training.setProperty(DESCRIPTION_LABEL, descriptionValue);
			training.setProperty(DOMAIN_LABEL, domainValue);

			datastore.put(training);

			Query q = new Query("training");
			PreparedQuery pq = datastore.prepare(q);
			for (Entity result : pq.asIterable()) {
				System.out.println("my training plan title : "
						+ (String) result.getProperty(TITLE_LABEL));
			}
		}
	}
}
