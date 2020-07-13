package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.modules.product.model.repository.ProductServerParameterValueRepository;
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
public class ProductServerParameterValueService implements ServiceInterface<ProductServerParameterValue> {
    private ProductServerParameterValueRepository productServerParameterValueRepository;

    @Autowired
    public ProductServerParameterValueService(ProductServerParameterValueRepository productServerParameterValueRepository) {
        this.productServerParameterValueRepository = productServerParameterValueRepository;
    }

    @Override
    @Transactional
    public ProductServerParameterValue addNewItem(ProductServerParameterValue productServerParameterValue) {
        productServerParameterValue.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productServerParameterValueRepository.save(productServerParameterValue);
    }

    @Override
    @Transactional
    public ProductServerParameterValue updateItem(ProductServerParameterValue productServerParameterValue) throws InvocationTargetException, IllegalAccessException {
        ProductServerParameterValue exist = this.productServerParameterValueRepository.findByIsDeletedIsNullAndProductServerParameterId(productServerParameterValue.getProductServerParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productServerParameterValue);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productServerParameterValueRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductServerParameterValue deleteItem(ProductServerParameterValue productServerParameterValue) {
        ProductServerParameterValue exist = this.productServerParameterValueRepository.findByIsDeletedIsNullAndProductServerParameterId(productServerParameterValue.getProductServerParameterId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productServerParameterValueRepository.save(exist);
    }

    @Override
    public List<ProductServerParameterValue> findAllItem() {
        return this.productServerParameterValueRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductServerParameterValue> findAllItem(Pageable pageable) {
        return this.productServerParameterValueRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductServerParameterValue> findAllItemWithDeleted(Pageable pageable) {
        return this.productServerParameterValueRepository.findAll(pageable);
    }

    @Override
    public ProductServerParameterValue findOne(ProductServerParameterValue productServerParameterValue) {
        return this.productServerParameterValueRepository.findByIsDeletedIsNullAndProductServerParameterId(productServerParameterValue.getProductServerParameterId());
    }

    @Override
    public ProductServerParameterValue findById(Long id) {
        return this.productServerParameterValueRepository.findByIsDeletedIsNullAndProductServerParameterId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productServerParameterValueRepository.existsByIsDeletedIsNullAndProductServerParameterId(id);
    }

    public Page<ProductServerParameterValue> findAllItemByProduct(Product product, Pageable pageable) {
        return this.productServerParameterValueRepository.findAllByIsDeletedIsNullAndProduct(product, pageable);
    }

    public Page<ProductServerParameterValue> findAllItemByProductAddon(ProductAddon productAddon, Pageable pageable) {
        return this.productServerParameterValueRepository.findAllByIsDeletedIsNullAndProductAddon(productAddon, pageable);
    }
}
