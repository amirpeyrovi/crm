package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupEditDTO;
import ir.parto.crm.modules.product.controller.validate.ProductParameterGroupValidate;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
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
            List<ProductParameterGroupDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(),ProductParameterGroupDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductParameterGroupAddDTO productParameterGroupDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroup productParameterGroup = productParameterGroupDTO.convert2Object();
        productParameterGroup.setProductParameterGroupId(null);

        ValidateObject validateObject = this.productParameterGroupValidate.validateAddNewItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.addNewItem(productParameterGroup)
                    .convert2Object())).getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductParameterGroupEditDTO productParameterGroupDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroup productParameterGroup = productParameterGroupDTO.convert2Object();
        productParameterGroup.setProductParameterGroupId(Long.valueOf(id));

        ValidateObject validateObject = this.productParameterGroupValidate.validateUpdateItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.updateItem(productParameterGroup).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductParameterGroup productParameterGroup = new ProductParameterGroup();
        productParameterGroup.setProductParameterGroupId(Long.valueOf(id));
        ValidateObject validateObject = this.productParameterGroupValidate.deleteItem(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.deleteItem(productParameterGroup).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, Arrays.asList("Integrity constraint violated - child record")).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, Arrays.asList("An error occurred during the Delete"))
                            .getFaultResponse();
                }
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductParameterGroup productParameterGroup = new ProductParameterGroup();
        productParameterGroup.setProductParameterGroupId(Long.valueOf(id));
        ValidateObject validateObject = this.productParameterGroupValidate.findOne(productParameterGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupService.findOne(productParameterGroup).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
