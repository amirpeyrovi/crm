package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteSwitch;
import ir.parto.crm.modules.popSite.model.repository.PopSiteSwitchRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PopSiteSwitchService implements ServiceInterface<PopSiteSwitch> {
    private PopSiteSwitchRepository popSiteSwitchRepository;

    @Autowired
    public PopSiteSwitchService(PopSiteSwitchRepository popSiteSwitchRepository) {
        this.popSiteSwitchRepository = popSiteSwitchRepository;
    }

    @Override
    @Transactional
    public PopSiteSwitch addNewItem(PopSiteSwitch popSiteSwitch) {
        popSiteSwitch.setCreatedBy("");
        return this.popSiteSwitchRepository.save(popSiteSwitch);
    }

    @Override
    @Transactional
    public PopSiteSwitch updateItem(PopSiteSwitch popSiteSwitch) throws InvocationTargetException, IllegalAccessException {
        PopSiteSwitch exist = this.popSiteSwitchRepository.findByIsDeletedIsNullAndSwitchId(popSiteSwitch.getSwitchId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteSwitch);
        exist.setUpdatedBy("");
        return this.popSiteSwitchRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSiteSwitch deleteItem(PopSiteSwitch popSiteSwitch) {
        PopSiteSwitch exist = this.popSiteSwitchRepository.findByIsDeletedIsNullAndSwitchId(popSiteSwitch.getSwitchId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteSwitchRepository.save(exist);
    }

    @Override
    public List<PopSiteSwitch> findAllItem() {
        return this.popSiteSwitchRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteSwitch> findAllItem(Pageable pageable) {
        return this.popSiteSwitchRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteSwitch> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteSwitchRepository.findAll(pageable);
    }

    @Override
    public PopSiteSwitch findOne(PopSiteSwitch popSiteSwitch) {
        return this.popSiteSwitchRepository.findByIsDeletedIsNullAndSwitchId(popSiteSwitch.getSwitchId());
    }

    @Override
    public PopSiteSwitch findById(Long id) {
        return this.popSiteSwitchRepository.findByIsDeletedIsNullAndSwitchId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteSwitchRepository.existsByIsDeletedIsNullAndSwitchId(id);
    }
}
