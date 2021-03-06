package ir.parto.crm.modules.admin.controller.transientObject.admin;

import ir.parto.crm.modules.admin.controller.transientObject.adminRole.AdminRoleDTO;
import ir.parto.crm.modules.admin.model.entity.Admin;

public class AdminRelationalDTO {
    private Long adminId;
    private String username;
    private String firstName;
    private String lastName;
    private String identifyCode;
    private String phoneNumber;
    private AdminRoleDTO adminRole;

    public AdminRelationalDTO() {
    }

    public AdminRelationalDTO(String username, String firstName, String lastName, String identifyCode, String phoneNumber) {
        this.username = username;
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public AdminRoleDTO getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRoleDTO adminRole) {
        this.adminRole = adminRole;
    }

    public Admin convert2Object(){
        Admin admin = new Admin();
        if(this.username != null) admin.setUsername(this.username);
        if(this.firstName != null) admin.setFirstName(this.firstName);
        if(this.lastName != null) admin.setLastName(this.lastName);
        if(this.identifyCode != null) admin.setIdentifyCode(this.identifyCode);
        if(this.phoneNumber != null) admin.setPhoneNumber(this.phoneNumber);
        if(this.adminRole != null) admin.setAdminRole(this.adminRole.convert2Object());

        return admin;
    }
}
