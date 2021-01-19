package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonEditDTO;
import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ProductAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.Convert2Object;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/productAddon")
public class ProductAddonRestController implements RestControllerInterface {
    private ProductAddonValidate productAddonValidate;
    private ProductAddonService productAddonService;
    private ProductGroupService productGroupService;
    private ServerGroupService serverGroupService;
    private TicketStageService ticketStageService;
    private TicketStateService ticketStateService;

    @Autowired
    public ProductAddonRestController(ProductAddonValidate productAddonValidate, ProductAddonService productAddonService, ProductGroupService productGroupService, ServerGroupService serverGroupService, TicketStageService ticketStageService, TicketStateService ticketStateService) {
        this.productAddonValidate = productAddonValidate;
        this.productAddonService = productAddonService;
        this.productGroupService = productGroupService;
        this.serverGroupService = serverGroupService;
        this.ticketStageService = ticketStageService;
        this.ticketStateService = ticketStateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductAddon")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddon - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productAddonValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductAddon> productPage = this.productAddonService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductAddon", sortProperty, sortOrder));
            List<ProductAddonDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(),ProductAddonDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductAddonAddDTO productAddonDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductAddon")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddon - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductAddon productAddon = productAddonDTO.convert2Object();
        productAddon.setProductAddonId(null);

        if (productAddon.getProductGroup() != null)
            productAddon.setProductGroup(this.productGroupService.findOne(productAddon.getProductGroup()));
        if (productAddon.getServerGroup() != null)
            productAddon.setServerGroup(this.serverGroupService.findOne(productAddon.getServerGroup()));
        if (productAddon.getTicketStage() != null)
            productAddon.setTicketStage(this.ticketStageService.findOne(productAddon.getTicketStage()));
        if (productAddon.getTicketState() != null)
            productAddon.setTicketState(this.ticketStateService.findOne(productAddon.getTicketState()));

        ValidateObject validateObject = this.productAddonValidate.validateAddNewItem(productAddon);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productAddonService.addNewItem(productAddon)
                    .convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductAddonEditDTO productAddonDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductAddon")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddon - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ProductAddon productAddon = productAddonDTO.convert2Object();
        productAddon.setProductAddonId(Long.valueOf(id));

        if (productAddon.getProductGroup() != null)
            productAddon.setProductGroup(this.productGroupService.findOne(productAddon.getProductGroup()));
        if (productAddon.getServerGroup() != null)
            productAddon.setServerGroup(this.serverGroupService.findOne(productAddon.getServerGroup()));
        if (productAddon.getTicketStage() != null)
            productAddon.setTicketStage(this.ticketStageService.findOne(productAddon.getTicketStage()));
        if (productAddon.getTicketState() != null)
            productAddon.setTicketState(this.ticketStateService.findOne(productAddon.getTicketState()));

        ValidateObject validateObject = this.productAddonValidate.validateUpdateItem(productAddon);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productAddonService.updateItem(productAddon).convert2Object()))
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
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "ProductAddon")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddon - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductAddon productAddon = new ProductAddon();
        productAddon.setProductAddonId(Long.valueOf(id));
        ValidateObject validateObject = this.productAddonValidate.deleteItem(productAddon);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productAddonService.deleteItem(productAddon).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductAddon")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddon - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductAddon productAddon = new ProductAddon();
        productAddon.setProductAddonId(Long.valueOf(id));
        ValidateObject validateObject = this.productAddonValidate.findOne(productAddon);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productAddonService.findOne(productAddon)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
