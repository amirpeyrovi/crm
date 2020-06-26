package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.repository.TicketStateActionTypeRepository;
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
public class TicketStateActionTypeService implements ServiceInterface<TicketStateActionType> {
    private TicketStateActionTypeRepository ticketStateActionTypeRepository;

    @Autowired
    public TicketStateActionTypeService(TicketStateActionTypeRepository ticketStateActionTypeRepository) {
        this.ticketStateActionTypeRepository = ticketStateActionTypeRepository;
    }

    @Override
    @Transactional
    public TicketStateActionType addNewItem(TicketStateActionType ticketStateActionType) {
        return this.ticketStateActionTypeRepository.save(ticketStateActionType);
    }

    @Override
    @Transactional
    public TicketStateActionType updateItem(TicketStateActionType ticketStateActionType) throws InvocationTargetException, IllegalAccessException {
        TicketStateActionType exist = this.ticketStateActionTypeRepository.getOne(ticketStateActionType.getTicketStateActionTypeId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStateActionType);
        return this.ticketStateActionTypeRepository.save(exist);
    }

    @Override
    @Transactional
    public List<TicketStateActionType> deleteItem(TicketStateActionType ticketStateActionType) {
        this.ticketStateActionTypeRepository.delete(ticketStateActionType);
        return this.ticketStateActionTypeRepository.findAll();
    }

    @Override
    public List<TicketStateActionType> findAllItem() {
        return this.ticketStateActionTypeRepository.findAll();
    }

    @Override
    public Page<TicketStateActionType> findAllItem(Pageable pageable) {
        return this.ticketStateActionTypeRepository.findAll(pageable);
    }

    @Override
    public TicketStateActionType findOne(TicketStateActionType ticketStateActionType) {
        return this.ticketStateActionTypeRepository.getOne(ticketStateActionType.getTicketStateActionTypeId());
    }

    @Override
    public TicketStateActionType findById(Long id) {
        if(this.ticketStateActionTypeRepository.existsById(id)){
            return this.ticketStateActionTypeRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStateActionTypeRepository.existsById(id);
    }
}
