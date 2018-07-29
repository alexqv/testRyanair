package com.ryanair.test.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ryanair.test.model.bean.Schedule;
import com.ryanair.test.model.bean.ScheduleDay;
import com.ryanair.test.model.bean.ScheduleMonth;
import com.ryanair.test.model.bean.TripPlace;
import com.ryanair.test.model.service.ScheduleService;

@Component
public class TimetableV3ScheduleClient implements ScheduleService {
	
	private static final String ENDPOINT_TIMETABLE_V3_SCHEDULES = "https://api.ryanair.com/timetable/3/schedules";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Schedule getSchedule(TripPlace tripPlace, Integer year, Byte month) {
	    String endpoint = getEndpoint(tripPlace, year, month);
	    
	    try {
			ResponseEntity<ScheduleMonth> response = restTemplate.getForEntity(endpoint, ScheduleMonth.class);
		    return new Schedule(tripPlace, year, response.getBody());
	    } catch(HttpClientErrorException exception) {
			return new Schedule(tripPlace, year, new ScheduleMonth(month, new ArrayList<>()));    	
	    }
	}

	private String getEndpoint(TripPlace tripPlace, Integer year, Byte month) {
		return String.format("%s/%s/%s/years/%s/months/%s",
							  ENDPOINT_TIMETABLE_V3_SCHEDULES,
							  tripPlace.getDepartureAirport(),
							  tripPlace.getArrivalAirport(),
							  year,
							  month);
	}

	@Override
	public Schedule getSchedule(TripPlace tripPlace, Integer year, Byte month, Byte lastDay) {
		Schedule schedule = getSchedule(tripPlace, year, month);
		
		ScheduleMonth scheduleMonth = schedule.getMonthSchedule();
		
		List<ScheduleDay> filteredDays = scheduleMonth
										.getDays()
										.stream()
										.filter(ds -> Byte.compare(ds.getDay(), lastDay) <= 0)
										.collect(Collectors.toList());
		
		return new Schedule(schedule.getTripPlace(), 
							schedule.getYear(), 
							new ScheduleMonth(scheduleMonth.getMonth(), filteredDays));
	}
}
