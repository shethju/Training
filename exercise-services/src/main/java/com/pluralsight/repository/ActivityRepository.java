package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Activity;

public interface ActivityRepository {

	public abstract List<Activity> findAllActivities();

	public abstract Activity findActivity(String activityId);

	public abstract void create(Activity activity);

	public abstract Activity update(Activity activity);

	public abstract void delete(String activityId);

	public abstract List<Activity> findByDescription(List<String> descriptions);

}