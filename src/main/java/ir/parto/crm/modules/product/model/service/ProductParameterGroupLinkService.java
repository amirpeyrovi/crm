package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.modules.product.model.repository.ProductParameterGroupLinkRepository;
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
public class ProductParameterGroupLinkService implements ServiceInterface<ProductParameterGroupLink> {
    private ProductParameterGroupLinkRepository productParameterGroupLinkRepository;

    @Autowired
    public ProductParameterGroupLinkService(ProductParameterGroupLinkRepository productParameterGroupLinkRepository) {
        this.productParameterGroupLinkRepository = productParameterGroupLinkRepository;
    }

    @Override
    @Transactional
    public ProductParameterGroupLink addNewItem(ProductParameterGroupLink productParameterGroupLink) {
        productParameterGroupLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productParameterGroupLinkRepository.save(productParameterGroupLink);
    }

    @Override
    @Transactional
    public ProductParameterGroupLink updateItem(ProductParameterGroupLink productParameterGroupLink) throws InvocationTargetException, IllegalAccessException {
        ProductParameterGroupLink exist = this.productParameterGroupLinkRepository.findByIsDeletedIsNullAndProductParameterGroupLinkId(productParameterGroupLink.getProductParameterGroupLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameterGroupLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.productParameterGroupLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductParameterGroupLink deleteItem(ProductParameterGroupLink productParameterGroupLink) {
        ProductParameterGroupLink exist = this.productParameterGroupLinkRepository.findByIsDeletedIsNullAndProductParameterGroupLinkId(productParameterGroupLink.getProductParameterGroupLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productParameterGroupLinkRepository.save(exist);
    }

    @Override
    public List<ProductParameterGroupLink> findAllItem() {
        return this.productParameterGroupLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ProductParameterGroupLink> findAllItem(Pageable pageable) {
        return this.productParameterGroupLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ProductParameterGroupLink> findAllItemWithDeleted(Pageable pageable) {
        return this.productParameterGroupLinkRepository.findAll(pageable);
    }

    @Override
    public ProductParameterGroupLink findOne(ProductParameterGroupLink productParameterGroupLink) {
        return this.productParameterGroupLinkRepository.findByIsDeletedIsNullAndProductParameterGroupLinkId(productParameterGroupLink.getProductParameterGroupLinkId());
    }

    @Override
    public ProductParameterGroupLink findById(Long id) {
        return this.productParameterGroupLinkRepository.findByIsDeletedIsNullAndProductParameterGroupLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterGroupLinkRepository.existsByIsDeletedIsNullAndProductParameterGroupLinkId(id);
    }
}
