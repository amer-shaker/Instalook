package com.instalook.instalook.view.controller.salon;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.instalook.instalook.model.dal.service.SalonServiceService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
@RestController
@RequestMapping("/service")
public class SalonServiceRestController {

    @Autowired
    private SalonServiceService salonServiceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> addService(@RequestBody ServiceDTO service, UriComponentsBuilder ucBuilder) {
        int id = salonServiceService.addService(service);
        if (id != 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/add/{id}")
                    .buildAndExpand(service.getService().getServiceId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/getsalonsprovide/{servicename}")
    public List<Salon> getAllServiceProviders(@PathVariable("servicename") String serviceName) {
        return salonServiceService.getAllServiceProviders(serviceName);
    }

    @RequestMapping(value = "/getAllServices")
    public List<Service> getAllServicesById(@RequestParam("salonId") Integer salonId) {
        return salonServiceService.getAllServicesById(salonId);
    }

    @RequestMapping(value = "/salon/updateservice", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> updateService(@RequestBody Service service, UriComponentsBuilder ucBuilder) {
        salonServiceService.updateService(service);
        if (true) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/salon/update/{id}")
                    .buildAndExpand(service.getServiceId()).toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteServiceById(@RequestParam("serviceId") Integer serviceId) {
        return salonServiceService.deleteServiceById(serviceId);
    }
}
