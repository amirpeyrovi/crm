package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.repository.ProductParameterRepository;
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
public class ProductParameterService implements ServiceInterface<ProductParameter> {
    private ProductParameterRepository productParameterRepository;

    @Autowired
    public ProductParameterService(ProductParameterRepository productParameterRepository) {
        this.productParameterRepository = productParameterRepository;
    }

    @Override
    @Transactional
    public ProductParameter addNewItem(ProductParameter productParameter) {
        return this.productParameterRepository.save(productParameter);
    }

    @Override
    @Transactional
    public ProductParameter updateItem(ProductParameter productParameter) throws InvocationTargetException, IllegalAccessException {
        ProductParameter exist = this.productParameterRepository.getOne(productParameter.getProductParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameter);
        return this.productParameterRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ProductParameter> deleteItem(ProductParameter productParameter) {
        this.productParameterRepository.delete(productParameter);
        return this.productParameterRepository.findAll();
    }

    @Override
    public List<ProductParameter> findAllItem() {
        return this.productParameterRepository.findAll();
    }

    @Override
    public Page<ProductParameter> findAllItem(Pageable pageable) {
        return this.productParameterRepository.findAll(pageable);
    }

    @Override
    public ProductParameter findOne(ProductParameter productParameter) {
        return this.productParameterRepository.getOne(productParameter.getProductParameterId());
    }

    @Override
    public ProductParameter findById(Long id) {
        if(this.productParameterRepository.existsById(id)){
            return this.productParameterRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterRepository.existsById(id);
    }
}
