package com.lin_q.debursement_api.model;

public class GSettingsReq {
  
    private Integer userId;
  
  
    public void setUserId(Integer userId) { this.userId = userId; } 
    private String currency; 
    private Integer validationStep; 
    public void setCurrency(String currency) { this.currency = currency; } 
    public void setValidationStep(Integer validationStep) { this.validationStep = validationStep; } 
      
    public Integer getUserId() { return this.userId; }
  
    public String getCurrency() { return this.currency; } 
        public Integer getValidationStep() {
        return this.validationStep;
    }
}
