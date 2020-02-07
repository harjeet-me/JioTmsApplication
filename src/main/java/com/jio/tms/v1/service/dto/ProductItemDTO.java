package com.jio.tms.v1.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.ProductItem} entity.
 */
public class ProductItemDTO implements Serializable {

    private Long id;

    private String itemName;

    private String description;

    private Integer qty;

    private Double price;

    private Double discount;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductItemDTO productItemDTO = (ProductItemDTO) o;
        if (productItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductItemDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", description='" + getDescription() + "'" +
            ", qty=" + getQty() +
            ", price=" + getPrice() +
            ", discount=" + getDiscount() +
            "}";
    }
}
