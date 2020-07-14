package ir.parto.crm.modules.order.controller.rest;

import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.modules.order.controller.validate.OrderValidate;
import ir.parto.crm.modules.order.model.entity.Order;
import ir.parto.crm.modules.order.model.service.OrderService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/order/order")
public class OrderRestController implements RestControllerInterface {
    private OrderService orderService;
    private OrderValidate orderValidate;
    private ClientService clientService;

    @Autowired
    public OrderRestController(OrderService orderService, OrderValidate orderValidate, ClientService clientService) {
        this.orderService = orderService;
        this.orderValidate = orderValidate;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Order")) {
            return new ApiResponse("Error", 101, Arrays.asList("Order - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.orderValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Order> productPage = this.orderService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "Order", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Order order) {
        if (CheckPermission.getInstance().check("admin_add", "Order")) {
            order.setClient(this.clientService.findOne(order.getClient()));
            return new ApiResponse("Error", 101, Arrays.asList("Order - admin_add - access denied!"))
                    .getFaultResponse();
        }

        order.setOrderId(null);

        ValidateObject validateObject = this.orderValidate.validateAddNewItem(order);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderService.addNewItem(order)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Order order) {
        if (CheckPermission.getInstance().check("admin_update", "Order")) {
            return new ApiResponse("Error", 101, Arrays.asList("Order - admin_update - access denied!"))
                    .getFaultResponse();
        }

        order.setOrderId(id);

        ValidateObject validateObject = this.orderValidate.validateUpdateItem(order);
        if (validateObject.getResult().equals("success")) {
            try {
                order.setClient(this.clientService.findOne(order.getClient()));
                return new ApiResponse("Success", Arrays.asList(this.orderService.updateItem(order)))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "Order")) {
            return new ApiResponse("Error", 101, Arrays.asList("Order - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Order order = new Order();
        order.setOrderId(id);
        ValidateObject validateObject = this.orderValidate.deleteItem(order);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderService.deleteItem(order)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Order")) {
            return new ApiResponse("Error", 101, Arrays.asList("Order - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Order order = new Order();
        order.setOrderId(id);
        ValidateObject validateObject = this.orderValidate.findOne(order);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderService.findOne(order)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
