package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {

    @Autowired
    ProvisioningService provisioningService;

    @GetMapping(value = "/provisioning/{macAddress}")
    public ResponseEntity<Device> getProvisioningService(@PathVariable String macAddress) throws Exception {
        Device device = provisioningService.getProvisioningFile(macAddress);
        return ResponseEntity.status(200).body(device);
    }
}