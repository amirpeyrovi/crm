package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.repository.ProductCycleRepository;
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
public class ProductCycleService  implements ServiceInterface<ProductCycle> {
    private ProductCycleRepository productCycleRepository;

    @Autowired
    public ProductCycleService(ProductCycleRepository productCycleRepository) {
        this.productCycleRepository = productCycleRepository;
    }

    @Override
    @Transactional
    public ProductCycle addNewItem(ProductCycle productCycle) {
        return this.productCycleRepository.save(productCycle);
    }

    @Override
    @Transactional
    public ProductCycle updateItem(ProductCycle productCycle) throws InvocationTargetException, IllegalAccessException {
        ProductCycle exist = this.productCycleRepository.getOne(productCycle.getProductCycleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productCycle);
        return this.productCycleRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ProductCycle> deleteItem(ProductCycle productCycle) {
        this.productCycleRepository.delete(productCycle);
        return this.productCycleRepository.findAll();
    }

    @Override
    public List<ProductCycle> findAllItem() {
        return this.productCycleRepository.findAll();
    }

    @Override
    public Page<ProductCycle> findAllItem(Pageable pageable) {
        return this.productCycleRepository.findAll(pageable);
    }

    @Override
    public ProductCycle findOne(ProductCycle productCycle) {
        return this.productCycleRepository.getOne(productCycle.getProductCycleId());
    }

    @Override
    public ProductCycle findById(Long id) {
        if(this.productCycleRepository.existsById(id)){
            return this.productCycleRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productCycleRepository.existsById(id);
    }
}
