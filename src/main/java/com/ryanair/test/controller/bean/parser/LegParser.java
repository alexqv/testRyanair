package com.ryanair.test.controller.bean.parser;

import org.springframework.stereotype.Component;

import com.ryanair.test.controller.bean.Leg;
import com.ryanair.test.model.bean.Flight;

@Component
public class LegParser implements ModelDataParser<Flight, Leg> {

	@Override
	public Leg parseModelData(Flight modelBean) {
		Leg leg = new Leg();
		
		leg.setDepartureAirport(modelBean.getTrip().getPlace().getDepartureAirport());
		leg.setArrivalAirport(modelBean.getTrip().getPlace().getArrivalAirport());
		leg.setDepartureDateTime(modelBean.getTrip().getTime().getDepartureTime());
		leg.setArrivalDateTime(modelBean.getTrip().getTime().getArrivalTime());
		
		return leg;
	}
}
