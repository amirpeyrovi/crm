package ir.parto.crm.modules.ticket.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticket_state_action")
public class TicketStateAction implements Serializable {
    @Id
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "type", columnDefinition = "number")
    private Long type;
}
