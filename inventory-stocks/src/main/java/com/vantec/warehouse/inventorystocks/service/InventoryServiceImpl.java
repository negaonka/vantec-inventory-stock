package com.vantec.warehouse.inventorystocks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vantec.warehouse.inventorystocks.dao.InventoryDao;
import com.vantec.warehouse.inventorystocks.domain.Inventory;
import com.vantec.warehouse.inventorystocks.exception.InventoryNotFound;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public Inventory addInventoryQuantity(Inventory inventory) {
		Optional<Inventory> availableInventory = inventoryDao
				.getOne(inventory.getPartNumber() + inventory.getSerialNumber());
		if (availableInventory.isPresent()) {
			availableInventory.get()
					.setAvailableQty(inventory.getAvailableQty() + availableInventory.get().getAvailableQty());
			updateInventoryQuantity(availableInventory);
			inventoryDao.save(availableInventory.get());
		} else {
			throw new InventoryNotFound("Inventory not found for the given serial and part number");
		}
		return availableInventory.get();
	}

	@Override
	public Inventory reduceInventoryQuantity(Inventory inventory) throws Exception {
		Optional<Inventory> availableInventory = inventoryDao
				.getOne(inventory.getPartNumber() + inventory.getSerialNumber());
		if (availableInventory.isPresent()) {
			availableInventory.get()
					.setAvailableQty(availableInventory.get().getAvailableQty() - inventory.getAvailableQty());
			updateInventoryQuantity(availableInventory);
			if (availableInventory.get().getInventoryQty() < 0.0 || availableInventory.get().getAvailableQty() < 0.0) {
				inventoryDao.delete(inventory);
				throw new Exception(
						"Inventory/available quantity is less than or equal to 0 hence deleted the inventory");
			} else {
				inventoryDao.save(availableInventory.get());
			}
		}
		return availableInventory.get();
	}

	// inventory quantity is available quantity + allocated quantity
	private void updateInventoryQuantity(Optional<Inventory> availableInventory) {
		availableInventory.get().setInventoryQty(
				availableInventory.get().getAvailableQty() + availableInventory.get().getAllocatedQty());
	}

}
