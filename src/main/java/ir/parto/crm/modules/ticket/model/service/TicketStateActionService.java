package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.repository.TicketStateActionRepository;
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
public class TicketStateActionService implements ServiceInterface<TicketStateAction> {
    private TicketStateActionRepository ticketStateActionRepository;

    @Autowired
    public TicketStateActionService(TicketStateActionRepository ticketStateActionRepository) {
        this.ticketStateActionRepository = ticketStateActionRepository;
    }

    @Override
    @Transactional
    public TicketStateAction addNewItem(TicketStateAction ticketStateAction) {
        ticketStateAction.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStateActionRepository.save(ticketStateAction);
    }

    @Override
    @Transactional
    public TicketStateAction updateItem(TicketStateAction ticketStateAction) throws InvocationTargetException, IllegalAccessException {
        TicketStateAction exist = this.ticketStateActionRepository.findByIsDeletedIsNullAndTicketStateActionId(ticketStateAction.getTicketStateActionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStateAction);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStateActionRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStateAction deleteItem(TicketStateAction ticketStateAction) {
        TicketStateAction exist = this.ticketStateActionRepository.findByIsDeletedIsNullAndTicketStateActionId(ticketStateAction.getTicketStateActionId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStateActionRepository.save(exist);
    }

    @Override
    public List<TicketStateAction> findAllItem() {
        return this.ticketStateActionRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketStateAction> findAllItem(Pageable pageable) {
        return this.ticketStateActionRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketStateAction> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStateActionRepository.findAll(pageable);
    }

    @Override
    public TicketStateAction findOne(TicketStateAction ticketStateAction) {
        return this.ticketStateActionRepository.findByIsDeletedIsNullAndTicketStateActionId(ticketStateAction.getTicketStateActionId());
    }

    @Override
    public TicketStateAction findById(Long id) {
        return this.ticketStateActionRepository.findByIsDeletedIsNullAndTicketStateActionId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateActionRepository.existsByIsDeletedIsNullAndTicketStateActionId(id);
    }

    public TicketStateAction findByIsDeletedIsNullAndTitle(String title) {
        return this.ticketStateActionRepository.findByIsDeletedIsNullAndTitle(title);
    }
}
