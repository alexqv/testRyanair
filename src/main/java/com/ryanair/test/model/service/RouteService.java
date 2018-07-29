package com.ryanair.test.model.service;

import java.util.List;

import com.ryanair.test.model.bean.Route;

public interface RouteService {

	List<Route> getAllRoutes();
	
	List<Route> getDirectRoutes();
}
