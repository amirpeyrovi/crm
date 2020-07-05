package ir.parto.crm.modules.inventory.model.service;

import ir.parto.crm.modules.inventory.model.entity.InventoryGroup;
import ir.parto.crm.modules.inventory.model.repository.InventoryGroupRepository;
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
public class InventoryGroupService implements ServiceInterface<InventoryGroup> {
    private InventoryGroupRepository inventoryGroupRepository;

    @Autowired
    public InventoryGroupService(InventoryGroupRepository inventoryGroupRepository) {
        this.inventoryGroupRepository = inventoryGroupRepository;
    }

    @Override
    @Transactional
    public InventoryGroup addNewItem(InventoryGroup inventoryGroup) {
        inventoryGroup.setCreatedBy("");
        return this.inventoryGroupRepository.save(inventoryGroup);
    }

    @Override
    @Transactional
    public InventoryGroup updateItem(InventoryGroup inventoryGroup) throws InvocationTargetException, IllegalAccessException {
        InventoryGroup exist = this.inventoryGroupRepository.findByIsDeletedIsNullAndInventoryGroupId(inventoryGroup.getInventoryGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, inventoryGroup);
        exist.setUpdatedBy("");
        return this.inventoryGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public InventoryGroup deleteItem(InventoryGroup inventoryGroup) {
        InventoryGroup exist = this.inventoryGroupRepository.findByIsDeletedIsNullAndInventoryGroupId(inventoryGroup.getInventoryGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.inventoryGroupRepository.save(exist);
    }

    @Override
    public List<InventoryGroup> findAllItem() {
        return this.inventoryGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<InventoryGroup> findAllItem(Pageable pageable) {
        return this.inventoryGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<InventoryGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.inventoryGroupRepository.findAll(pageable);
    }

    @Override
    public InventoryGroup findOne(InventoryGroup inventoryGroup) {
        return this.inventoryGroupRepository.findByIsDeletedIsNullAndInventoryGroupId(inventoryGroup.getInventoryGroupId());
    }

    @Override
    public InventoryGroup findById(Long id) {
        return this.inventoryGroupRepository.findByIsDeletedIsNullAndInventoryGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.inventoryGroupRepository.existsByIsDeletedIsNullAndInventoryGroupId(id);
    }
}
