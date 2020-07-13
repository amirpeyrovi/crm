package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.controller.validate.ProductServerParameterValueValidate;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductServerParameterValueService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.server.controller.validate.ServerParameterValidate;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
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
@RequestMapping("/v1/product/ProductServerParameterValue")
public class ProductServerParameterValueRestController implements RestControllerInterface {
    private ProductServerParameterValueValidate productServerParameterValueValidate;
    private ProductAddonValidate productAddonValidate;
    private ProductValidate productValidate;
    private ProductServerParameterValueService productServerParameterValueService;
    private ServerParameterService serverParameterService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductServerParameterValueRestController(ProductServerParameterValueValidate productServerParameterValueValidate, ProductAddonValidate productAddonValidate, ProductValidate productValidate, ProductServerParameterValueService productServerParameterValueService, ServerParameterService serverParameterService, ProductAddonService productAddonService, ProductService productService) {
        this.productServerParameterValueValidate = productServerParameterValueValidate;
        this.productAddonValidate = productAddonValidate;
        this.productValidate = productValidate;
        this.productServerParameterValueService = productServerParameterValueService;
        this.serverParameterService = serverParameterService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productServerParameterValueValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductServerParameterValue> productPage = this.productServerParameterValueService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductServerParameterValue", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Object findAllByProduct(@PathVariable("id") Long id,
                                   @RequestParam(required = false, defaultValue = "0") String page,
                                   @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                   @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productServerParameterValueValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Product product = new Product();
            product.setProductId(id);
            ValidateObject validateObjectProduct = this.productValidate.findOne(product);
            if (validateObjectProduct.getResult().equals("success")) {
                Product productExist = this.productService.findOne(product);
                Page<ProductServerParameterValue> productPage = this.productServerParameterValueService.findAllItemByProduct(productExist, PageableRequest.getInstance().createPageRequest(page, "ProductServerParameterValue", sortProperty, sortOrder));
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

    @RequestMapping(value = "/productAddon/{id}", method = RequestMethod.GET)
    public Object findAllByProductAddon(@PathVariable("id") Long id,
                                        @RequestParam(required = false, defaultValue = "0") String page,
                                        @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                        @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productServerParameterValueValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ProductAddon productAddon = new ProductAddon();
            productAddon.setProductAddonId(id);
            ValidateObject validateObjectProduct = this.productAddonValidate.findOne(productAddon);
            if (validateObjectProduct.getResult().equals("success")) {
                ProductAddon productAddonExist = this.productAddonService.findOne(productAddon);
                Page<ProductServerParameterValue> productPage = this.productServerParameterValueService.findAllItemByProductAddon(productAddonExist, PageableRequest.getInstance().createPageRequest(page, "ProductServerParameterValue", sortProperty, sortOrder));
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
    public Object addOne(@RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(null);

        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
            if (productServerParameterValue.getProduct() == null) {
                productServerParameterValue.setProduct(null);
                productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));
            } else {
                productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
                productServerParameterValue.setProductAddon(null);
            }
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.addNewItem(productServerParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Object addOneWithProduct(@RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(null);

        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));

            productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
            productServerParameterValue.setProductAddon(null);

            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.addNewItem(productServerParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/productAddon", method = RequestMethod.POST)
    public Object addOneWithProductAddon(@RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(null);

        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
            productServerParameterValue.setProduct(null);
            productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));

            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.addNewItem(productServerParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(id);
        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
                if (productServerParameterValue.getProduct() == null) {
                    productServerParameterValue.setProduct(null);
                    productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));
                } else {
                    productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
                    productServerParameterValue.setProductAddon(null);
                }
                return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.updateItem(productServerParameterValue)))
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

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public Object updateOneWithProduct(@PathVariable("id") Long id, @RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(id);
        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
                productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
                productServerParameterValue.setProductAddon(null);

                return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.updateItem(productServerParameterValue)))
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

    @RequestMapping(value = "/productAddon/{id}", method = RequestMethod.PUT)
    public Object updateOneWithProductAddon(@PathVariable("id") Long id, @RequestBody ProductServerParameterValue productServerParameterValue) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }

        productServerParameterValue.setProductServerParameterId(id);
        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
                productServerParameterValue.setProduct(null);
                productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));

                return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.updateItem(productServerParameterValue)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductServerParameterValue productServerParameterValue = new ProductServerParameterValue();
        productServerParameterValue.setProductServerParameterId(id);
        ValidateObject validateObject = this.productServerParameterValueValidate.deleteItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.deleteItem(productServerParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductServerParameterValue productServerParameterValue = new ProductServerParameterValue();
        productServerParameterValue.setProductServerParameterId(id);
        ValidateObject validateObject = this.productServerParameterValueValidate.findOne(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService.findOne(productServerParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
