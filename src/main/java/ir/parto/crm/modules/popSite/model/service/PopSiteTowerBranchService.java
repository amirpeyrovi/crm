package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTowerBranch;
import ir.parto.crm.modules.popSite.model.repository.PopSiteTowerBranchRepository;
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
public class PopSiteTowerBranchService implements ServiceInterface<PopSiteTowerBranch> {
    private PopSiteTowerBranchRepository popSiteTowerBranchRepository;

    @Autowired
    public PopSiteTowerBranchService(PopSiteTowerBranchRepository popSiteTowerBranchRepository) {
        this.popSiteTowerBranchRepository = popSiteTowerBranchRepository;
    }

    @Override
    @Transactional
    public PopSiteTowerBranch addNewItem(PopSiteTowerBranch popSiteTowerBranch) {
        popSiteTowerBranch.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSiteTowerBranchRepository.save(popSiteTowerBranch);
    }

    @Override
    @Transactional
    public PopSiteTowerBranch updateItem(PopSiteTowerBranch popSiteTowerBranch) throws InvocationTargetException, IllegalAccessException {
        PopSiteTowerBranch exist = this.popSiteTowerBranchRepository.findByIsDeletedIsNullAndBranchId(popSiteTowerBranch.getBranchId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteTowerBranch);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.popSiteTowerBranchRepository.save(exist);
    }

    @Override
    @Transactional
    public PopSiteTowerBranch deleteItem(PopSiteTowerBranch popSiteTowerBranch) {
        PopSiteTowerBranch exist = this.popSiteTowerBranchRepository.findByIsDeletedIsNullAndBranchId(popSiteTowerBranch.getBranchId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteTowerBranchRepository.save(exist);
    }

    @Override
    public List<PopSiteTowerBranch> findAllItem() {
        return this.popSiteTowerBranchRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteTowerBranch> findAllItem(Pageable pageable) {
        return this.popSiteTowerBranchRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteTowerBranch> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteTowerBranchRepository.findAll(pageable);
    }

    @Override
    public PopSiteTowerBranch findOne(PopSiteTowerBranch popSiteTowerBranch) {
        return this.popSiteTowerBranchRepository.findByIsDeletedIsNullAndBranchId(popSiteTowerBranch.getBranchId());
    }

    @Override
    public PopSiteTowerBranch findById(Long id) {
        return this.popSiteTowerBranchRepository.findByIsDeletedIsNullAndBranchId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteTowerBranchRepository.existsByIsDeletedIsNullAndBranchId(id);
    }
}
