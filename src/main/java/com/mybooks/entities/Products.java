package com.mybooks.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "PRODUCTS_SEQ")
@Access(AccessType.FIELD)
public class Products extends AuditableEntity implements BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCTS_SEQ")
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "PRODUCT_NAME", nullable = false, length = 35)
	private String productName;
	
	@Column(name = "PRODUCT_CODE", nullable = false, length = 35)
	private String productCode;
	
	@Column(name = "PRODUCT_PRICE", precision = 8, scale = 2)
	private BigDecimal Price;
	
	@Column(name = "DISCOUNT_PERCENTAGE")
	private String Discount;
	
	private long Quantity;
	
	private String Author;
	
	private String Publisher;
	
	private String Edition;
	
	private String Description;
	
	@Column(name = "PRODUCT_IMAGE_URL")
	private String Images;
	
	private String ProductAttributes;
	
	@ManyToMany
	@JoinTable(name = "PRODUCTS_AND_SUBCATEGORY",
				joinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID"),
				inverseJoinColumns = @JoinColumn(name = "SUBCATEGORY_ID", referencedColumnName = "SUBCATEGORY_ID"))
	private List<SubCategory> subcategorieslist;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	public Products(){
		
	}

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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public long getQuantity() {
		return Quantity;
	}

	public void setQuantity(long quantity) {
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

	public String getEdition() {
		return Edition;
	}

	public void setEdition(String edition) {
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

	public List<SubCategory> getSubcategorieslist() {
		return subcategorieslist;
	}

	public void setSubcategorieslist(List<SubCategory> subcategorieslist) {
		this.subcategorieslist = subcategorieslist;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
