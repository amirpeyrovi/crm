package ir.parto.crm.modules.order.controller.rest;

import ir.parto.crm.modules.financial.model.service.TransactionService;
import ir.parto.crm.modules.order.controller.validate.OrderTransactionValidate;
import ir.parto.crm.modules.order.model.entity.OrderTransaction;
import ir.parto.crm.modules.order.model.service.OrderService;
import ir.parto.crm.modules.order.model.service.OrderTransactionService;
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
@RequestMapping("/v1/order/orderTransaction")
public class OrderTransactionRestController implements RestControllerInterface {
    private OrderTransactionValidate orderTransactionValidate;
    private OrderTransactionService orderTransactionService;
    private OrderService orderService;
    private TransactionService transactionService;

    @Autowired
    public OrderTransactionRestController(OrderTransactionValidate orderTransactionValidate, OrderTransactionService orderTransactionService,
                                          OrderService orderService , TransactionService transactionService) {
        this.orderTransactionValidate = orderTransactionValidate;
        this.orderTransactionService = orderTransactionService;
        this.orderService = orderService;
        this.transactionService = transactionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "OrderTransaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderTransaction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.orderTransactionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<OrderTransaction> productPage = this.orderTransactionService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "OrderTransaction", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody OrderTransaction orderTransaction) {
        if (CheckPermission.getInstance().check("admin_add", "OrderTransaction")) {
            orderTransaction.setOrder(this.orderService.findOne(orderTransaction.getOrder()));
            orderTransaction.setTransaction(this.transactionService.findOne(orderTransaction.getTransaction()));
            return new ApiResponse("Error", 101, Arrays.asList("OrderTransaction - admin_add - access denied!"))
                    .getFaultResponse();
        }

        orderTransaction.setOrderTransactionId(null);

        ValidateObject validateObject = this.orderTransactionValidate.validateAddNewItem(orderTransaction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderTransactionService.addNewItem(orderTransaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody OrderTransaction orderTransaction) {
        if (CheckPermission.getInstance().check("admin_update", "OrderTransaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderTransaction - admin_update - access denied!"))
                    .getFaultResponse();
        }

        orderTransaction.setOrderTransactionId(id);

        ValidateObject validateObject = this.orderTransactionValidate.validateUpdateItem(orderTransaction);
        if (validateObject.getResult().equals("success")) {
            try {
                orderTransaction.setOrder(this.orderService.findOne(orderTransaction.getOrder()));
                orderTransaction.setTransaction(this.transactionService.findOne(orderTransaction.getTransaction()));
                return new ApiResponse("Success", Arrays.asList(this.orderTransactionService.updateItem(orderTransaction)))
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
        if (CheckPermission.getInstance().check("admin_delete", "OrderTransaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderTransaction - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setOrderTransactionId(id);
        ValidateObject validateObject = this.orderTransactionValidate.deleteItem(orderTransaction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderTransactionService.deleteItem(orderTransaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "OrderTransaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderTransaction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setOrderTransactionId(id);
        ValidateObject validateObject = this.orderTransactionValidate.findOne(orderTransaction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderTransactionService.findOne(orderTransaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
