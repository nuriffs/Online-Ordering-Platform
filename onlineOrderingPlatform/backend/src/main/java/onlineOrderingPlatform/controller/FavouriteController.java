package onlineOrderingPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlineOrderingPlatform.model.Favourite;
import onlineOrderingPlatform.service.FavouriteService;

@RestController
@RequestMapping("/api/v1/favourites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getFavouritesByUserId(@PathVariable long userId) {
        List<Favourite> favourites = favouriteService.getFavouritesByUserId(userId);
        // return ResponseEntity.ok(favourites);
        if (!favourites.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(favourites);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fav items not found");
        }
    }

    @PostMapping("/{userId}/add/{foodId}")
    public ResponseEntity<String> addToFavorites(@PathVariable long userId, @PathVariable long menuId) {
        favouriteService.addToFavorites(userId, menuId);
        return ResponseEntity.ok("Menu added to favorites");
    }

    @DeleteMapping("/{userId}/remove/{foodId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable long userId, @PathVariable long menuId) {
        favouriteService.removeFromFavorites(userId, menuId);
        return ResponseEntity.ok("Menu removed from favorites");
    }
}
