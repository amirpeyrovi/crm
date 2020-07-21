package ir.parto.crm.modules.contract.model.service;

import ir.parto.crm.modules.contract.model.entity.ContractServiceLink;
import ir.parto.crm.modules.contract.model.repository.ContractServiceLinkRepository;
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
public class ContractServiceLinkService implements ServiceInterface<ContractServiceLink> {
    private ContractServiceLinkRepository contractServiceLinkRepository;

    @Autowired
    public ContractServiceLinkService(ContractServiceLinkRepository contractServiceLinkRepository) {
        this.contractServiceLinkRepository = contractServiceLinkRepository;
    }

    @Override
    @Transactional
    public ContractServiceLink addNewItem(ContractServiceLink contractServiceLink) {
        contractServiceLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractServiceLinkRepository.save(contractServiceLink);
    }

    @Override
    @Transactional
    public ContractServiceLink updateItem(ContractServiceLink contractServiceLink) throws InvocationTargetException, IllegalAccessException {
        ContractServiceLink exist = this.contractServiceLinkRepository.findByIsDeletedIsNullAndContractServiceLinkId(
                contractServiceLink.getContractServiceLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, contractServiceLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractServiceLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ContractServiceLink deleteItem(ContractServiceLink contractServiceLink) {
        ContractServiceLink exist = this.contractServiceLinkRepository.
                findByIsDeletedIsNullAndContractServiceLinkId(contractServiceLink.getContractServiceLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.contractServiceLinkRepository.save(exist);
    }

    @Override
    public List<ContractServiceLink> findAllItem() {
        return this.contractServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ContractServiceLink> findAllItem(Pageable pageable) {
        return this.contractServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ContractServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.contractServiceLinkRepository.findAll(pageable);
    }

    @Override
    public ContractServiceLink findOne(ContractServiceLink contractServiceLink) {
        return this.contractServiceLinkRepository.findByIsDeletedIsNullAndContractServiceLinkId(contractServiceLink.getContractServiceLinkId());
    }

    @Override
    public ContractServiceLink findById(Long id) {
        return this.contractServiceLinkRepository.findByIsDeletedIsNullAndContractServiceLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.contractServiceLinkRepository.existsByIsDeletedIsNullAndContractServiceLinkId(id);
    }
}
