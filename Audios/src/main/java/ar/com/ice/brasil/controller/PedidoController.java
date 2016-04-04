package ar.com.ice.brasil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.ice.brasil.model.beans.Pedido;
import ar.com.ice.brasil.model.beans.constants.AudiosConstants;
import ar.com.ice.brasil.model.dao.PedidoDAO;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoDAO pedidoDAO;

	@RequestMapping(value = "")
	public ModelAndView getPedidos() {
		return new ModelAndView(AudiosConstants.PEDIDOS_PATH + "pedidos").addObject("pedidosList", pedidoDAO.getPedidoByCriteria(null, null, null, null));
	}

	@RequestMapping(value = "/persist", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request) {
		Pedido pedido = new Pedido();
		try {
			String cliente = request.getParameter("cliente").trim();
			String descripcion = request.getParameter("descripcion").trim();
			boolean abonado = "on".equals(request.getParameter("abonado"));
			boolean entregado = "on".equals(request.getParameter("entregado"));
			float montoAbonado = Float.parseFloat(request.getParameter("montoAbonado"));

			pedido.setCliente(cliente);
			pedido.setDescripcion(descripcion);
			pedido.setAbonado(abonado);
			pedido.setEntregado(entregado);
			pedido.setMontoAbonado(montoAbonado);

			try {
				pedidoDAO.commit(pedido);
				return null;
			} catch (DataAccessException e) {
				return e.getMessage();
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/removePedido", method = RequestMethod.POST)
	public @ResponseBody
	String deletePedido(HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			pedidoDAO.delete(id);
			return null;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getPedidos")
	public ModelAndView getPedidos(HttpServletRequest request) {
		String cliente = request.getParameter("cliente");
		String descripcion = request.getParameter("descripcion");
		Boolean abonado = null;
		Boolean entregado = null;
		if (request.getParameter("abonado") != null) {
			abonado = Boolean.valueOf(request.getParameter("abonado"));
		}
		if (request.getParameter("entregado") != null) {
			entregado = Boolean.valueOf(request.getParameter("entregado"));
		}

		ModelAndView mview = new ModelAndView(AudiosConstants.PEDIDOS_PATH + "pedidos");
		List<Pedido> lista = pedidoDAO.getPedidoByCriteria(cliente, descripcion, abonado, entregado);

		mview.addObject("pedidosList", lista);
		return mview;
	}
}
