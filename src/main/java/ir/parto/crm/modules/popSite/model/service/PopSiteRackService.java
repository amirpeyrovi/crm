package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRack;
import ir.parto.crm.modules.popSite.model.repository.PopSiteRackRepository;
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
public class PopSiteRackService implements ServiceInterface<PopSiteRack> {
    private PopSiteRackRepository popSiteRackRepository;

    @Autowired
    public PopSiteRackService(PopSiteRackRepository popSiteRackRepository) {
        this.popSiteRackRepository = popSiteRackRepository;
    }

    @Override
    @Transactional
    public PopSiteRack addNewItem(PopSiteRack popSiteRack) {
        return this.popSiteRackRepository.save(popSiteRack);
    }

    @Override
    @Transactional
    public PopSiteRack updateItem(PopSiteRack popSiteRack) throws InvocationTargetException, IllegalAccessException {
        PopSiteRack exit = this.popSiteRackRepository.findByIsDeletedIsNullAndRackId(popSiteRack.getRackId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exit, popSiteRack);
        return this.popSiteRackRepository.save(exit);
    }

    @Override
    @Transactional
    public PopSiteRack deleteItem(PopSiteRack popSiteRack) {
        PopSiteRack exist = this.popSiteRackRepository.findByIsDeletedIsNullAndRackId(popSiteRack.getRackId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteRackRepository.save(exist);
    }

    @Override
    public List<PopSiteRack> findAllItem() {
        return this.popSiteRackRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteRack> findAllItem(Pageable pageable) {
        return this.popSiteRackRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteRack> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteRackRepository.findAll(pageable);
    }

    @Override
    public PopSiteRack findOne(PopSiteRack popSiteRack) {
        return this.popSiteRackRepository.findByIsDeletedIsNullAndRackId(popSiteRack.getRackId());
    }

    @Override
    public PopSiteRack findById(Long id) {
        return this.popSiteRackRepository.findByIsDeletedIsNullAndRackId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteRackRepository.existsByIsDeletedIsNullAndRackId(id);
    }
}
