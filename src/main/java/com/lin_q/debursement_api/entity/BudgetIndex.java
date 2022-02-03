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
@Table(name = "budget_index")
public class BudgetIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgindex_id")
    private Integer budgindexId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "budgsector_id")
    private Integer budgsectorId;
    @Column(name = "groupedbudget_id")
    private Integer groupedbudgetId;
    @Column(name = "budgindex_name")
    private String budgindexName;
    @Column(name = "budgindex_description")
    private String budgindexDescription;
    @Column(name = "budgindex_periodic")
    private Integer budgindexPeriodic;
    @Column(name = "budgindex_value")
    private Integer budgindexValue;
    @Column(name = "created_on")
    private Date createdOn; 
    @Column(name = "updated_on")
    private Date updatedOn; 
    private String status;

    @OneToMany(targetEntity = Renewal.class)
    @JoinTable(name = "group_renewal",
             joinColumns = @JoinColumn(name = "groupedbudget_id"),
             inverseJoinColumns = @JoinColumn(name = "renewal_id"))
    private List<Renewal> renewal;
}
