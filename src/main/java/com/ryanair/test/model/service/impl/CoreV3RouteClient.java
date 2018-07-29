package com.ryanair.test.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ryanair.test.model.bean.Route;
import com.ryanair.test.model.bean.RouteTraits;
import com.ryanair.test.model.bean.TripPlace;
import com.ryanair.test.model.service.RouteService;

@Component
public class CoreV3RouteClient implements RouteService {

	private static final String ENDPOINT_CORE_V3_ROUTES = "https://api.ryanair.com/core/3/routes";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Route> getAllRoutes() {
		try {
			ResponseEntity<RouteV3[]> response = restTemplate.getForEntity(ENDPOINT_CORE_V3_ROUTES, RouteV3[].class);
			return Stream.of(response.getBody()).map(this::mapRoute).collect(Collectors.toList());
	    } catch(HttpClientErrorException exception) {
			return new ArrayList<>();    	
	    }
	}

	private Route mapRoute(RouteV3 routeV3) {
		Route route = new Route();
		
		TripPlace tripPlace = new TripPlace();
		tripPlace.setDepartureAirport(routeV3.getAirportFrom());
		tripPlace.setArrivalAirport(routeV3.getAirportTo());
		route.setTripPlace(tripPlace);
		
		route.setConnectingAirport(routeV3.getConnectingAirport());
		
		RouteTraits routeTraits = new RouteTraits();
		routeTraits.setNewRoute(routeV3.getNewRoute());
		routeTraits.setSeasonalRoute(routeV3.getSeasonalRoute());
		routeTraits.setGroup(routeV3.getGroup());
		route.setRouteTraits(routeTraits);
		
		return route;
	}

	@Override
	public List<Route> getDirectRoutes() {
		return getAllRoutes().stream().filter(route -> route.getConnectingAirport() == null).collect(Collectors.toList());
	}
}
