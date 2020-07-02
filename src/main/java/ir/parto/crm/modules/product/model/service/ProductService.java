package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateItem(Product product) throws InvocationTargetException, IllegalAccessException {
        Product exist = this.productRepository.getOne(product.getProductId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, product);
        return this.productRepository.save(exist);
    }

    @Override
    @Transactional
    public Product deleteItem(Product product) {
        this.productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> findAllItem() {
        return this.productRepository.findAll();
    }

    @Override
    public Page<Product> findAllItem(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Product findOne(Product product) {
        return this.productRepository.getOne(product.getProductId());
    }

    @Override
    public Product findById(Long id) {
        if(this.productRepository.existsById(id)){
            return this.productRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productRepository.existsById(id);
    }

    public Product findByTitleAndProductGroup(String title, ProductGroup productGroup) {
        if(this.productRepository.findByTitleAndProductGroup(title,productGroup) != null){
            return this.productRepository.findByTitleAndProductGroup(title,productGroup);
        }
        return null;
    }

}
