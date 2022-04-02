package pl.witoldbrzezinski.transportmarket.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="loads")
public class Load {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="load_id")
    private Long loadId;
	
	@Column(name = "name")
	@NotEmpty(message="Load name cannot be empty")
	private String name;
	
	@Column(name="loading_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@FutureOrPresent(message="Loading date must be in the future or present")
	@NotNull(message="Load date cannot be empty")
	private LocalDateTime loadingDate;
	
	@Column(name="loading_city")
	@NotEmpty(message="Loading city cannot be empty")
	private String loadingCity;
	
	@Column(name="loading_postcode")
	@NotEmpty(message="Loading postcode cannot be empty")
	@Pattern(regexp= "\\d{2}-\\d{3}", message="Wrong postcode format")
	private String loadingPostcode;
	
	@Column(name="unloading_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message="Unloading date must be in the future")
	private LocalDateTime unloadingDate;
	
	@Column(name="unloading_city")
	@NotEmpty(message="Unloading city cannot be empty")
	private String unloadingCity;
	
	@Column(name="unloading_postcode")
	@NotEmpty(message="Unloading postcode cannot be empty")
	@Pattern(regexp= "\\d{2}-\\d{3}", message="Wrong postcode format")
	private String unloadingPostcode;
	
	@Column(name="weight_in_tones")
	@Min(value=0, message="Weight must be greater than 0")
	private BigDecimal weight;
	
	@Column(name="loadType")
	@NotEmpty(message="Load type cannot be empty")
	private String loadType;
	
	@Column(name="price_in_pln")
	@NotNull(message="Price cannot be empty")
	@Min(value=0, message="Price must be greater than 0")
	private BigDecimal price;
	
	public Load() {
		
	}

	public Load(String name, LocalDateTime loadingDate, String loadingCity, String loadingPostcode,
			LocalDateTime unloadingDate, String unloadingCity, String unloadingPostcode, BigDecimal weight, String loadType,
			BigDecimal price) {
		this.name = name;
		this.loadingDate = loadingDate;
		this.loadingCity = loadingCity;
		this.loadingPostcode = loadingPostcode;
		this.unloadingDate = unloadingDate;
		this.unloadingCity = unloadingCity;
		this.unloadingPostcode = unloadingPostcode;
		this.weight = weight;
		this.loadType = loadType;
		this.price = price;
	}

	public Long getLoadId() {
		return loadId;
	}

	public void setLoadId(Long loadId) {
		this.loadId = loadId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(LocalDateTime loadingDate) {
		this.loadingDate = loadingDate;
	}

	public String getLoadingCity() {
		return loadingCity;
	}

	public void setLoadingCity(String loadingCity) {
		this.loadingCity = loadingCity;
	}

	public String getLoadingPostcode() {
		return loadingPostcode;
	}

	public void setLoadingPostcode(String loadingPostcode) {
		this.loadingPostcode = loadingPostcode;
	}

	public LocalDateTime getUnloadingDate() {
		return unloadingDate;
	}

	public void setUnloadingDate(LocalDateTime unloadingDate) {
		this.unloadingDate = unloadingDate;
	}

	public String getUnloadingCity() {
		return unloadingCity;
	}

	public void setUnloadingCity(String unloadingCity) {
		this.unloadingCity = unloadingCity;
	}

	public String getUnloadingPostcode() {
		return unloadingPostcode;
	}

	public void setUnloadingPostcode(String unloadingPostcode) {
		this.unloadingPostcode = unloadingPostcode;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getLoadType() {
		return loadType;
	}

	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Load [load_id=" + loadId + ", name=" + name + ", loadingDate=" + loadingDate + ", loadingCity="
				+ loadingCity + ", loadingPostcode=" + loadingPostcode + ", unloadingDate=" + unloadingDate
				+ ", unloadingCity=" + unloadingCity + ", unloadingPostcode=" + unloadingPostcode + ", weight=" + weight
				+ ", loadType=" + loadType + ", price=" + price + "]";
	}
	
	
	
}
