package com.ryanair.test.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RouteTraits {

	private Boolean newRoute;
	private Boolean seasonalRoute;
	private String group;

	public Boolean getNewRoute() {
		return newRoute;
	}

	public void setNewRoute(Boolean newRoute) {
		this.newRoute = newRoute;
	}

	public Boolean getSeasonalRoute() {
		return seasonalRoute;
	}

	public void setSeasonalRoute(Boolean seasonalRoute) {
		this.seasonalRoute = seasonalRoute;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof RouteTraits)) {
			return false;
		}
		RouteTraits castOther = (RouteTraits) other;
		return new EqualsBuilder().append(newRoute, castOther.newRoute).append(seasonalRoute, castOther.seasonalRoute)
				.append(group, castOther.group).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(newRoute).append(seasonalRoute).append(group).toHashCode();
	}
}
