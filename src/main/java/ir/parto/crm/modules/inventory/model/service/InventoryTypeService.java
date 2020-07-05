package ir.parto.crm.modules.inventory.model.service;

import ir.parto.crm.modules.inventory.model.entity.InventoryType;
import ir.parto.crm.modules.inventory.model.repository.InventoryTypeRepository;
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
public class InventoryTypeService implements ServiceInterface<InventoryType> {
    private InventoryTypeRepository inventoryTypeRepository;

    @Autowired
    public InventoryTypeService(InventoryTypeRepository inventoryTypeRepository) {
        this.inventoryTypeRepository = inventoryTypeRepository;
    }

    @Override
    @Transactional
    public InventoryType addNewItem(InventoryType inventoryType) {
        inventoryType.setCreatedBy("");
        return this.inventoryTypeRepository.save(inventoryType);
    }

    @Override
    @Transactional
    public InventoryType updateItem(InventoryType inventoryType) throws InvocationTargetException, IllegalAccessException {
        InventoryType exist = this.inventoryTypeRepository.findByIsDeletedIsNullAndInventoryTypeId(inventoryType.getInventoryTypeId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, inventoryType);
        exist.setUpdatedBy("");
        return this.inventoryTypeRepository.save(exist);
    }

    @Override
    @Transactional
    public InventoryType deleteItem(InventoryType inventoryType) {
        InventoryType exist = this.inventoryTypeRepository.findByIsDeletedIsNullAndInventoryTypeId(inventoryType.getInventoryTypeId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.inventoryTypeRepository.save(exist);
    }

    @Override
    public List<InventoryType> findAllItem() {
        return this.inventoryTypeRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<InventoryType> findAllItem(Pageable pageable) {
        return this.inventoryTypeRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<InventoryType> findAllItemWithDeleted(Pageable pageable) {
        return this.inventoryTypeRepository.findAll(pageable);
    }

    @Override
    public InventoryType findOne(InventoryType inventoryType) {
        return this.inventoryTypeRepository.findByIsDeletedIsNullAndInventoryTypeId(inventoryType.getInventoryTypeId());
    }

    @Override
    public InventoryType findById(Long id) {
        return this.inventoryTypeRepository.findByIsDeletedIsNullAndInventoryTypeId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.inventoryTypeRepository.existsByIsDeletedIsNullAndInventoryTypeId(id);
    }
}
