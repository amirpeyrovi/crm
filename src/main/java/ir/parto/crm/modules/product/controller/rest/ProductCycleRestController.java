package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleDTO;
import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleEditDTO;
import ir.parto.crm.modules.product.controller.validate.ProductCycleValidate;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.service.ProductCycleService;
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
@RequestMapping("/v1/product/productCycle")
public class ProductCycleRestController implements RestControllerInterface {
    private ProductCycleValidate productCycleValidate;
    private ProductCycleService productCycleService;

    @Autowired
    public ProductCycleRestController(ProductCycleValidate productCycleValidate, ProductCycleService productCycleService) {
        this.productCycleValidate = productCycleValidate;
        this.productCycleService = productCycleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductCycle")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCycle - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productCycleValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductCycle> productPage = this.productCycleService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductCycle", sortProperty, sortOrder));
            List<ProductCycleDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductCycleDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductCycleAddDTO productCycleDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductCycle")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCycle - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductCycle productCycle = productCycleDTO.convert2Object();
        productCycle.setProductCycleId(null);

        ValidateObject validateObject = this.productCycleValidate.validateAddNewItem(productCycle);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCycleService.addNewItem(productCycle).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductCycleEditDTO productCycleDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductCycle")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCycle - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductCycle productCycle = productCycleDTO.convert2Object();
        productCycle.setProductCycleId(Long.valueOf(id));

        ValidateObject validateObject = this.productCycleValidate.validateUpdateItem(productCycle);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productCycleService.updateItem(productCycle).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductCycle")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCycle - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductCycle productCycle = new ProductCycle();
        productCycle.setProductCycleId(Long.parseLong(id));
        ValidateObject validateObject = this.productCycleValidate.deleteItem(productCycle);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCycleService.deleteItem(productCycle).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductCycle")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCycle - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductCycle productCycle = new ProductCycle();
        productCycle.setProductCycleId(Long.parseLong(id));
        ValidateObject validateObject = this.productCycleValidate.findOne(productCycle);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCycleService.findOne(productCycle)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
