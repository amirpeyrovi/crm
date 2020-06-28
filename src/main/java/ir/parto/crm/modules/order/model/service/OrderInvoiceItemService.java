package ir.parto.crm.modules.order.model.service;

import ir.parto.crm.modules.order.model.entity.OrderInvoiceItem;
import ir.parto.crm.modules.order.model.repository.OrderInvoiceItemRepository;
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
public class OrderInvoiceItemService implements ServiceInterface<OrderInvoiceItem> {

    private OrderInvoiceItemRepository orderInvoiceItemRepository;

    @Autowired
    public OrderInvoiceItemService(OrderInvoiceItemRepository orderInvoiceItemRepository) {
        this.orderInvoiceItemRepository = orderInvoiceItemRepository;
    }

    @Override
    @Transactional
    public OrderInvoiceItem addNewItem(OrderInvoiceItem orderInvoiceItem) {
        return this.orderInvoiceItemRepository.save(orderInvoiceItem);
    }

    @Override
    @Transactional
    public OrderInvoiceItem updateItem(OrderInvoiceItem orderInvoiceItem) throws InvocationTargetException, IllegalAccessException {
        OrderInvoiceItem exist = this.orderInvoiceItemRepository.getOne(orderInvoiceItem.getOrderInvoiceItemId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,orderInvoiceItem);
        return this.orderInvoiceItemRepository.save(exist);
    }

    @Override
    @Transactional
    public List<OrderInvoiceItem> deleteItem(OrderInvoiceItem orderInvoiceItem) {
        this.orderInvoiceItemRepository.delete(orderInvoiceItem);
        return this.orderInvoiceItemRepository.findAll();
    }

    @Override
    public List<OrderInvoiceItem> findAllItem() {
        return this.orderInvoiceItemRepository.findAll();
    }

    @Override
    public Page<OrderInvoiceItem> findAllItem(Pageable pageable) {
        return this.orderInvoiceItemRepository.findAll(pageable);
    }

    @Override
    public OrderInvoiceItem findOne(OrderInvoiceItem orderInvoiceItem) {
        if(this.orderInvoiceItemRepository.existsById(orderInvoiceItem.getOrderInvoiceItemId())){
            return this.orderInvoiceItemRepository.getOne(orderInvoiceItem.getOrderInvoiceItemId());
        }
        return null;
    }

    @Override
    public OrderInvoiceItem findById(Long id) {
        if(this.orderInvoiceItemRepository.existsById(id)){
            return this.orderInvoiceItemRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.orderInvoiceItemRepository.existsById(id);
    }
}
