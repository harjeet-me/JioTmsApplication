package com.jio.tms.v1.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.TransactionType;
import com.jio.tms.v1.domain.enumeration.TxStatus;
import com.jio.tms.v1.domain.enumeration.CURRENCY;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.TransactionsRecord} entity.
 */
public class TransactionsRecordDTO implements Serializable {

    private Long id;

    private TransactionType txType;

    private String description;

    private Double txAmmount;

    private String txRefNo;

    private String txCreatedBy;

    private LocalDate txCreatedDate;

    private String txCompletedBy;

    private LocalDate txCompletedDate;

    private TxStatus status;

    @Lob
    private byte[] txDoc;

    private String txDocContentType;
    private CURRENCY currency;

    private String remarks;


    private Long customerId;

    private String customerCompany;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTxType() {
        return txType;
    }

    public void setTxType(TransactionType txType) {
        this.txType = txType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTxAmmount() {
        return txAmmount;
    }

    public void setTxAmmount(Double txAmmount) {
        this.txAmmount = txAmmount;
    }

    public String getTxRefNo() {
        return txRefNo;
    }

    public void setTxRefNo(String txRefNo) {
        this.txRefNo = txRefNo;
    }

    public String getTxCreatedBy() {
        return txCreatedBy;
    }

    public void setTxCreatedBy(String txCreatedBy) {
        this.txCreatedBy = txCreatedBy;
    }

    public LocalDate getTxCreatedDate() {
        return txCreatedDate;
    }

    public void setTxCreatedDate(LocalDate txCreatedDate) {
        this.txCreatedDate = txCreatedDate;
    }

    public String getTxCompletedBy() {
        return txCompletedBy;
    }

    public void setTxCompletedBy(String txCompletedBy) {
        this.txCompletedBy = txCompletedBy;
    }

    public LocalDate getTxCompletedDate() {
        return txCompletedDate;
    }

    public void setTxCompletedDate(LocalDate txCompletedDate) {
        this.txCompletedDate = txCompletedDate;
    }

    public TxStatus getStatus() {
        return status;
    }

    public void setStatus(TxStatus status) {
        this.status = status;
    }

    public byte[] getTxDoc() {
        return txDoc;
    }

    public void setTxDoc(byte[] txDoc) {
        this.txDoc = txDoc;
    }

    public String getTxDocContentType() {
        return txDocContentType;
    }

    public void setTxDocContentType(String txDocContentType) {
        this.txDocContentType = txDocContentType;
    }

    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountsId) {
        this.accountId = accountsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionsRecordDTO transactionsRecordDTO = (TransactionsRecordDTO) o;
        if (transactionsRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionsRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionsRecordDTO{" +
            "id=" + getId() +
            ", txType='" + getTxType() + "'" +
            ", description='" + getDescription() + "'" +
            ", txAmmount=" + getTxAmmount() +
            ", txRefNo='" + getTxRefNo() + "'" +
            ", txCreatedBy='" + getTxCreatedBy() + "'" +
            ", txCreatedDate='" + getTxCreatedDate() + "'" +
            ", txCompletedBy='" + getTxCompletedBy() + "'" +
            ", txCompletedDate='" + getTxCompletedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", txDoc='" + getTxDoc() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", customerId=" + getCustomerId() +
            ", customerCompany='" + getCustomerCompany() + "'" +
            ", accountId=" + getAccountId() +
            "}";
    }
}
