package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadioServiceLink;
import ir.parto.crm.modules.popSite.model.repository.PopSiteRadioServiceLinkRepository;
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
public class PopSiteRadioServiceLinkService implements ServiceInterface<PopSiteRadioServiceLink> {
    private PopSiteRadioServiceLinkRepository popSiteRadioServiceLinkRepository;

    @Autowired
    public PopSiteRadioServiceLinkService(PopSiteRadioServiceLinkRepository popSiteRadioServiceLinkRepository) {
        this.popSiteRadioServiceLinkRepository = popSiteRadioServiceLinkRepository;
    }

    @Override
    @Transactional
    public PopSiteRadioServiceLink addNewItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        return this.popSiteRadioServiceLinkRepository.save(popSiteRadioServiceLink);
    }

    @Override
    @Transactional
    public PopSiteRadioServiceLink updateItem(PopSiteRadioServiceLink popSiteRadioServiceLink) throws InvocationTargetException, IllegalAccessException {
        PopSiteRadioServiceLink exist = this.popSiteRadioServiceLinkRepository.findByIsDeletedIsNullAndRadioServiceLinkId(popSiteRadioServiceLink.getRadioServiceLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteRadioServiceLink);
        return this.popSiteRadioServiceLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSiteRadioServiceLink deleteItem(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        PopSiteRadioServiceLink exist = this.popSiteRadioServiceLinkRepository.findByIsDeletedIsNullAndRadioServiceLinkId(popSiteRadioServiceLink.getRadioServiceLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteRadioServiceLinkRepository.save(exist);
    }

    @Override
    public List<PopSiteRadioServiceLink> findAllItem() {
        return this.popSiteRadioServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteRadioServiceLink> findAllItem(Pageable pageable) {
        return this.popSiteRadioServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteRadioServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteRadioServiceLinkRepository.findAll(pageable);
    }

    @Override
    public PopSiteRadioServiceLink findOne(PopSiteRadioServiceLink popSiteRadioServiceLink) {
        return this.popSiteRadioServiceLinkRepository.findByIsDeletedIsNullAndRadioServiceLinkId(popSiteRadioServiceLink.getRadioServiceLinkId());
    }

    @Override
    public PopSiteRadioServiceLink findById(Long id) {
        return this.popSiteRadioServiceLinkRepository.findByIsDeletedIsNullAndRadioServiceLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteRadioServiceLinkRepository.existsByIsDeletedIsNullAndRadioServiceLinkId(id);
    }
}
