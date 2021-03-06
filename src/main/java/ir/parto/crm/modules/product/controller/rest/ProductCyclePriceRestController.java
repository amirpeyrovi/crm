package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productCyclePrice.*;
import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.controller.validate.ProductCyclePriceValidate;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductCyclePriceService;
import ir.parto.crm.modules.product.model.service.ProductCycleService;
import ir.parto.crm.modules.product.model.service.ProductService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/productCyclePrice")
public class ProductCyclePriceRestController implements RestControllerInterface {
    private ProductCyclePriceValidate productCyclePriceValidate;
    private ProductAddonValidate productAddonValidate;
    private ProductValidate productValidate;
    private ProductCyclePriceService productCyclePriceService;
    private ProductCycleService productCycleService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductCyclePriceRestController(ProductCyclePriceValidate productCyclePriceValidate, ProductAddonValidate productAddonValidate, ProductValidate productValidate, ProductCyclePriceService productCyclePriceService, ProductCycleService productCycleService, ProductAddonService productAddonService, ProductService productService) {
        this.productCyclePriceValidate = productCyclePriceValidate;
        this.productAddonValidate = productAddonValidate;
        this.productValidate = productValidate;
        this.productCyclePriceService = productCyclePriceService;
        this.productCycleService = productCycleService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productCyclePriceValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductCyclePrice> productPage = this.productCyclePriceService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductCyclePrice", sortProperty, sortOrder));
            List<ProductCyclePriceDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductCyclePriceDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
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
        if (CheckPermission.getInstance().check("admin_show", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productCyclePriceValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Product product = new Product();
            product.setProductId(Long.valueOf(id));
            ValidateObject validateObjectProduct = this.productValidate.findOne(product);
            if (validateObjectProduct.getResult().equals("success")) {
                Product productExist = this.productService.findOne(product);
                Page<ProductCyclePrice> productPage = this.productCyclePriceService.findAllItemByProduct(productExist, PageableRequest.getInstance().createPageRequest(page, "ProductCyclePrice", sortProperty, sortOrder));
                List<ProductCyclePriceDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductCyclePriceDTO.class);
                return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
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
        if (CheckPermission.getInstance().check("admin_show", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productCyclePriceValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ProductAddon productAddon = new ProductAddon();
            productAddon.setProductAddonId(Long.valueOf(id));
            ValidateObject validateObjectProductAddon = this.productAddonValidate.findOne(productAddon);
            if (validateObjectProductAddon.getResult().equals("success")) {
                ProductAddon productAddonExist = this.productAddonService.findOne(productAddon);
                Page<ProductCyclePrice> productPage = this.productCyclePriceService.findAllItemByProductAddon(productAddonExist, PageableRequest.getInstance().createPageRequest(page, "ProductCyclePrice", sortProperty, sortOrder));
                List<ProductCyclePriceDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductCyclePriceDTO.class);
                return new ApiResponse("Success", returnDTO).getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectProductAddon.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductCyclePriceAddDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();
        productCyclePrice.setProductCyclePriceId(null);

        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        if (productCyclePrice.getProduct() == null) {
            productCyclePrice.setProduct(null);
            productCyclePrice.setProductAddon(this.productAddonService.findOne(productCyclePrice.getProductAddon()));
        } else {
            productCyclePrice.setProduct(this.productService.findOne(productCyclePrice.getProduct()));
            productCyclePrice.setProductAddon(null);
        }

        ValidateObject validateObject = this.productCyclePriceValidate.validateAddNewItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.addNewItem(productCyclePrice).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Object addOneWithProduct(@RequestBody ProductCyclePriceProductAddDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();
        productCyclePrice.setProductCyclePriceId(null);

        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        if (productCyclePrice.getProduct() != null)
            productCyclePrice.setProduct(this.productService.findOne(productCyclePrice.getProduct()));
        productCyclePrice.setProductAddon(null);

        ValidateObject validateObject = this.productCyclePriceValidate.validateAddNewItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.addNewItem(productCyclePrice).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/productAddon", method = RequestMethod.POST)
    public Object addOneWithProductAddon(@RequestBody ProductCyclePriceAddonAddDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();
        productCyclePrice.setProductCyclePriceId(null);

        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        productCyclePrice.setProduct(null);
        if (productCyclePrice.getProductAddon() != null)
            productCyclePrice.setProductAddon(this.productAddonService.findOne(productCyclePrice.getProductAddon()));


        ValidateObject validateObject = this.productCyclePriceValidate.validateAddNewItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.addNewItem(productCyclePrice).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductCyclePriceEditDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();
        productCyclePrice.setProductCyclePriceId(Long.valueOf(id));
        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        if (productCyclePrice.getProduct() == null) {
            productCyclePrice.setProduct(null);
            productCyclePrice.setProductAddon(this.productAddonService.findOne(productCyclePrice.getProductAddon()));
        } else {
            productCyclePrice.setProduct(this.productService.findOne(productCyclePrice.getProduct()));
            productCyclePrice.setProductAddon(null);
        }
        ValidateObject validateObject = this.productCyclePriceValidate.validateUpdateItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService
                        .updateItem(productCyclePrice).convert2Object()))
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
    public Object updateOneWithProduct(@PathVariable("id") String id, @RequestBody ProductCyclePriceEditDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();

        productCyclePrice.setProductCyclePriceId(Long.valueOf(id));
        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        if (productCyclePrice.getProduct() != null)
            productCyclePrice.setProduct(this.productService.findOne(productCyclePrice.getProduct()));
        productCyclePrice.setProductAddon(null);
        ValidateObject validateObject = this.productCyclePriceValidate.validateUpdateItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.updateItem(productCyclePrice).convert2Object()))
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
    public Object updateOneWithProductAddon(@PathVariable("id") String id,
                                            @RequestBody ProductCyclePriceEditDTO productCyclePriceDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductCyclePrice productCyclePrice = productCyclePriceDTO.convert2Object();

        productCyclePrice.setProductCyclePriceId(Long.valueOf(id));
        if (productCyclePrice.getProductCycle() != null)
            productCyclePrice.setProductCycle(this.productCycleService.findOne(productCyclePrice.getProductCycle()));
        if (productCyclePrice.getProduct() != null)
            productCyclePrice.setProduct(this.productService.findOne(productCyclePrice.getProduct()));
        if (productCyclePrice.getProductAddon() != null)
            productCyclePrice.setProductAddon(this.productAddonService.findOne(productCyclePrice.getProductAddon()));

        ValidateObject validateObject = this.productCyclePriceValidate.validateUpdateItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService
                        .updateItem(productCyclePrice).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductCyclePrice productCyclePrice = new ProductCyclePrice();
        productCyclePrice.setProductCyclePriceId(Long.valueOf(id));
        ValidateObject validateObject = this.productCyclePriceValidate.deleteItem(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.deleteItem(productCyclePrice).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, Arrays.asList("" +
                            "Integrity constraint violated - child record")).getFaultResponse();
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
        if (CheckPermission.getInstance().check("admin_show", "ProductCyclePrice")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductCyclePrice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductCyclePrice productCyclePrice = new ProductCyclePrice();
        productCyclePrice.setProductCyclePriceId(Long.valueOf(id));
        ValidateObject validateObject = this.productCyclePriceValidate.findOne(productCyclePrice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productCyclePriceService.findOne(productCyclePrice)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
