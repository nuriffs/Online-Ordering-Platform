package onlineOrderingPlatform.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuIdGenerator")
	@SequenceGenerator(name="menuIdGenerator", sequenceName="menuIdGenerator", allocationSize = 1)
	private long menuId;
	private String menuName;
	private String description;
	private double price;
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Favourite> favourites;
    
	public Menu() {
		super();
	}
	
	public Menu(String menuName, String description, double price, List<CartItem> cartItems,
			List<Favourite> favourites) {
		super();
		this.menuName = menuName;
		this.description = description;
		this.price = price;
		this.cartItems = cartItems;
		this.favourites = favourites;
	}

	public long getmenuId() {
		return menuId;
	}

	public void setmenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getmenuName() {
		return menuName;
	}
	
	public void setmenuName(String menuName) {
		this.menuName = menuName;
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

