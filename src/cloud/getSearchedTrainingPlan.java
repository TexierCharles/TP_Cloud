package cloud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

import cloud.SearchTrainingPlan;

public class getSearchedTrainingPlan extends HttpServlet{
	
	private Entity getedTrainingPlan;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		/*
		 * useless !!!! you will not get the good training plan !! because of this fucking new !!
		SearchTrainingPlan stp = new SearchTrainingPlan();
		resp.getWriter().println(" Your searched training plan is : " +  getSearchedTrainingPlan());*/ 
		
	}

}
