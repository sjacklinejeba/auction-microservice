package com.bidding.sellservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "BidInformation")
public class BidInformation {

	@DynamoDBAutoGeneratedKey
	@DynamoDBRangeKey(attributeName = "Id")
	private String id;
	@DynamoDBAttribute(attributeName = "FirstName")
	private String firstName;
	@DynamoDBAttribute(attributeName = "LastName")
	private String lastName;
	@DynamoDBAttribute(attributeName = "Address")
	private String address;
	@DynamoDBAttribute(attributeName = "City")
	private String city;
	@DynamoDBAttribute(attributeName = "State")
	private String state;
	@DynamoDBAttribute(attributeName = "Pin")
	private String pin;
	@DynamoDBAttribute(attributeName = "Phone")
	private String phone;
	@DynamoDBHashKey(attributeName = "Email")
	private String email;
	@DynamoDBRangeKey(attributeName = "ProductId")
	private String productId;
	@DynamoDBAttribute(attributeName = "BidAmount")
	private int bidAmount;
}
