package com.pluralsight.client;

import static org.junit.Assert.assertNotNull; // Import static

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;

public class ActivityClientTest {

	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("1234"); // passes no issue
		System.out.println(activity);
		assertNotNull(activity);
	}

	@Test(expected=RuntimeException.class)
	public void testGetWithException() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("123"); // passes no issue		
	}
	
	@Test
	public void testGetList() {
		ActivityClient client = new ActivityClient();
		List<Activity> activities = client.get();
		assertNotNull(activities);
	}
	
	@Test
	public void testCreateClient() {
		ActivityClient client = new ActivityClient();
		Activity activity = new Activity();
		activity.setDescription("running");
		activity.setDuration(10);
		activity = client.create(activity);
		assertNotNull(activity);
	}
	
	@Test
	public void testSearchClient() {
		ActivitySearchClient client = new ActivitySearchClient();
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");
		
		// Test for ranges
		
		List<Activity> activities = client.search(param, searchValues);
		System.out.println(activities);
	}
	@Test
	public void testPut() {
		Activity activity = new Activity();
		activity.setDescription("running");
		activity.setId("3456");
		activity.setDuration(10);
		ActivityClient client = new ActivityClient();
		client.update(activity);
	}
	
	@Test
	public void testDelete() {
		ActivityClient client = new ActivityClient();
		client.delete("1234");
	}
}
