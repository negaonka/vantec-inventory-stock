package com.vantec.warehouse.inventorystocks.domain;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {
	
	@NotBlank(message = "Provide part number to find the imventory.")
	private String partNumber;
	@NotBlank(message = "Provide serial number to find the imventory.")
	private String serialNumber;
	private Double inventoryQty;
	@Positive(message="Available quantity must be greater than 0")
	private Double availableQty;
	private Double allocatedQty;

}
