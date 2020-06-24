package ir.parto.crm.modules.admin.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_admin_log")
public class AdminLog implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq")
    private Long adminLogId;

    @Column(name = "log", columnDefinition = "nvarchar2(1000)")
    private String log;

    @Column(name = "admin_username", columnDefinition = "nvarchar2(1000)")
    private String username;

    @Column(name = "ip_address", columnDefinition = "nvarchar2(1000)")
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public AdminLog() {
    }

    public AdminLog(String log, String username, String ipAddress, Admin admin, LocalDateTime createdDate) {
        this.log = log;
        this.username = username;
        this.ipAddress = ipAddress;
        this.admin = admin;
        this.createdDate = createdDate;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}