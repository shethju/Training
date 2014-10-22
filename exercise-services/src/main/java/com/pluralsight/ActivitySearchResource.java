package com.pluralsight;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;
// When media type is json, we need to add jersey-json jar (jersey-media-moxy)
// @xmlelement and @xmlrootelement in xml will be used since jaxb converts it to xml to json
// look in pom.xml 
// postman chrome plugin
@Path("search/activities")
public class ActivitySearchResource {
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response searchForActivities(@QueryParam(value="description") List<String> descriptions ) {
		System.out.println(descriptions);
		List<Activity> activities = activityRepository.findByDescription(descriptions);
		System.out.println(activities);
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
	}
}
