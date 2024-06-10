package lv.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lv.backend.models.ParkingArea;
import lv.backend.models.Reservation;
import lv.backend.services.IReservationServices;

@Controller
public class ReservationController {
	
	@Autowired
	private IReservationServices reservationServices;

	@GetMapping("/reservation/create")
	public String createReservationGetFunc(Reservation reservation, Model model) {
		model.addAttribute("allReservations", reservationServices.selectAllReservations());
		return "reservation-create-page";
	}

	
	@PostMapping("/reservation/create")
	public ResponseEntity<Void> createReservationPostFunc(@RequestBody @Validated Reservation reservation, BindingResult result, Model model) {
	    if (!result.hasErrors()) {
	        reservationServices.createNewReservation(reservation.getStartTime(), reservation.getEndTime(), null, null);
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}



	@GetMapping("/reservation/update/{id}")
	public String updateReservationByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("reservation", reservationServices.retrieveReservationById(id));
			model.addAttribute("allReservations", reservationServices.selectAllReservations());
			return "reservation-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/reservation/update/{id}")
	public String updateReservationByIdPostFunc(@PathVariable("id") Long id, @Validated Reservation reservation, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Reservation temp = reservationServices.updateReservationById(id, reservation.getStartTime(), reservation.getEndTime());
				return "redirect:/reservation/showAll/" + temp.getIdr();
			} catch (Exception e) {
				return "redirect:/reservation/error";
			}
		} else {
			return "reservation-update-page";
		}
	}

	@GetMapping("/reservation/delete/{id}")
	public String deleteReservationById(@PathVariable("id") Long id, Model model) {
		try {
			reservationServices.deleteReservationById(id);
			model.addAttribute("allreservations", reservationServices.selectAllReservations());
			return "reservation-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/reservation/showAll/{id}")
	public String reservationByIdFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("reservation", reservationServices.retrieveReservationById(id));
			return "reservation-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/reservation/showAll")
	public String allreservationsGetFunc(Model model) {
		model.addAttribute("allreservations", reservationServices.selectAllReservations());
		return "reservation-all-page";
	}

	@GetMapping("/reservation/error")
	public String errorreservationFunc() {
		return "error-page";
	}

}
