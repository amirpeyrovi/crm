package ir.parto.crm.modules.ticket.model.service;

import ir.parto.crm.modules.ticket.model.entity.TicketStageRole;
import ir.parto.crm.modules.ticket.model.repository.TicketStageRoleRepository;
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
public class TicketStageRoleService implements ServiceInterface<TicketStageRole> {
    private TicketStageRoleRepository ticketStageRoleRepository;

    @Autowired
    public TicketStageRoleService(TicketStageRoleRepository ticketStageRoleRepository) {
        this.ticketStageRoleRepository = ticketStageRoleRepository;
    }

    @Override
    @Transactional
    public TicketStageRole addNewItem(TicketStageRole ticketStageRole) {
        return this.ticketStageRoleRepository.save(ticketStageRole);
    }

    @Override
    @Transactional
    public TicketStageRole updateItem(TicketStageRole ticketStageRole) throws InvocationTargetException, IllegalAccessException {
        TicketStageRole exist = this.ticketStageRoleRepository.getOne(ticketStageRole.getTicketStageRoleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ticketStageRole);
        return this.ticketStageRoleRepository.save(exist);
    }

    @Override
    @Transactional
    public List<TicketStageRole> deleteItem(TicketStageRole ticketStageRole) {
        this.ticketStageRoleRepository.delete(ticketStageRole);
        return this.ticketStageRoleRepository.findAll();
    }

    @Override
    public List<TicketStageRole> findAllItem() {
        return this.ticketStageRoleRepository.findAll();
    }

    @Override
    public Page<TicketStageRole> findAllItem(Pageable pageable) {
        return this.ticketStageRoleRepository.findAll(pageable);
    }

    @Override
    public TicketStageRole findOne(TicketStageRole ticketStageRole) {
        return this.ticketStageRoleRepository.getOne(ticketStageRole.getTicketStageRoleId());
    }

    @Override
    public TicketStageRole findById(Long id) {
        if(this.ticketStageRoleRepository.existsById(id)){
            return this.ticketStageRoleRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ticketStageRoleRepository.existsById(id);
    }
}
