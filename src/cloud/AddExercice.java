package cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AddExercice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatastoreService datastore;
	private final static String EX_CMD_LABEL = "cmd";
	private final static String TITLE_LABEL = "title";
	private final static String EX_TITLE_LABEL = "ex_title";
	private final static String EX_DESCRIPTION_LABEL = "description_ex";
	private final static String EX_TIME_LABEL = "duree_ex";
	private final static String EX_POSITION_LABEL = "position_ex";

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

		String cmdValue = req.getParameter(EX_CMD_LABEL);
		if ("addExercice".equals(cmdValue)) {
			// Retrieve Parameter to HTTP request
			String titleTraining = req.getParameter(TITLE_LABEL);
			String exercices = req.getParameter("exercices");
			String exos = "{exercices:" + exercices + "}";
			System.out.println(exos);

			try {
				JSONObject jsonObject = new JSONObject(exos);
				JSONArray jsonArray = jsonObject.getJSONArray("exercices");
				System.out.println(jsonObject.toString());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject curr = jsonArray.getJSONObject(i);
					String exerciceTitle = curr.getString("title");
					String exerciceDescription = curr.getString("description");
					String exerciceDuree = curr.getString("duree");
					String exercicePosition = curr.getString("position");

					Entity exercice = new Entity("exercice");
					exercice.setProperty(EX_TITLE_LABEL, exerciceTitle);
					exercice.setProperty(EX_DESCRIPTION_LABEL, exerciceDescription);
					exercice.setProperty(EX_TIME_LABEL, exerciceDuree);
					exercice.setProperty(EX_POSITION_LABEL, exercicePosition);
					exercice.setProperty(TITLE_LABEL, titleTraining);

					datastore.put(exercice);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			Query q = new Query("exercice");
			PreparedQuery pq = datastore.prepare(q);
			for (Entity result : pq.asIterable()) {
				System.out.println("my exercice plan title : "
						+ (String) result.getProperty(""));
			}
		}
	}
}