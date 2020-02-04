package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Reference} entity.
 */
public class ReferenceDTO implements Serializable {

    private Long id;

    private String reference;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferenceDTO referenceDTO = (ReferenceDTO) o;
        if (referenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenceDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            "}";
    }
}
