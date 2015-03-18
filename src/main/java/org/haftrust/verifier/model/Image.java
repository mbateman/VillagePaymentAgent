package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.VerificationStatus;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_image")
public class Image implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idimage")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "verification_status", length = 45)
    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;
    @Column(name = "verification_date")
    private Date verificationDate;
    @Column(name = "verification_comment", length = 100)
    private String verificationComment;
    @Column(name = "employee_type", length = 25)
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", date=" + date + ", photo=" + photo + ", verificationStatus=" + verificationStatus + ", verificationDate=" + verificationDate + ", verificationComment=" + verificationComment + ", employeeType=" + employeeType + '}';
    }    
}
