package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.VerificationStatus;
import org.haftrust.verifier.model.enums.converters.EmployeeTypeConverter;
import org.haftrust.verifier.model.enums.converters.VerificationStatusConverter;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_reference")
public class Reference implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idreference")
    private Integer id;
    
    @Column(name = "organisation_name", length = 45)
    private String organisationName;
    
    @Column(name = "contact_number", length = 25)
    private String contactNumber;
    
    @Column(name = "address", length = 100)
    private String address;
    
    @Column(name = "title", length = 10)
    private String title;
    
    @Column(name = "full_name", length = 45)
    private String fullName;
    
    @Column(name = "designation", length = 45)
    private String designation;
    
    @Column(name = "email", length = 45)
    private String email;
    
    @Column(name = "verification_status", length = 45)
    @Convert(converter = VerificationStatusConverter.class)
    private VerificationStatus verificationStatus;
    
    @Column(name = "verification_date")
    private Date verificationDate;
    
    @Column(name = "verification_comment", length = 100)
    private String verificationComment;
    
    @Column(name = "employee_type")
    @Convert(converter = EmployeeTypeConverter.class)
    private EmployeeType employeeType;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Verifier verifier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationComment() {
        return verificationComment;
    }

    public void setVerificationComment(String verificationComment) {
        this.verificationComment = verificationComment;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Verifier getVerifier() {
        return verifier;
    }

    public void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    @Override
    public String toString() {
        return "Reference { id=" + id 
                + ", organisationName=" + organisationName 
                + ", contactNumber=" + contactNumber 
                + ", address=" + address 
                + ", title=" + title 
                + ", fullName=" + fullName 
                + ", designation=" + designation 
                + ", email=" + email 
                + ", verificationStatus=" + verificationStatus 
                + ", verificationDate=" + verificationDate 
                + ", verificationComment=" + verificationComment 
                + ", employeeType=" + employeeType 
                + ", verifier=" + verifier + '}';
    }
    
}
