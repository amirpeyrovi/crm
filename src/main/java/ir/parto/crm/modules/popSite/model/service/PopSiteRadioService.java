package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadio;
import ir.parto.crm.modules.popSite.model.repository.PopSiteRadioRepository;
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
public class PopSiteRadioService implements ServiceInterface<PopSiteRadio> {
    private PopSiteRadioRepository popSiteRadioRepository;

    @Autowired
    public PopSiteRadioService(PopSiteRadioRepository popSiteRadioRepository) {
        this.popSiteRadioRepository = popSiteRadioRepository;
    }

    @Override
    @Transactional
    public PopSiteRadio addNewItem(PopSiteRadio popSiteRadio) {
        return this.popSiteRadioRepository.save(popSiteRadio);
    }

    @Override
    @Transactional
    public PopSiteRadio updateItem(PopSiteRadio popSiteRadio) throws InvocationTargetException, IllegalAccessException {
        PopSiteRadio exist = this.popSiteRadioRepository.findByIsDeletedIsNullAndRadioId(popSiteRadio.getRadioId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteRadio);
        return this.popSiteRadioRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSiteRadio deleteItem(PopSiteRadio popSiteRadio) {
        PopSiteRadio exist = this.popSiteRadioRepository.findByIsDeletedIsNullAndRadioId(popSiteRadio.getRadioId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteRadioRepository.save(exist);
    }

    @Override
    public List<PopSiteRadio> findAllItem() {
        return this.popSiteRadioRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteRadio> findAllItem(Pageable pageable) {
        return this.popSiteRadioRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteRadio> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteRadioRepository.findAll(pageable);
    }

    @Override
    public PopSiteRadio findOne(PopSiteRadio popSiteRadio) {
        return this.popSiteRadioRepository.findByIsDeletedIsNullAndRadioId(popSiteRadio.getRadioId());
    }

    @Override
    public PopSiteRadio findById(Long id) {
        return this.popSiteRadioRepository.findByIsDeletedIsNullAndRadioId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteRadioRepository.existsByIsDeletedIsNullAndRadioId(id);
    }
}
