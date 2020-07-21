package ir.parto.crm.modules.contract.model.service;

import ir.parto.crm.modules.contract.model.entity.ContractGroup;
import ir.parto.crm.modules.contract.model.repository.ContractGroupRepository;
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
public class ContractGroupService implements ServiceInterface<ContractGroup> {
    private ContractGroupRepository contractGroupRepository;

    @Autowired
    public ContractGroupService(ContractGroupRepository contractGroupRepository) {
        this.contractGroupRepository = contractGroupRepository;
    }

    @Override
    @Transactional
    public ContractGroup addNewItem(ContractGroup contractGroup) {
        contractGroup.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractGroupRepository.save(contractGroup);
    }

    @Override
    @Transactional
    public ContractGroup updateItem(ContractGroup contractGroup) throws InvocationTargetException, IllegalAccessException {
        ContractGroup exist = this.contractGroupRepository.findByIsDeletedIsNullAndContractGroupId(contractGroup.getContractGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,contractGroup);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ContractGroup deleteItem(ContractGroup contractGroup) {
        ContractGroup exist = this.contractGroupRepository.
                findByIsDeletedIsNullAndContractGroupId(contractGroup.getContractGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.contractGroupRepository.save(exist);
    }

    @Override
    public List<ContractGroup> findAllItem() {
        return this.contractGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ContractGroup> findAllItem(Pageable pageable) {
        return this.contractGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ContractGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.contractGroupRepository.findAll(pageable);
    }

    @Override
    public ContractGroup findOne(ContractGroup contractGroup) {
        return this.contractGroupRepository.findByIsDeletedIsNullAndContractGroupId(contractGroup.getContractGroupId());
    }

    @Override
    public ContractGroup findById(Long id) {
        return this.contractGroupRepository.findByIsDeletedIsNullAndContractGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.contractGroupRepository.existsByIsDeletedIsNullAndContractGroupId(id);
    }
}
