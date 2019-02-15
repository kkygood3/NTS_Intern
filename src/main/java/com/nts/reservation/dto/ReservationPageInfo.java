package com.nts.reservation.dto;

public class ReservationPageInfo {
	private long productId;
	private String description;
	private String placeName;
	private String openingHours;
	private String mainImageName;

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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getMainImageName() {
		return mainImageName;
	}

	public void setMainImageName(String mainImageName) {
		this.mainImageName = mainImageName;
	}

	@Override
	public String toString() {
		return "ReservationPageInfo [productId=" + productId + ", description=" + description + ", placeName="
			+ placeName + ", openingHours=" + openingHours + ", mainImageName=" + mainImageName + "]";
	}
}
