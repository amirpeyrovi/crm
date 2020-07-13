package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.controller.validate.ProductAddonsLinkValidate;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductAddonsLinkService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ProductAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/productAddonsLink")
public class ProductAddonsLinkRestController implements RestControllerInterface {
    private ProductAddonsLinkValidate productAddonsLinkValidate;
    private ProductAddonValidate productAddonValidate;
    private ProductValidate productValidate;
    private ProductAddonsLinkService productAddonsLinkService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductAddonsLinkRestController(ProductAddonsLinkValidate productAddonsLinkValidate, ProductAddonValidate productAddonValidate, ProductValidate productValidate, ProductAddonsLinkService productAddonsLinkService, ProductAddonService productAddonService, ProductService productService) {
        this.productAddonsLinkValidate = productAddonsLinkValidate;
        this.productAddonValidate = productAddonValidate;
        this.productValidate = productValidate;
        this.productAddonsLinkService = productAddonsLinkService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productAddonsLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductAddonsLink> productPage = this.productAddonsLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductAddonsLink", sortProperty, sortOrder));
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
        if (CheckPermission.getInstance().check("admin_show", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productAddonsLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Product product = new Product();
            product.setProductId(id);
            ValidateObject validateObjectProduct = this.productValidate.findOne(product);
            if (validateObjectProduct.getResult().equals("success")) {
                Product productExist = this.productService.findOne(product);
                Page<ProductAddonsLink> productPage = this.productAddonsLinkService.findAllItemByProduct(productExist, PageableRequest.getInstance().createPageRequest(page, "ProductAddonsLink", sortProperty, sortOrder));
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
        if (CheckPermission.getInstance().check("admin_show", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productAddonsLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ProductAddon productAddon = new ProductAddon();
            productAddon.setProductAddonId(id);
            ValidateObject validateObjectProductAddon = this.productAddonValidate.findOne(productAddon);
            if (validateObjectProductAddon.getResult().equals("success")) {
                ProductAddon productAddonExist = this.productAddonService.findOne(productAddon);
                Page<ProductAddonsLink> productPage = this.productAddonsLinkService.findAllItemByProductAddon(productAddonExist, PageableRequest.getInstance().createPageRequest(page, "ProductAddonsLink", sortProperty, sortOrder));
                return new ApiResponse("Success", productPage)
                        .getSuccessResponse();
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
    public Object addOne(@RequestBody ProductAddonsLink productAddonsLink) {
        if (CheckPermission.getInstance().check("admin_add", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_add - access denied!"))
                    .getFaultResponse();
        }

        productAddonsLink.setProductAddonLinkId(null);

        ValidateObject validateObject = this.productAddonsLinkValidate.validateAddNewItem(productAddonsLink);
        if (validateObject.getResult().equals("success")) {
            productAddonsLink.setProduct(this.productService.findOne(productAddonsLink.getProduct()));
            productAddonsLink.setProductAddon(this.productAddonService.findOne(productAddonsLink.getProductAddon()));
            return new ApiResponse("Success", Arrays.asList(this.productAddonsLinkService.addNewItem(productAddonsLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ProductAddonsLink productAddonsLink) {
        if (CheckPermission.getInstance().check("admin_update", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_update - access denied!"))
                    .getFaultResponse();
        }

        return new ApiResponse("Error", 100, Arrays.asList("request not valid!"))
                .getFaultResponse();

//        productAddonsLink.setProductAddonLinkId(id);
//        ValidateObject validateObject = this.productAddonsLinkValidate.validateUpdateItem(productAddonsLink);
//        if (validateObject.getResult().equals("success")) {
//            try {
//                return new ApiResponse("Success", Arrays.asList(this.productAddonsLinkService.updateItem(productAddonsLink)))
//                        .getSuccessResponse();
//            } catch (InvocationTargetException e) {
//                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
//                        .getFaultResponse();
//            } catch (IllegalAccessException e) {
//                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
//                        .getFaultResponse();
//            }
//        } else {
//            return new ApiResponse("Error", 102, validateObject.getMessages())
//                    .getFaultResponse();
//        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ProductAddonsLink productAddonsLink = new ProductAddonsLink();
        productAddonsLink.setProductAddonLinkId(id);
        ValidateObject validateObject = this.productAddonsLinkValidate.deleteItem(productAddonsLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productAddonsLinkService.deleteItem(productAddonsLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ProductAddonsLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductAddonsLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ProductAddonsLink productAddonsLink = new ProductAddonsLink();
        productAddonsLink.setProductAddonLinkId(id);
        ValidateObject validateObject = this.productAddonsLinkValidate.findOne(productAddonsLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productAddonsLinkService.findOne(productAddonsLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
