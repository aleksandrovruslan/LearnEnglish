package ru.aleksandrov.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
            min = 1,
            max = 255,
            message = "Incorrect size name user. Min 1, max 255."
    )
    @Column(nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Size(
            min = 4,
            max = 20,
            message = "Incorrect size login. Min 4, max 20."
    )
    @Column(nullable = false)
    private String login;

    @NotNull
    @Size(
            min = 6,
            max = 20,
            message = "Incorrect size password. Min 6, max 20."
    )
    private String password;

    @ManyToOne(optional = false
            , cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    @JoinTable(name = "collection_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private Set<Collection> collections = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String login, String password, Role role, Set<Collection> collections) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.collections = collections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", collections=" + collections +
                '}';
    }
}
