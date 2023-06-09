package com.leaveManagment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
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
    private Boolean isArchive = false ;
    private LocalDate archiveDate ;
    private int children;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Leave> leaves;
    @ManyToMany(mappedBy = "userList")
    @JsonIgnore
    private List<Team> teamList;
    @OneToMany(mappedBy = "userClaim")
    @JsonIgnore
    private List<Claim> claim;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Team team;
}
