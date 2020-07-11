package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_stage_role")
public class TicketStageRole implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketStageRoleId;

    @ManyToOne
    @JoinColumn(name = "ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_stage_role_ticket_stage_fk"))
    private TicketStage ticketStage;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "ticket_stage_role_admin_role_fk"))
    private AdminRole adminRole;

    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public TicketStageRole() {
    }

    public TicketStageRole(TicketStage ticketStage, AdminRole adminRole, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.ticketStage = ticketStage;
        this.adminRole = adminRole;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getTicketStageRoleId() {
        return ticketStageRoleId;
    }

    public void setTicketStageRoleId(Long ticketStageRoleId) {
        this.ticketStageRoleId = ticketStageRoleId;
    }

    public TicketStage getTicketStage() {
        return ticketStage;
    }

    public void setTicketStage(TicketStage ticketStage) {
        this.ticketStage = ticketStage;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public TicketStageRole setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public TicketStageRole setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public TicketStageRole setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public TicketStageRole setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public TicketStageRole setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
