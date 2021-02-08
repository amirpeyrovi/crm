package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.repository.ProductParameterRepository;
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
public class ProductParameterService implements ServiceInterface<ProductParameter> {
    private ProductParameterRepository productParameterRepository;

    @Autowired
    public ProductParameterService(ProductParameterRepository productParameterRepository) {
        this.productParameterRepository = productParameterRepository;
    }

    @Override
    @Transactional
    public ProductParameter addNewItem(ProductParameter productParameter) {
        productParameter.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        productParameter.setTitle(productParameter.getTitle().trim());
        return this.productParameterRepository.save(productParameter);
    }

    @Override
    @Transactional
    public ProductParameter updateItem(ProductParameter productParameter) throws InvocationTargetException, IllegalAccessException {
        ProductParameter exist = this.productParameterRepository.findByIsDeletedIsNullAndProductParameterId(productParameter.getProductParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameter);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        if (productParameter.getTitle() != null) exist.setTitle(productParameter.getTitle().trim());
        return this.productParameterRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductParameter deleteItem(ProductParameter productParameter) {
        ProductParameter exist = this.productParameterRepository.findByIsDeletedIsNullAndProductParameterId(productParameter.getProductParameterId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productParameterRepository.save(exist);
    }

    @Override
    public List<ProductParameter> findAllItem() {
        return this.productParameterRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductParameter> findAllItem(Pageable pageable) {
        return this.productParameterRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductParameter> findAllItemWithDeleted(Pageable pageable) {
        return this.productParameterRepository.findAll(pageable);
    }

    @Override
    public ProductParameter findOne(ProductParameter productParameter) {
        return this.productParameterRepository.findByIsDeletedIsNullAndProductParameterId(productParameter.getProductParameterId());
    }

    @Override
    public ProductParameter findById(Long id) {
        return this.productParameterRepository.findByIsDeletedIsNullAndProductParameterId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterRepository.existsByIsDeletedIsNullAndProductParameterId(id);
    }

    public Page<ProductParameter> findAllItemByParameterGroup(ProductParameterGroup productParameterGroup, Pageable pageable) {
        return this.productParameterRepository.findAllByIsDeletedIsNullAndProductParameterGroup(productParameterGroup, pageable);
    }
}
