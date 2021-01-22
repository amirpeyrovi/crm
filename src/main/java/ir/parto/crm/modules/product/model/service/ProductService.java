package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductRepository;
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
public class ProductService implements ServiceInterface<Product> {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addNewItem(Product product) {
        product.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        product.setTitle(product.getTitle().trim());
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateItem(Product product) throws InvocationTargetException, IllegalAccessException {
        Product exist = this.productRepository.findByIsDeletedIsNullAndProductId(product.getProductId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, product);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        if (product.getTitle() != null) exist.setTitle(product.getTitle().trim());
        return this.productRepository.save(exist);
    }

    @Override
    @Transactional
    public Product deleteItem(Product product) {
        Product exist = this.productRepository.findByIsDeletedIsNullAndProductId(product.getProductId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.productRepository.save(exist);
    }

    @Override
    public List<Product> findAllItem() {
        return this.productRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Product> findAllItem(Pageable pageable) {
        return this.productRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Product> findAllItemWithDeleted(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Product findOne(Product product) {
        return this.productRepository.findByIsDeletedIsNullAndProductId(product.getProductId());
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findByIsDeletedIsNullAndProductId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productRepository.existsByIsDeletedIsNullAndProductId(id);
    }

    public Product findByTitleAndProductGroup(String title, ProductGroup productGroup) {
        return this.productRepository.findByTitleAndProductGroup(title, productGroup);
    }

}
