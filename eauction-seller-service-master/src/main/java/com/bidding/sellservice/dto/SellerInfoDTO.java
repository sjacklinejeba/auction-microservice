package com.bidding.sellservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SellerInfoDTO {

	@NotEmpty
	@NotBlank(message = "firstName can't be blank")
	@Size(min = 5, max = 30)
	private String firstName;
	@NotBlank(message = "lastName can't be blank")
	@Size(min = 3, max = 25)
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String pin;
	@NotBlank(message = "phone can't be blank")
	@Size(min = 10, max = 10)
	private String phone;
	@Email
	private String email;
}
