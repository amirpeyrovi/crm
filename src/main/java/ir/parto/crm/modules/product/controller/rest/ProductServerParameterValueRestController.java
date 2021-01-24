package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue.ProductServerParameterValueAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue.ProductServerParameterValueDTO;
import ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue.ProductServerParameterValueEditDTO;
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
            List<ProductServerParameterValueDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(),ProductServerParameterValueDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Object findAllByProduct(@PathVariable("id") String id,
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
            product.setProductId(Long.valueOf(id));
            ValidateObject validateObjectProduct = this.productValidate.findOne(product);
            if (validateObjectProduct.getResult().equals("success")) {
                Product productExist = this.productService.findOne(product);
                Page<ProductServerParameterValue> productPage = this.productServerParameterValueService.findAllItemByProduct(productExist, PageableRequest.getInstance().createPageRequest(page, "ProductServerParameterValue", sortProperty, sortOrder));
                List<ProductServerParameterValueDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(),ProductServerParameterValueDTO.class);
                return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
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
    public Object findAllByProductAddon(@PathVariable("id") String id,
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
            productAddon.setProductAddonId(Long.valueOf(id));
            ValidateObject validateObjectProduct = this.productAddonValidate.findOne(productAddon);
            if (validateObjectProduct.getResult().equals("success")) {
                ProductAddon productAddonExist = this.productAddonService.findOne(productAddon);
                Page<ProductServerParameterValue> productPage = this.productServerParameterValueService.findAllItemByProductAddon(productAddonExist, PageableRequest.getInstance().createPageRequest(page, "ProductServerParameterValue", sortProperty, sortOrder));
                List<ProductServerParameterValueDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(),ProductServerParameterValueDTO.class);
                return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
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
    public Object addOne(@RequestBody ProductServerParameterValueAddDTO productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ProductServerParameterValue productServerParameterValue = productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(null);
        productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
        if (productServerParameterValue.getProduct() == null) {
            productServerParameterValue.setProduct(null);
            productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));
        } else {
            productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
            productServerParameterValue.setProductAddon(null);
        }
        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService
                    .addNewItem(productServerParameterValue).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Object addOneWithProduct(@RequestBody ProductServerParameterValueAddDTO productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductServerParameterValue productServerParameterValue =productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(null);
        productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));

        productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
        productServerParameterValue.setProductAddon(null);

        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService
                    .addNewItem(productServerParameterValue).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/productAddon", method = RequestMethod.POST)
    public Object addOneWithProductAddon(@RequestBody ProductServerParameterValueAddDTO productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductServerParameterValue productServerParameterValue = productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(null);
        productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
        productServerParameterValue.setProduct(null);
        productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));

        ValidateObject validateObject = this.productServerParameterValueValidate.validateAddNewItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.productServerParameterValueService.addNewItem(productServerParameterValue).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductServerParameterValueEditDTO
            productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductServerParameterValue productServerParameterValue = productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(Long.valueOf(id));
        productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
        if (productServerParameterValue.getProduct() == null) {
            productServerParameterValue.setProduct(null);
            productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));
        } else {
            productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
            productServerParameterValue.setProductAddon(null);
        }
        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {

                return new ApiResponse("Success", Arrays.asList(
                        this.productServerParameterValueService.updateItem(productServerParameterValue).convert2Object()))
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
    public Object updateOneWithProduct(@PathVariable("id") String id, @RequestBody ProductServerParameterValueEditDTO
            productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductServerParameterValue productServerParameterValue = productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(Long.valueOf(id));
        productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
        productServerParameterValue.setProduct(this.productService.findOne(productServerParameterValue.getProduct()));
        productServerParameterValue.setProductAddon(null);

        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.productServerParameterValueService.updateItem(productServerParameterValue).convert2Object()))
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
    public Object updateOneWithProductAddon(@PathVariable("id") String id, @RequestBody ProductServerParameterValueEditDTO
            productServerParameterValueDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductServerParameterValue productServerParameterValue = productServerParameterValueDTO.convert2Object();
        productServerParameterValue.setProductServerParameterId(Long.valueOf(id));
        ValidateObject validateObject = this.productServerParameterValueValidate.validateUpdateItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                productServerParameterValue.setServerParameter(this.serverParameterService.findOne(productServerParameterValue.getServerParameter()));
                productServerParameterValue.setProduct(null);
                productServerParameterValue.setProductAddon(this.productAddonService.findOne(productServerParameterValue.getProductAddon()));

                return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService
                        .updateItem(productServerParameterValue).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductServerParameterValue productServerParameterValue = new ProductServerParameterValue();
        productServerParameterValue.setProductServerParameterId(Long.valueOf(id));
        ValidateObject validateObject = this.productServerParameterValueValidate.deleteItem(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService
                    .deleteItem(productServerParameterValue).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductServerParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductServerParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductServerParameterValue productServerParameterValue = new ProductServerParameterValue();
        productServerParameterValue.setProductServerParameterId(Long.valueOf(id));
        ValidateObject validateObject = this.productServerParameterValueValidate.findOne(productServerParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productServerParameterValueService
                    .findOne(productServerParameterValue).conver2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
