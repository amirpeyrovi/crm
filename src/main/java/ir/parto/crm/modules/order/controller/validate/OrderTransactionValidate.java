package ir.parto.crm.modules.order.controller.validate;

import ir.parto.crm.modules.order.model.entity.OrderTransaction;
import ir.parto.crm.modules.order.model.service.OrderTransactionService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class OrderTransactionValidate implements ValidateInterface<OrderTransaction> {
    private OrderTransactionService orderTransactionService;

    @Autowired
    public OrderTransactionValidate(OrderTransactionService orderTransactionService) {
        this.orderTransactionService = orderTransactionService;
    }

    @Override
    public ValidateObject validateAddNewItem(OrderTransaction orderTransaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderTransaction == null || orderTransaction.getOrder() == null || orderTransaction.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(orderTransaction == null || orderTransaction.getTransaction() == null || orderTransaction.getTransaction().getTransactionId() == 0){
            errorList.add("Transaction is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(OrderTransaction orderTransaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderTransaction != null && orderTransaction.getOrder() != null && orderTransaction.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(orderTransaction != null && orderTransaction.getTransaction() != null && orderTransaction.getTransaction().getTransactionId() == 0){
            errorList.add("Transaction is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(OrderTransaction orderTransaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderTransaction == null || !this.orderTransactionService.existsById(orderTransaction.getOrderTransactionId())){
            errorList.add("Order Transaction not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findOne(OrderTransaction orderTransaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderTransaction == null || !this.orderTransactionService.existsById(orderTransaction.getOrderTransactionId())){
            errorList.add("Order Transaction not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findById(OrderTransaction orderTransaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderTransaction == null || !this.orderTransactionService.existsById(orderTransaction.getOrderTransactionId())){
            errorList.add("Order Transaction not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }
}
