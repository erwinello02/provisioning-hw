package com.voxloud.provisioning.repository;

import com.voxloud.provisioning.entity.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void testFindByMacAddress() {
        Device device = new Device(
                "aa-bb-cc-dd-ee-ff",
                Device.DeviceModel.DESK,
                null,
                "john",
                "doe"
        );
        deviceRepository.save(device);
        Optional<Device> foundDevice = deviceRepository.findByMacAddress("aa-bb-cc-dd-ee-ff");
        assertThat(foundDevice).isPresent();
        assertThat(foundDevice.get().getMacAddress()).isEqualTo("aa-bb-cc-dd-ee-ff");
    }
}


