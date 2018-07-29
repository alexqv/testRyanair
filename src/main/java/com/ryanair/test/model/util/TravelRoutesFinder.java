package com.ryanair.test.model.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ryanair.test.model.bean.Route;
import com.ryanair.test.model.bean.TravelRoute;
import com.ryanair.test.model.bean.Trip;
import com.ryanair.test.model.bean.TripPlace;

public class TravelRoutesFinder {

	private List<Route> routes;
	private Integer maxStops;
	
	public TravelRoutesFinder(List<Route> routes, Integer maxStops) {
		this.routes = routes;
		this.maxStops = maxStops;
	}

	public List<TravelRoute> findTravelRoutes(Trip trip) {
		List<TravelRoute> travelRoutes = findTravelRoutes(trip.getPlace(), 0, getRoutesDeparting(trip.getPlace()));
		travelRoutes.forEach(travelRoute -> travelRoute.setTrip(trip));
		return travelRoutes == null ? new ArrayList<>() : travelRoutes;
	}

	private List<Route> getRoutesDeparting(TripPlace place) {
		return routes.stream().filter(equalsDepartureAirport(place)).collect(Collectors.toList());
	}

	private Predicate<Route> equalsDepartureAirport(TripPlace place) {
		return route -> StringUtils.equalsIgnoreCase(place.getDepartureAirport(), route.getTripPlace().getDepartureAirport());
	}

	/*
	 * TODO 
	 * Fix recursion data retrieval
	 */
	private List<TravelRoute> findTravelRoutes(TripPlace place, int recursionLevel, List<Route> departureRoutes) {
		List<TravelRoute> travelRoutes = new ArrayList<>();
		
		getRoutesArriving(place, departureRoutes)
		.forEach(travelRouteLink -> travelRoutes.add(new TravelRoute(travelRouteLink)));
		
		/*
		if(recursionLevel < maxStops) {
			getRoutesNotArriving(place, departureRoutes)
			.forEach(
				travelRouteLink -> {
					TripPlace travelRouteLinkPlace = travelRouteLink.getTripPlace(); 
					List<Route> travelRouteLinkDepartureRoutes = getRoutesDeparting(travelRouteLink.getTripPlace());
					
					List<TravelRoute> calculatingTravelRoutes = 
						findTravelRoutes(travelRouteLinkPlace, recursionLevel + 1, travelRouteLinkDepartureRoutes);
					
					if(calculatingTravelRoutes != null) {
						calculatingTravelRoutes
						.stream()
						.filter(ctr -> checkTravelRouteLinkNotContainAirportAlreadyOnTravelRoute(travelRouteLink, ctr))
						.forEach(ctr -> {
							ctr.getRoutes().add(0, travelRouteLink);
							travelRoutes.add(ctr);
						});
					}
				}
			);
		}
		*/
		
		return CollectionUtils.isEmpty(travelRoutes) ? null : travelRoutes;
	}

	private List<Route> getRoutesArriving(TripPlace place, List<Route> routes) {
		return routes.stream().filter(equalsArrivalAirport(place)).collect(Collectors.toList());
	}

	private Predicate<Route> equalsArrivalAirport(TripPlace place) {
		return route -> StringUtils.equalsIgnoreCase(place.getArrivalAirport(), route.getTripPlace().getArrivalAirport());
	}

	private List<Route> getRoutesNotArriving(TripPlace place, List<Route> departureRoutes) {
		return routes.stream().filter(equalsArrivalAirport(place).negate()).collect(Collectors.toList());
	}

	/*
	 * This method check if a travel route link contains an airport already visited in the rest of the travel route.
	 * This method is meant to support identify and discard travel routes with loops. E.g: DUB - MAD - DUB - WRO (which does not make much sense)
	 */
	private Boolean checkTravelRouteLinkNotContainAirportAlreadyOnTravelRoute(Route travelRouteLink, TravelRoute travelRoute) {
		String linkDepAir = travelRouteLink.getTripPlace().getDepartureAirport();
		String linkArrAir = travelRouteLink.getTripPlace().getArrivalAirport();
		
		return travelRoute.getRoutes()
						  .stream()
						  .anyMatch(
							  route -> {
								  String routeDepAir = route.getTripPlace().getDepartureAirport();
								  String routeArrAir = route.getTripPlace().getArrivalAirport();
								  
								  return StringUtils.equalsAnyIgnoreCase(linkDepAir, routeDepAir, routeArrAir)
										  || StringUtils.equalsAnyIgnoreCase(linkArrAir, routeDepAir, routeArrAir); 
							  }
						  );
	}
}
