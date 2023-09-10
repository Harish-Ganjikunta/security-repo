package com.login.register.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(columnNames = {"userName"}),
        @UniqueConstraint(columnNames = {"email"})})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn (name = "user_Id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "role_Id",referencedColumnName = "id") )
    private Set<Roles> roles;


}
