package ar.com.ice.brasil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.ice.brasil.model.beans.Test;
import ar.com.ice.brasil.model.dao.MensajeDAO;
import ar.com.ice.brasil.model.dao.ReunionDAO;

@Controller
public class TestController {

	@Autowired
	private MensajeDAO mensajeDAO;

	@Autowired
	private ReunionDAO reunionDAO;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView doTestPost() {
		return new ModelAndView("test");
	}

	@RequestMapping(value = "/testPost", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public @ResponseBody
	Test doPost(@RequestBody Test test) {
		System.out.println(test.getInput1());
		System.out.println(test.getInput2());
		System.out.println(test.getInput3());
		System.out.println(test.getSelect1());

		return test;
	}
}
