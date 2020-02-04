package com.jio.tms.v1.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.jio.tms.v1.domain.Email} entity.
 */
public class EmailDTO implements Serializable {

    private Long id;

    private String userto;

    private String usercc;

    private String userbcc;

    private String subject;

    private String message;

    private Boolean multipart;

    private Boolean htmlBody;

    @Lob
    private byte[] attachment;

    private String attachmentContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserto() {
        return userto;
    }

    public void setUserto(String userto) {
        this.userto = userto;
    }

    public String getUsercc() {
        return usercc;
    }

    public void setUsercc(String usercc) {
        this.usercc = usercc;
    }

    public String getUserbcc() {
        return userbcc;
    }

    public void setUserbcc(String userbcc) {
        this.userbcc = userbcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(Boolean multipart) {
        this.multipart = multipart;
    }

    public Boolean isHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(Boolean htmlBody) {
        this.htmlBody = htmlBody;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmailDTO emailDTO = (EmailDTO) o;
        if (emailDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
            "id=" + getId() +
            ", userto='" + getUserto() + "'" +
            ", usercc='" + getUsercc() + "'" +
            ", userbcc='" + getUserbcc() + "'" +
            ", subject='" + getSubject() + "'" +
            ", message='" + getMessage() + "'" +
            ", multipart='" + isMultipart() + "'" +
            ", htmlBody='" + isHtmlBody() + "'" +
            ", attachment='" + getAttachment() + "'" +
            "}";
    }
}
