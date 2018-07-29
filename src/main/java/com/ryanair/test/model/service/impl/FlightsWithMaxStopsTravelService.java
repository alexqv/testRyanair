package com.ryanair.test.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ryanair.test.model.bean.Flight;
import com.ryanair.test.model.bean.Route;
import com.ryanair.test.model.bean.Schedule;
import com.ryanair.test.model.bean.ScheduleDay;
import com.ryanair.test.model.bean.ScheduleFlight;
import com.ryanair.test.model.bean.Travel;
import com.ryanair.test.model.bean.TravelRoute;
import com.ryanair.test.model.bean.Trip;
import com.ryanair.test.model.bean.TripPlace;
import com.ryanair.test.model.bean.TripTime;
import com.ryanair.test.model.service.RouteService;
import com.ryanair.test.model.service.ScheduleService;
import com.ryanair.test.model.service.TravelService;
import com.ryanair.test.model.util.TravelRoutesFinder;

@Service
public class FlightsWithMaxStopsTravelService implements TravelService {
	
	@Autowired
	private RouteService routeService;	
	
	@Autowired
	private ScheduleService scheduleService;
	
	private Integer maxStops;
	
	@Override
	public List<Travel> getTravels(Trip trip) {
		List<TravelRoute> travelRoutes = getAvailableRoutes(trip);
		List<Calendar> travelMonths = getTravelMonths(trip.getTime());
		
		if(CollectionUtils.isEmpty(travelRoutes) || CollectionUtils.isEmpty(travelMonths)) {
			return new ArrayList<>();
		}
		
		return travelRoutes.stream()
						   .map(travelRoute -> getTravel(trip, travelRoute, travelMonths))
						   .filter(travel -> travel != null)
						   .collect(Collectors.toList());
	}

	private List<TravelRoute> getAvailableRoutes(Trip trip) {
		return new TravelRoutesFinder(routeService.getDirectRoutes(), maxStops).findTravelRoutes(trip);
	}

	private List<Calendar> getTravelMonths(TripTime time) {
		List<Calendar> travelMonths = new ArrayList<>();
		
		Date indexMonth = DateUtils.truncate(time.getDepartureTime(), Calendar.MONTH);
		Date endMonth = DateUtils.truncate(time.getArrivalTime(), Calendar.MONTH);
		
		while(!indexMonth.after(endMonth)) {
			travelMonths.add(getCalendar(indexMonth));
			indexMonth = DateUtils.addMonths(indexMonth, 1);
		}
		
		return travelMonths;
	}

	private Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/*
	 * TODO
	 * Here I should retrieve all available travels instead of the earlier one, 
	 * but I do not have time to implement the full solution, sorry :(
	 */
	private Travel getTravel(Trip trip, TravelRoute travelRoute, List<Calendar> travelMonths) {
		List<Flight> flights = new ArrayList<>();
		
		Date nextFlightEarlierDepartureTime = trip.getTime().getDepartureTime();
		
		for(Route route : travelRoute.getRoutes()) {
			Flight earlierAvailableFlight = getEarlierAvailableFlight(route, nextFlightEarlierDepartureTime, travelMonths);
			
			if(earlierAvailableFlight == null) {
				return null;
			}
			
			flights.add(earlierAvailableFlight);
			
			nextFlightEarlierDepartureTime = 
				DateUtils.addHours(earlierAvailableFlight.getTrip().getTime().getArrivalTime(), 2);
		}
		
		return new Travel(trip, flights);
	}
	
	private Flight getEarlierAvailableFlight(Route route, Date nextFlightEarlierDepartureTime, List<Calendar> travelMonths) {
		for(Calendar travelMonth : travelMonths) {
			TripPlace routePlace = route.getTripPlace();
			Schedule schedule = getSchedule(routePlace, travelMonth);
			
			for(ScheduleDay scheduleDay : schedule.getMonthSchedule().getDays()) {
				Date flightDay = getFlightDay(travelMonth, scheduleDay.getDay());
				
				if(flightDay.before(nextFlightEarlierDepartureTime)
					&& !DateUtils.isSameDay(flightDay, nextFlightEarlierDepartureTime)) {
					continue;
				}
				
				for(ScheduleFlight scheduleFlight : scheduleDay.getFlights()) {
					Date scheduleFlightDeparture = getFlightDate(flightDay, scheduleFlight.getDepartureTime());
					
					if(scheduleFlightDeparture.equals(nextFlightEarlierDepartureTime)
						|| scheduleFlightDeparture.after(nextFlightEarlierDepartureTime)) {
						return getFlight(routePlace, flightDay, scheduleFlight);
					}
				}
			}
		}
		
		return null;
	}

	private Date getFlightDay(Calendar travelMonth, Byte day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(travelMonth.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	private Schedule getSchedule(TripPlace place, Calendar travelMonth) {
		return scheduleService.getSchedule(place, travelMonth.get(Calendar.YEAR), (byte)(travelMonth.get(Calendar.MONTH) + 1));
	}

	private Flight getFlight(TripPlace routePlace, Date flightDay, ScheduleFlight scheduleFlight) {
		Trip flightTrip = new Trip(routePlace.getDepartureAirport(), 
								   routePlace.getArrivalAirport(),
								   getFlightDate(flightDay, scheduleFlight.getDepartureTime()),
								   getFlightDate(flightDay, scheduleFlight.getArrivalTime()));
		
		return new Flight(scheduleFlight.getNumber(), flightTrip);
	}

	private Date getFlightDate(Date day, String time) {
		Calendar calendar = getCalendar(day);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.valueOf(time.split(":")[1]));
		return calendar.getTime();
	}

	public Integer getMaxStops() {
		return maxStops;
	}

	public void setMaxStops(Integer maxStops) {
		this.maxStops = maxStops;
	}
}
