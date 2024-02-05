package onlineOrderingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepo;
	
	public List<Menu> getAllMenu() {
		return menuRepo.findAll();
	}
	
	public Menu getMenuById(Long foodId) {
		return null;
	}

	public Menu createMenu(Menu menu) {
		return null;
	}

	public Menu updateMenu(Long menuId, Menu updatedMenu) {
		return null;
	}

	public boolean deleteMenu(Long menuId) {
		return false;
	}
}
