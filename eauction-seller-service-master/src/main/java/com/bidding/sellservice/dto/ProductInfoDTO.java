package com.bidding.sellservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDTO {

	private String id;
	@NotBlank(message = "productName can't be blank")
	@Size(min = 5, max = 30)
	private String productName;
	private String shortDescription;
	private String detailDescription;
	@NotBlank(message = "category can't be blank")
	@Pattern(regexp = "Painting|Sculptor|Ornament", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String category;
	@Positive
	private int startingPrice;
	private String bidEndDate;
	@Valid
	@NotNull
	private SellerInfoDTO sellerInfo;

}
