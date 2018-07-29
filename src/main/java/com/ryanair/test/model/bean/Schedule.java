package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Schedule {

	private TripPlace tripPlace;
	private Integer year;
	private ScheduleMonth monthSchedule;

	public Schedule() {
	}

	public Schedule(TripPlace tripPlace, Integer year, ScheduleMonth monthSchedule) {
		this.tripPlace = tripPlace;
		this.year = year;
		this.monthSchedule = monthSchedule;
	}

	public TripPlace getTripPlace() {
		return tripPlace;
	}

	public void setTripPlace(TripPlace tripPlace) {
		this.tripPlace = tripPlace;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public ScheduleMonth getMonthSchedule() {
		return monthSchedule;
	}

	public void setMonthSchedule(ScheduleMonth monthSchedule) {
		this.monthSchedule = monthSchedule;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Schedule)) {
			return false;
		}
		Schedule castOther = (Schedule) other;
		return new EqualsBuilder().append(tripPlace, castOther.tripPlace).append(year, castOther.year)
				.append(monthSchedule, castOther.monthSchedule).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tripPlace).append(year).append(monthSchedule).toHashCode();
	}
}
