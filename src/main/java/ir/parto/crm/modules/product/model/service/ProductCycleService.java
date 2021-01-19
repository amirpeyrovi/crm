package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.repository.ProductCycleRepository;
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
public class ProductCycleService implements ServiceInterface<ProductCycle> {
    private ProductCycleRepository productCycleRepository;

    @Autowired
    public ProductCycleService(ProductCycleRepository productCycleRepository) {
        this.productCycleRepository = productCycleRepository;
    }

    @Override
    @Transactional
    public ProductCycle addNewItem(ProductCycle productCycle) {
        productCycle.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        productCycle.setTitle(productCycle.getTitle().trim());
        return this.productCycleRepository.save(productCycle);
    }

    @Override
    @Transactional
    public ProductCycle updateItem(ProductCycle productCycle) throws InvocationTargetException, IllegalAccessException {
        ProductCycle exist = this.productCycleRepository.findByIsDeletedIsNullAndProductCycleId(productCycle.getProductCycleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productCycle);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setTitle(productCycle.getTitle().trim());
        return this.productCycleRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductCycle deleteItem(ProductCycle productCycle) {
        ProductCycle exist = this.productCycleRepository.findByIsDeletedIsNullAndProductCycleId(productCycle.getProductCycleId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productCycleRepository.save(exist);
    }

    @Override
    public List<ProductCycle> findAllItem() {
        return this.productCycleRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductCycle> findAllItem(Pageable pageable) {
        return this.productCycleRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductCycle> findAllItemWithDeleted(Pageable pageable) {
        return this.productCycleRepository.findAll(pageable);
    }

    @Override
    public ProductCycle findOne(ProductCycle productCycle) {
        return this.productCycleRepository.findByIsDeletedIsNullAndProductCycleId(productCycle.getProductCycleId());
    }

    @Override
    public ProductCycle findById(Long id) {
        return this.productCycleRepository.findByIsDeletedIsNullAndProductCycleId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productCycleRepository.existsByIsDeletedIsNullAndProductCycleId(id);
    }
}
