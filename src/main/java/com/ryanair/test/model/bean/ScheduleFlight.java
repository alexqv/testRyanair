package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ScheduleFlight {

	private String number;
	private String departureTime;
	private String arrivalTime;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ScheduleFlight)) {
			return false;
		}
		ScheduleFlight castOther = (ScheduleFlight) other;
		return new EqualsBuilder().append(number, castOther.number).append(departureTime, castOther.departureTime)
				.append(arrivalTime, castOther.arrivalTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(number).append(departureTime).append(arrivalTime).toHashCode();
	}
}
