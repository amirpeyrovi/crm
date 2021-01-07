package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.repository.TicketStageRepository;
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
public class TicketStageService implements ServiceInterface<TicketStage> {
    private TicketStageRepository ticketStageRepository;

    @Autowired
    public TicketStageService(TicketStageRepository ticketStageRepository) {
        this.ticketStageRepository = ticketStageRepository;
    }

    @Override
    @Transactional
    public TicketStage addNewItem(TicketStage ticketStage) {
        ticketStage.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        ticketStage.setTitle(ticketStage.getTitle().trim());
        return this.ticketStageRepository.save(ticketStage);
    }

    @Override
    @Transactional
    public TicketStage updateItem(TicketStage ticketStage) throws InvocationTargetException, IllegalAccessException {
        TicketStage exist = this.ticketStageRepository.findByIsDeletedIsNullAndTicketStageId(ticketStage.getTicketStageId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStage);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setTitle(exist.getTitle().trim());
        return this.ticketStageRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStage deleteItem(TicketStage ticketStage) {
        TicketStage exist = this.ticketStageRepository.findByIsDeletedIsNullAndTicketStageId(ticketStage.getTicketStageId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.ticketStageRepository.save(exist);
    }

    @Override
    public List<TicketStage> findAllItem() {
        return this.ticketStageRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<TicketStage> findAllItem(Pageable pageable) {
        return this.ticketStageRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<TicketStage> findAllItemWithDeleted(Pageable pageable) {
        return this.ticketStageRepository.findAll(pageable);
    }

    @Override
    public TicketStage findOne(TicketStage ticketStage) {
        return this.ticketStageRepository.findByIsDeletedIsNullAndTicketStageId(ticketStage.getTicketStageId());
    }

    @Override
    public TicketStage findById(Long id) {
        return this.ticketStageRepository.findByIsDeletedIsNullAndTicketStageId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageRepository.existsByIsDeletedIsNullAndTicketStageId(id);
    }

    public TicketStage findByIsDeletedIsNullAndTitle(String title) {
        return this.ticketStageRepository.findByIsDeletedIsNullAndTitle(title);
    }
}
