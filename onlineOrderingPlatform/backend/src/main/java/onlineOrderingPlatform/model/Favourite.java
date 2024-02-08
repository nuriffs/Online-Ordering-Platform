package onlineOrderingPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Favourite {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favIdGenerator")
	@SequenceGenerator(name="favIdGenerator", sequenceName="favIdGenerator", allocationSize = 1)
    private long favouriteId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

	public Favourite() {
		super();
	}

	public Favourite(User user, Menu menu) {
		super();
		this.user = user;
		this.menu = menu;
	}

	public long getFavouriteId() {
		return favouriteId;
	}

	public void setFavouriteId(long favouriteId) {
		this.favouriteId = favouriteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
