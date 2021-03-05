package ir.parto.crm.modules.contract.model.service;

import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.modules.contract.model.repository.ContractTemplateRepository;
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
public class ContractTemplateService implements ServiceInterface<ContractTemplate> {
    private ContractTemplateRepository contractTemplateRepository;

    @Autowired
    public ContractTemplateService(ContractTemplateRepository contractTemplateRepository) {
        this.contractTemplateRepository = contractTemplateRepository;
    }

    @Override
    @Transactional
    public ContractTemplate addNewItem(ContractTemplate contractTemplate) {
        contractTemplate.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        contractTemplate.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractTemplateRepository.save(contractTemplate);
    }

    @Override
    @Transactional
    public ContractTemplate updateItem(ContractTemplate contractTemplate) throws InvocationTargetException, IllegalAccessException {
        ContractTemplate exist = this.contractTemplateRepository.findByIsDeletedIsNullAndContractTemplateId(contractTemplate.getContractTemplateId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,contractTemplate);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.contractTemplateRepository.save(exist);
    }

    @Override
    @Transactional
    public ContractTemplate deleteItem(ContractTemplate contractTemplate) {
        ContractTemplate exist = this.contractTemplateRepository.findByIsDeletedIsNullAndContractTemplateId(
                contractTemplate.getContractTemplateId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.contractTemplateRepository.save(exist);
    }

    @Override
    public List<ContractTemplate> findAllItem() {
        return this.contractTemplateRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ContractTemplate> findAllItem(Pageable pageable) {
        return this.contractTemplateRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ContractTemplate> findAllItemWithDeleted(Pageable pageable) {
        return this.contractTemplateRepository.findAll(pageable);
    }

    @Override
    public ContractTemplate findOne(ContractTemplate contractTemplate) {
        return this.contractTemplateRepository.findByIsDeletedIsNullAndContractTemplateId(contractTemplate.getContractTemplateId());
    }

    @Override
    public ContractTemplate findById(Long id) {
        return this.contractTemplateRepository.findByIsDeletedIsNullAndContractTemplateId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.contractTemplateRepository.existsByIsDeletedIsNullAndContractTemplateId(id);
    }
}
