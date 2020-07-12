package ir.parto.crm.modules.order.controller.validate;

import ir.parto.crm.modules.order.model.entity.Order;
import ir.parto.crm.modules.order.model.service.OrderService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class OrderValidate implements ValidateInterface<Order> {
    private OrderService orderService;

    @Autowired
    public OrderValidate(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ValidateObject validateAddNewItem(Order order) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (order == null || order.getClient() == null || order.getClient().getClientId() == 0) {
            errorList.add("Client is required");
        }
        if (order == null || order.getPrice() == null) {
            errorList.add("Price is required");
        }
        if (order == null || order.getStatus() == null || order.getStatus() == 0) {
            errorList.add("Status is required");
        }
        if (order == null || order.getOfficialType() == null || order.getOfficialType() == 0) {
            errorList.add("OfficialType is required");
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
    public ValidateObject validateUpdateItem(Order order) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (order != null && order.getClient() != null && order.getClient().getClientId() == 0) {
            errorList.add("Client is required");
        }
        if (order != null && order.getPrice() == null) {
            errorList.add("Price is required");
        }
        if (order != null && order.getStatus() != null && order.getStatus() == 0) {
            errorList.add("Status is required");
        }
        if (order != null && order.getOfficialType() != null && order.getOfficialType() == 0) {
            errorList.add("OfficialType is required");
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
    public ValidateObject deleteItem(Order order) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (order == null || !this.orderService.existsById(order.getOrderId())) {
            errorList.add("Order not defined");
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
    public ValidateObject findOne(Order order) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (order == null || !this.orderService.existsById(order.getOrderId())) {
            errorList.add("Order not defined");
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
    public ValidateObject findById(Order order) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (order == null || !this.orderService.existsById(order.getOrderId())) {
            errorList.add("Order not defined");
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
