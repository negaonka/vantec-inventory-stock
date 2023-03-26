package com.vantec.warehouse.inventorystocks.service;

import org.springframework.stereotype.Service;

import com.vantec.warehouse.inventorystocks.domain.Inventory;

@Service
public interface InventoryService {

	public Inventory addInventoryQuantity(Inventory inventory);
	
	public Inventory reduceInventoryQuantity(Inventory inventory) throws Exception;
}
