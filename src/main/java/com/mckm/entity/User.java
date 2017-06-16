package com.mckm.entity;

import javax.persistence.*;

/**
 * Created by @author km2
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
}
