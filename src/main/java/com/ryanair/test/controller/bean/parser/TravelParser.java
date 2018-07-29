package com.ryanair.test.controller.bean.parser;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryanair.test.controller.bean.Interconnection;
import com.ryanair.test.controller.bean.Leg;
import com.ryanair.test.model.bean.Flight;
import com.ryanair.test.model.bean.Travel;

@Component
public class TravelParser implements ModelDataParser<Travel, Interconnection> {

	@Autowired
	private ModelDataParser<Flight, Leg> legParser;

	@Override
	public Interconnection parseModelData(Travel modelBean) {
		Interconnection interconnection = new Interconnection();
		
		interconnection.setStops(modelBean.getFlights().size() - 1);
		interconnection.setLegs(modelBean.getFlights().stream().map(legParser::parseModelData).collect(Collectors.toList()));
		
		return interconnection;
	}
}
