package com.endproduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endproduct.Dao.EndProductDao;
import com.endproduct.Entity.EndProduct;

@Service
public class EndProductService {

	@Autowired
	EndProductDao epd;
	
	public String postAllEndProduct(List<EndProduct> a) {
		return epd.postAllEndProduct(a); 
	}

	
}
