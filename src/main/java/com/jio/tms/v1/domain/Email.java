package com.jio.tms.v1.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Email.
 */
@Entity
@Table(name = "email")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "email")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "userto")
    private String userto;

    @Column(name = "usercc")
    private String usercc;

    @Column(name = "userbcc")
    private String userbcc;

    @Column(name = "message")
    private String message;

    @OneToMany(mappedBy = "email")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Files> files = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserto() {
        return userto;
    }

    public Email userto(String userto) {
        this.userto = userto;
        return this;
    }

    public void setUserto(String userto) {
        this.userto = userto;
    }

    public String getUsercc() {
        return usercc;
    }

    public Email usercc(String usercc) {
        this.usercc = usercc;
        return this;
    }

    public void setUsercc(String usercc) {
        this.usercc = usercc;
    }

    public String getUserbcc() {
        return userbcc;
    }

    public Email userbcc(String userbcc) {
        this.userbcc = userbcc;
        return this;
    }

    public void setUserbcc(String userbcc) {
        this.userbcc = userbcc;
    }

    public String getMessage() {
        return message;
    }

    public Email message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Files> getFiles() {
        return files;
    }

    public Email files(Set<Files> files) {
        this.files = files;
        return this;
    }

    public Email addFiles(Files files) {
        this.files.add(files);
        files.setEmail(this);
        return this;
    }

    public Email removeFiles(Files files) {
        this.files.remove(files);
        files.setEmail(null);
        return this;
    }

    public void setFiles(Set<Files> files) {
        this.files = files;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }
        return id != null && id.equals(((Email) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Email{" +
            "id=" + getId() +
            ", userto='" + getUserto() + "'" +
            ", usercc='" + getUsercc() + "'" +
            ", userbcc='" + getUserbcc() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
