package ir.parto.crm.modules.admin.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_admin")
public class Admin implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @Column(name = "username", columnDefinition = "nvarchar2(100)", unique = true)
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar2(200)")
    private String password;

    @Column(name = "first_name", columnDefinition = "nvarchar2(100)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "nvarchar2(100)")
    private String lastName;

    @Column(name = "identify_code", columnDefinition = "nvarchar2(32)")
    private String identifyCode;

    @Column(name = "phone_number", columnDefinition = "nvarchar2(16)")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "admin_role_fk"))
    private AdminRole adminRole;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_by", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;
}
