package com.vantec.warehouse.inventorystocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vantec.warehouse.inventorystocks.domain.Inventory;
import com.vantec.warehouse.inventorystocks.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@PutMapping("add-quantity")
	public Inventory addInventoryQuantity(@Valid @RequestBody Inventory inventory) {
		return this.inventoryService.addInventoryQuantity(inventory);
	}
	
	@PutMapping("reduce-quantity")
	public Inventory InventoryQuantity(@Valid @RequestBody Inventory inventory) throws Exception {
		return this.inventoryService.reduceInventoryQuantity(inventory);
	}
}
	