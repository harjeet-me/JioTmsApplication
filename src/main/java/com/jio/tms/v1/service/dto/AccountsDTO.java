package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Accounts} entity.
 */
public class AccountsDTO implements Serializable {

    private Long id;

    private Double balance;

    private Double over30;

    private Double over60;

    private Double over90;


    private Long customerId;

    private String customerCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getOver30() {
        return over30;
    }

    public void setOver30(Double over30) {
        this.over30 = over30;
    }

    public Double getOver60() {
        return over60;
    }

    public void setOver60(Double over60) {
        this.over60 = over60;
    }

    public Double getOver90() {
        return over90;
    }

    public void setOver90(Double over90) {
        this.over90 = over90;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountsDTO accountsDTO = (AccountsDTO) o;
        if (accountsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accountsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccountsDTO{" +
            "id=" + getId() +
            ", balance=" + getBalance() +
            ", over30=" + getOver30() +
            ", over60=" + getOver60() +
            ", over90=" + getOver90() +
            ", customerId=" + getCustomerId() +
            ", customerCompany='" + getCustomerCompany() + "'" +
            "}";
    }
}
