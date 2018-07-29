package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TripPlace {

	private String departureAirport;
	private String arrivalAirport;

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TripPlace)) {
			return false;
		}
		TripPlace castOther = (TripPlace) other;
		return new EqualsBuilder().append(departureAirport, castOther.departureAirport)
				.append(arrivalAirport, castOther.arrivalAirport).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(departureAirport).append(arrivalAirport).toHashCode();
	}
}
