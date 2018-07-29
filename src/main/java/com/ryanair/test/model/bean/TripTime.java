package com.ryanair.test.model.bean;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TripTime {

	private Date departureTime;
	private Date arrivalTime;

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TripTime)) {
			return false;
		}
		TripTime castOther = (TripTime) other;
		return new EqualsBuilder().append(departureTime, castOther.departureTime)
				.append(arrivalTime, castOther.arrivalTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(departureTime).append(arrivalTime).toHashCode();
	}
}
