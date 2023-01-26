package threeinarow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import threeinarow.models.ThreeInARow;
import threeinarow.services.IValidationService;

@RestController
@RequestMapping("api/threeinarow")
public class ThreeInARowController {
	
	@Autowired
	private IValidationService validationService;
	
	/**
	 * Receives the positions played and then validate if the current player is the winner
	 * or if the game is a draw.
	 * @param input
	 * @return
	 */
	@GetMapping
	public ThreeInARow checkResults(@RequestBody ThreeInARow input) {
		return validationService.checkResults(input);
	}

}
