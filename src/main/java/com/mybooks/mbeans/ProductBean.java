package com.mybooks.mbeans;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ProductBean {
	
	private Long id;
	
	private String productName;
	
	private String productLabel;
	
	private String productCode;
	
	private BigDecimal OriginalPrice;
	
	private BigDecimal UsedBookSellingPrice;
	
	private BigDecimal NewBookSellingPrice;
	
	private BigDecimal UsedBookDiscount;
	
	private BigDecimal NewBookDiscount;
	
	private List<ProductImageBean> productImages;
	
	private Long Quantity;
	
	private String Author;
	
	private String Publisher;
	
	private int Edition;
	
	private String Description;
	
	private String Images;
	
	private String ProductAttributes;
	
	private CategoryBean category;
	
	private List<SubCategoryBean> subcategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getOriginalPrice() {
		return OriginalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		OriginalPrice = originalPrice;
	}

	public BigDecimal getUsedBookSellingPrice() {
		return UsedBookSellingPrice;
	}

	public void setUsedBookSellingPrice(BigDecimal usedBookSellingPrice) {
		UsedBookSellingPrice = usedBookSellingPrice;
	}

	public BigDecimal getNewBookSellingPrice() {
		return NewBookSellingPrice;
	}

	public void setNewBookSellingPrice(BigDecimal newBookSellingPrice) {
		NewBookSellingPrice = newBookSellingPrice;
	}

	public BigDecimal getUsedBookDiscount() {
		return UsedBookDiscount;
	}

	public void setUsedBookDiscount(BigDecimal usedBookDiscount) {
		UsedBookDiscount = usedBookDiscount;
	}

	public BigDecimal getNewBookDiscount() {
		return NewBookDiscount;
	}

	public void setNewBookDiscount(BigDecimal newBookDiscount) {
		NewBookDiscount = newBookDiscount;
	}

	public List<ProductImageBean> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageBean> productImages) {
		this.productImages = productImages;
	}

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public int getEdition() {
		return Edition;
	}

	public void setEdition(int edition) {
		Edition = edition;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImages() {
		return Images;
	}

	public void setImages(String images) {
		Images = images;
	}

	public String getProductAttributes() {
		return ProductAttributes;
	}

	public void setProductAttributes(String productAttributes) {
		ProductAttributes = productAttributes;
	}

	public CategoryBean getCategory() {
		return category;
	}


	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public List<SubCategoryBean> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<SubCategoryBean> subcategory) {
		this.subcategory = subcategory;
	}

}
