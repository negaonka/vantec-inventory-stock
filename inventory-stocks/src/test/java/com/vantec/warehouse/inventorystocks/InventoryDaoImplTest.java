package com.vantec.warehouse.inventorystocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vantec.warehouse.inventorystocks.dao.InventoryDaoImpl;
import com.vantec.warehouse.inventorystocks.domain.Inventory;

class PropertyDaoImplTest {

	private InventoryDaoImpl underTest;

	@BeforeEach
	void setup() {
		underTest = new InventoryDaoImpl();
	}

	@AfterEach
	void tearDown() {
		underTest.clear();
	}

	@Test
	void shouldSaveInventory() {
		Inventory inventory = new Inventory("20932K3150XA","VMS000447", 10.0, 5.0, 5.0);
		underTest.save(inventory);

		Optional<Inventory> one = underTest.getOne(inventory.getPartNumber() + inventory.getSerialNumber());

		assertTrue(one.isPresent());
		assertEquals("20932K3150XAVMS000447", one.get().getPartNumber() + one.get().getSerialNumber());
	}

	@Test
	void shouldUpdateInventoryIfAdded() {
		Inventory inventory = new Inventory("20932K3150XA","VMS000447", 10.0, 5.0, 5.0);

		underTest.save(inventory);

		Inventory inventoryUpdate = new Inventory("20932K3150XA","VMS000447", 15.0, 10.0, 5.0);

		underTest.save(inventoryUpdate);

		Optional<Inventory> result = underTest.getOne(inventory.getPartNumber() + inventory.getSerialNumber());

		assertEquals(1, underTest.getAll().size());
		assertEquals(15.0, result.get().getInventoryQty());
		assertEquals(10.0, result.get().getAvailableQty());
	}

	@Test
	void shouldDeleteOneInventory() {
		Inventory inventory = new Inventory("20932K3150XA","VMS000447", 10.0, 5.0, 5.0);

		underTest.save(inventory);

		underTest.delete(inventory);

		Optional<Inventory> propertyEntities = underTest.getOne("20932K3150XAVMS000447");

		assertTrue(propertyEntities.isEmpty());
	}
}
