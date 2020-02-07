package com.jio.tms.v1.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.StatusEnum;
import com.jio.tms.v1.domain.enumeration.HAZMAT;
import com.jio.tms.v1.domain.enumeration.COVEREDBY;
import com.jio.tms.v1.domain.enumeration.LoadType;
import com.jio.tms.v1.domain.enumeration.SizeEnum;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Trip} entity.
 */
public class TripDTO implements Serializable {

    private Long id;

    private String tripNumber;

    private String description;

    private String shipmentNumber;

    private String bol;

    private LocalDate pickup;

    private LocalDate drop;

    private String currentLocation;

    private StatusEnum status;

    private Long detention;

    private Instant chasisInTime;

    @Lob
    private byte[] pod;

    private String podContentType;
    private HAZMAT hazmat;

    private String recievedBy;

    private COVEREDBY coveredBy;

    private LoadType loadType;

    private SizeEnum containerSize;

    private Integer numbersOfContainer;

    private String comments;


    private Long pickupLocationId;

    private String pickupLocationAddress;

    private Long dropLocationId;

    private String dropLocationAddress;

    private Long customerId;

    private String customerEmail;

    private Long driverId;

    private Long equipmentId;

    private Long ownerOperatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getBol() {
        return bol;
    }

    public void setBol(String bol) {
        this.bol = bol;
    }

    public LocalDate getPickup() {
        return pickup;
    }

    public void setPickup(LocalDate pickup) {
        this.pickup = pickup;
    }

    public LocalDate getDrop() {
        return drop;
    }

    public void setDrop(LocalDate drop) {
        this.drop = drop;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getDetention() {
        return detention;
    }

    public void setDetention(Long detention) {
        this.detention = detention;
    }

    public Instant getChasisInTime() {
        return chasisInTime;
    }

    public void setChasisInTime(Instant chasisInTime) {
        this.chasisInTime = chasisInTime;
    }

    public byte[] getPod() {
        return pod;
    }

    public void setPod(byte[] pod) {
        this.pod = pod;
    }

    public String getPodContentType() {
        return podContentType;
    }

    public void setPodContentType(String podContentType) {
        this.podContentType = podContentType;
    }

    public HAZMAT getHazmat() {
        return hazmat;
    }

    public void setHazmat(HAZMAT hazmat) {
        this.hazmat = hazmat;
    }

    public String getRecievedBy() {
        return recievedBy;
    }

    public void setRecievedBy(String recievedBy) {
        this.recievedBy = recievedBy;
    }

    public COVEREDBY getCoveredBy() {
        return coveredBy;
    }

    public void setCoveredBy(COVEREDBY coveredBy) {
        this.coveredBy = coveredBy;
    }

    public LoadType getLoadType() {
        return loadType;
    }

    public void setLoadType(LoadType loadType) {
        this.loadType = loadType;
    }

    public SizeEnum getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(SizeEnum containerSize) {
        this.containerSize = containerSize;
    }

    public Integer getNumbersOfContainer() {
        return numbersOfContainer;
    }

    public void setNumbersOfContainer(Integer numbersOfContainer) {
        this.numbersOfContainer = numbersOfContainer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(Long locationId) {
        this.pickupLocationId = locationId;
    }

    public String getPickupLocationAddress() {
        return pickupLocationAddress;
    }

    public void setPickupLocationAddress(String locationAddress) {
        this.pickupLocationAddress = locationAddress;
    }

    public Long getDropLocationId() {
        return dropLocationId;
    }

    public void setDropLocationId(Long locationId) {
        this.dropLocationId = locationId;
    }

    public String getDropLocationAddress() {
        return dropLocationAddress;
    }

    public void setDropLocationAddress(String locationAddress) {
        this.dropLocationAddress = locationAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getOwnerOperatorId() {
        return ownerOperatorId;
    }

    public void setOwnerOperatorId(Long ownerOperatorId) {
        this.ownerOperatorId = ownerOperatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TripDTO tripDTO = (TripDTO) o;
        if (tripDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tripDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TripDTO{" +
            "id=" + getId() +
            ", tripNumber='" + getTripNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", shipmentNumber='" + getShipmentNumber() + "'" +
            ", bol='" + getBol() + "'" +
            ", pickup='" + getPickup() + "'" +
            ", drop='" + getDrop() + "'" +
            ", currentLocation='" + getCurrentLocation() + "'" +
            ", status='" + getStatus() + "'" +
            ", detention=" + getDetention() +
            ", chasisInTime='" + getChasisInTime() + "'" +
            ", pod='" + getPod() + "'" +
            ", hazmat='" + getHazmat() + "'" +
            ", recievedBy='" + getRecievedBy() + "'" +
            ", coveredBy='" + getCoveredBy() + "'" +
            ", loadType='" + getLoadType() + "'" +
            ", containerSize='" + getContainerSize() + "'" +
            ", numbersOfContainer=" + getNumbersOfContainer() +
            ", comments='" + getComments() + "'" +
            ", pickupLocationId=" + getPickupLocationId() +
            ", pickupLocationAddress='" + getPickupLocationAddress() + "'" +
            ", dropLocationId=" + getDropLocationId() +
            ", dropLocationAddress='" + getDropLocationAddress() + "'" +
            ", customerId=" + getCustomerId() +
            ", customerEmail='" + getCustomerEmail() + "'" +
            ", driverId=" + getDriverId() +
            ", equipmentId=" + getEquipmentId() +
            ", ownerOperatorId=" + getOwnerOperatorId() +
            "}";
    }
}
