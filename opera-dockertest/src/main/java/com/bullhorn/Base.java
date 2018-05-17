package com.bullhorn;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {

		File f = new File("app.properties");
		if (f.exists()) {
			return f.getAbsolutePath();
		} else
			return "hello";
	}

}