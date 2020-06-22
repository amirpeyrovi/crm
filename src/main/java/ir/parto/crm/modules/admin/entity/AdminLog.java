package ir.parto.crm.modules.admin.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_admin_log")
public class AdminLog implements Serializable {
    @Id
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
}
