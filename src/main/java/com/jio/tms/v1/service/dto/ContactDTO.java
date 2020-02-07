package com.jio.tms.v1.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.jio.tms.v1.domain.enumeration.Designation;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Contact} entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private Designation contactDesignation;

    private String email;

    private Long phoneNumber;

    private String remarks;

    private String preferredTime;


    private Long customerId;

    private String customerCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Designation getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(Designation contactDesignation) {
        this.contactDesignation = contactDesignation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;
        if (contactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactDesignation='" + getContactDesignation() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", remarks='" + getRemarks() + "'" +
            ", preferredTime='" + getPreferredTime() + "'" +
            ", customerId=" + getCustomerId() +
            ", customerCompany='" + getCustomerCompany() + "'" +
            "}";
    }
}
