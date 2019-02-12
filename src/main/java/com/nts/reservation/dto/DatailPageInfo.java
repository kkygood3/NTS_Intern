package com.nts.reservation.dto;

public class DatailPageInfo {
	private long productId;
	private String description;
	private String content;
	private String placeName;
	private String placeStreet;
	private String placeLot;
	private String tel;
	private String mainImageFileName;
	private double averageScore;
	private int commentCount;
	private String mapFileName;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMainImageFileName() {
		return mainImageFileName;
	}

	public void setMainImageFileName(String mainImageFileName) {
		this.mainImageFileName = mainImageFileName;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getMapFileName() {
		return mapFileName;
	}

	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	@Override
	public String toString() {
		return "Datail [productId=" + productId + ", description=" + description + ", content=" + content
			+ ", placeName=" + placeName + ", placeStreet=" + placeStreet + ", placeLot=" + placeLot + ", tel=" + tel
			+ ", mainImageFileName=" + mainImageFileName + ", averageScore=" + averageScore + ", commentCount="
			+ commentCount + ", mapFileName=" + mapFileName + "]";
	}
}
