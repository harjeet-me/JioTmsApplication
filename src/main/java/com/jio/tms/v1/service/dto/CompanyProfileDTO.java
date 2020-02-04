package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.CountryEnum;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;
import com.jio.tms.v1.domain.enumeration.CURRENCY;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.CompanyProfile} entity.
 */
public class CompanyProfileDTO implements Serializable {

    private Long id;

    private Boolean active;

    private String company;

    private String address;

    private String streetAddress;

    private String city;

    private String stateProvince;

    private CountryEnum country;

    private String postalCode;

    private String email;

    private String website;

    private Long phoneNumber;

    private String dot;

    private Long mc;

    @Lob
    private byte[] companyLogo;

    private String companyLogoContentType;
    private ToggleStatus profileStatus;

    private CURRENCY preffredCurrency;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyProfileDTO companyProfileDTO = (CompanyProfileDTO) o;
        if (companyProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyProfileDTO{" +
            "id=" + getId() +
            ", active='" + isActive() + "'" +
            ", company='" + getCompany() + "'" +
            ", address='" + getAddress() + "'" +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", email='" + getEmail() + "'" +
            ", website='" + getWebsite() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", dot='" + getDot() + "'" +
            ", mc=" + getMc() +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", profileStatus='" + getProfileStatus() + "'" +
            ", preffredCurrency='" + getPreffredCurrency() + "'" +
            "}";
    }
}
