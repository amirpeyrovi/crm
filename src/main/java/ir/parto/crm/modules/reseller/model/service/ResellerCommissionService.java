package ir.parto.crm.modules.reseller.model.service;

import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.entity.ResellerCommission;
import ir.parto.crm.modules.reseller.model.repository.ResellerCommissionRepository;
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
public class ResellerCommissionService implements ServiceInterface<ResellerCommission> {
    private ResellerCommissionRepository resellerCommissionRepository;

    @Autowired
    public ResellerCommissionService(ResellerCommissionRepository resellerCommissionRepository) {
        this.resellerCommissionRepository = resellerCommissionRepository;
    }

    @Override
    @Transactional
    public ResellerCommission addNewItem(ResellerCommission resellerCommission) {
        resellerCommission.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerCommissionRepository.save(resellerCommission);
    }

    @Override
    @Transactional
    public ResellerCommission updateItem(ResellerCommission resellerCommission) throws InvocationTargetException, IllegalAccessException {
        ResellerCommission exist = this.resellerCommissionRepository.findByIsDeletedIsNullAndResellerCommissionId(resellerCommission.getResellerCommissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, resellerCommission);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerCommissionRepository.save(exist);
    }

    @Override
    @Transactional
    public ResellerCommission deleteItem(ResellerCommission resellerCommission) {
        ResellerCommission exist = this.resellerCommissionRepository.findByIsDeletedIsNullAndResellerCommissionId(resellerCommission.getResellerCommissionId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.resellerCommissionRepository.save(exist);
    }

    @Override
    public List<ResellerCommission> findAllItem() {
        return this.resellerCommissionRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ResellerCommission> findAllItem(Pageable pageable) {
        return this.resellerCommissionRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ResellerCommission> findAllItemWithDeleted(Pageable pageable) {
        return this.resellerCommissionRepository.findAll(pageable);
    }

    @Override
    public ResellerCommission findOne(ResellerCommission resellerCommission) {
        return this.resellerCommissionRepository.findByIsDeletedIsNullAndResellerCommissionId(resellerCommission.getResellerCommissionId());
    }

    @Override
    public ResellerCommission findById(Long id) {
        return this.resellerCommissionRepository.findByIsDeletedIsNullAndResellerCommissionId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.resellerCommissionRepository.existsByIsDeletedIsNullAndResellerCommissionId(id);
    }

    public Page<ResellerCommission> findAllItemByReseller(Reseller reseller, Pageable pageable) {
        return this.resellerCommissionRepository.findAllByIsDeletedIsNullAndReseller(reseller, pageable);
    }

    public Page<ResellerCommission> findAllItemByResellerAndProductGroup(Reseller reseller, ProductGroup productGroup, Pageable pageable) {
        return this.resellerCommissionRepository.findAllByIsDeletedIsNullAndResellerAndProductGroup(reseller, productGroup, pageable);
    }
}
