package com.routeone.interview;

import java.io.IOException;

public class InvalidData extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidData(Exception e) {
		super(e);
	}

	public InvalidData(String message) {
		super(message);
	}

	public InvalidData(String message, IOException e) {
		super(message, e);
	}

}
