package com.jio.tms.v1.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.InvoiceRef} entity.
 */
public class InvoiceRefDTO implements Serializable {

    private Long id;

    private String refName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceRefDTO invoiceRefDTO = (InvoiceRefDTO) o;
        if (invoiceRefDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceRefDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceRefDTO{" +
            "id=" + getId() +
            ", refName='" + getRefName() + "'" +
            "}";
    }
}
