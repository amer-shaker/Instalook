package com.instalook.instalook.view.controller.booking;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import com.instalook.instalook.model.dal.service.BookingService;
import com.instalook.instalook.view.controller.utils.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anas
 */
@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/salon/getBookings", method = RequestMethod.GET,
            produces = "application/json")
    public List<Object[]> getBarberBookings(@RequestParam int barberId) {
        return bookingService.getBarberBookings(barberId);
    }

    @RequestMapping(value = "/user/getBookings", method = RequestMethod.GET,
            produces = "application/json")
    public List<Object[]> getUserBookings(@RequestParam int userId) {
        return bookingService.getUserBookings(userId);
    }

    @RequestMapping(value = "/cancelBooking", method = RequestMethod.GET,
            produces = "application/json")
    public Object cancelBooking(@RequestParam int bookingId) {
        boolean isBookingDeleted = bookingService.cancelBooking(bookingId);
        if (isBookingDeleted) {
            BaseResponse responseBody = new BaseResponse(HttpStatus.OK, "Reservation Canceled Successfully");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return responseBody;
        } else {
            BaseResponse responseBody = new BaseResponse(HttpStatus.CONFLICT, "Failed to Canceled Reservation");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return responseBody;
        }
    }

    @RequestMapping(value = "/user/book", method = RequestMethod.POST,
            produces = "application/json")
    public Object getUserBookings(@RequestBody BookingDTO bookingDTO) {
        int id = bookingService.insertNewBooking(bookingDTO);
        if (id != 0) {
            BaseResponse responseBody = new BaseResponse();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            responseBody.setStatusMessage("Booking Done Successfully.");
            return responseBody;
        } else {
            BaseResponse responseBody = new BaseResponse();
            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Booking Error");
            return response;
        }

    }

}
