package com.vantec.warehouse.inventorystocks.dao;

import java.util.Optional;
import java.util.Set;

import com.vantec.warehouse.inventorystocks.domain.Inventory;

/**
 * A repository in charge of Inventory data.
 */
public interface InventoryDao {

	/**
	 * Gets a property by its id.
	 * @param id The Id of the property we wish to return.
	 * @return an {@code Optional} {@link Inventory}. If no inventory can be found by the given Id will return empty.
	 */
	Optional<Inventory> getOne(String id);

	/**
	 * @return A {@code Set} of all the inventories currently stored in this repository.
	 */
	Set<Inventory> getAll();

	/**
	 * Clears all the inventories currently stored in this repository.
	 */
	void clear();

	/**
	 * Saves the given {@link Inventory} to the repository. If a inventory entity with the same Id already exists, it will be overridden.
	 * @param inventory The {@code Inventory} to save to the repository.
	 */
	void save(Inventory inventory);
	
	/**
	 * Deletes the given {@link Inventory} to the repository.
	 * @param inventory The {@code Inventory} to delete in this repository.
	 */
	void delete(Inventory inventory);
}
