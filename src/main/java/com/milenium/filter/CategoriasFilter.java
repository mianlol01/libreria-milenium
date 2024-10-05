package com.milenium.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.milenium.model.Categoria;
import com.milenium.repository.RCategoria;

/**
 * Servlet Filter implementation class CategoriasFilter
 */
@WebFilter("/*")
public class CategoriasFilter implements Filter {
	private List<Categoria> listaCategorias;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public CategoriasFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("listaCategorias", listaCategorias);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		RCategoria rc = new RCategoria();
		listaCategorias = rc.listarCategoria(); // Cargar la lista una vez
	}

}
