package com.ryanair.test.model.bean;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Trip {

	private TripPlace place;
	private TripTime time;

	public Trip() {
	}

	public Trip(String departureAirport, String arrivalAirport, Date departureTime, Date arrivalTime) {
		place = new TripPlace();
		place.setDepartureAirport(departureAirport);
		place.setArrivalAirport(arrivalAirport);
		
		time = new TripTime();
		time.setDepartureTime(departureTime);
		time.setArrivalTime(arrivalTime);
	}
	
	public Trip(TripPlace place) {
		this.place = place;
	}

	public TripPlace getPlace() {
		return place;
	}

	public void setPlace(TripPlace place) {
		this.place = place;
	}

	public TripTime getTime() {
		return time;
	}

	public void setTime(TripTime time) {
		this.time = time;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Trip)) {
			return false;
		}
		Trip castOther = (Trip) other;
		return new EqualsBuilder().append(place, castOther.place).append(time, castOther.time).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(place).append(time).toHashCode();
	}
}
