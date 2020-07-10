package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.modules.ticket.model.repository.TicketStageParameterRepository;
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
public class TicketStageParameterService implements ServiceInterface<TicketStageParameter> {
    private TicketStageParameterRepository ticketStageParameterRepository;

    @Autowired
    public TicketStageParameterService(TicketStageParameterRepository ticketStageParameterRepository) {
        this.ticketStageParameterRepository = ticketStageParameterRepository;
    }

    @Override
    @Transactional
    public TicketStageParameter addNewItem(TicketStageParameter ticketStageParameter) {
        ticketStageParameter.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStageParameterRepository.save(ticketStageParameter);
    }

    @Override
    @Transactional
    public TicketStageParameter updateItem(TicketStageParameter ticketStageParameter) throws InvocationTargetException, IllegalAccessException {
        TicketStageParameter exist = this.ticketStageParameterRepository.findByIsDeletedIsNullAndTicketStageParameterId(ticketStageParameter.getTicketStageParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStageParameter);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ticketStageParameterRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStageParameter deleteItem(TicketStageParameter ticketStageParameter) {
        TicketStageParameter exist = this.ticketStageParameterRepository.findByIsDeletedIsNullAndTicketStageParameterId(ticketStageParameter.getTicketStageParameterId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStageParameterRepository.save(exist);
    }

    @Override
    public List<TicketStageParameter> findAllItem() {
        return this.ticketStageParameterRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketStageParameter> findAllItem(Pageable pageable) {
        return this.ticketStageParameterRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketStageParameter> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStageParameterRepository.findAll(pageable);
    }

    @Override
    public TicketStageParameter findOne(TicketStageParameter ticketStageParameter) {
        return this.ticketStageParameterRepository.findByIsDeletedIsNullAndTicketStageParameterId(ticketStageParameter.getTicketStageParameterId());
    }

    @Override
    public TicketStageParameter findById(Long id) {
        return this.ticketStageParameterRepository.findByIsDeletedIsNullAndTicketStageParameterId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageParameterRepository.existsByIsDeletedIsNullAndTicketStageParameterId(id);
    }
}
