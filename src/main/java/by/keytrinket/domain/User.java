package by.keytrinket.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ntishkevich
 */
@Entity
@Table(name = "kt_user")
public class User extends KeyTrinketEntity implements Serializable {

    @Id
    @SequenceGenerator(name="kt_user_seq_id", sequenceName="kt_user_seq_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="kt_user_seq_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;

    @Column(name = "username", length = 64, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getLastEventDate(), user.getLastEventDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getUsername(), getPassword(), getLastEventDate());
    }
}
