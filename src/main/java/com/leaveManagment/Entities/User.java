package com.leaveManagment.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String matricule;
    private String firstName;
    private String lastName;
    private String address;
    private String position;
    private Date birthDate;
    private Date startDate;
    private Float leaveBalance;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    private String phoneSecondary;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Leave> leaves;
    @ManyToMany(mappedBy = "userList")
    @JsonIgnore
    private List<Team> teamList;
    @OneToMany(mappedBy = "userClaim")
    @JsonIgnore
    private List<Claim> claim;
}
