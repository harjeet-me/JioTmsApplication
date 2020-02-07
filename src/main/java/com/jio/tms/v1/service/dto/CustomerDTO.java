package com.jio.tms.v1.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.Designation;
import com.jio.tms.v1.domain.enumeration.PreffredContactType;
import com.jio.tms.v1.domain.enumeration.CountryEnum;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;
import com.jio.tms.v1.domain.enumeration.CURRENCY;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String company;

    private String firstName;

    private String lastName;

    private Designation contactDesignation;

    private String email;

    private Long phoneNumber;

    private PreffredContactType preffredContactType;

    private String website;

    private String secondaryContactPerson;

    private Long secContactNumber;

    private String secContactEmail;

    private String secContactPreContactTime;

    private Long fax;

    private String address;

    private String streetAddress;

    private String city;

    private String stateProvince;

    private CountryEnum country;

    private String postalCode;

    private String dot;

    private Long mc;

    @Lob
    private byte[] companyLogo;

    private String companyLogoContentType;
    private LocalDate customerSince;

    private String remarks;

    @Lob
    private byte[] contract;

    private String contractContentType;
    private ToggleStatus status;

    private CURRENCY preffredCurrency;

    private String payterms;

    private ZonedDateTime timeZone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public PreffredContactType getPreffredContactType() {
        return preffredContactType;
    }

    public void setPreffredContactType(PreffredContactType preffredContactType) {
        this.preffredContactType = preffredContactType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSecondaryContactPerson() {
        return secondaryContactPerson;
    }

    public void setSecondaryContactPerson(String secondaryContactPerson) {
        this.secondaryContactPerson = secondaryContactPerson;
    }

    public Long getSecContactNumber() {
        return secContactNumber;
    }

    public void setSecContactNumber(Long secContactNumber) {
        this.secContactNumber = secContactNumber;
    }

    public String getSecContactEmail() {
        return secContactEmail;
    }

    public void setSecContactEmail(String secContactEmail) {
        this.secContactEmail = secContactEmail;
    }

    public String getSecContactPreContactTime() {
        return secContactPreContactTime;
    }

    public void setSecContactPreContactTime(String secContactPreContactTime) {
        this.secContactPreContactTime = secContactPreContactTime;
    }

    public Long getFax() {
        return fax;
    }

    public void setFax(Long fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(CountryEnum country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public Long getMc() {
        return mc;
    }

    public void setMc(Long mc) {
        this.mc = mc;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public byte[] getContract() {
        return contract;
    }

    public void setContract(byte[] contract) {
        this.contract = contract;
    }

    public String getContractContentType() {
        return contractContentType;
    }

    public void setContractContentType(String contractContentType) {
        this.contractContentType = contractContentType;
    }

    public ToggleStatus getStatus() {
        return status;
    }

    public void setStatus(ToggleStatus status) {
        this.status = status;
    }

    public CURRENCY getPreffredCurrency() {
        return preffredCurrency;
    }

    public void setPreffredCurrency(CURRENCY preffredCurrency) {
        this.preffredCurrency = preffredCurrency;
    }

    public String getPayterms() {
        return payterms;
    }

    public void setPayterms(String payterms) {
        this.payterms = payterms;
    }

    public ZonedDateTime getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZonedDateTime timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactDesignation='" + getContactDesignation() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", preffredContactType='" + getPreffredContactType() + "'" +
            ", website='" + getWebsite() + "'" +
            ", secondaryContactPerson='" + getSecondaryContactPerson() + "'" +
            ", secContactNumber=" + getSecContactNumber() +
            ", secContactEmail='" + getSecContactEmail() + "'" +
            ", secContactPreContactTime='" + getSecContactPreContactTime() + "'" +
            ", fax=" + getFax() +
            ", address='" + getAddress() + "'" +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", dot='" + getDot() + "'" +
            ", mc=" + getMc() +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", customerSince='" + getCustomerSince() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", contract='" + getContract() + "'" +
            ", status='" + getStatus() + "'" +
            ", preffredCurrency='" + getPreffredCurrency() + "'" +
            ", payterms='" + getPayterms() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            "}";
    }
}
