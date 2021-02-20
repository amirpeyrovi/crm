package ir.parto.crm.modules.contract.model.service;

import ir.parto.crm.modules.contract.model.entity.ContractProductGroupLink;
import ir.parto.crm.modules.contract.model.repository.ContractProductGroupLinkRepository;
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
public class ContractProductGroupLinkService implements ServiceInterface<ContractProductGroupLink> {
    private ContractProductGroupLinkRepository contractProductGroupLinkRepository;

    @Autowired
    public ContractProductGroupLinkService(ContractProductGroupLinkRepository contractProductGroupLinkRepository) {
        this.contractProductGroupLinkRepository = contractProductGroupLinkRepository;
    }

    @Override
    @Transactional
    public ContractProductGroupLink addNewItem(ContractProductGroupLink contractProductGroupLink) {
        contractProductGroupLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        contractProductGroupLink.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractProductGroupLinkRepository.save(contractProductGroupLink);
    }

    @Override
    @Transactional
    public ContractProductGroupLink updateItem(ContractProductGroupLink contractProductGroupLink) throws InvocationTargetException, IllegalAccessException {
        ContractProductGroupLink exist = this.contractProductGroupLinkRepository.
                findByIsDeletedIsNullAndContractProductGroupLinkId(contractProductGroupLink.getContractProductGroupLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, contractProductGroupLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractProductGroupLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ContractProductGroupLink deleteItem(ContractProductGroupLink contractProductGroupLink) {
        ContractProductGroupLink exist = this.contractProductGroupLinkRepository.
                findByIsDeletedIsNullAndContractProductGroupLinkId(contractProductGroupLink.getContractProductGroupLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.contractProductGroupLinkRepository.save(exist);
    }

    @Override
    public List<ContractProductGroupLink> findAllItem() {
        return this.contractProductGroupLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ContractProductGroupLink> findAllItem(Pageable pageable) {
        return this.contractProductGroupLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ContractProductGroupLink> findAllItemWithDeleted(Pageable pageable) {
        return this.contractProductGroupLinkRepository.findAll(pageable);
    }

    @Override
    public ContractProductGroupLink findOne(ContractProductGroupLink contractProductGroupLink) {
        return this.contractProductGroupLinkRepository.findByIsDeletedIsNullAndContractProductGroupLinkId(
                contractProductGroupLink.getContractProductGroupLinkId()
        );
    }

    @Override
    public ContractProductGroupLink findById(Long id) {
        return this.contractProductGroupLinkRepository.findByIsDeletedIsNullAndContractProductGroupLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.contractProductGroupLinkRepository.existsByIsDeletedIsNullAndContractProductGroupLinkId(id);
    }
}
