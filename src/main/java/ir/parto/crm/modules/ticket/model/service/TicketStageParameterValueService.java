package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameterValue;
import ir.parto.crm.modules.ticket.model.repository.TicketStageParameterValueRepository;
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
public class TicketStageParameterValueService implements ServiceInterface<TicketStageParameterValue> {
    private TicketStageParameterValueRepository ticketStageParameterValueRepository;

    @Autowired
    public TicketStageParameterValueService(TicketStageParameterValueRepository ticketStageParameterValueRepository) {
        this.ticketStageParameterValueRepository = ticketStageParameterValueRepository;
    }

    @Override
    @Transactional
    public TicketStageParameterValue addNewItem(TicketStageParameterValue ticketStageParameterValue) {
        return this.ticketStageParameterValueRepository.save(ticketStageParameterValue);
    }

    @Override
    @Transactional
    public TicketStageParameterValue updateItem(TicketStageParameterValue ticketStageParameterValue) throws InvocationTargetException, IllegalAccessException {
        TicketStageParameterValue exist = this.ticketStageParameterValueRepository.getOne(ticketStageParameterValue.getTicketStageParameterValueId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStageParameterValue);
        return this.ticketStageParameterValueRepository.save(exist);
    }

    @Override
    @Transactional
    public List<TicketStageParameterValue> deleteItem(TicketStageParameterValue ticketStageParameterValue) {
        this.ticketStageParameterValueRepository.delete(ticketStageParameterValue);
        return this.ticketStageParameterValueRepository.findAll();
    }

    @Override
    public List<TicketStageParameterValue> findAllItem() {
        return this.ticketStageParameterValueRepository.findAll();
    }

    @Override
    public Page<TicketStageParameterValue> findAllItem(Pageable pageable) {
        return this.ticketStageParameterValueRepository.findAll(pageable);
    }

    @Override
    public TicketStageParameterValue findOne(TicketStageParameterValue ticketStageParameterValue) {
        return this.ticketStageParameterValueRepository.getOne(ticketStageParameterValue.getTicketStageParameterValueId());
    }

    @Override
    public TicketStageParameterValue findById(Long id) {
        if(this.ticketStageParameterValueRepository.existsById(id)){
            return this.ticketStageParameterValueRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageParameterValueRepository.existsById(id);
    }
}
