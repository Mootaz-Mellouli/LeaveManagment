package com.leaveManagment.entities;

import java.util.Date;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    private String comment;
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
    private float nbr_days;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    @ManyToOne
    private User user;
}
