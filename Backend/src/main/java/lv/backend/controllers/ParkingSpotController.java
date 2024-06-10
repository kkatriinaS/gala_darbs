package lv.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.models.SpotStatus;
import lv.backend.services.IParkingSpotServices;

@Controller
public class ParkingSpotController {
	@Autowired
	private IParkingSpotServices parkingSpotServices;

	@GetMapping("/parkingSpot/create")
	public String createParkingSpotGetFunc(ParkingSpot parkingSpot, Model model) {
		model.addAttribute("allParkingspots", parkingSpotServices.selectAllParkingSpots());
		return "parkingSpot-create-page";
	}
	
	@PostMapping("/parkingSpot/create")
	public ResponseEntity<Void> createParkingAreaPostFunc(@RequestBody @Validated ParkingSpot parkingSpot, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        parkingSpotServices.createNewParkingSpot(parkingSpot.getSpotStatus());
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}
	


	@GetMapping("/parkingspot/update/{id}")
	public String updateParkingspotByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("parkingSpot", parkingSpotServices.retrieveParkingSpotById(id));
			model.addAttribute("allUsers", parkingSpotServices.selectAllParkingSpots());
			return "parkingSpot-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/parkingSpot/update/{id}")
	public String updateParkingSpotByIdPostFunc(@PathVariable("id") Long id, @Validated ParkingSpot parkingSpot,
			BindingResult result) {
		if (!result.hasErrors()) {
			try {
				ParkingSpot temp = parkingSpotServices.updateParkingSpotById(id,
						(Enum<SpotStatus>) parkingSpot.getTotalSpots());
				return "redirect:/parkingSpot/showAll/" + temp.getIds();
			} catch (Exception e) {
				return "redirect:/parkingSpot/error";
			}
		} else {
			return "parkingSpot-update-page";
		}
	}

	@DeleteMapping("/parkingSpot/delete/{id}")
	public String deleteParkingSpotById(@PathVariable("id") Long id, Model model) {
		try {
			parkingSpotServices.deleteParkingSpotById(id);
			model.addAttribute("allParkingSpots", parkingSpotServices.selectAllParkingSpots());
			return "parkingSpot-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/parkingSpot/showAll/{id}")
	public String parkingSpotByIdFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("parkingSpot", parkingSpotServices.retrieveParkingSpotById(id));
			return "parkingSpot-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/parkingSpot/showAll")
	public String allParkingSpotsGetFunc(Model model) {
		model.addAttribute("allParkingSpots", parkingSpotServices.selectAllParkingSpots());
		return "parkingSpot-all-page";
	}

	@GetMapping("/parkingSpot/error")
	public String errorParkingSpotFunc() {
		return "error-page";
	}

}
