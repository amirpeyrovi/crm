package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSite;
import ir.parto.crm.modules.popSite.model.repository.PopSiteRepository;
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
public class PopSiteService implements ServiceInterface<PopSite> {
    private PopSiteRepository popSiteRepository;

    @Autowired
    public PopSiteService(PopSiteRepository popSiteRepository) {
        this.popSiteRepository = popSiteRepository;
    }

    @Override
    @Transactional
    public PopSite addNewItem(PopSite popSite) {
        popSite.setCreatedBy("");
        return this.popSiteRepository.save(popSite);
    }

    @Override
    @Transactional
    public PopSite updateItem(PopSite popSite) throws InvocationTargetException, IllegalAccessException {
        PopSite exist = this.popSiteRepository.findByIsDeletedIsNullAndPopSiteId(popSite.getPopSiteId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSite);
        exist.setUpdatedBy("");
        return this.popSiteRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSite deleteItem(PopSite popSite) {
        PopSite exist = this.popSiteRepository.findByIsDeletedIsNullAndPopSiteId(popSite.getPopSiteId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteRepository.save(exist);
    }

    @Override
    public List<PopSite> findAllItem() {
        return this.popSiteRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSite> findAllItem(Pageable pageable) {
        return this.popSiteRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSite> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteRepository.findAll(pageable);
    }

    @Override
    public PopSite findOne(PopSite popSite) {
        return this.popSiteRepository.findByIsDeletedIsNullAndPopSiteId(popSite.getPopSiteId());
    }

    @Override
    public PopSite findById(Long id) {
        return this.popSiteRepository.findByIsDeletedIsNullAndPopSiteId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteRepository.existsByIsDeletedIsNullAndPopSiteId(id);
    }
}
