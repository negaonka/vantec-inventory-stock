package com.vantec.warehouse.inventorystocks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InventoryNotFound extends RuntimeException {

	public InventoryNotFound(String message) {
		super(message);
	}

}
