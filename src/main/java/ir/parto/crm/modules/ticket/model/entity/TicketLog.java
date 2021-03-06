package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketLog.TicketLogDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketLog.TicketLogInfoDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_log")
public class TicketLog implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketLogId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_log_ticket_fk"))
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_log_ticket_stage_fk"))
    private TicketStage ticketStage;

    @ManyToOne
    @JoinColumn(name = "ticket_state_id", foreignKey = @ForeignKey(name = "ticket_log_ticket_state_fk"))
    private TicketState ticketState;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public TicketLog() {
    }

    public TicketLog(Ticket ticket, TicketStage ticketStage, TicketState ticketState, String createdBy, LocalDateTime createdDate) {
        this.ticket = ticket;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getTicketLogId() {
        return ticketLogId;
    }

    public void setTicketLogId(Long ticketLogId) {
        this.ticketLogId = ticketLogId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketStage getTicketStage() {
        return ticketStage;
    }

    public void setTicketStage(TicketStage ticketStage) {
        this.ticketStage = ticketStage;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
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

    public TicketLogDTO convert2Object() {
        TicketLogDTO dto = new TicketLogDTO();
        if (this.ticketLogId != null) dto.setTicketLogId(this.ticketLogId);
        if (this.ticket != null) dto.setTicket(this.ticket.convert2RelationalObject());
        if (this.ticketStage != null) dto.setTicketStage(this.ticketStage.convert2RelationalObject());
        if (this.ticketState != null) dto.setTicketState(this.ticketState.convert2RelationalObject());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        return dto;
    }

    public TicketLogInfoDTO convert2InfoObject() {
        TicketLogInfoDTO dto = new TicketLogInfoDTO();
        if (this.ticketLogId != null) dto.setTicketLogId(this.ticketLogId);
        if (this.ticket != null) dto.setTicket(this.ticket.convert2RelationalObject());
        if (this.ticketStage != null) dto.setTicketStage(this.ticketStage.convert2RelationalObject());
        if (this.ticketState != null) dto.setTicketState(this.ticketState.convert2RelationalObject());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        return dto;
    }
}
