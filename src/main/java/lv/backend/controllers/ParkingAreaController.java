package lv.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.backend.models.ParkingArea;
import lv.backend.services.IParkingAreaServices;

@Controller
public class ParkingAreaController {

	@Autowired
	private IParkingAreaServices parkingAreaServices;

	@GetMapping("/parkingArea/create")
	public String createParkingAreaGetFunc(ParkingArea parkingArea, Model model) {
		model.addAttribute("allParkingAreas", parkingAreaServices.selectAllParkingArea());
		return "academicStaff-create-page";
	}

	@PostMapping("/parkingArea/create")
	public String createParkingAreaPostFunc(@Validated ParkingArea parkingArea, BindingResult result) {
		if (!result.hasErrors()) {
			parkingAreaServices.createNewParkingArea(parkingArea.getAreaName(), parkingArea.getTotalSpots());
			return "redirect:/parkingArea/showAll";
		} else {
			return "parkingArea-create-page";
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
			return "parkingArea-update-page";
		}
	}

	@GetMapping("/parkingArea/delete/{id}")
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
