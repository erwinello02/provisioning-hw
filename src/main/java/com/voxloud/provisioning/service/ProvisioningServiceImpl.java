package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  ProvisioningServiceImpl implements ProvisioningService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public Device getProvisioningFile(String macAddress) throws Exception {
       return deviceRepository.findByMacAddress(macAddress).orElseThrow(
               () -> new NotFoundException("No mac address found!")
       );
    }
}
