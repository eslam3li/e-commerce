package com.jets.ecommerce.service.user.reviews;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


@WebServlet("/ReviewServlet")
public class ReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Vector<Review> messages = new Vector<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// content type must be set to text/event-stream
		response.setContentType("text/event-stream");
		// encoding must be set to UTF-8
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		Gson gsonUser = new Gson();

		int msgId = 0;
		String lastId = request.getHeader("Last-Event-Id");
		if (lastId != null) {
			msgId = Integer.parseInt(lastId);
			String data = "";
			if (msgId >= 0) {
				for (int i = msgId; i < messages.size(); i++) {
					writer.write("data: " + gsonUser.toJson(messages.get(i)) + "\n");
					msgId = messages.size();
					writer.write("id: " + msgId + "\n\n");
					writer.flush();
				}

			}
		} else {
			writer.write("id: " + msgId + "\n");
			writer.write("data: " + gsonUser.toJson("{" + "welcome" + ":" + "welcome" + "}") + " \n\n");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String review = request.getParameter("review");
		String rating = request.getParameter("rating");
		Review userReview = new Review(name, review, Integer.parseInt(rating));
		messages.add(userReview);
		System.out.println(name + userReview);
		// doGet(request, response);
	}

}
