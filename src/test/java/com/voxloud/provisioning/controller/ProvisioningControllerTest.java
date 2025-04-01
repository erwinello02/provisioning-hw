package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.service.ProvisioningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisioningControllerTest {

    @Mock
    private ProvisioningService provisioningService;

    @InjectMocks
    private ProvisioningController provisioningController;

    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device(
                "aa-bb-cc-dd-ee-ff",
                Device.DeviceModel.DESK,
                null,
                "john",
                "doe"
        );
    }

    @Test
    void testGetProvisioningService_Success() throws Exception {
        when(provisioningService.getProvisioningFile("aa-bb-cc-dd-ee-ff")).thenReturn(device);
        ResponseEntity<Device> response = provisioningController.getProvisioningService("aa-bb-cc-dd-ee-ff");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(device, response.getBody());
    }

    @Test
    void testGetProvisioningService_DeviceNotFound() throws Exception {
        when(provisioningService.getProvisioningFile("invalid-mac-address")).thenThrow(new Exception("Device not found"));
        Exception exception = assertThrows(Exception.class, () -> {
            provisioningController.getProvisioningService("invalid-mac-address");
        });
        assertEquals("Device not found", exception.getMessage());
    }
}