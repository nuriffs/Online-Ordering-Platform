package onlineOrderingPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import onlineOrderingPlatform.model.Favourite;
import onlineOrderingPlatform.model.User;
import onlineOrderingPlatform.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @GetMapping
	    public ResponseEntity<List<User>> getUsers() {
	        List<User> allUsers = userService.getAllUsers();

	        if (allUsers.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	        }
	    }
	
	// Authenticate a user
	@GetMapping("/authenticate")
	public ResponseEntity<String> autehnticateUser(@RequestParam @NotBlank String username, 
												   @RequestParam @Size(min=8) String password) {
		User authenticatedUser = userService.authenticateUser(username, password);
		
		if (authenticatedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
	}
	
	// Retrieve information about a user
	@GetMapping("/{userId}")
	public Optional<User> getUserByUserId(@PathVariable String userId) {
		User user = userService.getUserByUserId(userId);
		return Optional.ofNullable(user);
	}
	
	// Retrieve the list of favourite items of a user	
	@GetMapping("/{userId}/favourites")
	public Optional<List<Favourite>> getUserFavourites(@PathVariable String userId) {
	    List<Favourite> favorites = userService.getUserFavorites(userId);
	    return Optional.ofNullable(favorites);
	}
	
	// Add an item to the user's list of favourites
	@PostMapping("/{userId}/favourites/add")
	public ResponseEntity<String> addToUserFavorites(@PathVariable String userId, @RequestParam String menuItemId) {
		if (userService.addToUserFavorites(userId, menuItemId)) {
			return ResponseEntity.status(HttpStatus.OK).body("Item added to favorites successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or menu item not found");
		}
	}
	 
	// Remove an item from the user's list of favourites
	@DeleteMapping("/{userId}/favorites/remove/{menuItemId}")
	public ResponseEntity<String> removeFromUserFavorites(@PathVariable String userId, @PathVariable String menuItemId) {
	    if (userService.removeFromUserFavorites(userId, menuItemId)) {
	    	return ResponseEntity.status(HttpStatus.OK).body("Item removed from favorites successfully");
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or menu item not found");
	    }
	}
	
	// Update user profile information
	@PutMapping("/{userId}")
	public ResponseEntity<String> updateUserProfile(@PathVariable String userId, @RequestBody User updatedUser) {
        if (userService.updateUserProfile(userId, updatedUser)) {
            return ResponseEntity.status(HttpStatus.OK).body("User profile updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or profile update failed");
        }
    }

	
	// Change user password
	@PutMapping("/{userId}/changepwd")
	public ResponseEntity<String> changeUserPassword(@PathVariable String userId, @RequestBody String newPassword) {
        if (userService.changeUserPassword(userId, newPassword)) {
            return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or password change failed");
        }
    }
	// Logout
	@GetMapping("/logout")
    public ResponseEntity<String> logout() {
        try {
            userService.logout();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }
}
