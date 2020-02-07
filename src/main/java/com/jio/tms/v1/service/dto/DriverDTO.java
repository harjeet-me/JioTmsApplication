package com.jio.tms.v1.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Driver} entity.
 */
public class DriverDTO implements Serializable {

    private Long id;

    private String company;

    private String firstName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    private Long licenceNumber;

    private LocalDate dob;

    private LocalDate companyJoinedOn;

    private LocalDate companyLeftOn;

    @Lob
    private byte[] image;

    private String imageContentType;
    @Lob
    private byte[] licenceImage;

    private String licenceImageContentType;
    private String remarks;

    @Lob
    private byte[] contractDoc;

    private String contractDocContentType;
    private ToggleStatus status;


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

    public Long getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(Long licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getCompanyJoinedOn() {
        return companyJoinedOn;
    }

    public void setCompanyJoinedOn(LocalDate companyJoinedOn) {
        this.companyJoinedOn = companyJoinedOn;
    }

    public LocalDate getCompanyLeftOn() {
        return companyLeftOn;
    }

    public void setCompanyLeftOn(LocalDate companyLeftOn) {
        this.companyLeftOn = companyLeftOn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getLicenceImage() {
        return licenceImage;
    }

    public void setLicenceImage(byte[] licenceImage) {
        this.licenceImage = licenceImage;
    }

    public String getLicenceImageContentType() {
        return licenceImageContentType;
    }

    public void setLicenceImageContentType(String licenceImageContentType) {
        this.licenceImageContentType = licenceImageContentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public byte[] getContractDoc() {
        return contractDoc;
    }

    public void setContractDoc(byte[] contractDoc) {
        this.contractDoc = contractDoc;
    }

    public String getContractDocContentType() {
        return contractDocContentType;
    }

    public void setContractDocContentType(String contractDocContentType) {
        this.contractDocContentType = contractDocContentType;
    }

    public ToggleStatus getStatus() {
        return status;
    }

    public void setStatus(ToggleStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DriverDTO driverDTO = (DriverDTO) o;
        if (driverDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), driverDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", licenceNumber=" + getLicenceNumber() +
            ", dob='" + getDob() + "'" +
            ", companyJoinedOn='" + getCompanyJoinedOn() + "'" +
            ", companyLeftOn='" + getCompanyLeftOn() + "'" +
            ", image='" + getImage() + "'" +
            ", licenceImage='" + getLicenceImage() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", contractDoc='" + getContractDoc() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
