package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Flight {

	private String number;
	private Trip trip;
	
	public Flight() {
	}

	public Flight(String number, Trip trip) {
		this.number = number;
		this.trip = trip;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Flight)) {
			return false;
		}
		Flight castOther = (Flight) other;
		return new EqualsBuilder().append(number, castOther.number).append(trip, castOther.trip).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(number).append(trip).toHashCode();
	}
}
