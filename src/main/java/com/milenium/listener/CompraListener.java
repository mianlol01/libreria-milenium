package com.milenium.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.milenium.model.DetalleBoleta;

/**
 * Application Lifecycle Listener implementation class CompraListener
 *
 */
@WebListener
public class CompraListener implements HttpSessionListener {
	/**
	 * Default constructor.
	 */
	public CompraListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// crear los elementos del carrito
		ArrayList<DetalleBoleta> carro = new ArrayList<DetalleBoleta>();
		int cantArticulos = 0;
		double subTotalVenta = 0;

		// enviar a nivel de session
		se.getSession().setAttribute("carro", carro);
		se.getSession().setAttribute("cantArticulos", cantArticulos);
		se.getSession().setAttribute("subTotalVenta", subTotalVenta);

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}
}