package com.jio.tms.v1.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.jio.tms.v1.domain.enumeration.TaxType;
import com.jio.tms.v1.domain.enumeration.CURRENCY;
import com.jio.tms.v1.domain.enumeration.InvoiceStatus;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Invoice} entity.
 */
public class InvoiceDTO implements Serializable {

    private Long id;

    private String orderNo;

    private Double taxRate;

    private TaxType taxType;

    private CURRENCY currency;

    private Double invoiceTaxTotal;

    private Double invoiceSubTotal;

    private Double invoiceTotal;

    private LocalDate invoiceDate;

    private LocalDate invoicePaidDate;

    private String refValue1;

    private String refValue2;

    private String refValue3;

    private String payRefNo;

    private LocalDate invoiceDueDate;

    private InvoiceStatus status;

    @Lob
    private byte[] invoicePdf;

    private String invoicePdfContentType;
    private String remarks;


    private Long reference1Id;

    private String reference1Reference;

    private Long reference2Id;

    private String reference2Reference;

    private Long reference3Id;

    private String reference3Reference;

    private Long customerId;

    private String customerCompany;

    private Long tripId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }

    public Double getInvoiceTaxTotal() {
        return invoiceTaxTotal;
    }

    public void setInvoiceTaxTotal(Double invoiceTaxTotal) {
        this.invoiceTaxTotal = invoiceTaxTotal;
    }

    public Double getInvoiceSubTotal() {
        return invoiceSubTotal;
    }

    public void setInvoiceSubTotal(Double invoiceSubTotal) {
        this.invoiceSubTotal = invoiceSubTotal;
    }

    public Double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getInvoicePaidDate() {
        return invoicePaidDate;
    }

    public void setInvoicePaidDate(LocalDate invoicePaidDate) {
        this.invoicePaidDate = invoicePaidDate;
    }

    public String getRefValue1() {
        return refValue1;
    }

    public void setRefValue1(String refValue1) {
        this.refValue1 = refValue1;
    }

    public String getRefValue2() {
        return refValue2;
    }

    public void setRefValue2(String refValue2) {
        this.refValue2 = refValue2;
    }

    public String getRefValue3() {
        return refValue3;
    }

    public void setRefValue3(String refValue3) {
        this.refValue3 = refValue3;
    }

    public String getPayRefNo() {
        return payRefNo;
    }

    public void setPayRefNo(String payRefNo) {
        this.payRefNo = payRefNo;
    }

    public LocalDate getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(LocalDate invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public byte[] getInvoicePdf() {
        return invoicePdf;
    }

    public void setInvoicePdf(byte[] invoicePdf) {
        this.invoicePdf = invoicePdf;
    }

    public String getInvoicePdfContentType() {
        return invoicePdfContentType;
    }

    public void setInvoicePdfContentType(String invoicePdfContentType) {
        this.invoicePdfContentType = invoicePdfContentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getReference1Id() {
        return reference1Id;
    }

    public void setReference1Id(Long referenceId) {
        this.reference1Id = referenceId;
    }

    public String getReference1Reference() {
        return reference1Reference;
    }

    public void setReference1Reference(String referenceReference) {
        this.reference1Reference = referenceReference;
    }

    public Long getReference2Id() {
        return reference2Id;
    }

    public void setReference2Id(Long referenceId) {
        this.reference2Id = referenceId;
    }

    public String getReference2Reference() {
        return reference2Reference;
    }

    public void setReference2Reference(String referenceReference) {
        this.reference2Reference = referenceReference;
    }

    public Long getReference3Id() {
        return reference3Id;
    }

    public void setReference3Id(Long referenceId) {
        this.reference3Id = referenceId;
    }

    public String getReference3Reference() {
        return reference3Reference;
    }

    public void setReference3Reference(String referenceReference) {
        this.reference3Reference = referenceReference;
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

        InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        if (invoiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
            "id=" + getId() +
            ", orderNo='" + getOrderNo() + "'" +
            ", taxRate=" + getTaxRate() +
            ", taxType='" + getTaxType() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", invoiceTaxTotal=" + getInvoiceTaxTotal() +
            ", invoiceSubTotal=" + getInvoiceSubTotal() +
            ", invoiceTotal=" + getInvoiceTotal() +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoicePaidDate='" + getInvoicePaidDate() + "'" +
            ", refValue1='" + getRefValue1() + "'" +
            ", refValue2='" + getRefValue2() + "'" +
            ", refValue3='" + getRefValue3() + "'" +
            ", payRefNo='" + getPayRefNo() + "'" +
            ", invoiceDueDate='" + getInvoiceDueDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", invoicePdf='" + getInvoicePdf() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", reference1Id=" + getReference1Id() +
            ", reference1Reference='" + getReference1Reference() + "'" +
            ", reference2Id=" + getReference2Id() +
            ", reference2Reference='" + getReference2Reference() + "'" +
            ", reference3Id=" + getReference3Id() +
            ", reference3Reference='" + getReference3Reference() + "'" +
            ", customerId=" + getCustomerId() +
            ", customerCompany='" + getCustomerCompany() + "'" +
            ", tripId=" + getTripId() +
            "}";
    }
}
