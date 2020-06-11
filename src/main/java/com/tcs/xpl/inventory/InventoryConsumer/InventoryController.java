package com.tcs.xpl.inventory.InventoryConsumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InventoryController {

	
	@Autowired
	public InventoryConsumerService cservice;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<String> createProduct(@RequestBody Inventory iv){
		return new ResponseEntity<String>(cservice.createProduct(iv), HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventory>> getProducts(){
		return new ResponseEntity<List<Inventory>>(cservice.fetchProducts(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Inventory> getProductById(@PathVariable("id") int idd){
		return new ResponseEntity<Inventory>(cservice.fetchProdcut(idd),HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody Inventory iv){
		return new ResponseEntity<String>(cservice.updateProd(iv),HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{product_Id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("product_Id") int idd){
		return new ResponseEntity<String>(cservice.delProd(idd),HttpStatus.OK);
	}
}