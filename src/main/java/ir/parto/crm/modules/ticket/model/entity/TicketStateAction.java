package ir.parto.crm.modules.ticket.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticket_state_action")
public class TicketStateAction implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_action_seq", sequenceName = "ticket_action_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_action_seq")
    private Long ticketStateActionId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "ticket_state_action_type_id", foreignKey = @ForeignKey(name = "thicket_state_action_ticket_state_action_type_fk"))
    private TicketStateActionType ticketStateActionType;

    public TicketStateAction() {
    }

    public TicketStateAction(String title, TicketStateActionType ticketStateActionType) {
        this.title = title;
        this.ticketStateActionType = ticketStateActionType;
    }

    public Long getTicketStateActionId() {
        return ticketStateActionId;
    }

    public void setTicketStateActionId(Long ticketStateActionId) {
        this.ticketStateActionId = ticketStateActionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TicketStateActionType getTicketStateActionType() {
        return ticketStateActionType;
    }

    public void setTicketStateActionType(TicketStateActionType ticketStateActionType) {
        this.ticketStateActionType = ticketStateActionType;
    }
}
