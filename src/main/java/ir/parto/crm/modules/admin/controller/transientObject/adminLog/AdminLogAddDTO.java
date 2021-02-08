package ir.parto.crm.modules.admin.controller.transientObject.adminLog;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminLog;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdminLogAddDTO implements Serializable {
    private String log;
    private String username;
    private String ipAddress;
    private Long adminId;

    public AdminLogAddDTO() {
    }

    public AdminLogAddDTO(String log, String username, String ipAddress, Long adminId) {
        this.log = log;
        this.username = username;
        this.ipAddress = ipAddress;
        this.adminId = adminId;
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public AdminLog convert2Object(){
        AdminLog adminLog = new AdminLog();
        if(this.adminId != null) adminLog.setAdmin(new Admin(this.adminId));
        if(this.log != null) adminLog.setLog(this.log);
        if(this.username != null) adminLog.setUsername(this.username);
        if(this.ipAddress != null) adminLog.setIpAddress(this.ipAddress);
        adminLog.setIsDeleted(0);
        return adminLog;
    }
}
