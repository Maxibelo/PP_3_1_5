package ru.kata.spring.boot_security.PP_3_1_3.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public Role() {}
    public Role(String name) {
        this.name = name;
    }
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //  ", users=" + users +
                '}';
    }

    /**
     * @return
     */
    @Override
    public String getAuthority() {
        return name;
    }
}
