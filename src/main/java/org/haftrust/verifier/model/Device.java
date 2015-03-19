package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.DeviceAllocation;
import org.haftrust.verifier.model.enums.converters.DeviceAllocationConverter;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_device")
public class Device implements java.io.Serializable {

    @Id
    @Column(name = "imei")
    private Long imei;
    
    @Column(name = "product_number")
    private String productNumber;
    
    @Column(name = "model_number")
    private String modelNumber;
    
    @Column(name = "ht_mobile_number")
    private String htMobileNumber;
    
    @Column(name = "allocation")
    @Convert(converter = DeviceAllocationConverter.class)
    private DeviceAllocation allocation;
    
    @Column(name = "allocation_date")
    private Date allocationDate;

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getHtMobileNumber() {
        return htMobileNumber;
    }

    public void setHtMobileNumber(String htMobileNumber) {
        this.htMobileNumber = htMobileNumber;
    }

    public DeviceAllocation getAllocation() {
        return allocation;
    }

    public void setAllocation(DeviceAllocation allocation) {
        this.allocation = allocation;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    @Override
    public String toString() {
        return "Device { imei=" + imei 
                + ", productNumber=" + productNumber 
                + ", modelNumber=" + modelNumber 
                + ", htMobileNumber=" + htMobileNumber 
                + ", allocation=" + allocation 
                + ", allocationDate=" + allocationDate + '}';
    }

}
