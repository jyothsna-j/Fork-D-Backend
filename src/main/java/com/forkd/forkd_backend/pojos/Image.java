package com.forkd.forkd_backend.pojos;

public class Image {
	
    private Long id;

    private byte[] data;

    public Image() {}

    public Image(byte[] data) {
    	System.out.println(data.toString());
        this.data = data;
    }
    
    public byte[] getData() {
        return data;
    }

}
