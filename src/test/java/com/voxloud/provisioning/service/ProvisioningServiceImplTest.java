package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisioningServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private ProvisioningServiceImpl provisioningService;

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
    public void testGetProvisioningFile_Success() throws Exception {
        when(deviceRepository.findByMacAddress(device.getMacAddress())).thenReturn(Optional.of(device));
        Device result = provisioningService.getProvisioningFile(device.getMacAddress());
        assertNotNull(result);
        assertEquals(device.getMacAddress(), result.getMacAddress());
        verify(deviceRepository, times(1)).findByMacAddress(device.getMacAddress());
    }

    @Test
    void testGetProvisioningFile_NotFoundException() {
        String invalidMac = "aa-aa-aa-aa-aa-aa";
        when(deviceRepository.findByMacAddress(invalidMac)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundException.class, () -> provisioningService.getProvisioningFile(invalidMac));
        assertEquals("No mac address found!", exception.getMessage());
        verify(deviceRepository, times(1)).findByMacAddress(invalidMac);
    }
}
