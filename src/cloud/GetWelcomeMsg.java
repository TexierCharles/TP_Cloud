package cloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

public class GetWelcomeMsg extends HttpServlet {
	public static final String WELCOME_MSG_ENTITY_KEY="WelcomeMsg";
	public static final String WELCOME_MSG_MSG_ENTITY_PROPERTY="msg";
	
	private final static String MSG_KEY="msgkey";
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world from GetWelcomeMsg ");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Cache cache=null;
	    Map props = new HashMap();
	    props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
	    props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
	    try {
	      // R�cup�ration du Cache
	        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	        // cr�ation/r�cup�ration du cache suivant des propri�t�s sp�cifiques
	        cache = cacheFactory.createCache(props);
	        // Si aucune propri�t� n'est sp�cifi�e, 
	        //cr�er/r�cup�rer un cache comme ci-dessous
	        //cache = cacheFactory.createCache(Collections.emptyMap());
	     } catch (CacheException e) {
	         // Traitement en cas d'erreur sur la r�cup�ration/configuration du cache
	     }
	    
		String welcome_msg = getWelcomeMsg(datastore, cache);
		
		resp.getWriter().println("My welcome msg from cache or datastore but anyway added in cache is : " + welcome_msg);
	}
	
	
	private String getWelcomeMsg(DatastoreService datastore, Cache cache) {
		if( cache.get(MSG_KEY)!=null){
			return (String)cache.get(MSG_KEY);
		}else{
			Filter msgWelcomeFilter =
					  new FilterPredicate(GetWelcomeMsg.WELCOME_MSG_MSG_ENTITY_PROPERTY,
					                      FilterOperator.NOT_EQUAL,
					                      null);
			
			// Use class Query to assemble a query
			Query q = new Query(GetWelcomeMsg.WELCOME_MSG_ENTITY_KEY).setFilter(msgWelcomeFilter);
			
			// Use PreparedQuery interface to retrieve results
			PreparedQuery pq = datastore.prepare(q);

			String welcomeString="";
			for (Entity result : pq.asIterable()) {
				welcomeString = (String) result.getProperty(GetWelcomeMsg.WELCOME_MSG_MSG_ENTITY_PROPERTY);
			}
			
			cache.put(MSG_KEY, welcomeString);
			return welcomeString;
		}
	}
	
	
}
