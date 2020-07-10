package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.repository.TicketRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService  implements ServiceInterface<Ticket> {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket addNewItem(Ticket ticket) {
        ticket.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket updateItem(Ticket ticket) throws InvocationTargetException, IllegalAccessException {
        Ticket exist = this.ticketRepository.findByIsDeletedIsNullAndTicketId(ticket.getTicketId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticket);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketRepository.save(exist);
    }

    @Override
    @Transactional
    public Ticket deleteItem(Ticket ticket) {
        Ticket exist = this.ticketRepository.findByIsDeletedIsNullAndTicketId(ticket.getTicketId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketRepository.save(exist);
    }

    @Override
    public List<Ticket> findAllItem() {
        return this.ticketRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Ticket> findAllItem(Pageable pageable) {
        return this.ticketRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Ticket> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket findOne(Ticket ticket) {
        return this.ticketRepository.findByIsDeletedIsNullAndTicketId(ticket.getTicketId());
    }

    @Override
    public Ticket findById(Long id) {
        return this.ticketRepository.findByIsDeletedIsNullAndTicketId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketRepository.existsByIsDeletedIsNullAndTicketId(id);
    }

    public Ticket findByIsDeletedIsNullAndTitle(String title) {
        return this.ticketRepository.findByIsDeletedIsNullAndTitle(title);
    }
}
