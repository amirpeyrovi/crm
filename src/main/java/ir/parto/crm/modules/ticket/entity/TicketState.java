package ir.parto.crm.modules.ticket.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_state")
public class TicketState implements Serializable {
    @Id
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(500)")
    private String description;

    @Column(name = "color", columnDefinition = "nvarchar2(12)")
    private String color;

    @ManyToOne
    @JoinColumn(name = "ticket_state_action_id", foreignKey = @ForeignKey(name = "thicket_state_ticket_state_action_fk"))
    private TicketStateAction ticketStateAction;

    @Column(name = "is_active", columnDefinition = "number(1)")
    private Integer isActive;

    @Column(name = "is_pending", columnDefinition = "number(1)")
    private Integer isPending;

    @Column(name = "is_close", columnDefinition = "number(1)")
    private Integer isClose;


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
