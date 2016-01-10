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
	
	public static final String SEARCH_EXERCICE_ENTITY_PROPERTY_TITLE_TRAINING="title";
	public static final String SEARCH_EXERCICE_ENTITY_PROPERTY_DESCRIPTION="description_ex";
	public static final String SEARCH_EXERCICE_ENTITY_PROPERTY_TITLE="ex_title";
	public static final String SEARCH_EXERCICE_ENTITY_PROPERTY_DUREE="duree_ex";
	public static final String SEARCH_EXERCICE_ENTITY_PROPERTY_POSITION="position_ex";
	
	private final static String SEARCH_TRAINING_KEY="trainingkey";
	
	private JSONObject searchTraining =new JSONObject();
	private JSONObject searchExercice =new JSONObject();
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
					
						try {
							System.out.println(result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE).toString());
							searchTraining.put("title", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE).toString());
							searchTraining.put("description", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_DESCRIPTION).toString());
							searchTraining.put("domain", result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_DOMAIN).toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// System.out.println("result : " + result);
						System.out.println("my training plan title : "+ (String) result.getProperty(SEARCH_TRAINING_ENTITY_PROPERTY_TITLE));
						System.out.println("JSON training : "+ searchTraining.toString());
					}				
					
					
					// new query to get exercice
					Query qE = new Query("exercice");
					qE.addFilter(SEARCH_EXERCICE_ENTITY_PROPERTY_TITLE_TRAINING, Query.FilterOperator.EQUAL, searchArea);
					
					PreparedQuery pqE = datastore.prepare(qE);
					
					for(Entity resultE : pqE.asIterable())
					{
						
						try {
							System.out.println("searchExercice : title_ex result"+ resultE );
							searchExercice.put("ex_title", resultE.getProperty(SEARCH_EXERCICE_ENTITY_PROPERTY_TITLE).toString());
							searchExercice.put("description_ex", resultE.getProperty(SEARCH_EXERCICE_ENTITY_PROPERTY_DESCRIPTION).toString());
							searchExercice.put("duree_ex", resultE.getProperty(SEARCH_EXERCICE_ENTITY_PROPERTY_DUREE).toString());
							searchExercice.put("position_ex", resultE.getProperty(SEARCH_EXERCICE_ENTITY_PROPERTY_POSITION).toString());
							
							
							System.out.println("searchExercice : ex_title"+ searchExercice.toString() );
							searchTraining.put("exercice", searchExercice);
							
							
						/*	for(int i = 0; i < searchExercice.length(); i++) {
							    searchExercice.remove("title_ex");
							    searchExercice.remove("description_ex");
							    searchExercice.remove("duree_ex");
							    searchExercice.remove("position_ex");
							}*/
						
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// System.out.println("result : " + result);
						System.out.println("my exercice plan title : "+ (String) resultE.getProperty(SEARCH_EXERCICE_ENTITY_PROPERTY_TITLE));
						System.out.println("JSON exercice : "+ searchExercice.toString());
					}	
					
					
					
					PrintWriter out = resp.getWriter();
				    out.write(searchTraining.toString());
				    out.flush();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		System.out.println("servlet do get");
		/*try {
			doPost(req, resp);
		} catch (ServletException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
	
			e.printStackTrace();
		}*/
	}

}
