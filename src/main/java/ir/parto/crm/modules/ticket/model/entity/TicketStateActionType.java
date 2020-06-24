package ir.parto.crm.modules.ticket.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_ticket_state_action_type")
public class TicketStateActionType implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_action_type_seq", sequenceName = "ticket_action_type_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_action_type_seq")
    private Long ticketStateActionTypeId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    public TicketStateActionType() {
    }

    public TicketStateActionType(String title) {
        this.title = title;
    }

    public Long getTicketStateActionTypeId() {
        return ticketStateActionTypeId;
    }

    public void setTicketStateActionTypeId(Long ticketStateActionTypeId) {
        this.ticketStateActionTypeId = ticketStateActionTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
