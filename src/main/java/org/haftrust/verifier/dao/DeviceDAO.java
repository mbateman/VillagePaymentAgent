package org.haftrust.verifier.dao;

import java.util.List;

import org.haftrust.verifier.model.Device;
import org.haftrust.verifier.model.enums.DeviceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface DeviceDAO extends JpaRepository<Device, Integer> {

    Device findByImei(Long imei);
    
    List<Device> findByAllocation(DeviceAllocation allocation);

}
