package onlineOrderingPlatform.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
	@SequenceGenerator(name="userIdGenerator", sequenceName="userIdGenerator", allocationSize = 1)
	private long userId;
	
	@Column(name = "username", length = 20, unique = true, nullable = false)
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
	private String username;
	
	// add Spring Security(BCrypt)
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	
	private String blockNumber;
	private String unitNumber;
	
	@Column(length = 6, nullable = false)
	@Digits(integer = 6, fraction = 0, message = "Postal Code must be a 6-digit number")
	private String postalCode;
	
	private String otherAddress;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	 private List<Order> orders;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favourite> favourites;
	
	@OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
	
	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, String email, String blockNumber,
			String unitNumber, String postalCode, String otherAddress, List<Order> orders, List<Favourite> favourites) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.blockNumber = blockNumber;
		this.unitNumber = unitNumber;
		this.postalCode = postalCode;
		this.otherAddress = otherAddress;
		this.orders = orders;
		this.favourites = favourites;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getOtherAddress() {
		return otherAddress;
	}

	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Favourite> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
}
