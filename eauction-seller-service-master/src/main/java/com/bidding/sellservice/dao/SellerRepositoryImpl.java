package com.bidding.sellservice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.bidding.sellservice.dto.ProductInfoDTO;
import com.bidding.sellservice.dto.Products;
import com.bidding.sellservice.model.BidInformation;
import com.bidding.sellservice.model.ProductInfo;
import com.bidding.sellservice.utils.Constants;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

	static Logger logger = Logger.getLogger(SellerRepositoryImpl.class.getName());

	@Autowired
	private DynamoDBMapper mapper;
	@Autowired
	private DynamoDB dynamoDB;

	public String saveProduct(ProductInfoDTO productInfoDTO) throws Exception {
		try {
			logger.info("SellerController saveProduct Method Starts Here : " + productInfoDTO.toString());
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			ProductInfo productInfo = dozerBeanMapper.map(productInfoDTO, ProductInfo.class);
			mapper.save(productInfo);
			return "Product Added successfully";
		} catch (Exception e) {
			logger.info("SellerController saveProduct Method Exception " + e.getLocalizedMessage());
			throw new Exception(e);
		}
	}

	public List<Products> getProductList() {
		List<Products> products = new ArrayList<>(); 
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<ProductInfo> scanResult = mapper.scan(ProductInfo.class, scanExpression);
		for(ProductInfo prodInfo : scanResult){
			Products prods = new Products();
			prods.setId(prodInfo.getId());
			prods.setValue(prodInfo.getProductName());
			products.add(prods);
		}
		return products;
	}

	public ProductInfo getProductInformation(String productId) {
		return mapper.load(ProductInfo.class, productId);
	}

	public List<BidInformation> getBidInformation(String productId) {
		Table table = dynamoDB.getTable("BidInformation");

		Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		expressionAttributeValues.put(":pr", productId);

		ItemCollection<ScanOutcome> items = table.scan("ProductId = :pr", null, expressionAttributeValues);

		Iterator<Item> iterator = items.iterator();
		List<BidInformation> bidInformations = new ArrayList<BidInformation>();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			BidInformation bidInformation = new BidInformation();
			bidInformation.setBidAmount(item.getInt("BidAmount"));
			bidInformation.setFirstName(item.getString("FirstName"));
			bidInformation.setEmail(item.getString("Email"));
			bidInformation.setPhone(item.getString("Phone"));
			bidInformations.add(bidInformation);

		}
		return bidInformations;
	}

	public void deleteProduct(String productId) throws Exception {

		try {
			logger.info("Repository Layer, deleteProduct Starts Here : " + productId);
			ProductInfo productInfo = new ProductInfo();
			productInfo.setId(productId);
			mapper.delete(productInfo);
		} catch (Exception e) {
			logger.info("Repository Layer, deleteProduct Exception Here : " + e.getLocalizedMessage());
		}
	}
}
