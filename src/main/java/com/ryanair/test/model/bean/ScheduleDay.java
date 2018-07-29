package com.ryanair.test.model.bean;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ScheduleDay {

	private Byte day;
	private List<ScheduleFlight> flights;

	public Byte getDay() {
		return day;
	}

	public void setDay(Byte day) {
		this.day = day;
	}

	public List<ScheduleFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<ScheduleFlight> flights) {
		this.flights = flights;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ScheduleDay)) {
			return false;
		}
		ScheduleDay castOther = (ScheduleDay) other;
		return new EqualsBuilder().append(day, castOther.day).append(flights, castOther.flights).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(day).append(flights).toHashCode();
	}
}
