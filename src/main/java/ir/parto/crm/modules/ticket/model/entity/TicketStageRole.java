package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public TicketStageRole() {
    }

    public TicketStageRole(TicketStage ticketStage, AdminRole adminRole, String createdBy, LocalDateTime createdDate) {
        this.ticketStage = ticketStage;
        this.adminRole = adminRole;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
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
}
