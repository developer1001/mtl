package com.zgc.mtl.common.enu;

public enum ImageEnum {
	JPG("jpg"),
	JPEG("jpeg"),
	GIF("gif"),
	SVG("svg+xml");
	
	private String name;
	ImageEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
