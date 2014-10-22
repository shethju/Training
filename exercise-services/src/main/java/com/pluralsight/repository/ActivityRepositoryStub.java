package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	@Override
	public Activity update(Activity activity) {
		// TODO Auto-generated method stub
		System.out.println("update activity to db");
		return activity;
	}
	@Override
	public void create(Activity activity) {
		// write to db		
	}
	
	/* (non-Javadoc)
	 * @see com.pluralsight.repository.ActivityRepository#findAllActivities()
	 */
	@Override
	public List<Activity> findAllActivities() {
		ArrayList<Activity> activities = new ArrayList<Activity>();
		Activity act1 = new Activity();
		act1.setDescription("Swimming");
		act1.setDuration(5);
		activities.add(act1);
		Activity act2 = new Activity();
		act2.setDescription("Running");
		act2.setDuration(55);
		activities.add(act2);
		return activities;
	}
	
	@Override
	public Activity findActivity(String activityId) {
		// TODO Auto-generated method stub
		Activity act1 = new Activity();
		act1.setDescription("Swimming");
		act1.setDuration(5);
		act1.setId("1234");
		User u = new User();
		u.setId("5678");
		u.setName("Brian");
		act1.setUser(u);
		return act1;
	}
	@Override
	public void delete(String activityId) {
		// TODO Auto-generated method stub
		System.out.println("deleting activity");
	}
	
	@Override
	public List<Activity> findByDescription(List<String> descriptions) {
		// TODO Auto-generated method stub
		System.out.println("find by description");
		List<Activity> activities = new ArrayList<Activity>();
		Activity activity = new Activity();
		activity.setId("1212");
		activity.setDescription("run");
		activity.setDuration(11);
		activities.add(activity);
		return activities;
	}
}
