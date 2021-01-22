package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.repository.TicketStateRepository;
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
public class TicketStateService implements ServiceInterface<TicketState> {
    private TicketStateRepository ticketStateRepository;

    @Autowired
    public TicketStateService(TicketStateRepository ticketStateRepository) {
        this.ticketStateRepository = ticketStateRepository;
    }

    @Override
    @Transactional
    public TicketState addNewItem(TicketState ticketState) {
        ticketState.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        ticketState.setTitle(ticketState.getTitle().trim());
        return this.ticketStateRepository.save(ticketState);
    }

    @Override
    @Transactional
    public TicketState updateItem(TicketState ticketState) throws InvocationTargetException, IllegalAccessException {
        TicketState exist = this.ticketStateRepository.findByIsDeletedIsNullAndTicketStateId(ticketState.getTicketStateId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketState);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        if (ticketState.getTitle() != null && ticketState.getTitle().trim() != null)
            exist.setTitle(ticketState.getTitle().trim());
        return this.ticketStateRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketState deleteItem(TicketState ticketState) {
        TicketState exist = this.ticketStateRepository.findByIsDeletedIsNullAndTicketStateId(ticketState.getTicketStateId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStateRepository.save(exist);
    }

    @Override
    public List<TicketState> findAllItem() {
        return this.ticketStateRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketState> findAllItem(Pageable pageable) {
        return this.ticketStateRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketState> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStateRepository.findAll(pageable);
    }

    @Override
    public TicketState findOne(TicketState ticketState) {
        return this.ticketStateRepository.findByIsDeletedIsNullAndTicketStateId(ticketState.getTicketStateId());
    }

    @Override
    public TicketState findById(Long id) {
        return this.ticketStateRepository.findByIsDeletedIsNullAndTicketStateId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateRepository.existsByIsDeletedIsNullAndTicketStateId(id);
    }

    public TicketState findByIsDeletedIsNullAndTitle(String title) {
        return this.ticketStateRepository.findByIsDeletedIsNullAndTitle(title);
    }
}
