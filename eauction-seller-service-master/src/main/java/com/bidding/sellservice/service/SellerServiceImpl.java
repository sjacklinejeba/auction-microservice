package com.bidding.sellservice.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.sellservice.dao.SellerRepository;
import com.bidding.sellservice.dto.ProductInfoDTO;
import com.bidding.sellservice.dto.Products;
import com.bidding.sellservice.model.BidInformation;
import com.bidding.sellservice.model.ProductInfo;
import com.bidding.sellservice.model.ProductResponse;
import com.bidding.sellservice.utils.BidDateException;

@Service
public class SellerServiceImpl implements SellerService {
	static Logger logger = Logger.getLogger(SellerServiceImpl.class.getName());
	@Autowired
	SellerRepository sellerRepository;
	
	public List<Products> getProductList() {
		logger.info("Inside SellerServiceImpl getProductList Method");
		return sellerRepository.getProductList();
	}

	public String saveProduct(ProductInfoDTO productInfoDTO) throws Exception {
		logger.info("Inside SellerServiceImpl saveProduct Method");
		LocalDate now = LocalDate.now();
		LocalDate bidDate = LocalDate.parse(productInfoDTO.getBidEndDate());
		if (bidDate.isAfter(now)) {
			return sellerRepository.saveProduct(productInfoDTO);
		} else {
			throw new BidDateException("Bid Date Should be future Date");
		}
	}

	public ProductResponse getBidInformation(String productId) throws Exception {
		logger.info("Inside SellerServiceImpl getBidInformation Method");
		ProductInfo productInfo = sellerRepository.getProductInformation(productId);
		List<BidInformation> bidInformation = sellerRepository.getBidInformation(productId);
		List<BidInformation> bids = bidInformation.stream()
				.sorted(Comparator.comparing(BidInformation::getBidAmount).reversed()).collect(Collectors.toList());
		ProductResponse response = new ProductResponse();
		response.setProductInfo(productInfo);
		response.setBidInfo(bids);
		return response;
	}

	public String deleteProduct(String productId) {
		logger.info("Inside SellerServiceImpl deleteProduct Method");
		try {
			sellerRepository.deleteProduct(productId);
			return "Product Deleted !";
		} catch (Exception e) {
			logger.info("Service Layer, deleteProduct Exception : " + e.getLocalizedMessage());
			return "Exception on delete" + e.getLocalizedMessage();
		}
	}
}
