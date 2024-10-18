package com.milenium.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.milenium.model.Cart;
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
		Cart cart = new Cart();

		se.getSession().setAttribute("cart", cart);
		System.out.println("se entró al listener");

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}
}