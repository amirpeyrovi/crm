package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductAddonRepository;
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
public class ProductAddonService implements ServiceInterface<ProductAddon> {
    private ProductAddonRepository productAddonRepository;

    @Autowired
    public ProductAddonService(ProductAddonRepository productAddonRepository) {
        this.productAddonRepository = productAddonRepository;
    }

    @Override
    @Transactional
    public ProductAddon addNewItem(ProductAddon productAddon) {
        productAddon.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productAddonRepository.save(productAddon);
    }

    @Override
    @Transactional
    public ProductAddon updateItem(ProductAddon productAddon) throws InvocationTargetException, IllegalAccessException {
        ProductAddon exist = this.productAddonRepository.findByIsDeletedIsNullAndProductAddonId(productAddon.getProductAddonId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productAddon);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productAddonRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductAddon deleteItem(ProductAddon productAddon) {
        ProductAddon exist = this.productAddonRepository.findByIsDeletedIsNullAndProductAddonId(productAddon.getProductAddonId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productAddonRepository.save(exist);
    }

    @Override
    public List<ProductAddon> findAllItem() {
        return this.productAddonRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductAddon> findAllItem(Pageable pageable) {
        return this.productAddonRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductAddon> findAllItemWithDeleted(Pageable pageable) {
        return this.productAddonRepository.findAll(pageable);
    }

    @Override
    public ProductAddon findOne(ProductAddon productAddon) {
        return this.productAddonRepository.findByIsDeletedIsNullAndProductAddonId(productAddon.getProductAddonId());
    }

    @Override
    public ProductAddon findById(Long id) {
        return this.productAddonRepository.findByIsDeletedIsNullAndProductAddonId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productAddonRepository.existsByIsDeletedIsNullAndProductAddonId(id);
    }

    public ProductAddon findByTitleAndProductGroup(String title, ProductGroup productGroup) {
        return this.productAddonRepository.findByTitleAndProductGroup(title,productGroup);
    }
}
