package com.jio.tms.v1.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Container} entity.
 */
public class ContainerDTO implements Serializable {

    private Long id;

    private String number;

    private String description;

    private Integer size;


    private Long tripId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContainerDTO containerDTO = (ContainerDTO) o;
        if (containerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), containerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContainerDTO{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", size=" + getSize() +
            ", tripId=" + getTripId() +
            "}";
    }
}
