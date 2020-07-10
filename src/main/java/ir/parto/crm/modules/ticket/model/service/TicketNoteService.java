package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketNote;
import ir.parto.crm.modules.ticket.model.repository.TicketNoteRepository;
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
public class TicketNoteService implements ServiceInterface<TicketNote> {
    private TicketNoteRepository ticketNoteRepository;

    @Autowired
    public TicketNoteService(TicketNoteRepository ticketNoteRepository) {
        this.ticketNoteRepository = ticketNoteRepository;
    }

    @Override
    @Transactional
    public TicketNote addNewItem(TicketNote ticketNote) {
        ticketNote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketNoteRepository.save(ticketNote);
    }

    @Override
    @Transactional
    public TicketNote updateItem(TicketNote ticketNote) throws InvocationTargetException, IllegalAccessException {
        TicketNote exist = this.ticketNoteRepository.findByIsDeletedIsNullAndTicketNoteId(ticketNote.getTicketNoteId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketNote);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketNoteRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketNote deleteItem(TicketNote ticketNote) {
        TicketNote exist = this.ticketNoteRepository.findByIsDeletedIsNullAndTicketNoteId(ticketNote.getTicketNoteId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketNoteRepository.save(exist);
    }

    @Override
    public List<TicketNote> findAllItem() {
        return this.ticketNoteRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketNote> findAllItem(Pageable pageable) {
        return this.ticketNoteRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketNote> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketNoteRepository.findAll(pageable);
    }

    @Override
    public TicketNote findOne(TicketNote ticketNote) {
        return this.ticketNoteRepository.findByIsDeletedIsNullAndTicketNoteId(ticketNote.getTicketNoteId());
    }

    @Override
    public TicketNote findById(Long id) {
        return this.ticketNoteRepository.findByIsDeletedIsNullAndTicketNoteId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketNoteRepository.existsByIsDeletedIsNullAndTicketNoteId(id);
    }
}
