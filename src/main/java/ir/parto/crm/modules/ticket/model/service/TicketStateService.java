package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.repository.TicketStateRepository;
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
public class TicketStateService implements ServiceInterface<TicketState> {
    private TicketStateRepository ticketStateRepository;

    @Autowired
    public TicketStateService(TicketStateRepository ticketStateRepository) {
        this.ticketStateRepository = ticketStateRepository;
    }

    @Override
    @Transactional
    public TicketState addNewItem(TicketState ticketState) {
        return this.ticketStateRepository.save(ticketState);
    }

    @Override
    @Transactional
    public TicketState updateItem(TicketState ticketState) throws InvocationTargetException, IllegalAccessException {
        TicketState exist = this.ticketStateRepository.getOne(ticketState.getTicketStateId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketState);
        return this.ticketStateRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketState deleteItem(TicketState ticketState) {
        this.ticketStateRepository.delete(ticketState);
        return ticketState;
    }

    @Override
    public List<TicketState> findAllItem() {
        return this.ticketStateRepository.findAll();
    }

    @Override
    public Page<TicketState> findAllItem(Pageable pageable) {
        return this.ticketStateRepository.findAll(pageable);
    }

    @Override
    public Page<TicketState> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public TicketState findOne(TicketState ticketState) {
        return this.ticketStateRepository.getOne(ticketState.getTicketStateId());
    }

    @Override
    public TicketState findById(Long id) {
        if(this.ticketStateRepository.existsById(id)){
            return this.ticketStateRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateRepository.existsById(id);
    }
}
