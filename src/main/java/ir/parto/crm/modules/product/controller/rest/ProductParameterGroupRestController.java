package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductParameterGroupValidate;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ProductAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/ProductParameterGroup")
public class ProductParameterGroupRestController implements RestControllerInterface {
    private ProductParameterGroupValidate productParameterGroupValidate;
    private ProductParameterGroupService productParameterGroupService;

    @Autowired
    public ProductParameterGroupRestController(ProductParameterGroupValidate productParameterGroupValidate, ProductParameterGroupService productParameterGroupService) {
        this.productParameterGroupValidate = productParameterGroupValidate;
        this.productParameterGroupService = productParameterGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductParameterGroup> productPage = this.productParameterGroupService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductParameterGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductParameterGroup productParameterGroup) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productParameterGroup.setProductParameterGroupId(null);

        ValidateObject validateObject = this.productParameterGroupValidate.validateAddNewItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.addNewItem(productParameterGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductParameterGroup productParameterGroup) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productParameterGroup.setProductParameterGroupId(id);

        ValidateObject validateObject = this.productParameterGroupValidate.validateUpdateItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.updateItem(productParameterGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductParameterGroup productParameterGroup = new ProductParameterGroup();
        productParameterGroup.setProductParameterGroupId(id);
        ValidateObject validateObject = this.productParameterGroupValidate.deleteItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.deleteItem(productParameterGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductParameterGroup productParameterGroup = new ProductParameterGroup();
        productParameterGroup.setProductParameterGroupId(id);
        ValidateObject validateObject = this.productParameterGroupValidate.findOne(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.findOne(productParameterGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
