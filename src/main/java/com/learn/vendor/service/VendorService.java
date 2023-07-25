package com.learn.vendor.service;

import com.learn.vendor.entity.VendorEntity;
import com.learn.vendor.model.Vendor;
import com.learn.vendor.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    private static final Logger LOG = LoggerFactory.getLogger("VendorService.class");
    @Autowired
    public VendorRepository vendorRepository;

    // Method for saving new Vendor to the database
    public Boolean registerVendor(Vendor vendor) throws Exception{
        LOG.info("Inside registerVendor");
        VendorEntity vendorEntity = new VendorEntity();
        BeanUtils.copyProperties(vendor, vendorEntity);
        Boolean isStatusSuccess = false;
        try {
            vendorEntity = vendorRepository.save(vendorEntity);
            isStatusSuccess = true;
        }catch (Exception e)
        {
            LOG.error("Exception occurred while saving the vendor into the database.");
            isStatusSuccess = false;
        }
        return isStatusSuccess;
    }

    public Vendor getVendorInfo(Long id)
    {
        VendorEntity vendorEntity = new VendorEntity();
        Vendor vendor = new Vendor();
        LOG.info("Calling vendorRepository for getting vendor data based on Id.");
        try {
            vendorEntity = vendorRepository.findById(id).orElse(null);
            BeanUtils.copyProperties(vendorEntity, vendor);
        }catch (Exception e)
        {
            LOG.error("Exception occurred while retrieving Vendor Data for Id: "+id+". Error details: "+e.getMessage());
        }
        return vendor;
    }
}
