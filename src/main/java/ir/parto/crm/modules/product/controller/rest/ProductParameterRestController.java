package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductParameterGroupValidate;
import ir.parto.crm.modules.product.controller.validate.ProductParameterValidate;
import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
import ir.parto.crm.modules.product.model.service.ProductParameterService;
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
@RequestMapping("/v1/product/productParameter")
public class ProductParameterRestController implements RestControllerInterface {
    private ProductParameterValidate productParameterValidate;
    private ProductParameterGroupValidate productParameterGroupValidate;
    private ProductParameterService productParameterService;
    private ProductParameterGroupService productParameterGroupService;

    @Autowired
    public ProductParameterRestController(ProductParameterValidate productParameterValidate, ProductParameterGroupValidate productParameterGroupValidate, ProductParameterService productParameterService, ProductParameterGroupService productParameterGroupService) {
        this.productParameterValidate = productParameterValidate;
        this.productParameterGroupValidate = productParameterGroupValidate;
        this.productParameterService = productParameterService;
        this.productParameterGroupService = productParameterGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductParameter> productPage = this.productParameterService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductParameter", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/ParameterGroup/{id}", method = RequestMethod.GET)
    public Object findAllByProduct(@PathVariable("id") Long id,
                                   @RequestParam(required = false, defaultValue = "0") String page,
                                   @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                   @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ProductParameterGroup productParameterGroup = new ProductParameterGroup();
            productParameterGroup.setProductParameterGroupId(id);
            ValidateObject validateObjectProduct = this.productParameterGroupValidate.findOne(productParameterGroup);
            if (validateObjectProduct.getResult().equals("success")) {
                ProductParameterGroup productParameterGroupExist = this.productParameterGroupService.findOne(productParameterGroup);
                Page<ProductParameter> productPage = this.productParameterService.findAllItemByParameterGroup(productParameterGroupExist, PageableRequest.getInstance().createPageRequest(page, "ProductParameter", sortProperty, sortOrder));
                return new ApiResponse("Success", productPage)
                        .getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectProduct.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductParameter productParameter) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productParameter.setProductParameterId(null);

        ValidateObject validateObject = this.productParameterValidate.validateAddNewItem(productParameter);
        if (validateObject.getResult().equals("success")) {
            productParameter.setProductParameterGroup(this.productParameterGroupService.findOne(productParameter.getProductParameterGroup()));
            return new ApiResponse("Success", Arrays.asList(this.productParameterService.addNewItem(productParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductParameter productParameter) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productParameter.setProductParameterId(id);
        ValidateObject validateObject = this.productParameterValidate.validateUpdateItem(productParameter);
        if (validateObject.getResult().equals("success")) {
            try {
                productParameter.setProductParameterGroup(this.productParameterGroupService.findOne(productParameter.getProductParameterGroup()));
                return new ApiResponse("Success", Arrays.asList(this.productParameterService.updateItem(productParameter)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductParameter productParameter = new ProductParameter();
        productParameter.setProductParameterId(id);
        ValidateObject validateObject = this.productParameterValidate.deleteItem(productParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterService.deleteItem(productParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductParameter productParameter = new ProductParameter();
        productParameter.setProductParameterId(id);
        ValidateObject validateObject = this.productParameterValidate.findOne(productParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterService.findOne(productParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
