package com.forkd.forkd_backend.pojos;

public class Address {

	private String address;
	private Double latitude;
	private Double longitude;
	private Double contactNumber;
	
	public Address(String address, Double latitude, Double longitude) {
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Address(String address, Double latitude, Double longitude, Double contactNumber) {
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.contactNumber = contactNumber;
	}
	
	public Address() {
    }


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Double contactNumber) {
		this.contactNumber = contactNumber;
	}
}
