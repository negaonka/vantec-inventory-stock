package com.vantec.warehouse.inventorystocks.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vantec.warehouse.inventorystocks.dao.InventoryDao;
import com.vantec.warehouse.inventorystocks.domain.Inventory;

import jakarta.annotation.PostConstruct;

@Component
public class DataPopulator {
	
		private final InventoryDao inventoryDao;

		@Autowired
		public DataPopulator(InventoryDao inventoryDao) {
			this.inventoryDao = inventoryDao;
		}

		@PostConstruct
		public void insertPropertyData() {
			inventoryDao.save(new Inventory("20932K3150XC","VMS000457", 10.0, 5.0, 5.0));
			inventoryDao.save(new Inventory("20832K1450","VMS000337", 2.0, 0.0, 2.0));
			inventoryDao.save(new Inventory("20832K1460","VMS000265", 22.0,	11.0, 11.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000495", 10.0, 10.0, 0.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000493", 50.0, 25.0, 25.0));
			inventoryDao.save(new Inventory("2A732K1110XC","VMS000508", 10.0, 0.0, 10.0));
			inventoryDao.save(new Inventory("2A732K1110XC","VMS000567", 63.0, 60.0, 3.0));
			inventoryDao.save(new Inventory("20832K1430","VMS000481", 25.0, 25.0, 0.0));
			inventoryDao.save(new Inventory("20832K1430","VMS000482", 45.0, 40.0, 5.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000488", 800.0, 400.0, 400.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000489", 26.0, 22.0, 4.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000490", 25.0, 25.0, 0.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000555", 25.0, 0.0, 25.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000486", 44.0, 22.0, 22.0));
			inventoryDao.save(new Inventory("20932K3160XC","VMS000487", 10.0, 10.0, 0.0));
			
		}
}
