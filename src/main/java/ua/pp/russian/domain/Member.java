package ua.pp.russian.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The Member entity.
 */
@Entity
@Table(name = "member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "picture", length = 128, nullable = false)
    private String picture;

    @NotNull
    @Size(max = 32)
    @Column(name = "realname", length = 32, nullable = false)
    private String realname;

    @NotNull
    @Size(max = 32)
    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @NotNull
    @Size(max = 32)
    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @NotNull
    @Size(max = 128)
    @Column(name = "address", length = 128, nullable = false)
    private String address;

    @NotNull
    @Size(max = 128)
    @Column(name = "matrix", length = 128, nullable = false)
    private String matrix;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Member id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return this.picture;
    }

    public Member picture(String picture) {
        this.setPicture(picture);
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRealname() {
        return this.realname;
    }

    public Member realname(String realname) {
        this.setRealname(realname);
        return this;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return this.username;
    }

    public Member username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public Member password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public Member address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMatrix() {
        return this.matrix;
    }

    public Member matrix(String matrix) {
        this.setMatrix(matrix);
        return this;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        return id != null && id.equals(((Member) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Member{" +
            "id=" + getId() +
            ", picture='" + getPicture() + "'" +
            ", realname='" + getRealname() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", address='" + getAddress() + "'" +
            ", matrix='" + getMatrix() + "'" +
            "}";
    }
}
