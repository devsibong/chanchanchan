package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.ProductMapper;

@Service
public class ProductService implements MyService<Integer, Product>{

	@Autowired
	ProductMapper productmapper;
	
	@Override
	public void register(Product v) throws Exception {
		productmapper.insert(v);
	}

	@Override
	public void modify(Product v) throws Exception {
		productmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		productmapper.delete(k);
	}

	@Override
	public Product get(Integer k) throws Exception {
		return productmapper.select(k);
	}

	@Override
	public List<Product> getall() throws Exception {
		return productmapper.selectall();
	}
	
	//Paging
	public List<Product> getListByPaging(Criteria cri) throws Exception{
		return productmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return productmapper.getTotal(cri);
	}
	
	public int changeSale(int id, int salerate) throws Exception{
		return productmapper.changeSale(id,salerate);
	}
	
	public String getName(int id) throws Exception{
		return productmapper.getName(id);
	}
}
