package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStageRole;
import ir.parto.crm.modules.ticket.model.repository.TicketStageRoleRepository;
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
public class TicketStageRoleService implements ServiceInterface<TicketStageRole> {
    private TicketStageRoleRepository ticketStageRoleRepository;

    @Autowired
    public TicketStageRoleService(TicketStageRoleRepository ticketStageRoleRepository) {
        this.ticketStageRoleRepository = ticketStageRoleRepository;
    }

    @Override
    @Transactional
    public TicketStageRole addNewItem(TicketStageRole ticketStageRole) {
        ticketStageRole.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStageRoleRepository.save(ticketStageRole);
    }

    @Override
    @Transactional
    public TicketStageRole updateItem(TicketStageRole ticketStageRole) throws InvocationTargetException, IllegalAccessException {
        TicketStageRole exist = this.ticketStageRoleRepository.findByIsDeletedIsNullAndTicketStageRoleId(ticketStageRole.getTicketStageRoleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStageRole);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStageRoleRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStageRole deleteItem(TicketStageRole ticketStageRole) {
        TicketStageRole exist = this.ticketStageRoleRepository.findByIsDeletedIsNullAndTicketStageRoleId(ticketStageRole.getTicketStageRoleId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStageRoleRepository.save(exist);
    }

    @Override
    public List<TicketStageRole> findAllItem() {
        return this.ticketStageRoleRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketStageRole> findAllItem(Pageable pageable) {
        return this.ticketStageRoleRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketStageRole> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStageRoleRepository.findAll(pageable);
    }

    @Override
    public TicketStageRole findOne(TicketStageRole ticketStageRole) {
        return this.ticketStageRoleRepository.findByIsDeletedIsNullAndTicketStageRoleId(ticketStageRole.getTicketStageRoleId());
    }

    @Override
    public TicketStageRole findById(Long id) {
        return this.ticketStageRoleRepository.findByIsDeletedIsNullAndTicketStageRoleId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageRoleRepository.existsByIsDeletedIsNullAndTicketStageRoleId(id);
    }
}
