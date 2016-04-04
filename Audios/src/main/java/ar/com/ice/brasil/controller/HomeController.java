package ar.com.ice.brasil.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(value = "")
	public ModelAndView doGet(HttpServletResponse response) {
		return new ModelAndView("home");
	}
}