package ir.parto.crm.modules.reseller.model.service;

import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.repository.ResellerRepository;
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
public class ResellerService implements ServiceInterface<Reseller> {
    private ResellerRepository resellerRepository;

    @Autowired
    public ResellerService(ResellerRepository resellerRepository) {
        this.resellerRepository = resellerRepository;
    }

    @Override
    @Transactional
    public Reseller addNewItem(Reseller reseller) {
        reseller.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        reseller.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerRepository.save(reseller);
    }

    @Override
    @Transactional
    public Reseller updateItem(Reseller reseller) throws InvocationTargetException, IllegalAccessException {
        Reseller exist = this.resellerRepository.findByIsDeletedIsNullAndResellerId(reseller.getResellerId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, reseller);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerRepository.save(exist);
    }

    @Override
    @Transactional
    public Reseller deleteItem(Reseller reseller) {
        Reseller exist = this.resellerRepository.findByIsDeletedIsNullAndResellerId(reseller.getResellerId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.resellerRepository.save(exist);
    }

    @Override
    public List<Reseller> findAllItem() {
        return this.resellerRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Reseller> findAllItem(Pageable pageable) {
        return this.resellerRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Reseller> findAllItemWithDeleted(Pageable pageable) {
        return this.resellerRepository.findAll(pageable);
    }

    @Override
    public Reseller findOne(Reseller reseller) {
        return this.resellerRepository.findByIsDeletedIsNullAndResellerId(reseller.getResellerId());
    }

    @Override
    public Reseller findById(Long id) {
        return this.resellerRepository.findByIsDeletedIsNullAndResellerId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.resellerRepository.existsByIsDeletedIsNullAndResellerId(id);
    }
}
