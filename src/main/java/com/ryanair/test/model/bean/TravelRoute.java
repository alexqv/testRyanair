package com.ryanair.test.model.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TravelRoute {

	private Trip trip;
	private List<Route> routes;
	

	public TravelRoute() {
	}

	public TravelRoute(Route... routes) {
		this.routes = new ArrayList<Route>(Arrays.asList(routes));
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TravelRoute)) {
			return false;
		}
		TravelRoute castOther = (TravelRoute) other;
		return new EqualsBuilder().append(trip, castOther.trip).append(routes, castOther.routes).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(trip).append(routes).toHashCode();
	}
}
