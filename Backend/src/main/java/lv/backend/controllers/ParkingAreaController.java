package lv.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.services.IParkingAreaServices;
import lv.backend.services.IParkingSpotServices;
import lv.backend.services.impl.ParkingSpotServicesImplementation;

@Controller
public class ParkingAreaController {

	@Autowired
	private IParkingAreaServices parkingAreaServices;
	

	@GetMapping("/parkingArea/create")
	public String createParkingAreaGetFunc(ParkingArea parkingArea, Model model) {
		model.addAttribute("allParkingAreas", parkingAreaServices.selectAllParkingArea());
		return "parkingArea-create-page";
	}

	@PostMapping("/parkingArea/create")
	public ResponseEntity<Void> createParkingAreaPostFunc(@RequestBody @Validated ParkingArea parkingArea, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        parkingAreaServices.createNewParkingArea(parkingArea.getAreaName(), parkingArea.getTotalSpots(), null);
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}


	@GetMapping("/parkingArea/update/{id}")
	public String updateParkingAreaByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("parkingArea", parkingAreaServices.retrieveParkingAreaById(id));
			model.addAttribute("allUsers", parkingAreaServices.selectAllParkingArea());
			return "parkingArea-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/parkingArea/update/{id}")
	public String updateParkingAreaByIdPostFunc(@PathVariable("id") Long id, @Validated ParkingArea parkingArea,
			BindingResult result) {
		if (!result.hasErrors()) {
			try {
				ParkingArea temp = parkingAreaServices.updateParkingAreaById(id, parkingArea.getAreaName(),
						parkingArea.getTotalSpots());
				return "redirect:/parkingArea/showAll/" + temp.getIda();
			} catch (Exception e) {
				return "redirect:/parkingArea/error";
			}
		} else {
			return "parkingArea-all-page";
		}
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<ParkingArea> getParkingAreaById(@PathVariable("id") Long id) {
        try {
            ParkingArea parkingArea = parkingAreaServices.retrieveParkingAreaById(id);
            return ResponseEntity.ok(parkingArea);
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingArea> updateParkingAreaById(@PathVariable("id") Long id, @RequestBody ParkingArea parkingArea) {
        try {
            ParkingArea updatedParkingArea = parkingAreaServices.updateParkingAreaById(id, parkingArea.getAreaName(), parkingArea.getTotalSpots());
            return ResponseEntity.ok(updatedParkingArea);
        } catch (Exception e) {
            return ResponseEntity.status(400).build(); // Bad Request
        }
    }

	@DeleteMapping("/parkingArea/delete/{id}")
	public String deleteParkingAreaById(@PathVariable("id") Long id, Model model) {
		try {
			parkingAreaServices.deleteParkingAreaById(id);
			model.addAttribute("allParkingAreas", parkingAreaServices.selectAllParkingArea());
			return "parkingArea-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}



	@GetMapping("/parkingArea/showAll/{id}")
	public String parkingAreaByIdFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("parkingArea", parkingAreaServices.retrieveParkingAreaById(id));
			return "parkingArea-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/parkingArea/showAll")
	public String allParkingAreasGetFunc(Model model) {
		model.addAttribute("allParkingAreas", parkingAreaServices.selectAllParkingArea());
		return "parkingArea-all-page";
	}
	


	@GetMapping("/parkingArea/error")
	public String errorParkingAreaFunc() {
		return "error-page";
	}

}
