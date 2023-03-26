package com.vantec.warehouse.inventorystocks.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.vantec.warehouse.inventorystocks.domain.Inventory;

@Component
public class InventoryDaoImpl implements InventoryDao {

	private static Map<String, Inventory> inventories = new HashMap<>();

	@Override
	public Optional<Inventory> getOne(String id) {
		return Optional.ofNullable(inventories.get(id));
	}

	@Override
	public Set<Inventory> getAll() {
		return new HashSet<>(inventories.values());
	}

	@Override
	public void clear() {
		inventories.clear();
	}

	@Override
	public void save(Inventory inventory) {
		inventories.put(inventory.getPartNumber() + inventory.getSerialNumber(), inventory);
	}

	@Override
	public void delete(Inventory inventory) {
		inventories.remove(inventory.getPartNumber() + inventory.getSerialNumber());
		
	}

}
