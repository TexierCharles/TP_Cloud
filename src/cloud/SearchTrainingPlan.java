package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SearchTrainingPlan extends HttpServlet{
	
	private DatastoreService datastore;
	
	public static final String SEARCH_TRAINING_ENTITY_KEY="training";
	public static final String SEARCH_TRAINING_ENTITY_PROPERTY_TITLE="title";
	public static final String SEARCH_TRAINING_ENTITY_PROPERTY_DESCRIPTION="description";
	public static final String SEARCH_TRAINING_ENTITY_PROPERTY_DOMAIN="domain";
	
	private final static String SEARCH_TRAINING_KEY="trainingkey";
	
	private JSONObject searchTraining =new JSONObject();
	private String searchAreaFilter; 
	
	private final static String CMD_LABEL="cmd";
	private final static String SEARCHAREA_LABEL="searchArea";
	
	@Override
	public void init() throws ServletException {
		   super.init();
		   //Create a connection to the datastore ONETIME at the init servlet process
		   datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{	
					String searchArea = req.getParameter(SEARCHAREA_LABEL);
					Query q = new Query("training");
					q.addFilter(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE, Query.FilterOperator.EQUAL, searchArea);
					
					PreparedQuery pq = datastore.prepare(q);
					for(Entity result : pq.asIterable())
					{
						System.out.println("result : " + result);
						System.out.println("my training plan title : "+ (String) result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE));
						
						try {
							searchTraining.put("title", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE).toString());
							searchTraining.put("description", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_DESCRIPTION).toString());
							searchTraining.put("domain", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_DOMAIN).toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
					}			
					PrintWriter out = resp.getWriter();
				    out.write(searchTraining.toString());
				    out.flush();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			doPost(req, resp);
		} catch (ServletException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}

}
