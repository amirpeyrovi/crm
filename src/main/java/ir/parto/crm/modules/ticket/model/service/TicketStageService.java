package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.repository.TicketStageRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
        return this.ticketStageRepository.save(ticketStage);
    }

    @Override
    @Transactional
    public TicketStage updateItem(TicketStage ticketStage) throws InvocationTargetException, IllegalAccessException {
        TicketStage exist = this.ticketStageRepository.getOne(ticketStage.getTicketStageId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStage);
        return this.ticketStageRepository.save(exist);
    }

    @Override
    @Transactional
    public List<TicketStage> deleteItem(TicketStage ticketStage) {
        this.ticketStageRepository.delete(ticketStage);
        return this.ticketStageRepository.findAll();
    }

    @Override
    public List<TicketStage> findAllItem() {
        return this.ticketStageRepository.findAll();
    }

    @Override
    public Page<TicketStage> findAllItem(Pageable pageable) {
        return this.ticketStageRepository.findAll(pageable);
    }

    @Override
    public TicketStage findOne(TicketStage ticketStage) {
        return this.ticketStageRepository.getOne(ticketStage.getTicketStageId());
    }

    @Override
    public TicketStage findById(Long id) {
        if(this.ticketStageRepository.existsById(id)){
            return this.ticketStageRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageRepository.existsById(id);
    }
}
