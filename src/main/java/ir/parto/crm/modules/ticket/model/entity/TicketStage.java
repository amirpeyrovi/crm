package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_stage")
public class TicketStage implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketStageId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(1000)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "thicket_admin_fk"))
    private Admin admin;


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

    public TicketStage() {
    }

    public TicketStage(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
    }

    public TicketStage(String title, String description, Admin admin, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.title = title;
        this.description = description;
        this.admin = admin;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public void setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public TicketStageRelationalDTO convert2RelationalObject() {
        TicketStageRelationalDTO dto = new TicketStageRelationalDTO();
        if(this.ticketStageId != null) dto.setTicketStageId(this.ticketStageId);
        if(this.title != null) dto.setTitle(this.title);
        return dto;
    }
}
