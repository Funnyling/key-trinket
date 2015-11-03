package by.keytrinket.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: ntishkevich
 */
@Entity
@Table(name = "kt_user", schema = "keyTrinket")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable, KeyTrinketEntity {

    @Id
    @SequenceGenerator(name="KT_USER_SEQ_ID", sequenceName="KT_USER_SEQ_ID", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KT_USER_SEQ_ID")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false, length = 254)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login_date", nullable = false)
    private Date lastLoginDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getLastLoginDate(), user.getLastLoginDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getUsername(), getPassword(), getLastLoginDate());
    }
}
