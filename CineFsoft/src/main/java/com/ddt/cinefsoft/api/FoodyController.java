package com.ddt.cinefsoft.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddt.cinefsoft.model.Foody;
import com.ddt.cinefsoft.service.FoodyService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("foody")
public class FoodyController {
	
	@Autowired
	private FoodyService foodyService;
	
	@GetMapping
	public List<Foody> getFoody() {
		return foodyService.getFoody();
	}
}
