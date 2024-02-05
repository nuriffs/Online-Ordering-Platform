package onlineOrderingPlatform.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import onlineOrderingPlatform.model.Favourite;
import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.model.User;
import onlineOrderingPlatform.repository.MenuRepository;
import onlineOrderingPlatform.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	public User authenticateUser(@NotBlank String username, @Size(min = 8) String password) {
		User user = userRepo.findByUsername(username);

        if (user != null && isValidPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

	public User getUserByUserId(String userId) {
		return userRepo.findById(Long.parseLong(userId)).orElse(null);
	}

	public List<Favourite> getUserFavorites(String userId) {
	    User user = userRepo.findById(Long.parseLong(userId)).orElse(null);

	    if (user != null) {
	        return user.getFavourites();
	    }

	    return Collections.emptyList();
	}

	public boolean addToUserFavorites(String userId, String menuItemId) {
		User user = userRepo.findById(Long.parseLong(userId)).orElse(null);
	    Menu menuItem = menuRepo.findById(Long.parseLong(menuItemId)).orElse(null);

	    if (user != null && menuItem != null) {
	        List<Favourite> userFavorites = user.getFavourites();
	        
	        if (userFavorites.stream().noneMatch(f -> f.getMenu().equals(menuItem))) {
	            Favourite favourite = new Favourite();
	            favourite.setMenu(menuItem);
	            
	            userFavorites.add(favourite);
	            userRepo.save(user);
	            return true;
	        }
	    }

	    return false;
	}

	public boolean removeFromUserFavorites(String userId, String menuItemId) {
        User user = userRepo.findById(Long.parseLong(userId)).orElse(null);

        Menu menuItem = menuRepo.findById(Long.parseLong(menuItemId)).orElse(null);

        if (user != null && menuItem != null) {
            Favourite favoriteToRemove = null;

            for (Favourite favourite : user.getFavourites()) {
                if (favourite.getMenu().equals(menuItem)) {
                    favoriteToRemove = favourite;
                    break;
                }
            }

            if (favoriteToRemove != null) {
                user.getFavourites().remove(favoriteToRemove);
                userRepo.save(user);
                return true;
            }

            return false;
        }

        return false;
    }

	public boolean updateUserProfile(String userId, User updatedUser) {
		User user = userRepo.findById(Long.parseLong(userId)).orElse(null);

		if (user != null) {
			if (updatedUser.getFirstName() != null) {
	            user.setFirstName(updatedUser.getFirstName());
	        }
	        if (updatedUser.getLastName() != null) {
	            user.setLastName(updatedUser.getLastName());
	        }
	        if (updatedUser.getEmail() != null) {
	            user.setEmail(updatedUser.getEmail());
	        }
	        if (updatedUser.getBlockNumber() != null) {
	            user.setBlockNumber(updatedUser.getBlockNumber());
	        }
	        if (updatedUser.getUnitNumber() != null) {
	            user.setUnitNumber(updatedUser.getUnitNumber());
	        }
	        if (updatedUser.getPostalCode() != null) {
	            user.setPostalCode(updatedUser.getPostalCode());
	        }
	        if (updatedUser.getOtherAddress() != null) {
	            user.setOtherAddress(updatedUser.getOtherAddress());
	        }
	
	        userRepo.save(user);
	        return true;
	    }

        return false;
	}

	public boolean changeUserPassword(String userId, String newPassword) {
		User user = userRepo.findById(Long.parseLong(userId)).orElse(null);

        if (user != null) {
            user.setPassword(newPassword);

            userRepo.save(user);
            return true;
        }

        return false;
	}

	 private boolean isValidPassword(String providedPassword, String storedPassword) {
		// later on can add the usage of: BCryptPasswordEncoder
	    return providedPassword.equals(storedPassword);
	 }

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public void logout() {
    }
}
