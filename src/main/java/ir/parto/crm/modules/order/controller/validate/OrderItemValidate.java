package ir.parto.crm.modules.order.controller.validate;

import ir.parto.crm.modules.order.model.entity.OrderItem;
import ir.parto.crm.modules.order.model.service.OrderItemService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class OrderItemValidate implements ValidateInterface<OrderItem> {

    private OrderItemService orderItemService;

    @Autowired
    public OrderItemValidate(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @Override
    public ValidateObject validateAddNewItem(OrderItem orderItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderItem == null || orderItem.getOrder() == null || orderItem.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(orderItem == null || orderItem.getService() == null || orderItem.getService().getServiceId() == 0){
            errorList.add("Service is required");
        }
        if(orderItem == null || orderItem.getNextProduct() == null || orderItem.getNextProduct().getProductId() == 0){
            errorList.add("Next Product is required");
        }
        if(orderItem == null || orderItem.getProductCycle() == null || orderItem.getProductCycle().getProductCycleId() == 0){
            errorList.add("Cycle is required");
        }
        if(orderItem == null || orderItem.getPrice() == null ){
            errorList.add("Price is required");
        }
        if(orderItem == null || orderItem.getType() == null || orderItem.getType() == 0){
            errorList.add("Type is required");
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
    public ValidateObject validateUpdateItem(OrderItem orderItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderItem != null && orderItem.getOrder() == null && orderItem.getOrder().getOrderId() == 0){
            errorList.add("Order is required");
        }
        if(orderItem != null && orderItem.getService() == null && orderItem.getService().getServiceId() == 0){
            errorList.add("Service is required");
        }
        if(orderItem != null && orderItem.getNextProduct() == null && orderItem.getNextProduct().getProductId() == 0){
            errorList.add("Next Product is required");
        }
        if(orderItem != null && orderItem.getProductCycle() == null && orderItem.getProductCycle().getProductCycleId() == 0){
            errorList.add("Cycle is required");
        }
        if(orderItem != null && orderItem.getPrice() == null ){
            errorList.add("Price is required");
        }
        if(orderItem != null && orderItem.getType() == null && orderItem.getType() == 0){
            errorList.add("Type is required");
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
    public ValidateObject deleteItem(OrderItem orderItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderItem == null || !this.orderItemService.existsById(orderItem.getOrderItemId())){
            errorList.add("OrderItem not defined");
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
    public ValidateObject findOne(OrderItem orderItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderItem == null || !this.orderItemService.existsById(orderItem.getOrderItemId())){
            errorList.add("OrderItem not defined");
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
    public ValidateObject findById(OrderItem orderItem) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(orderItem == null || !this.orderItemService.existsById(orderItem.getOrderItemId())){
            errorList.add("OrderItem not defined");
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
