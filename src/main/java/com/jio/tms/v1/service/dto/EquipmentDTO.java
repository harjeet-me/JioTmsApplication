package com.jio.tms.v1.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.jio.tms.v1.domain.enumeration.EquipmentEnum;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Equipment} entity.
 */
public class EquipmentDTO implements Serializable {

    private Long id;

    private String enumber;

    private EquipmentEnum type;

    private String ownershiptype;

    private ToggleStatus status;

    private String vin;

    private String make;

    private String model;

    private String description;

    private String year;

    private String yearPurchased;

    private String licensePlateNumber;

    private LocalDate licensePlateExpiration;

    private LocalDate inspectionStickerExpiration;


    private Long insuranceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumber() {
        return enumber;
    }

    public void setEnumber(String enumber) {
        this.enumber = enumber;
    }

    public EquipmentEnum getType() {
        return type;
    }

    public void setType(EquipmentEnum type) {
        this.type = type;
    }

    public String getOwnershiptype() {
        return ownershiptype;
    }

    public void setOwnershiptype(String ownershiptype) {
        this.ownershiptype = ownershiptype;
    }

    public ToggleStatus getStatus() {
        return status;
    }

    public void setStatus(ToggleStatus status) {
        this.status = status;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYearPurchased() {
        return yearPurchased;
    }

    public void setYearPurchased(String yearPurchased) {
        this.yearPurchased = yearPurchased;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public LocalDate getLicensePlateExpiration() {
        return licensePlateExpiration;
    }

    public void setLicensePlateExpiration(LocalDate licensePlateExpiration) {
        this.licensePlateExpiration = licensePlateExpiration;
    }

    public LocalDate getInspectionStickerExpiration() {
        return inspectionStickerExpiration;
    }

    public void setInspectionStickerExpiration(LocalDate inspectionStickerExpiration) {
        this.inspectionStickerExpiration = inspectionStickerExpiration;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipmentDTO equipmentDTO = (EquipmentDTO) o;
        if (equipmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipmentDTO{" +
            "id=" + getId() +
            ", enumber='" + getEnumber() + "'" +
            ", type='" + getType() + "'" +
            ", ownershiptype='" + getOwnershiptype() + "'" +
            ", status='" + getStatus() + "'" +
            ", vin='" + getVin() + "'" +
            ", make='" + getMake() + "'" +
            ", model='" + getModel() + "'" +
            ", description='" + getDescription() + "'" +
            ", year='" + getYear() + "'" +
            ", yearPurchased='" + getYearPurchased() + "'" +
            ", licensePlateNumber='" + getLicensePlateNumber() + "'" +
            ", licensePlateExpiration='" + getLicensePlateExpiration() + "'" +
            ", inspectionStickerExpiration='" + getInspectionStickerExpiration() + "'" +
            ", insuranceId=" + getInsuranceId() +
            "}";
    }
}
