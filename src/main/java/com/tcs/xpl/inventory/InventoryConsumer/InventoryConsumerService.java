package com.tcs.xpl.inventory.InventoryConsumer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InventoryConsumerService {
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	public String createProduct(Inventory iv) {
			ServiceInstance serviceInstance = loadBalancerClient.choose("InventoryService");
			String baseUrl = serviceInstance.getUri().toString();
			String result=null;
			
			String url = baseUrl + "/create";
			
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, iv, String.class);
			
			if(responseEntity.getBody() != null) {
				result = responseEntity.getBody();
			}
		
		return result;
	}


	public List<Inventory> fetchProducts() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("InventoryService");
		String baseUrl = serviceInstance.getUri().toString();
		List<Inventory> result=null;
		
		String url = baseUrl + "/";
		
		ResponseEntity<Inventory[]> responseEntity = restTemplate.getForEntity(url, Inventory[].class);
		
		if(responseEntity.getBody() != null) {
			result = Arrays.asList(responseEntity.getBody());
		}
		
		return result;
	}


	public Inventory fetchProdcut(int idd) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("InventoryService");
		String baseUrl = serviceInstance.getUri().toString();
		Inventory result=null;
		
		String url = baseUrl + "/"+idd;
		
		ResponseEntity<Inventory> responseEntity = restTemplate.getForEntity(url, Inventory.class);
		
		if(responseEntity.getBody() != null) {
			result = responseEntity.getBody();
		}
		
		return result;
	}


	public String updateProd(Inventory iv) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("InventoryService");
		String baseUrl = serviceInstance.getUri().toString();

		ObjectMapper Obj = new ObjectMapper(); 
		
		String url = baseUrl + "/update";
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON); 
	    HttpEntity<String> entity = null;
		try {
			entity = new HttpEntity<String>(Obj.writeValueAsString(iv), headers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
	    response.getHeaders().getLocation();
	    response.getStatusCode();
	    String result = response.getBody();
	
	return result;
	}


	public String delProd(int idd) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("InventoryService");
		String baseUrl = serviceInstance.getUri().toString();

		ObjectMapper Obj = new ObjectMapper(); 
		
		String url = baseUrl + "/delete/"+idd;
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON); 
	    HttpEntity<String> entity = null;
		entity = new HttpEntity<String>(headers); 
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
	    response.getHeaders().getLocation();
	    response.getStatusCode();
	    String result = response.getBody();
	
	return result;
	}

}
