package com.endproduct.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endproduct.Entity.EndProduct;
import com.endproduct.Repository.EndProductRepository;

@Repository
public class EndProductDao {

	@Autowired
	EndProductRepository epr;
	public String postAllEndProduct(List<EndProduct> a) {
		epr.saveAll(a);
		return "saved again";
	}

	
	

}
