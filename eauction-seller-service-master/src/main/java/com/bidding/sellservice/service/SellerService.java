package com.bidding.sellservice.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bidding.sellservice.dto.ProductInfoDTO;
import com.bidding.sellservice.dto.Products;
import com.bidding.sellservice.model.ProductInfo;
import com.bidding.sellservice.model.ProductResponse;

@Component
public interface SellerService {
	String saveProduct(ProductInfoDTO productInfoDTO) throws Exception;
	List<Products> getProductList();
	ProductResponse getBidInformation(String productId) throws Exception;
	String deleteProduct(String productId);
}
