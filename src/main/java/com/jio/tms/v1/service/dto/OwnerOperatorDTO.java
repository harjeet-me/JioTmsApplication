package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.Designation;
import com.jio.tms.v1.domain.enumeration.CountryEnum;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;
import com.jio.tms.v1.domain.enumeration.CURRENCY;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.OwnerOperator} entity.
 */
public class OwnerOperatorDTO implements Serializable {

    private Long id;

    private String company;

    private String firstName;

    private String lastName;

    private Designation contactDesignation;

    private String email;

    private Long phoneNumber;

    private String address;

    private String streetAddress;

    private String city;

    private String stateProvince;

    private CountryEnum country;

    private String postalCode;

    private String dot;

    private Long mc;

    private ToggleStatus profileStatus;

    private CURRENCY preffredCurrency;

    @Lob
    private byte[] contractDoc;

    private String contractDocContentType;

    private Long operInsuranceId;

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

    public ToggleStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(ToggleStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public CURRENCY getPreffredCurrency() {
        return preffredCurrency;
    }

    public void setPreffredCurrency(CURRENCY preffredCurrency) {
        this.preffredCurrency = preffredCurrency;
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

    public Long getOperInsuranceId() {
        return operInsuranceId;
    }

    public void setOperInsuranceId(Long insuranceId) {
        this.operInsuranceId = insuranceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OwnerOperatorDTO ownerOperatorDTO = (OwnerOperatorDTO) o;
        if (ownerOperatorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ownerOperatorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OwnerOperatorDTO{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactDesignation='" + getContactDesignation() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", address='" + getAddress() + "'" +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", dot='" + getDot() + "'" +
            ", mc=" + getMc() +
            ", profileStatus='" + getProfileStatus() + "'" +
            ", preffredCurrency='" + getPreffredCurrency() + "'" +
            ", contractDoc='" + getContractDoc() + "'" +
            ", operInsuranceId=" + getOperInsuranceId() +
            "}";
    }
}
