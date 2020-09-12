package com.ddt.cinefsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.cinefsoft.model.Foody;
import com.ddt.cinefsoft.repository.FoodyRepository;

@Service
public class FoodyService {
	
	@Autowired
	private FoodyRepository foodyRepository;

	public List<Foody> getFoody() {
		return foodyRepository.getFoody();
	}

}
