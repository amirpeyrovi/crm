package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.repository.TicketStateActionTypeRepository;
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
public class TickeStateActionTypeService implements ServiceInterface<TicketStateActionType> {
    private TicketStateActionTypeRepository ticketStateActionTypeRepository;

    @Autowired
    public TickeStateActionTypeService(TicketStateActionTypeRepository ticketStateActionTypeRepository) {
        this.ticketStateActionTypeRepository = ticketStateActionTypeRepository;
    }

    @Override
    public TicketStateActionType addNewItem(TicketStateActionType ticketStateActionType) {
        ticketStateActionType.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        ticketStateActionType.setTitle(ticketStateActionType.getTitle().trim());
        return this.ticketStateActionTypeRepository.save(ticketStateActionType);
    }

    @Override
    public TicketStateActionType updateItem(TicketStateActionType ticketStateActionType) throws InvocationTargetException, IllegalAccessException {
        TicketStateActionType exist = this.ticketStateActionTypeRepository.findByIsDeletedIsNullAndTicketStateActionTypeId(ticketStateActionType
                .getTicketStateActionTypeId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStateActionType);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setTitle(ticketStateActionType.getTitle().trim());
        return this.ticketStateActionTypeRepository.save(exist);
    }

    @Override
    public TicketStateActionType deleteItem(TicketStateActionType ticketStateActionType) {
        TicketStateActionType exist = this.ticketStateActionTypeRepository.findByIsDeletedIsNullAndTicketStateActionTypeId(ticketStateActionType
                .getTicketStateActionTypeId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStateActionTypeRepository.save(exist);
    }

    @Override
    public List<TicketStateActionType> findAllItem() {
        return this.ticketStateActionTypeRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketStateActionType> findAllItem(Pageable pageable) {
        return this.ticketStateActionTypeRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketStateActionType> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStateActionTypeRepository.findAll(pageable);
    }

    @Override
    public TicketStateActionType findOne(TicketStateActionType ticketStateActionType) {
        return this.ticketStateActionTypeRepository.findByIsDeletedIsNullAndTicketStateActionTypeId(ticketStateActionType.getTicketStateActionTypeId());
    }

    @Override
    public TicketStateActionType findById(Long id) {
        return this.ticketStateActionTypeRepository.findByIsDeletedIsNullAndTicketStateActionTypeId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateActionTypeRepository.existsByIsDeletedIsNullAndTicketStateActionTypeId(id);
    }
}