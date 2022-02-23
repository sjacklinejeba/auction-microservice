package com.bidding.sellservice.model;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {

	private ProductInfo productInfo;
	private List<BidInformation> bidInfo; 
	
}
