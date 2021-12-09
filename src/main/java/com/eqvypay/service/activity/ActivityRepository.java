package com.eqvypay.service.activity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eqvypay.persistence.Activity;
import com.eqvypay.persistence.IActivity;

@Repository
public interface ActivityRepository {

	void addActivity(IActivity activity) throws Exception;
	List<IActivity> getUserActivity(String userId) throws Exception;
	void deleteActivity(String uuid) throws Exception;
	void createTable() throws Exception;
}
