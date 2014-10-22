package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;

// Mainly for testing. Client is similar to browser
public class ActivityClient {
	// java jersey client
	private Client client;
	
	public ActivityClient() {
		client = ClientBuilder.newClient();
	}
	
	public Activity get(String id) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// request - get default type back (xml). bind to activity
		Activity response = target.path("activities/" + id).request().get(Activity.class);
		// Response comes back as xml. Use get(string.class) to dump value
		String response1 = target.path("activities/" + id).request().get(String.class);
		System.out.println(response1);
		String response2 = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(response2);
		// Object response
		Response response3 = target.path("activities/" + id).request().get(Response.class);
		if (response3.getStatus() != 200) {
			throw new RuntimeException();
		}
		// return response; // without response object
		return response3.readEntity(Activity.class);
	}
	
	public List<Activity> get() {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// wont work
		//List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(List.class);
		List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>(){});
		return response;
	}

	public Activity create(Activity activity) {
		// TODO Auto-generated method stub
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// request - get default type back (xml). bind to activity
		Response response = target.path("activities/activity").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		// Response comes back as xml. Use get(string.class) to dump value
		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		// TODO Auto-generated method stub
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// request - get default type back (xml). bind to activity 
		// method type is put
		Response response = target.path("activities/" +activity.getId()).
				request(MediaType.APPLICATION_JSON).put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		return response.readEntity(Activity.class);
	}

	public void delete(String activityId) {
		// TODO Auto-generated method stub
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/" + activityId).
				request(MediaType.APPLICATION_JSON).delete();
		System.out.println(response.getStatus());
		
	}
}
