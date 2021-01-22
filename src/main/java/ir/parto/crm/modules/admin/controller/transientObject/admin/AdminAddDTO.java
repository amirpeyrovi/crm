package ir.parto.crm.modules.admin.controller.transientObject.admin;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;

public class AdminAddDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String identifyCode;
    private String phoneNumber;
    private Long adminRoleId;

    public AdminAddDTO() {
    }

    public AdminAddDTO(String username, String password, String firstName, String lastName, String identifyCode, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifyCode = identifyCode;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public Admin convert2Object(){
        Admin admin = new Admin();
        if(this.username != null) admin.setUsername(this.username);
        if(this.password != null) admin.setPassword(this.password);
        if(this.firstName != null) admin.setFirstName(this.firstName);
        if(this.lastName != null) admin.setLastName(this.lastName);
        if(this.identifyCode != null) admin.setIdentifyCode(this.identifyCode);
        if(this.phoneNumber != null) admin.setPhoneNumber(this.phoneNumber);
        if(this.adminRoleId != null) admin.setAdminRole(new AdminRole(this.adminRoleId));

        return admin;
    }
}