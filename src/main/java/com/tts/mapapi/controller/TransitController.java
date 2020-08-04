package com.tts.mapapi.controller;

import java.util.List;

import com.tts.mapapi.model.Bus;
import com.tts.mapapi.model.BusRequest;
import com.tts.mapapi.model.Location;
import com.tts.mapapi.service.TransitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransitController {
    @Autowired
    private TransitService apiService;
	
    @GetMapping("/")
    public String getBusesPage(Model model){
        model.addAttribute("request", new BusRequest());
        return "index";
    }
	
    @PostMapping("/")
    public String getNearbyBuses(BusRequest request, Model model) {
        List<Bus> buses = apiService.getNearbyBuses(request);
        Location personLocation = apiService.getPersonLocation(request);
        model.addAttribute("personLocation", personLocation);
        model.addAttribute("buses", buses);
        model.addAttribute("request", request);    
        return "index";
    }

}