package ar.com.ice.brasil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.ice.brasil.model.beans.Mensaje;
import ar.com.ice.brasil.model.beans.constants.AudiosConstants;
import ar.com.ice.brasil.model.dao.MensajeDAO;
import ar.com.ice.brasil.model.dao.ReunionDAO;

@Controller
@RequestMapping(value = "/audio")
public class AudioController {

	@Autowired
	private MensajeDAO mensajeDAO;

	@Autowired
	private ReunionDAO reunionDAO;

	@RequestMapping(value = "/menu")
	public ModelAndView audioMenu(HttpServletResponse response) {
		return new ModelAndView(AudiosConstants.AUDIO_PATH + "audioMenu");
	}

	@RequestMapping(value = "/persist", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public @ResponseBody
	String add(@RequestBody Mensaje mensaje) {
		try {
			mensajeDAO.commit(mensaje);
			return null;
		} catch (DataAccessException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/mensajes")
	public ModelAndView getMensajes(HttpServletRequest request) {
		String titulo = request.getParameter("titulo");
		String orador = request.getParameter("orador");
		String tema = request.getParameter("tema");
		String lugar = request.getParameter("lugar");

		ModelAndView mview = new ModelAndView(AudiosConstants.AUDIO_PATH + "audio");
		List<Mensaje> lista = mensajeDAO.getMensajeByCriteria(titulo, orador, tema, null, lugar);

		mview.addObject("mensajesList", lista);
		return mview;
	}

	@RequestMapping(value = "/nerd")
	public @ResponseBody
	String nerd(HttpServletResponse response) {
		return "<div><a>Nerd!!!! (^_^)</a></div>";
	}

}
