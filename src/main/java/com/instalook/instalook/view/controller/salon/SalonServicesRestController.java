package com.instalook.instalook.view.controller.salon;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import com.instalook.instalook.model.dal.service.SalonServicesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Mohamed Ramadan
 */
@RestController
@RequestMapping("/service")
public class SalonServicesRestController {

    @Autowired
    private SalonServicesService salonServicesService;

    @RequestMapping(value = "/getservices/{salonId}")
    public List<Service> getSalonServices(@PathVariable("salonId") int id) {
        return salonServicesService.getAllServicesOfSalon(id);
    }

    @RequestMapping("/getsalonsprovide/{servicename}")
    public List<Salon> getSalonsProvidedService(@PathVariable("servicename") String serviceName) {
        return salonServicesService.getAllSalonProvideService(serviceName);
    }

    @RequestMapping("/delete/{serviceId}")
    public void deleteServiceById(@PathVariable("serviceId") int serviceId) {
        salonServicesService.deletServiceFromSalon(serviceId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> addServiceToSalon(@RequestBody ServiceDTO service, UriComponentsBuilder ucBuilder) {
        int id = salonServicesService.insertServiceToSalon(service);
        if (id != 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/add/{id}")
                    .buildAndExpand(service.getService().getServiceId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/salon/updateservice", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> updateSalonService(@RequestBody Service service, UriComponentsBuilder ucBuilder) {
        System.out.println("Service name json" + service.getServiceName());
        System.out.println("Service name json" + service.getServiceType());

        int id = salonServicesService.updateSalonService(service);
        if (id == 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/salon/update/{id}")
                    .buildAndExpand(service.getServiceId()).toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
