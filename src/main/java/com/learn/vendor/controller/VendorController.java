package com.learn.vendor.controller;

import com.learn.vendor.model.Vendor;
import com.learn.vendor.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private static final Logger LOG = LoggerFactory.getLogger("VendorController.class");
    private static final String SYSTEM_HEALTHY = "System is healthy.";
    @Autowired
    public VendorService vendorService;

    @GetMapping("/health")
    public String getHealth(){
        LOG.info("Checking health of the application.");
        return SYSTEM_HEALTHY;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerVendor(@RequestBody Vendor vendor){
        Boolean isSuccess = false;
        try{
            LOG.info("Calling service to save vendor record from registerVendor().");
            isSuccess = vendorService.registerVendor(vendor);
        }catch (Exception e)
        {
            LOG.error("Error occurred while saving the vendor data.");
        }
        if (isSuccess) {
            return ResponseEntity.ok(vendor);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorInfo(@PathVariable Long id){
        Vendor vendor = new Vendor();
        vendor = vendorService.getVendorInfo(id);
        if ((vendor != null) && (vendor.getId() > 0)) {
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(vendor, HttpStatus.BAD_REQUEST);
        }
    }
}
