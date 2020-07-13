package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductGroupValidate;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
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
@RequestMapping("/v1/product/ProductGroup")
public class ProductGroupRestController implements RestControllerInterface {
    private ProductGroupValidate productGroupValidate;
    private ProductGroupService productGroupService;

    @Autowired
    public ProductGroupRestController(ProductGroupValidate productGroupValidate, ProductGroupService productGroupService) {
        this.productGroupValidate = productGroupValidate;
        this.productGroupService = productGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductGroup> productPage = this.productGroupService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductGroup productGroup) {
        if (CheckPermission.getInstance().check("admin_add", "ProductGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productGroup.setProductGroupId(null);

        ValidateObject validateObject = this.productGroupValidate.validateAddNewItem(productGroup);
        if (validateObject.getResult().equals("success")) {
            if (productGroup.getProductGroup() != null) {
                productGroup.setProductGroup(this.productGroupService.findOne(productGroup.getProductGroup()));
            }
            return new ApiResponse("Success", Arrays.asList(this.productGroupService.addNewItem(productGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductGroup productGroup) {
        if (CheckPermission.getInstance().check("admin_update", "ProductGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productGroup.setProductGroupId(id);

        ValidateObject validateObject = this.productGroupValidate.validateUpdateItem(productGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                if (productGroup.getProductGroup() != null) {
                    productGroup.setProductGroup(this.productGroupService.findOne(productGroup.getProductGroup()));
                }
                return new ApiResponse("Success", Arrays.asList(this.productGroupService.updateItem(productGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductGroup productGroup = new ProductGroup();
        productGroup.setProductGroupId(id);
        ValidateObject validateObject = this.productGroupValidate.deleteItem(productGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productGroupService.deleteItem(productGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductGroup productGroup = new ProductGroup();
        productGroup.setProductGroupId(id);

        ValidateObject validateObject = this.productGroupValidate.findOne(productGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productGroupService.findOne(productGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
