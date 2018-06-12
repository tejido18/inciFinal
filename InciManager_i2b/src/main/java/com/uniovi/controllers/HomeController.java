package com.uniovi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HomeController
{
	@RequestMapping("/")
	@ResponseBody
	public String getHome()
	{
	return "Funchona";
	}
	
	@RequestMapping("/error")
	@ResponseBody
	public String getHomeError()
	{
	return "No Funchona";
	}

}
