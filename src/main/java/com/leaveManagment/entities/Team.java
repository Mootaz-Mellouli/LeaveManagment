package com.leaveManagment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameTeam;
    private String description;
    private boolean archive;
    private Date createdOn;
    @ManyToMany
    private List<User> userList;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Event> eventList;
}
