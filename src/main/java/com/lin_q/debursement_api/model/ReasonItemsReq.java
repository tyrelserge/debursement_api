package com.lin_q.debursement_api.model;


public class ReasonItemsReq {
    
    private String designation;
    
    public void setDesignation(String designation) { this.designation = designation; } 
    private Integer unitprice; 
    private Integer quatity; 
    public void setUnitprice(Integer unitprice) { this.unitprice = unitprice; } 
    public void setQuatity(Integer quatity) { this.quatity = quatity; } 
    public void setStatus(String status) { this.status = status; }     
    public String getDesignation() { return this.designation; }    
    public Integer getUnitprice() { return this.unitprice; } 
    public Integer getQuatity() {
        return this.quatity;
    } 
    private String status = "active"; 
    public String getStatus() { return this.status; }

}
