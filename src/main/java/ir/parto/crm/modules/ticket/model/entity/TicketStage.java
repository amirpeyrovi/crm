package ir.parto.crm.modules.ticket.model.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(1000)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_ticket_stage_fk"))
    private TicketStage ticketStage;

    @ManyToOne
    @JoinColumn(name = "ticket_state_id", foreignKey = @ForeignKey(name = "ticket_ticket_state_fk"))
    private TicketStage ticketState;


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
