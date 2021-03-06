package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketWorkFlow;
import ir.parto.crm.modules.ticket.model.repository.TicketWorkFlowRepository;
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
public class TicketWorkFlowService implements ServiceInterface<TicketWorkFlow> {
    private TicketWorkFlowRepository ticketWorkFlowRepository;

    @Autowired
    public TicketWorkFlowService(TicketWorkFlowRepository ticketWorkFlowRepository) {
        this.ticketWorkFlowRepository = ticketWorkFlowRepository;
    }

    @Override
    @Transactional
    public TicketWorkFlow addNewItem(TicketWorkFlow ticketWorkFlow) {
        ticketWorkFlow.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketWorkFlowRepository.save(ticketWorkFlow);
    }

    @Override
    @Transactional
    public TicketWorkFlow updateItem(TicketWorkFlow ticketWorkFlow) throws InvocationTargetException, IllegalAccessException {
        TicketWorkFlow exist = this.ticketWorkFlowRepository.findByIsDeletedIsNullAndTicketWorkFlowId(ticketWorkFlow.getTicketWorkFlowId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketWorkFlow);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketWorkFlowRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketWorkFlow deleteItem(TicketWorkFlow ticketWorkFlow) {
        TicketWorkFlow exist = this.ticketWorkFlowRepository.findByIsDeletedIsNullAndTicketWorkFlowId(ticketWorkFlow.getTicketWorkFlowId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketWorkFlowRepository.save(exist);
    }

    @Override
    public List<TicketWorkFlow> findAllItem() {
        return this.ticketWorkFlowRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketWorkFlow> findAllItem(Pageable pageable) {
        return this.ticketWorkFlowRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketWorkFlow> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketWorkFlowRepository.findAll(pageable);
    }

    @Override
    public TicketWorkFlow findOne(TicketWorkFlow ticketWorkFlow) {
        return this.ticketWorkFlowRepository.findByIsDeletedIsNullAndTicketWorkFlowId(ticketWorkFlow.getTicketWorkFlowId());
    }

    @Override
    public TicketWorkFlow findById(Long id) {
        return this.ticketWorkFlowRepository.findByIsDeletedIsNullAndTicketWorkFlowId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketWorkFlowRepository.existsByIsDeletedIsNullAndTicketWorkFlowId(id);
    }

    public TicketWorkFlow findByIsDeletedIsNullAndTitle(String title) {
        return this.ticketWorkFlowRepository.findByIsDeletedIsNullAndTitle(title);
    }
}
