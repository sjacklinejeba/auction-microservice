package com.bidding.sellservice.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.sellservice.dto.ProductInfoDTO;
import com.bidding.sellservice.dto.Products;
import com.bidding.sellservice.model.ProductResponse;
import com.bidding.sellservice.service.SellerService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/e-auction/api/v1")
public class SellerController {

	static Logger logger = Logger.getLogger(SellerController.class.getName());

	@Autowired
	SellerService sellerService;

	@PostMapping(path="/seller/add-product")
	public String addProduct(@Valid @RequestBody ProductInfoDTO productInfoDTO, BindingResult bindingResult)
			throws Exception {
		logger.info("SellerController addProduct Method Begin : " + productInfoDTO.toString());
		if (null != bindingResult && bindingResult.hasErrors()) {
			return "Failed to Add product, Input validation failed : " + bindingResult.getAllErrors().toString();
		}
		return sellerService.saveProduct(productInfoDTO);
	}

	@GetMapping(path = "/seller/productlist")
	public List<Products> getProductList() {
		logger.info("SellerController getProductList Method Begin");
		return sellerService.getProductList();
	}

	@GetMapping(path="/seller/show-bids/{productId}")
	public ProductResponse placeBidController(@PathVariable String productId) throws Exception {
		return sellerService.getBidInformation(productId);
	}

	@DeleteMapping(path="/seller/delete/{productId}")
	public String deleteProduct(@PathVariable String productId) {
		// Trying to delete after bid end date (or) If the product size of bid > 0 throw custom exception
		logger.info("SellerController deleteProduct Method Begin");
		return sellerService.deleteProduct(productId);
	}
}