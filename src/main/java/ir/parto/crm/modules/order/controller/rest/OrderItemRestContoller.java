package ir.parto.crm.modules.order.controller.rest;

import ir.parto.crm.modules.order.controller.validate.OrderItemValidate;
import ir.parto.crm.modules.order.model.entity.OrderItem;
import ir.parto.crm.modules.order.model.service.OrderItemService;
import ir.parto.crm.modules.order.model.service.OrderService;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductCycleService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.service.model.service.ServiceService;
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
@RequestMapping("/v1/order/orderItem")
public class OrderItemRestContoller implements RestControllerInterface {
    private OrderItemService orderItemService;
    private OrderItemValidate orderItemValidate;
    private ProductService productService;
    private ProductAddonService productAddonService;
    private ProductCycleService productCycleService;
    private OrderService orderService;
    private ServiceService serviceService;

    @Autowired
    public OrderItemRestContoller(OrderItemService orderItemService, OrderItemValidate orderItemValidate, ProductService productService,
                                  ProductAddonService productAddonService , ProductCycleService productCycleService,
                                  OrderService orderService, ServiceService serviceService) {
        this.orderItemService = orderItemService;
        this.orderItemValidate = orderItemValidate;
        this.productService = productService;
        this.productAddonService = productAddonService;
        this.productCycleService = productCycleService;
        this.orderService = orderService;
        this.serviceService = serviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "OrderItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderItem - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.orderItemValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<OrderItem> productPage = this.orderItemService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "OrderItem", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody OrderItem orderItem) {
        if (CheckPermission.getInstance().check("admin_add", "OrderItem")) {
            orderItem.setNextProduct(this.productService.findOne(orderItem.getNextProduct()));
            orderItem.setNextProductAddon(this.productAddonService.findOne(orderItem.getNextProductAddon()));
            orderItem.setProductCycle(this.productCycleService.findOne(orderItem.getProductCycle()));
            orderItem.setOrder(this.orderService.findOne(orderItem.getOrder()));
            orderItem.setService(this.serviceService.findOne(orderItem.getService()));
            return new ApiResponse("Error", 101, Arrays.asList("OrderItem - admin_add - access denied!"))
                    .getFaultResponse();
        }

        orderItem.setOrderItemId(null);

        ValidateObject validateObject = this.orderItemValidate.validateAddNewItem(orderItem);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderItemService.addNewItem(orderItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody OrderItem orderItem) {
        if (CheckPermission.getInstance().check("admin_update", "OrderItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderItem - admin_update - access denied!"))
                    .getFaultResponse();
        }

        orderItem.setOrderItemId(id);

        ValidateObject validateObject = this.orderItemValidate.validateUpdateItem(orderItem);
        if (validateObject.getResult().equals("success")) {
            try {
                orderItem.setNextProduct(this.productService.findOne(orderItem.getNextProduct()));
                orderItem.setNextProductAddon(this.productAddonService.findOne(orderItem.getNextProductAddon()));
                orderItem.setProductCycle(this.productCycleService.findOne(orderItem.getProductCycle()));
                orderItem.setOrder(this.orderService.findOne(orderItem.getOrder()));
                orderItem.setService(this.serviceService.findOne(orderItem.getService()));
                return new ApiResponse("Success", Arrays.asList(this.orderItemService.updateItem(orderItem)))
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
        if (CheckPermission.getInstance().check("admin_delete", "OrderItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderItem - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(id);
        ValidateObject validateObject = this.orderItemValidate.deleteItem(orderItem);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderItemService.deleteItem(orderItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "OrderItem")) {
            return new ApiResponse("Error", 101, Arrays.asList("OrderItem - admin_show - access denied!"))
                    .getFaultResponse();
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(id);
        ValidateObject validateObject = this.orderItemValidate.findOne(orderItem);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.orderItemService.findOne(orderItem)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
