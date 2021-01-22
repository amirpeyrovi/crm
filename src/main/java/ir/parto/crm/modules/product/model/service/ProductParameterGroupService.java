package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.repository.ProductParameterGroupRepository;
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
public class ProductParameterGroupService implements ServiceInterface<ProductParameterGroup> {
    private ProductParameterGroupRepository productParameterGroupRepository;

    @Autowired
    public ProductParameterGroupService(ProductParameterGroupRepository productParameterGroupRepository) {

        this.productParameterGroupRepository = productParameterGroupRepository;
    }

    @Override
    @Transactional
    public ProductParameterGroup addNewItem(ProductParameterGroup productParameterGroup) {
        productParameterGroup.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        productParameterGroup.setTitle(productParameterGroup.getTitle().trim());
        return this.productParameterGroupRepository.save(productParameterGroup);
    }

    @Override
    @Transactional
    public ProductParameterGroup updateItem(ProductParameterGroup productParameterGroup) throws InvocationTargetException, IllegalAccessException {
        ProductParameterGroup exist = this.productParameterGroupRepository.findByIsDeletedIsNullAndProductParameterGroupId(productParameterGroup.getProductParameterGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameterGroup);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        if (productParameterGroup.getTitle() != null) exist.setTitle(productParameterGroup.getTitle().trim());
        return this.productParameterGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductParameterGroup deleteItem(ProductParameterGroup productParameterGroup) {
        ProductParameterGroup exist = this.productParameterGroupRepository.findByIsDeletedIsNullAndProductParameterGroupId(productParameterGroup.getProductParameterGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productParameterGroupRepository.save(exist);
    }

    @Override
    public List<ProductParameterGroup> findAllItem() {
        return this.productParameterGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductParameterGroup> findAllItem(Pageable pageable) {
        return this.productParameterGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductParameterGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.productParameterGroupRepository.findAll(pageable);
    }

    @Override
    public ProductParameterGroup findOne(ProductParameterGroup productParameterGroup) {
        return this.productParameterGroupRepository.findByIsDeletedIsNullAndProductParameterGroupId(productParameterGroup.getProductParameterGroupId());
    }

    @Override
    public ProductParameterGroup findById(Long id) {
        return this.productParameterGroupRepository.findByIsDeletedIsNullAndProductParameterGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterGroupRepository.existsByIsDeletedIsNullAndProductParameterGroupId(id);
    }
}
