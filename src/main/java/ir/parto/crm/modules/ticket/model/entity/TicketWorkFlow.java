package ir.parto.crm.modules.ticket.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_workflow")
public class TicketWorkFlow implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(1000)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "previous_ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_previous_ticket_stage_fk"))
    private TicketStage previousTicketStage;

    @ManyToOne
    @JoinColumn(name = "previous_ticket_state_id", foreignKey = @ForeignKey(name = "ticket_previous_ticket_state_fk"))
    private TicketStage previousTicketState;

    @ManyToOne
    @JoinColumn(name = "next_ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_next_ticket_stage_fk"))
    private TicketStage nextTicketStage;

    @ManyToOne
    @JoinColumn(name = "next_ticket_state_id", foreignKey = @ForeignKey(name = "ticket_next_ticket_state_fk"))
    private TicketStage nextTicketState;


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
