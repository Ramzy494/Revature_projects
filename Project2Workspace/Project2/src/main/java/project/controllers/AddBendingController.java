package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import project.service.AccountService;

@RestController
public class AddBendingController {
	
	AccountService serv;
	
	
	@Autowired
	public AddBendingController(AccountService serv) {
		super();
		this.serv = serv;
	}



	@PostMapping(value="/addwaterbending")
	/**
	 * Method that takes an input from a button on the home page to update someone's profile to include a bending style
	 * @author Brody
	 */
	public void addWaterBending() {
		
		String bendingStyle ="Water Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addearthbending")
	/**
	 * Method that takes an input from a button on the home page to update someone's profile to include a bending style
	 * @author Brody
	 */
	public void addEarthBending() {
		
		String bendingStyle ="Earth Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addairbending")
	/**
	 * Method that takes an input from a button on the home page to update someone's profile to include a bending style
	 * @author Brody
	 */
	public void addAirBending() {
		
		String bendingStyle ="Air Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addfirebending")
	/**
	 * Method that takes an input from a button on the home page to update someone's profile to include a bending style
	 * @author Brody
	 */
	public void addFireBending() {
		
		String bendingStyle ="Fire Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	
}
