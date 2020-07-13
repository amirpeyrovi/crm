package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.repository.ProductCyclePriceRepository;
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
public class ProductCyclePriceService implements ServiceInterface<ProductCyclePrice> {
    private ProductCyclePriceRepository productCyclePriceRepository;

    @Autowired
    public ProductCyclePriceService(ProductCyclePriceRepository productCyclePriceRepository) {
        this.productCyclePriceRepository = productCyclePriceRepository;
    }

    @Override
    @Transactional
    public ProductCyclePrice addNewItem(ProductCyclePrice productCyclePrice) {
        productCyclePrice.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productCyclePriceRepository.save(productCyclePrice);
    }

    @Override
    @Transactional
    public ProductCyclePrice updateItem(ProductCyclePrice productCyclePrice) throws InvocationTargetException, IllegalAccessException {
        ProductCyclePrice exist = this.productCyclePriceRepository.findByIsDeletedIsNullAndProductCyclePriceId(productCyclePrice.getProductCyclePriceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productCyclePrice);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productCyclePriceRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductCyclePrice deleteItem(ProductCyclePrice productCyclePrice) {
        ProductCyclePrice exist = this.productCyclePriceRepository.findByIsDeletedIsNullAndProductCyclePriceId(productCyclePrice.getProductCyclePriceId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productCyclePriceRepository.save(exist);
    }

    @Override
    public List<ProductCyclePrice> findAllItem() {
        return this.productCyclePriceRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductCyclePrice> findAllItem(Pageable pageable) {
        return this.productCyclePriceRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductCyclePrice> findAllItemWithDeleted(Pageable pageable) {
        return this.productCyclePriceRepository.findAll(pageable);
    }

    @Override
    public ProductCyclePrice findOne(ProductCyclePrice productCyclePrice) {
        return this.productCyclePriceRepository.findByIsDeletedIsNullAndProductCyclePriceId(productCyclePrice.getProductCyclePriceId());
    }

    @Override
    public ProductCyclePrice findById(Long id) {
        return this.productCyclePriceRepository.findByIsDeletedIsNullAndProductCyclePriceId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productCyclePriceRepository.existsByIsDeletedIsNullAndProductCyclePriceId(id);
    }

    public Page<ProductCyclePrice> findAllItemByProduct(Product product, Pageable pageable){
        return this.productCyclePriceRepository.findAllByIsDeletedIsNullAndProduct(product, pageable);
    }

    public Page<ProductCyclePrice> findAllItemByProductAddon(ProductAddon productAddon, Pageable pageable){
        return this.productCyclePriceRepository.findAllByIsDeletedIsNullAndProductAddon(productAddon, pageable);
    }
}
