package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductGroupRepository;
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
public class ProductGroupService implements ServiceInterface<ProductGroup> {
    private ProductGroupRepository productGroupRepository;

    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @Override
    @Transactional
    public ProductGroup addNewItem(ProductGroup productGroup) {
        productGroup.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productGroupRepository.save(productGroup);
    }

    @Override
    @Transactional
    public ProductGroup updateItem(ProductGroup productGroup) throws InvocationTargetException, IllegalAccessException {
        ProductGroup exist = this.productGroupRepository.findByIsDeletedIsNullAndProductGroupId(productGroup.getProductGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productGroup);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductGroup deleteItem(ProductGroup productGroup) {
        ProductGroup exist = this.productGroupRepository.findByIsDeletedIsNullAndProductGroupId(productGroup.getProductGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productGroupRepository.save(exist);
    }

    @Override
    public List<ProductGroup> findAllItem() {
        return this.productGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductGroup> findAllItem(Pageable pageable) {
        return this.productGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.productGroupRepository.findAll(pageable);
    }

    @Override
    public ProductGroup findOne(ProductGroup productGroup) {
        return this.productGroupRepository.findByIsDeletedIsNullAndProductGroupId(productGroup.getProductGroupId());
    }

    @Override
    public ProductGroup findById(Long id) {
        return this.productGroupRepository.findByIsDeletedIsNullAndProductGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productGroupRepository.existsByIsDeletedIsNullAndProductGroupId(id);
    }
}
