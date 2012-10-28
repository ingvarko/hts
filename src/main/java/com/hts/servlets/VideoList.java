package com.hts.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hts.exceptions.AppException;

public class VideoList extends HttpServlet {

	private static final long serialVersionUID = 17227839L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		PrintWriter out;

		if (response != null)
			out = response.getWriter();
		else
			out = new PrintWriter(System.console().writer());

		try {
			File servDir = new File(getServletContext().getRealPath("/"));
			out.print("\nPath: " + servDir.getAbsolutePath());
			if (servDir.exists()) {
				out.print("\nServlet directory exists");
				if (servDir.canRead()) {
					out.print("\nCan read from Servlet directory");
					if (servDir.canWrite()) {
						out.print("\nCan write to Servlet directory");
					}
				}
			}
		}
		catch (Exception e) {
			out.print("\nException with servlet directory: " + e.getMessage());
		}

	}

	public static void main(String[] args) throws AppException, ServletException, IOException {
		VideoList videoList = new VideoList();
		videoList.service(null, null);
	}

}
