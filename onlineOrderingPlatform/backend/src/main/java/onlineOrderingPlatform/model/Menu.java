package onlineOrderingPlatform.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long foodId;
	private String foodName;
	private String description;
	private double price;
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Favourite> favourites;
    
	public Menu() {
		super();
	}
	
	public Menu(String foodName, String description, double price, List<CartItem> cartItems,
			List<Favourite> favourites) {
		super();
		this.foodName = foodName;
		this.description = description;
		this.price = price;
		this.cartItems = cartItems;
		this.favourites = favourites;
	}

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}

