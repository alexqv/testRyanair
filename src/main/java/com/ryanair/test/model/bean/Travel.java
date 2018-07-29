package com.ryanair.test.model.bean;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Travel {

	private Trip trip;
	private List<Flight> flights;

	public Travel() {
	}

	public Travel(Trip trip, List<Flight> flights) {
		this.trip = trip;
		this.flights = flights;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Travel)) {
			return false;
		}
		Travel castOther = (Travel) other;
		return new EqualsBuilder().append(trip, castOther.trip).append(flights, castOther.flights).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(trip).append(flights).toHashCode();
	}
}
