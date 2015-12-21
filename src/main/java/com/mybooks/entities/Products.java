package com.mybooks.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name = "PRODUCT_LABEL", nullable = false, length = 35)
	private String productLabel;
	
	@Column(name = "PRODUCT_CODE", nullable = false, length = 35, unique = true)
	private String productCode;
	
	@Column(name = "ORIGINAL_PRICE", precision = 8, scale = 2)
	private BigDecimal OriginalPrice;
	
	@Column(name = "USEDBOOK_SELLINGPRICE", precision = 8, scale = 2)
	private BigDecimal UsedBookSellingPrice;
	
	@Column(name = "NEWBOOK_SELLINGPRICE", precision = 8, scale = 2)
	private BigDecimal NewBookSellingPrice;
	
	@Column(name = "USEDBOOK_DISCOUNT", precision = 4, scale = 2)
	private BigDecimal UsedBookDiscount;
	
	@Column(name = "NEWBOOK_DISCOUNT", precision = 4, scale = 2)
	private BigDecimal NewBookDiscount;
	
	private Long Quantity;
	
	private String Author;
	
	private String Publisher;
	
	private int Edition;
	
	private String Description;
	
	@OneToMany(mappedBy = "productType", fetch=FetchType.EAGER)
	private List<ProductImages> listImages;
	
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

	public List<ProductImages> getListImages() {
		return listImages;
	}

	public void setListImages(List<ProductImages> listImages) {
		this.listImages = listImages;
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
