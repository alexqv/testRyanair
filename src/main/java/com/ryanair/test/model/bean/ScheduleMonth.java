package com.ryanair.test.model.bean;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ScheduleMonth {

	private Byte month;
	private List<ScheduleDay> days;

	public ScheduleMonth() {
	}

	public ScheduleMonth(Byte month, List<ScheduleDay> days) {
		this.month = month;
		this.days = days;
	}

	public Byte getMonth() {
		return month;
	}

	public void setMonth(Byte month) {
		this.month = month;
	}

	public List<ScheduleDay> getDays() {
		return days;
	}

	public void setDays(List<ScheduleDay> days) {
		this.days = days;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ScheduleMonth)) {
			return false;
		}
		ScheduleMonth castOther = (ScheduleMonth) other;
		return new EqualsBuilder().append(month, castOther.month).append(days, castOther.days).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(month).append(days).toHashCode();
	}
}
