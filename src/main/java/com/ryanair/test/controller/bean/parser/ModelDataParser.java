package com.ryanair.test.controller.bean.parser;

/**
 * Interface that defines the logic to parse from model's beans to controllers' bean.
 * 
 * It is meant to maintain the encapsulation from the API (controllers' beans) and the model's data (model's beans)
 * 
 * @param <MB> Model bean to parse
 * @param <CB> Controller bean parsed
 */
public interface ModelDataParser<MB, CB> {
	
	CB parseModelData(MB modelBean);
}
