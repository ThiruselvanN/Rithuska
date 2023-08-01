package com.endproduct.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.endproduct.Entity.EndProduct;
import com.endproduct.service.EndProductService;

@RestController
public class EndProductController {
	
	@Autowired
	RestTemplate rt;
	@Autowired
	EndProductService eps;
	@GetMapping("/getStringUpper")
	public String getStringUpper() {
		
		String url = "http://localhost:8080/getString";
		
		ResponseEntity<String> s = rt.exchange(url,HttpMethod.GET,null,String.class);
		
		String x = s.getBody();
		return x.toUpperCase();		
	}
	
	
	@GetMapping("/getEndProduct")
	public List<EndProduct> getEndProduct (){
		
		String url1 = "http://localhost:8080/getAll";
		
		ResponseEntity <List<EndProduct>> r1 = rt.exchange(url1,HttpMethod.GET,null,new ParameterizedTypeReference <List<EndProduct>>(){});
		List<EndProduct> ep = r1.getBody();
		return ep;
	}

	
	@GetMapping("/getAllEndProduct")
	public List<EndProduct> getAllEndProduct(){
		String url1 = "http://localhost:8080/getAll";
		String url2 = "http://localhost:8081/getTaxByHsn/";
		
		ResponseEntity <List<EndProduct>> r1 = rt.exchange(url1,HttpMethod.GET,null,new ParameterizedTypeReference <List<EndProduct>>(){});
		List<EndProduct> p = r1.getBody();
		
		p.forEach(x->{
			int hsn = x.getHsn();
			ResponseEntity <Integer> r2 = rt.exchange(url2+hsn,HttpMethod.GET,null,Integer.class);
			int tax = r2.getBody();
			
			x.setPrice(x.getPrice()+x.getPrice()*tax/100);
		});
		return p;
	}
	
	@PostMapping("/postAllEndProduct")
	public String postAllEndProduct() {
		return eps.postAllEndProduct(getAllEndProduct());
	}
	

}
