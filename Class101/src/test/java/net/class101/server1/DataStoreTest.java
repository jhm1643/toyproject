package net.class101.server1;

import org.junit.Test;

import net.class101.server1.dataStore.DataStore;

public class DataStoreTest {

	@Test
	public void productTableTest() {
		DataStore productDataStore= new DataStore();
		productDataStore.setProductTable();
		productDataStore.showProductTable();
	}
}
