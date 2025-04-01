package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;

public interface ProvisioningService {

    Device getProvisioningFile(String macAddress) throws Exception;
}
