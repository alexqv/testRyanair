package com.ryanair.test.model.service;

import java.util.List;

import com.ryanair.test.model.bean.Travel;
import com.ryanair.test.model.bean.Trip;

public interface TravelService {

	List<Travel> getTravels(Trip trip);
}
