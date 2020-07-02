package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.repository.ProductParameterGroupRepository;
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
public class ProductParameterGroupService implements ServiceInterface<ProductParameterGroup> {
    private ProductParameterGroupRepository productParameterGroupRepository;

    @Autowired
    public ProductParameterGroupService(ProductParameterGroupRepository productParameterGroupRepository) {
        this.productParameterGroupRepository = productParameterGroupRepository;
    }

    @Override
    @Transactional
    public ProductParameterGroup addNewItem(ProductParameterGroup productParameterGroup) {
        return this.productParameterGroupRepository.save(productParameterGroup);
    }

    @Override
    @Transactional
    public ProductParameterGroup updateItem(ProductParameterGroup productParameterGroup) throws InvocationTargetException, IllegalAccessException {
        ProductParameterGroup exist = this.productParameterGroupRepository.getOne(productParameterGroup.getProductParameterGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameterGroup);
        return this.productParameterGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductParameterGroup deleteItem(ProductParameterGroup productParameterGroup) {
        this.productParameterGroupRepository.delete(productParameterGroup);
        return productParameterGroup;
    }

    @Override
    public List<ProductParameterGroup> findAllItem() {
        return this.productParameterGroupRepository.findAll();
    }

    @Override
    public Page<ProductParameterGroup> findAllItem(Pageable pageable) {
        return this.productParameterGroupRepository.findAll(pageable);
    }

    @Override
    public Page<ProductParameterGroup> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ProductParameterGroup findOne(ProductParameterGroup productParameterGroup) {
        return this.productParameterGroupRepository.getOne(productParameterGroup.getProductParameterGroupId());
    }

    @Override
    public ProductParameterGroup findById(Long id) {
        if(this.productParameterGroupRepository.existsById(id)){
            return this.productParameterGroupRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterGroupRepository.existsById(id);
    }
}
