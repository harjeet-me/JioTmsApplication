package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.InvoiceItem} entity.
 */
public class InvoiceItemDTO implements Serializable {

    private Long id;

    private String itemName;

    private String description;

    private Integer qty;

    private Double price;

    private Double discount;

    private Double total;


    private Long invoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceItemDTO invoiceItemDTO = (InvoiceItemDTO) o;
        if (invoiceItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceItemDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", description='" + getDescription() + "'" +
            ", qty=" + getQty() +
            ", price=" + getPrice() +
            ", discount=" + getDiscount() +
            ", total=" + getTotal() +
            ", invoiceId=" + getInvoiceId() +
            "}";
    }
}
