package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.repository.TicketRepository;
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
public class TicketService  implements ServiceInterface<Ticket> {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket addNewItem(Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket updateItem(Ticket ticket) throws InvocationTargetException, IllegalAccessException {
        Ticket exist = this.ticketRepository.getOne(ticket.getTicketId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticket);
        return this.ticketRepository.save(exist);
    }

    @Override
    @Transactional
    public Ticket deleteItem(Ticket ticket) {
        this.ticketRepository.delete(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllItem() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Page<Ticket> findAllItem(Pageable pageable) {
        return this.ticketRepository.findAll(pageable);
    }

    @Override
    public Page<Ticket> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Ticket findOne(Ticket ticket) {
        return this.ticketRepository.getOne(ticket.getTicketId());
    }

    @Override
    public Ticket findById(Long id) {
        if(this.ticketRepository.existsById(id)){
            return this.ticketRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketRepository.existsById(id);
    }
}
