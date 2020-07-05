package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTower;
import ir.parto.crm.modules.popSite.model.repository.PopSiteTowerRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PopSiteTowerService implements ServiceInterface<PopSiteTower> {
    private PopSiteTowerRepository popSiteTowerRepository;

    @Autowired
    public PopSiteTowerService(PopSiteTowerRepository popSiteTowerRepository) {
        this.popSiteTowerRepository = popSiteTowerRepository;
    }

    @Override
    @Transactional
    public PopSiteTower addNewItem(PopSiteTower popSiteTower) {
        popSiteTower.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSiteTowerRepository.save(popSiteTower);
    }

    @Override
    @Transactional
    public PopSiteTower updateItem(PopSiteTower popSiteTower) throws InvocationTargetException, IllegalAccessException {
        PopSiteTower exist = this.popSiteTowerRepository.findByIsDeletedIsNullAndTowerId(popSiteTower.getTowerId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteTower);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSiteTowerRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSiteTower deleteItem(PopSiteTower popSiteTower) {
        PopSiteTower exist = this.popSiteTowerRepository.findByIsDeletedIsNullAndTowerId(popSiteTower.getTowerId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteTowerRepository.save(exist);
    }

    @Override
    public List<PopSiteTower> findAllItem() {
        return this.popSiteTowerRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteTower> findAllItem(Pageable pageable) {
        return this.popSiteTowerRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteTower> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteTowerRepository.findAll(pageable);
    }

    @Override
    public PopSiteTower findOne(PopSiteTower popSiteTower) {
        return this.popSiteTowerRepository.findByIsDeletedIsNullAndTowerId(popSiteTower.getTowerId());
    }

    @Override
    public PopSiteTower findById(Long id) {
        return this.popSiteTowerRepository.findByIsDeletedIsNullAndTowerId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteTowerRepository.existsByIsDeletedIsNullAndTowerId(id);
    }
}
