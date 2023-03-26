package com.vantec.warehouse.inventorystocks;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vantec.warehouse.inventorystocks.dao.InventoryDao;
import com.vantec.warehouse.inventorystocks.exception.ErrorDetails;
import com.vantec.warehouse.inventorystocks.domain.Inventory;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryStocksApplicationTests {

	@Autowired
	private InventoryDao inventoryDao;

	@LocalServerPort
	private int serverPort;

	@BeforeEach
	void setup() {
		RestAssured.port = serverPort;
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig());
	}

	@AfterEach
	void tearDown() {
		inventoryDao.clear();
	}

	// added only positive scenarios
	@Test
	@Disabled
	void shouldAddInventoryQuantity() throws JSONException {
		Inventory inventory = new Inventory("20932K3150XA", "VMS000447", 10.0, 15.0, 5.0);
		inventoryDao.save(inventory);

		JSONObject jsonObj = new JSONObject().put("partNumber", "20932K3150XC").put("serialNumber", "VMS000457")
				.put("availableQty", "10.0");

		Response response = given().when().header("Content-Type", "application/json")
				.header("Accept", "application/json").body(jsonObj.toString()).put("/api/inventory/add-quantity").then()
				.statusCode(HttpStatus.SC_OK).extract().response();

		Inventory updatedInventory = response.getBody().as(Inventory.class);

		assertEquals(20.0, updatedInventory.getInventoryQty());
		assertEquals(15.0, updatedInventory.getAvailableQty());
	}

	@Test
	void shouldReduceInventoryQuantity() throws JSONException {
		Inventory inventory = new Inventory("20932K31502A", "VMS000487", 10.0, 10.0, 5.0);
		inventoryDao.save(inventory);

		JSONObject jsonObj = new JSONObject().put("partNumber", "20932K31502A").put("serialNumber", "VMS000487")
				.put("availableQty", "5.0");

		Response response = given().when().header("Content-Type", "application/json")
				.header("Accept", "application/json").body(jsonObj.toString()).put("/api/inventory/reduce-quantity")
				.then().statusCode(HttpStatus.SC_OK).extract().response();

		Inventory updatedInventory = response.getBody().as(Inventory.class);

		assertEquals(10.0, updatedInventory.getInventoryQty());
		assertEquals(5.0, updatedInventory.getAvailableQty());
	}

	@Disabled
	@Test
	void shouldDeleteInventoryQuantity() throws JSONException {
		Inventory inventory = new Inventory("20932K3150XA", "VMS000447", 10.0, 5.0, 5.0);
		inventoryDao.save(inventory);

		JSONObject jsonObj = new JSONObject().put("partNumber", "20932K3150XC").put("serialNumber", "VMS000457")
				.put("availableQty", "5.0");

		Response response = given().when().header("Content-Type", "application/json")
				.header("Accept", "application/json").body(jsonObj.toString()).put("/api/inventory/reduce-quantity")
				.then().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();

		ErrorDetails error = response.getBody().as(ErrorDetails.class);

		assertEquals("Inventory/available quantity is less than or equal to 0 hence deleted the inventory", error.getMessage());
	}

}
