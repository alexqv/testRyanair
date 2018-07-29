package com.ryanair.test.controller.bean;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Leg {

	private String departureAirport;
	private String arrivalAirport;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private Date departureDateTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private Date arrivalDateTime;

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

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Leg)) {
			return false;
		}
		Leg castOther = (Leg) other;
		return new EqualsBuilder().append(departureAirport, castOther.departureAirport)
				.append(arrivalAirport, castOther.arrivalAirport).append(departureDateTime, castOther.departureDateTime)
				.append(arrivalDateTime, castOther.arrivalDateTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(departureAirport).append(arrivalAirport).append(departureDateTime)
				.append(arrivalDateTime).toHashCode();
	}
}
