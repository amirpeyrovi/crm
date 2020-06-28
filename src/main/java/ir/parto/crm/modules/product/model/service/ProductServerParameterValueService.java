package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.modules.product.model.repository.ProductServerParameterValueRepository;
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
public class ProductServerParameterValueService implements ServiceInterface<ProductServerParameterValue> {
    private ProductServerParameterValueRepository productServerParameterValueRepository;

    @Autowired
    public ProductServerParameterValueService(ProductServerParameterValueRepository productServerParameterValueRepository) {
        this.productServerParameterValueRepository = productServerParameterValueRepository;
    }

    @Override
    @Transactional
    public ProductServerParameterValue addNewItem(ProductServerParameterValue productServerParameterValue) {
        return this.productServerParameterValueRepository.save(productServerParameterValue);
    }

    @Override
    @Transactional
    public ProductServerParameterValue updateItem(ProductServerParameterValue productServerParameterValue) throws InvocationTargetException, IllegalAccessException {
        ProductServerParameterValue exist = this.productServerParameterValueRepository.getOne(productServerParameterValue.getProductServerParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productServerParameterValue);
        return this.productServerParameterValueRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ProductServerParameterValue> deleteItem(ProductServerParameterValue productServerParameterValue) {
        this.productServerParameterValueRepository.delete(productServerParameterValue);
        return this.productServerParameterValueRepository.findAll();
    }

    @Override
    public List<ProductServerParameterValue> findAllItem() {
        return this.productServerParameterValueRepository.findAll();
    }

    @Override
    public Page<ProductServerParameterValue> findAllItem(Pageable pageable) {
        return this.productServerParameterValueRepository.findAll(pageable);
    }

    @Override
    public ProductServerParameterValue findOne(ProductServerParameterValue productServerParameterValue) {
        return this.productServerParameterValueRepository.getOne(productServerParameterValue.getProductServerParameterId());
    }

    @Override
    public ProductServerParameterValue findById(Long id) {
        if(this.productServerParameterValueRepository.existsById(id)){
            return this.productServerParameterValueRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productServerParameterValueRepository.existsById(id);
    }
}
