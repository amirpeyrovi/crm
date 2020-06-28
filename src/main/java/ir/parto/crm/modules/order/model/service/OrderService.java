package ir.parto.crm.modules.order.model.service;

import ir.parto.crm.modules.order.model.entity.Order;
import ir.parto.crm.modules.order.model.repository.OrderRepository;
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
public class OrderService implements ServiceInterface<Order> {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order addNewItem(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateItem(Order order) throws InvocationTargetException, IllegalAccessException {
        Order exist = this.orderRepository.getOne(order.getOrderId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,order);
        return this.orderRepository.save(exist);
    }

    @Override
    @Transactional
    public List<Order> deleteItem(Order order) {
        this.orderRepository.delete(order);
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllItem() {
        return this.orderRepository.findAll();
    }

    @Override
    public Page<Order> findAllItem(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Order findOne(Order order) {
        if(this.orderRepository.existsById(order.getOrderId())){
            return this.orderRepository.getOne(order.getOrderId());
        }
        return null;
    }

    @Override
    public Order findById(Long id) {
        if(this.orderRepository.existsById(id)){
            return this.orderRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.orderRepository.existsById(id);
    }
}
