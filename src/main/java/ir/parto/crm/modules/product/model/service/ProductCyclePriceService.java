package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.repository.ProductCyclePriceRepository;
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
public class ProductCyclePriceService implements ServiceInterface<ProductCyclePrice> {
    private ProductCyclePriceRepository productCyclePriceRepository;

    @Autowired
    public ProductCyclePriceService(ProductCyclePriceRepository productCyclePriceRepository) {
        this.productCyclePriceRepository = productCyclePriceRepository;
    }

    @Override
    @Transactional
    public ProductCyclePrice addNewItem(ProductCyclePrice productCyclePrice) {
        return this.productCyclePriceRepository.save(productCyclePrice);
    }

    @Override
    @Transactional
    public ProductCyclePrice updateItem(ProductCyclePrice productCyclePrice) throws InvocationTargetException, IllegalAccessException {
        ProductCyclePrice exist = this.productCyclePriceRepository.getOne(productCyclePrice.getProductCyclePriceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productCyclePrice);
        return this.productCyclePriceRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductCyclePrice deleteItem(ProductCyclePrice productCyclePrice) {
        this.productCyclePriceRepository.delete(productCyclePrice);
        return productCyclePrice;
    }

    @Override
    public List<ProductCyclePrice> findAllItem() {
        return this.productCyclePriceRepository.findAll();
    }

    @Override
    public Page<ProductCyclePrice> findAllItem(Pageable pageable) {
        return this.productCyclePriceRepository.findAll(pageable);
    }

    @Override
    public Page<ProductCyclePrice> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ProductCyclePrice findOne(ProductCyclePrice productCyclePrice) {
        return this.productCyclePriceRepository.getOne(productCyclePrice.getProductCyclePriceId());
    }

    @Override
    public ProductCyclePrice findById(Long id) {
        if(this.productCyclePriceRepository.existsById(id)){
            return this.productCyclePriceRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productCyclePriceRepository.existsById(id);
    }
}
