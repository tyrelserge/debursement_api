package com.lin_q.debursement_api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String lastname;
    private String firstname;
    private String gender;
    private String email;
    private String mobile;
    private String password;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "updated_on")
    private Date updatedOn;
    private String status;

    @OneToMany(targetEntity = Office.class)
    @JoinTable(name = "user_office",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "office_id"))
    private List<Office> offices;
}
