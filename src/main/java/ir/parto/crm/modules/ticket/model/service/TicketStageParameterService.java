package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.modules.ticket.model.repository.TicketStageParameterRepository;
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
public class TicketStageParameterService  implements ServiceInterface<TicketStageParameter> {
    private TicketStageParameterRepository ticketStageParameterRepository;

    @Autowired
    public TicketStageParameterService(TicketStageParameterRepository ticketStageParameterRepository) {
        this.ticketStageParameterRepository = ticketStageParameterRepository;
    }

    @Override
    @Transactional
    public TicketStageParameter addNewItem(TicketStageParameter ticketStageParameter) {
        return this.ticketStageParameterRepository.save(ticketStageParameter);
    }

    @Override
    @Transactional
    public TicketStageParameter updateItem(TicketStageParameter ticketStageParameter) throws InvocationTargetException, IllegalAccessException {
        TicketStageParameter exist = this.ticketStageParameterRepository.getOne(ticketStageParameter.getTicketStageParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStageParameter);
        return this.ticketStageParameterRepository.save(exist);
    }

    @Override
    @Transactional
    public TicketStageParameter deleteItem(TicketStageParameter ticketStageParameter) {
        this.ticketStageParameterRepository.delete(ticketStageParameter);
        return ticketStageParameter;
    }

    @Override
    public List<TicketStageParameter> findAllItem() {
        return this.ticketStageParameterRepository.findAll();
    }

    @Override
    public Page<TicketStageParameter> findAllItem(Pageable pageable) {
        return this.ticketStageParameterRepository.findAll(pageable);
    }

    @Override
    public Page<TicketStageParameter> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public TicketStageParameter findOne(TicketStageParameter ticketStageParameter) {
        return this.ticketStageParameterRepository.getOne(ticketStageParameter.getTicketStageParameterId());
    }

    @Override
    public TicketStageParameter findById(Long id) {
        if(this.ticketStageParameterRepository.existsById(id)){
            return this.ticketStageParameterRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageParameterRepository.existsById(id);
    }
}
