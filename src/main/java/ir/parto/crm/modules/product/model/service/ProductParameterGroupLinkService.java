package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.modules.product.model.repository.ProductParameterGroupLinkRepository;
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
public class ProductParameterGroupLinkService implements ServiceInterface<ProductParameterGroupLink> {
    private ProductParameterGroupLinkRepository productParameterGroupLinkRepository;

    @Autowired
    public ProductParameterGroupLinkService(ProductParameterGroupLinkRepository productParameterGroupLinkRepository) {
        this.productParameterGroupLinkRepository = productParameterGroupLinkRepository;
    }

    @Override
    @Transactional
    public ProductParameterGroupLink addNewItem(ProductParameterGroupLink productParameterGroupLink) {
        return this.productParameterGroupLinkRepository.save(productParameterGroupLink);
    }

    @Override
    @Transactional
    public ProductParameterGroupLink updateItem(ProductParameterGroupLink productParameterGroupLink) throws InvocationTargetException, IllegalAccessException {
        ProductParameterGroupLink exist = this.productParameterGroupLinkRepository.getOne(productParameterGroupLink.getProductParameterGroupLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productParameterGroupLink);
        return this.productParameterGroupLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductParameterGroupLink deleteItem(ProductParameterGroupLink productParameterGroupLink) {
        this.productParameterGroupLinkRepository.delete(productParameterGroupLink);
        return productParameterGroupLink;
    }

    @Override
    public List<ProductParameterGroupLink> findAllItem() {
        return this.productParameterGroupLinkRepository.findAll();
    }

    @Override
    public Page<ProductParameterGroupLink> findAllItem(Pageable pageable) {
        return this.productParameterGroupLinkRepository.findAll(pageable);
    }

    @Override
    public Page<ProductParameterGroupLink> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ProductParameterGroupLink findOne(ProductParameterGroupLink productParameterGroupLink) {
        return this.productParameterGroupLinkRepository.getOne(productParameterGroupLink.getProductParameterGroupLinkId());
    }

    @Override
    public ProductParameterGroupLink findById(Long id) {
        if(this.productParameterGroupLinkRepository.existsById(id)){
            return this.productParameterGroupLinkRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productParameterGroupLinkRepository.existsById(id);
    }
}
