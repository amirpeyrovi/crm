package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStateOfStage;
import ir.parto.crm.modules.ticket.model.repository.TicketStateOfStageRepository;
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
public class TicketStateOfStageService implements ServiceInterface<TicketStateOfStage> {
    private TicketStateOfStageRepository ticketStateOfStageRepository;

    @Autowired
    public TicketStateOfStageService(TicketStateOfStageRepository ticketStateOfStageRepository) {
        this.ticketStateOfStageRepository = ticketStateOfStageRepository;
    }

    @Override
    @Transactional
    public TicketStateOfStage addNewItem(TicketStateOfStage ticketStateOfStage) {
        return this.ticketStateOfStageRepository.save(ticketStateOfStage);
    }

    @Override
    @Transactional
    public TicketStateOfStage updateItem(TicketStateOfStage ticketStateOfStage) throws InvocationTargetException, IllegalAccessException {
        TicketStateOfStage exist = this.ticketStateOfStageRepository.getOne(ticketStateOfStage.getTicketStateOfStageId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStateOfStage);
        return this.ticketStateOfStageRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStateOfStage deleteItem(TicketStateOfStage ticketStateOfStage) {
        this.ticketStateOfStageRepository.delete(ticketStateOfStage);
        return ticketStateOfStage;
    }

    @Override
    public List<TicketStateOfStage> findAllItem() {
        return this.ticketStateOfStageRepository.findAll();
    }

    @Override
    public Page<TicketStateOfStage> findAllItem(Pageable pageable) {
        return this.ticketStateOfStageRepository.findAll(pageable);
    }

    @Override
    public Page<TicketStateOfStage> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public TicketStateOfStage findOne(TicketStateOfStage ticketStateOfStage) {
        return this.ticketStateOfStageRepository.getOne(ticketStateOfStage.getTicketStateOfStageId());
    }

    @Override
    public TicketStateOfStage findById(Long id) {
        if(this.ticketStateOfStageRepository.existsById(id)){
            return this.ticketStateOfStageRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateOfStageRepository.existsById(id);
    }
}
