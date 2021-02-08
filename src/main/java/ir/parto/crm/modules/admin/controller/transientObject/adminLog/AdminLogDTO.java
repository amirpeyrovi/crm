package ir.parto.crm.modules.admin.controller.transientObject.adminLog;

import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminRelationalDTO;
import ir.parto.crm.modules.admin.model.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdminLogDTO implements Serializable {
    private Long adminLogId;
    private String log;
    private String username;
    private String ipAddress;
    private AdminRelationalDTO admin;

    public AdminLogDTO() {
    }

    public AdminLogDTO(Long adminLogId, String log, String username, String ipAddress, AdminRelationalDTO admin) {
        this.adminLogId = adminLogId;
        this.log = log;
        this.username = username;
        this.ipAddress = ipAddress;
        this.admin = admin;
    }

    public Long getAdminLogId() {
        return adminLogId;
    }

    public void setAdminLogId(Long adminLogId) {
        this.adminLogId = adminLogId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public AdminRelationalDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminRelationalDTO admin) {
        this.admin = admin;
    }
}