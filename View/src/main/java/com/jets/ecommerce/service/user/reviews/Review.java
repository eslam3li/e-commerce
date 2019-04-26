package com.jets.ecommerce.service.user.reviews;

public class Review {

	private int userId;
	private String userName;
	private String userReview;
	private int userRating;

	public Review(String userName, String review, int rating) {
		super();
		this.userName = userName;
		this.userReview = review;
		this.userRating = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserReview() {
		return userReview;
	}

	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}

	public int getUserRating() {
		return userRating;
	}

	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
