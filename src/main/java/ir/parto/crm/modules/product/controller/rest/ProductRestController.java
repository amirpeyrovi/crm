package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.product.ProductDTO;
import ir.parto.crm.modules.product.controller.transientObject.product.ProductEditDTO;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.modules.product.model.service.ProductService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/product")
public class ProductRestController implements RestControllerInterface {
    private ProductValidate productValidate;
    private ProductService productService;
    private ProductGroupService productGroupService;
    private ServerGroupService serverGroupService;
    private TicketStageService ticketStageService;
    private TicketStateService ticketStateService;

    @Autowired
    public ProductRestController(ProductValidate productValidate, ProductService productService, ProductGroupService productGroupService, ServerGroupService serverGroupService, TicketStageService ticketStageService, TicketStateService ticketStateService) {
        this.productValidate = productValidate;
        this.productService = productService;
        this.productGroupService = productGroupService;
        this.serverGroupService = serverGroupService;
        this.ticketStageService = ticketStageService;
        this.ticketStateService = ticketStateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Product> productPage = this.productService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Product", sortProperty, sortOrder));
            List<ProductDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductAddDTO productAddDTO) {
        if (CheckPermission.getInstance().check("admin_add", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_add - access denied!"))
                    .getFaultResponse();
        }

        Product product = productAddDTO.convert2Object();
        product.setProductId(null);

        if (product.getProductGroup() != null)
            product.setProductGroup(this.productGroupService.findOne(product.getProductGroup()));
        if (product.getServerGroup() != null)
            product.setServerGroup(this.serverGroupService.findOne(product.getServerGroup()));
        if (product.getTicketStage() != null)
            product.setTicketStage(this.ticketStageService.findOne(product.getTicketStage()));
        if (product.getTicketState() != null)
            product.setTicketState(this.ticketStateService.findOne(product.getTicketState()));

        ValidateObject validateObject = this.productValidate.validateAddNewItem(product);
        if (validateObject.getResult().equals("success")) {

            return new ApiResponse("Success", Arrays.asList(this.productService.addNewItem(product).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductEditDTO productDTO) {
        if (CheckPermission.getInstance().check("admin_update", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_update - access denied!"))
                    .getFaultResponse();
        }
        Product product = productDTO.convert2Object();
        product.setProductId(id);
        if (product.getProductGroup() != null)
            product.setProductGroup(this.productGroupService.findOne(product.getProductGroup()));
        if (product.getServerGroup() != null)
            product.setServerGroup(this.serverGroupService.findOne(product.getServerGroup()));
        if (product.getTicketStage() != null)
            product.setTicketStage(this.ticketStageService.findOne(product.getTicketStage()));
        if (product.getTicketState() != null)
            product.setTicketState(this.ticketStateService.findOne(product.getTicketState()));

        ValidateObject validateObject = this.productValidate.validateUpdateItem(product);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productService.updateItem(product).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Product product = new Product();
        product.setProductId(id);
        ValidateObject validateObject = this.productValidate.deleteItem(product);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productService.deleteItem(product).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("" +
                            "Integrity constraint violated - child record"))).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("An error occurred during the Delete")))
                            .getFaultResponse();
                }
            }


        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Product product = new Product();
        product.setProductId(id);
        ValidateObject validateObject = this.productValidate.findOne(product);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productService.findOne(product)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
