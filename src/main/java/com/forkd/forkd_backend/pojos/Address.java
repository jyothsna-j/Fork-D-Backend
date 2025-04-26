package com.forkd.forkd_backend.pojos;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(address, contactNumber, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude);
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	
}
