package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSitePort;
import ir.parto.crm.modules.popSite.model.repository.PopSitePortRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

public class PopSitePortService implements ServiceInterface<PopSitePort> {
    private PopSitePortRepository popSitePortRepository;

    @Autowired
    public PopSitePortService(PopSitePortRepository popSitePortRepository) {
        this.popSitePortRepository = popSitePortRepository;
    }

    @Override
    @Transactional
    public PopSitePort addNewItem(PopSitePort popSitePort) {
        popSitePort.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSitePortRepository.save(popSitePort);
    }

    @Override
    @Transactional
    public PopSitePort updateItem(PopSitePort popSitePort) throws InvocationTargetException, IllegalAccessException {
        PopSitePort exist = this.popSitePortRepository.findByIsDeletedIsNullAndPortId(popSitePort.getPortId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSitePort);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSitePortRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSitePort deleteItem(PopSitePort popSitePort) {
        PopSitePort exist = this.popSitePortRepository.findByIsDeletedIsNullAndPortId(popSitePort.getPortId());
        popSitePort.setIsDeleted(1);
        popSitePort.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        popSitePort.setDeletedDate(LocalDateTime.now());
        return this.popSitePortRepository.save(popSitePort);
    }

    @Override
    public List<PopSitePort> findAllItem() {
        return this.popSitePortRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSitePort> findAllItem(Pageable pageable) {
        return this.popSitePortRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSitePort> findAllItemWithDeleted(Pageable pageable) {
        return this.popSitePortRepository.findAll(pageable);
    }

    @Override
    public PopSitePort findOne(PopSitePort popSitePort) {
        return this.popSitePortRepository.findByIsDeletedIsNullAndPortId(popSitePort.getPortId());
    }

    @Override
    public PopSitePort findById(Long id) {
        return this.popSitePortRepository.findByIsDeletedIsNullAndPortId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSitePortRepository.existsByIsDeletedIsNullAndPortId(id);
    }
}
