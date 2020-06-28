package ir.parto.crm.modules.order.model.service;

import ir.parto.crm.modules.order.model.entity.OrderItem;
import ir.parto.crm.modules.order.model.repository.OrderItemRepository;
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
public class OrderItemService implements ServiceInterface<OrderItem> {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderItem addNewItem(OrderItem orderItem) {
        return this.orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem updateItem(OrderItem orderItem) throws InvocationTargetException, IllegalAccessException {
        OrderItem exist = this.orderItemRepository.getOne(orderItem.getOrderItemId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,orderItem);
        return this.orderItemRepository.save(exist);
    }

    @Override
    @Transactional
    public List<OrderItem> deleteItem(OrderItem orderItem) {
        this.orderItemRepository.delete(orderItem);
        return this.orderItemRepository.findAll();
    }

    @Override
    public List<OrderItem> findAllItem() {
        return this.orderItemRepository.findAll();
    }

    @Override
    public Page<OrderItem> findAllItem(Pageable pageable) {
        return this.orderItemRepository.findAll(pageable);
    }

    @Override
    public OrderItem findOne(OrderItem orderItem) {
        if(this.orderItemRepository.existsById(orderItem.getOrderItemId())){
            return this.orderItemRepository.getOne(orderItem.getOrderItemId());
        }
        return null;
    }

    @Override
    public OrderItem findById(Long id) {
        if(this.orderItemRepository.existsById(id)){
            return this.orderItemRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.orderItemRepository.existsById(id);
    }
}
