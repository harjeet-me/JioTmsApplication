package com.jio.tms.v1.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Insurance} entity.
 */
public class InsuranceDTO implements Serializable {

    private Long id;

    private String providerName;

    private LocalDate issueDate;

    private LocalDate expiryDate;

    @Lob
    private byte[] policyDocument;

    private String policyDocumentContentType;
    private String coverageStatement;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public byte[] getPolicyDocument() {
        return policyDocument;
    }

    public void setPolicyDocument(byte[] policyDocument) {
        this.policyDocument = policyDocument;
    }

    public String getPolicyDocumentContentType() {
        return policyDocumentContentType;
    }

    public void setPolicyDocumentContentType(String policyDocumentContentType) {
        this.policyDocumentContentType = policyDocumentContentType;
    }

    public String getCoverageStatement() {
        return coverageStatement;
    }

    public void setCoverageStatement(String coverageStatement) {
        this.coverageStatement = coverageStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceDTO insuranceDTO = (InsuranceDTO) o;
        if (insuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceDTO{" +
            "id=" + getId() +
            ", providerName='" + getProviderName() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", policyDocument='" + getPolicyDocument() + "'" +
            ", coverageStatement='" + getCoverageStatement() + "'" +
            "}";
    }
}
