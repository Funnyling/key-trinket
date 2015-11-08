package by.keytrinket.web.dto;

import by.keytrinket.domain.User;

import java.util.Objects;

/**
 * @author ntishkevich
 */
public class UserDTO {

    private String username;

    private String email;

    private String password;

    public UserDTO(User user) {
        this(user.getUsername(), user.getEmail(), null);
    }

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getUsername(), userDTO.getUsername()) &&
                Objects.equals(getEmail(), userDTO.getEmail()) &&
                Objects.equals(getPassword(), userDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail(), getPassword());
    }
}
