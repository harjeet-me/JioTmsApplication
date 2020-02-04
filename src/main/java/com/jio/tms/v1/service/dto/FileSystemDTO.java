package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.FileSystem} entity.
 */
public class FileSystemDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] data;

    private String dataContentType;

    private Long emailId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileSystemDTO fileSystemDTO = (FileSystemDTO) o;
        if (fileSystemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileSystemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileSystemDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", emailId=" + getEmailId() +
            "}";
    }
}
