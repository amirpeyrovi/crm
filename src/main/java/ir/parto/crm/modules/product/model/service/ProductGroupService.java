package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductGroupRepository;
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
public class ProductGroupService implements ServiceInterface<ProductGroup> {
    private ProductGroupRepository productGroupRepository;

    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @Override
    @Transactional
    public ProductGroup addNewItem(ProductGroup productGroup) {
        return this.productGroupRepository.save(productGroup);
    }

    @Override
    @Transactional
    public ProductGroup updateItem(ProductGroup productGroup) throws InvocationTargetException, IllegalAccessException {
        ProductGroup exist = this.productGroupRepository.getOne(productGroup.getProductGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productGroup);
        return this.productGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ProductGroup> deleteItem(ProductGroup productGroup) {
        this.productGroupRepository.delete(productGroup);
        return this.productGroupRepository.findAll();
    }

    @Override
    public List<ProductGroup> findAllItem() {
        return this.productGroupRepository.findAll();
    }

    @Override
    public Page<ProductGroup> findAllItem(Pageable pageable) {
        return this.productGroupRepository.findAll(pageable);
    }

    @Override
    public ProductGroup findOne(ProductGroup productGroup) {
        return this.productGroupRepository.getOne(productGroup.getProductGroupId());
    }

    @Override
    public ProductGroup findById(Long id) {
        if(this.productGroupRepository.existsById(id)){
            return this.productGroupRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productGroupRepository.existsById(id);
    }
}
