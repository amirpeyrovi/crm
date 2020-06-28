package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.repository.ProductAddonRepository;
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
public class ProductAddonService implements ServiceInterface<ProductAddon> {
    private ProductAddonRepository productAddonRepository;

    @Autowired
    public ProductAddonService(ProductAddonRepository productAddonRepository) {
        this.productAddonRepository = productAddonRepository;
    }

    @Override
    @Transactional
    public ProductAddon addNewItem(ProductAddon productAddon) {
        return this.productAddonRepository.save(productAddon);
    }

    @Override
    @Transactional
    public ProductAddon updateItem(ProductAddon productAddon) throws InvocationTargetException, IllegalAccessException {
        ProductAddon exist = this.productAddonRepository.getOne(productAddon.getProductId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productAddon);
        return this.productAddonRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ProductAddon> deleteItem(ProductAddon productAddon) {
        this.productAddonRepository.delete(productAddon);
        return this.productAddonRepository.findAll();
    }

    @Override
    public List<ProductAddon> findAllItem() {
        return this.productAddonRepository.findAll();
    }

    @Override
    public Page<ProductAddon> findAllItem(Pageable pageable) {
        return this.productAddonRepository.findAll(pageable);
    }

    @Override
    public ProductAddon findOne(ProductAddon productAddon) {
        return this.productAddonRepository.getOne(productAddon.getProductId());
    }

    @Override
    public ProductAddon findById(Long id) {
        if (this.productAddonRepository.existsById(id)) {
            return this.productAddonRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productAddonRepository.existsById(id);
    }
}
