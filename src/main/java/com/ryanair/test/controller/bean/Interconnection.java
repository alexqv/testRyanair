package com.ryanair.test.controller.bean;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Interconnection {
	
	private Integer stops;
	private List<Leg> legs;

	public Integer getStops() {
		return stops;
	}

	public void setStops(Integer stops) {
		this.stops = stops;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Interconnection)) {
			return false;
		}
		Interconnection castOther = (Interconnection) other;
		return new EqualsBuilder().append(stops, castOther.stops).append(legs, castOther.legs).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(stops).append(legs).toHashCode();
	}
}
