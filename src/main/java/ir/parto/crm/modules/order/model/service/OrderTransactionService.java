package ir.parto.crm.modules.order.model.service;

import ir.parto.crm.modules.order.model.entity.OrderTransaction;
import ir.parto.crm.modules.order.model.repository.OrderTransactionRepository;
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
public class OrderTransactionService implements ServiceInterface<OrderTransaction> {

    private OrderTransactionRepository orderTransactionRepository;

    @Autowired
    public OrderTransactionService(OrderTransactionRepository orderTransactionRepository) {
        this.orderTransactionRepository = orderTransactionRepository;
    }

    @Override
    @Transactional
    public OrderTransaction addNewItem(OrderTransaction orderTransaction) {
        return null;
    }

    @Override
    @Transactional
    public OrderTransaction updateItem(OrderTransaction orderTransaction) throws InvocationTargetException, IllegalAccessException {
        OrderTransaction exist = this.orderTransactionRepository.getOne(orderTransaction.getOrderTransactionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,orderTransaction);
        return this.orderTransactionRepository.save(exist);
    }

    @Override
    @Transactional
    public List<OrderTransaction> deleteItem(OrderTransaction orderTransaction) {
        this.orderTransactionRepository.delete(orderTransaction);
        return this.orderTransactionRepository.findAll();
    }

    @Override
    public List<OrderTransaction> findAllItem() {
        return this.orderTransactionRepository.findAll();
    }

    @Override
    public Page<OrderTransaction> findAllItem(Pageable pageable) {
        return this.orderTransactionRepository.findAll(pageable);
    }

    @Override
    public OrderTransaction findOne(OrderTransaction orderTransaction) {
        if(this.orderTransactionRepository.existsById(orderTransaction.getOrderTransactionId())){
            return this.orderTransactionRepository.getOne(orderTransaction.getOrderTransactionId());
        }
        return null;
    }

    @Override
    public OrderTransaction findById(Long id) {
        if(this.orderTransactionRepository.existsById(id)){
            return this.orderTransactionRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.orderTransactionRepository.existsById(id);
    }
}
