package org.red5.demos.oflaDemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hts.exceptions.AppException;
import com.hts.service.HotelServiceImpl;

/**
 * Servlet implementation class SecurityTest
 */
public class JsonService extends HttpServlet {

	private static final long serialVersionUID = 17227839L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		// out.write(getB());
		// out.write(getA());
		String entityName = (String) request.getParameter("json");
		String oper = (String) request.getParameter("oper");

		System.out.print("\noper:" + oper);
		String id= (String) request.getParameter("id");

		System.out.print("\nid:" + id);

		if (entityName.equals("hotels")) {
			try {
				out.write(new HotelServiceImpl().getJson(
						new HotelServiceImpl().getAll(), "0"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

	}

}

// http://localhost:5080/oflaDemo/JsonService