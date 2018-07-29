package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Route {

	private TripPlace tripPlace;
	private String connectingAirport;
	private RouteTraits routeTraits;

	public TripPlace getTripPlace() {
		return tripPlace;
	}

	public void setTripPlace(TripPlace tripPlace) {
		this.tripPlace = tripPlace;
	}

	public String getConnectingAirport() {
		return connectingAirport;
	}

	public void setConnectingAirport(String connectingAirport) {
		this.connectingAirport = connectingAirport;
	}

	public RouteTraits getRouteTraits() {
		return routeTraits;
	}

	public void setRouteTraits(RouteTraits routeTraits) {
		this.routeTraits = routeTraits;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Route)) {
			return false;
		}
		Route castOther = (Route) other;
		return new EqualsBuilder().append(tripPlace, castOther.tripPlace)
				.append(connectingAirport, castOther.connectingAirport).append(routeTraits, castOther.routeTraits)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tripPlace).append(connectingAirport).append(routeTraits).toHashCode();
	}
}
