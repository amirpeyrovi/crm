package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.repository.TicketStateActionRepository;
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
public class TicketStateActionService implements ServiceInterface<TicketStateAction> {
    private TicketStateActionRepository ticketStateActionRepository;

    @Autowired
    public TicketStateActionService(TicketStateActionRepository ticketStateActionRepository) {
        this.ticketStateActionRepository = ticketStateActionRepository;
    }

    @Override
    @Transactional
    public TicketStateAction addNewItem(TicketStateAction ticketStateAction) {
        return this.ticketStateActionRepository.save(ticketStateAction);
    }

    @Override
    @Transactional
    public TicketStateAction updateItem(TicketStateAction ticketStateAction) throws InvocationTargetException, IllegalAccessException {
        TicketStateAction exist = this.ticketStateActionRepository.getOne(ticketStateAction.getTicketStateActionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStateAction);
        return this.ticketStateActionRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStateAction deleteItem(TicketStateAction ticketStateAction) {
        this.ticketStateActionRepository.delete(ticketStateAction);
        return ticketStateAction;
    }

    @Override
    public List<TicketStateAction> findAllItem() {
        return this.ticketStateActionRepository.findAll();
    }

    @Override
    public Page<TicketStateAction> findAllItem(Pageable pageable) {
        return this.ticketStateActionRepository.findAll(pageable);
    }

    @Override
    public Page<TicketStateAction> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public TicketStateAction findOne(TicketStateAction ticketStateAction) {
        return this.ticketStateActionRepository.getOne(ticketStateAction.getTicketStateActionId());
    }

    @Override
    public TicketStateAction findById(Long id) {
        if(this.ticketStateActionRepository.existsById(id)){
            return this.ticketStateActionRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateActionRepository.existsById(id);
    }
}
