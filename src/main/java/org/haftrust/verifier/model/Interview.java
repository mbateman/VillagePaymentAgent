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
import org.haftrust.verifier.model.enums.InterviewStatus;

/**
 *
 * @author LabClass
 */
@Entity
@Table(name = "ht_interview")
public class Interview implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idinterview")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private InterviewStatus status;
    @Column(name = "comment")
    private String comment;
    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Verifier verifier;
    @Column(name = "ht_fom_idfom")
    private Fom fom;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Fom getFom() {
        return fom;
    }

    public void setFom(Fom fom) {
        this.fom = fom;
    }

    @Override
    public String toString() {
        return "Interview{" + "id=" + id + ", date=" + date + ", address=" + address + ", status=" + status + ", comment=" + comment + ", employeeType=" + employeeType + ", verifier=" + verifier + ", fom=" + fom + '}';
    }

}
