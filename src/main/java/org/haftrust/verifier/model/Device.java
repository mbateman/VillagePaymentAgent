package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.DeviceAllocation;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_device")
public class Device implements java.io.Serializable {

    @Id
    @Column(name = "imei")
    private long imei;
    @Column(name = "product_number")
    private String productNumber;
    @Column(name = "model_number")
    private String modelNumber;
    @Column(name = "ht_mobile_number")
    private String htMobileNumber;
    @Column(name = "allocation")
    @Enumerated(EnumType.STRING)
    private DeviceAllocation allocation;
    @Column(name = "allocation_date")
    private Date allocationDate;

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
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
        return "Device{" + "imei=" + imei + ", productNumber=" + productNumber + ", modelNumber=" + modelNumber + ", htMobileNumber=" + htMobileNumber + ", allocation=" + allocation + ", allocationDate=" + allocationDate + '}';
    }

}
