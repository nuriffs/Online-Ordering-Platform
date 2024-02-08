package onlineOrderingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineOrderingPlatform.model.Favourite;
import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.model.User;
import onlineOrderingPlatform.repository.FavouriteRepository;
import onlineOrderingPlatform.repository.MenuRepository;
import onlineOrderingPlatform.repository.UserRepository;

@Service
public class FavouriteService {

	@Autowired
    private FavouriteRepository favouriteRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	
	public List<Favourite> getFavouritesByUserId(long userId) {
		return favouriteRepo.findByUserUserId(userId);
	}

	public void addToFavorites(long userId, long menuId) {
        User user = userRepo.findById(userId).orElse(null);
        Menu menu = menuRepo.findById(menuId).orElse(null);

        if (user == null || menu == null) {
            // Handle cases where user/menu does not exist
            // Throw an exception/return an error response
            return;
        }

        List<Favourite> existingFavorites = favouriteRepo.findByUserUserIdAndMenuMenuId(userId, menuId);

        if (existingFavorites.isEmpty()) {
            Favourite newFavorite = new Favourite();
            newFavorite.setUser(user);
            newFavorite.setMenu(menu);
            
            favouriteRepo.save(newFavorite);
        }
	}

	public void removeFromFavorites(long userId, long menuId) {
		favouriteRepo.deleteByUserUserIdAndMenuMenuId(userId, menuId);
	}
}
