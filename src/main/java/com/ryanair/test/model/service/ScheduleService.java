package com.ryanair.test.model.service;

import com.ryanair.test.model.bean.Schedule;
import com.ryanair.test.model.bean.TripPlace;

public interface ScheduleService {
	
	Schedule getSchedule(TripPlace tripPlace, Integer year, Byte month);
	
	Schedule getSchedule(TripPlace tripPlace, Integer year, Byte month, Byte lastDay);
}
