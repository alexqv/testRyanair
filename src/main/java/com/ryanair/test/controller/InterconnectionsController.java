package com.ryanair.test.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryanair.test.controller.bean.Interconnection;
import com.ryanair.test.controller.bean.parser.ModelDataParser;
import com.ryanair.test.model.bean.Travel;
import com.ryanair.test.model.bean.Trip;
import com.ryanair.test.model.service.TravelService;

@RestController
@RequestMapping("/v1/interconnections")
public class InterconnectionsController {
	
	@Autowired
	private ModelDataParser<Travel, Interconnection> parser;
	
	@Autowired
	@Qualifier("DirectFlightsOr1StopTravelService")
	private TravelService travelService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Interconnection> getInterconnections(@RequestParam(required = true) String departure, 
												  	 @RequestParam(required = true) String arrival,
												  	 @RequestParam(required = true) 
					    					  		 @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date departureDateTime,
					    					  		 @RequestParam(required = true) 
						  						  	 @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date arrivalDateTime) {
        List<Travel> travels = 
    		travelService.getTravels(new Trip(departure, arrival, departureDateTime, arrivalDateTime));
		
        return travels.stream().map(parser::parseModelData).collect(Collectors.toList());
    }
}
