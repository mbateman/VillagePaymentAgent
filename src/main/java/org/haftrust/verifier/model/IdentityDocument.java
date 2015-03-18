package org.haftrust.verifier.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.haftrust.verifier.model.enums.EmployeeType;
import org.haftrust.verifier.model.enums.IdentityDocumentType;
import org.haftrust.verifier.model.enums.VerificationStatus;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_identity_document")
public class IdentityDocument implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ididentitydocument")
    private int id;
    @Column(name = "number", length = 25)
    private String number;
    @Column(name = "type", length = 25)
    private IdentityDocumentType type;
    @Column(name = "issue_date")
    private Date issueDate;
    @Column(name = "expiry_date")
    private Date expiryDate;
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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Verifier verifier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public IdentityDocumentType getType() {
        return type;
    }

    public void setType(IdentityDocumentType type) {
        this.type = type;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
        return "IdentityDocument{" + "id=" + id + ", number=" + number + ", type=" + type + ", issueDate=" + issueDate + ", expiryDate=" + expiryDate + ", verificationStatus=" + verificationStatus + ", verificationDate=" + verificationDate + ", verificationComment=" + verificationComment + ", employeeType=" + employeeType + ", verifier=" + verifier + '}';
    }    
}
