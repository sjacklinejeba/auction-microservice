package com.bidding.sellservice.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bidding.sellservice.dto.ProductInfoDTO;
import com.bidding.sellservice.dto.Products;
import com.bidding.sellservice.model.BidInformation;
import com.bidding.sellservice.model.ProductInfo;

@Component
public interface SellerRepository {
	String saveProduct(ProductInfoDTO productInfoDTO) throws Exception;
	List<Products> getProductList();
	ProductInfo getProductInformation(String productId);
	List<BidInformation> getBidInformation(String productId);
	void deleteProduct(String productId) throws Exception;
}
