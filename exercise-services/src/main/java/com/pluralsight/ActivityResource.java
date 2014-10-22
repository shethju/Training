package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities") //http://localhost:8080/exercise-services/webapi/activities
public class ActivityResource {
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	//http://localhost:8080/exercise-services/webapi/activities/activity
	// With post can be used to create activity
	// With get it can be used to get activities
	// Put can be used to update and create records, but POST is generally used
	// Put is idempodent - if not create, if there update it
	// Put should be used if you are giving id for object
	// Post should be used if server is supplying id to object
	
	//http://localhost:8080/exercise-services/webapi/activities/activity
	/** post - no id, generic url, each call will create new object
	 * http://localhost:8080/exercise-services/webapi/activities/activity/1234
	 * put is to specific url, id in url, will either create or update object
	 * http://localhost:8080/exercise-services/webapi/activities/activity/1234
	 * delete is also specific to url
	 * http://localhost:8080/exercise-services/webapi/activities
	 * delete can be recursive too, idempodent
	 */
	
	// The basic form. Parameters mapped to hash map
	// Headers - Accept = application/json
	// Post - x-www-form-urlencoded ; key/value description/duration
	// Does not bind to object
	// POST v1
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParms) {
		System.out.println(formParms.get("description"));
		System.out.println(formParms.get("duration"));
		Activity activity = new Activity();
		activity.setDescription(formParms.getFirst("description")); // getFirst returns string. get returns object
		activity.setDuration(Integer.parseInt(formParms.getFirst("duration")));
		activityRepository.create(activity);
		return activity;
	}

	// Real world scenario
	// object bound to parameters send
	// When submit Json, it follows desc/ not description. Hence binds to xml definition of activity
	// We now accept JSON input to method
	// header - Content-Type application/json
	// raw/json - { "duration":55, 
	//              "desc":"running"
	//            }
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivity(Activity activity) {
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		return activity;
	}

	// if json - java converts it to xml and then to json, hence need @xmlrootelement etc so that
	// it can convert to xml and eventually to json
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> getAllActivies() {
		return activityRepository.findAllActivities();
	}
	
/*	Straight forward response, no error handling. v1
  	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}") //http://localhost:8080/exercise-services/webapi/activities/1234
	public Activity getActivity(@PathParam ("activityId") String activityId) {
		System.out.println("activity id" + activityId);
		return activityRepository.findActivity(activityId);
		
	}
*/
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}") //http://localhost:8080/exercise-services/webapi/activities/1234
	public Response getActivity(@PathParam ("activityId") String activityId) {
		if (activityId == null || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		System.out.println("activity id" + activityId);
		Activity activity = activityRepository.findActivity(activityId);
		if (activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity(activity).build();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}/user") //http://localhost:8080/exercise-services/webapi/activities/1234
	public User getActivityUser(@PathParam ("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
		
	}
	
	// Method name does not matter. It get bound to path
	@PUT
	@Path("{activity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateActivity(Activity activity) {
		System.out.println(activity.getId());
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		activityRepository.update(activity);
		return Response.ok().entity(activity).build();
	}
	
	@DELETE
	@Path("{activity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam ("activityId") String activityId) {
		System.out.println(activityId);
		activityRepository.delete(activityId);
		return Response.ok().build();
	}

}
