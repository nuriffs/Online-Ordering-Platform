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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.service.MenuService;

@RestController
@RequestMapping("/api/v1/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	// Retrieve a list of all menu items
	@GetMapping
	public ResponseEntity<?> getAllMenu() {
		List<Menu> menu = menuService.getAllMenu();
		
		if (!menu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(menu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu items not found");
        }
	}
	
	// Retrieve details of a specific menu item by ID
	@GetMapping("/{foodId}")
	public ResponseEntity<?> getMenuById(@PathVariable Long foodId) {		
		Menu menu = menuService.getMenuById(foodId);
		
        if (menu != null) {
            return ResponseEntity.status(HttpStatus.OK).body(menu);
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item not found");
        }
	}
	
	// Add new menu item
//	@PostMapping("/")
//	public ResponseEntity<?> createMenu(@RequestBody Menu menu) {
//		if (menu != null) {
//            Menu createdMenu = menuService.createMenu(menu);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
//        } else {
//        	// need to add some logic
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request payload");
//        }
//	}
	
	// Update menu item
	@PutMapping("/{foodId}")
	public ResponseEntity<?> updateMenu(@PathVariable Long menuId, @RequestBody Menu updatedMenu) {
        Menu menu = menuService.updateMenu(menuId, updatedMenu);
        
        if (menu != null) {
            return ResponseEntity.status(HttpStatus.OK).body(menu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item not found");
        }
    }

	// Delete menu item
	@DeleteMapping("/{foodId}")
	public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        if (menuService.deleteMenu(menuId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Menu item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item not found");
        }
    }

}
