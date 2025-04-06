package com.forkd.forkd_backend.pojos;

import java.util.Arrays;
import java.util.Objects;

public class Image {
	
    private String imageType;
    private String imageName;
    private byte[] data;

    public Image() {}

    public Image(String imageType, String imageName, byte[] data) {
		super();
		this.imageType = imageType;
		this.imageName = imageName;
		this.data = data;
	}

	public Image(byte[] data) {
        this.data = data;
    }

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + Objects.hash(imageName, imageType);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Arrays.equals(data, other.data)
				&& Objects.equals(imageName, other.imageName) && Objects.equals(imageType, other.imageType);
	}

	@Override
	public String toString() {
		return "Image [imageType=" + imageType + ", imageName=" + imageName + ", data="
				+ Arrays.toString(data) + "]";
	}
    
    
}
