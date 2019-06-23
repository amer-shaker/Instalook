package com.instalook.instalook.view.controller.booking;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import com.instalook.instalook.model.dal.entity.User;
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
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Anas
 */
@RestController
@RequestMapping("/booking")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/book",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public Object book(@RequestBody BookingDTO bookingDTO, UriComponentsBuilder ucBuilder) {
        int id = bookingService.book(bookingDTO);
        BaseResponse responseBody = new BaseResponse();

        if (id != 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setLocation(ucBuilder.path("/book/{id}")
                    .buildAndExpand(id).toUri());

            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody,
                    headers,
                    HttpStatus.CREATED);

            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Successfully, booked.");
            return response;
        } else {
            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Duplicate entry for e-mail");
            return response;
        }
    }

    @RequestMapping(value = "/salon/getBookings", method = RequestMethod.GET,
            produces = "application/json")
    public List<Object[]> getBarberBookings(@RequestParam int barberId) {
        return bookingService.getAllBarberBookings(barberId);
    }

    @RequestMapping(value = "/user/getBookings", method = RequestMethod.GET,
            produces = "application/json")
    public List<Object[]> getAllUserBookings(@RequestParam int userId) {
        return bookingService.getAllUserBookings(userId);
    }

    @RequestMapping(value = "/cancelBooking", method = RequestMethod.DELETE,
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
}
