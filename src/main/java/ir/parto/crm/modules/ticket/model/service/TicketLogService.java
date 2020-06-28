package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketLog;
import ir.parto.crm.modules.ticket.model.repository.TicketLogRepository;
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
public class TicketLogService implements ServiceInterface<TicketLog> {
    private TicketLogRepository ticketLogRepository;

    @Autowired
    public TicketLogService(TicketLogRepository ticketLogRepository) {
        this.ticketLogRepository = ticketLogRepository;
    }

    @Override
    @Transactional
    public TicketLog addNewItem(TicketLog ticketLog) {
        return this.ticketLogRepository.save(ticketLog);
    }

    @Override
    @Transactional
    public TicketLog updateItem(TicketLog ticketLog) throws InvocationTargetException, IllegalAccessException {
        TicketLog exist = this.ticketLogRepository.getOne(ticketLog.getTicketLogId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketLog);
        return this.ticketLogRepository.save(exist);
    }

    @Override
    @Transactional
    public List<TicketLog> deleteItem(TicketLog ticketLog) {
        this.ticketLogRepository.delete(ticketLog);
        return this.ticketLogRepository.findAll();
    }

    @Override
    public List<TicketLog> findAllItem() {
        return this.ticketLogRepository.findAll();
    }

    @Override
    public Page<TicketLog> findAllItem(Pageable pageable) {
        return this.ticketLogRepository.findAll(pageable);
    }

    @Override
    public TicketLog findOne(TicketLog ticketLog) {
        return this.ticketLogRepository.getOne(ticketLog.getTicketLogId());
    }

    @Override
    public TicketLog findById(Long id) {
        if(this.ticketLogRepository.existsById(id)){
            return this.ticketLogRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketLogRepository.existsById(id);
    }
}
