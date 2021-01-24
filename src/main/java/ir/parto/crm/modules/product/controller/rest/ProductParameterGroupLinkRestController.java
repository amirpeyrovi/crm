package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink.ProductParameterGroupLinkAddDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink.ProductParameterGroupLinkDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink.ProductParameterGroupLinkEditDTO;
import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.controller.validate.ProductParameterGroupLinkValidate;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupLinkService;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
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
import java.util.Arrays;
import java.util.List;

@RestController
@ProductAnnotation
@RequestMapping("/v1/product/ProductParameterGroupLink")
public class ProductParameterGroupLinkRestController implements RestControllerInterface {
    private ProductParameterGroupLinkValidate productParameterGroupLinkValidate;
    private ProductAddonValidate productAddonValidate;
    private ProductValidate productValidate;
    private ProductParameterGroupLinkService productParameterGroupLinkService;
    private ProductParameterGroupService productParameterGroupService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductParameterGroupLinkRestController(ProductParameterGroupLinkValidate productParameterGroupLinkValidate, ProductAddonValidate productAddonValidate, ProductValidate productValidate, ProductParameterGroupLinkService productParameterGroupLinkService, ProductParameterGroupService productParameterGroupService, ProductAddonService productAddonService, ProductService productService) {
        this.productParameterGroupLinkValidate = productParameterGroupLinkValidate;
        this.productAddonValidate = productAddonValidate;
        this.productValidate = productValidate;
        this.productParameterGroupLinkService = productParameterGroupLinkService;
        this.productParameterGroupService = productParameterGroupService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterGroupLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ProductParameterGroupLink> productPage = this.productParameterGroupLinkService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ProductParameterGroupLink", sortProperty, sortOrder));
            List<ProductParameterGroupLinkDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductParameterGroupLinkDTO.class);
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
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterGroupLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Product product = new Product();
            product.setProductId(Long.valueOf(id));
            ValidateObject validateObjectProduct = this.productValidate.findOne(product);
            if (validateObjectProduct.getResult().equals("success")) {
                Product productExist = this.productService.findOne(product);
                Page<ProductParameterGroupLink> productPage = this.productParameterGroupLinkService.findAllItemByProduct(productExist, PageableRequest.getInstance().createPageRequest(page, "ProductParameterGroupLink", sortProperty, sortOrder));
                List<ProductParameterGroupLinkDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductParameterGroupLinkDTO.class);
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
        if (CheckPermission.getInstance().check("admin_show", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.productParameterGroupLinkValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ProductAddon productAddon = new ProductAddon();
            productAddon.setProductAddonId(Long.valueOf(id));
            ValidateObject validateObjectProduct = this.productAddonValidate.findOne(productAddon);
            if (validateObjectProduct.getResult().equals("success")) {
                ProductAddon productAddonExist = this.productAddonService.findOne(productAddon);
                Page<ProductParameterGroupLink> productPage = this.productParameterGroupLinkService.findAllItemByProductAddon(productAddonExist, PageableRequest.getInstance().createPageRequest(page, "ProductParameterGroupLink", sortProperty, sortOrder));
                List<ProductParameterGroupLinkDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ProductParameterGroupLinkDTO.class);
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

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ProductParameterGroupLinkAddDTO productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(null);
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        if (productParameterGroupLink.getProduct() == null) {
            productParameterGroupLink.setProduct(null);
            productParameterGroupLink.setProductAddon(this.productAddonService.findOne(productParameterGroupLink.getProductAddon()));
        } else {
            productParameterGroupLink.setProduct(this.productService.findOne(productParameterGroupLink.getProduct()));
            productParameterGroupLink.setProductAddon(null);
        }
        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateAddNewItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.productParameterGroupLinkService.addNewItem(productParameterGroupLink).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Object addOneWithProduct(@RequestBody ProductParameterGroupLinkAddDTO productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(null);
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        productParameterGroupLink.setProduct(this.productService.findOne(productParameterGroupLink.getProduct()));
        productParameterGroupLink.setProductAddon(null);
        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateAddNewItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.productParameterGroupLinkService.addNewItem(productParameterGroupLink).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/productAddon", method = RequestMethod.POST)
    public Object addOneWithProductAddon(@RequestBody ProductParameterGroupLinkAddDTO productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(null);
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        productParameterGroupLink.setProduct(null);
        productParameterGroupLink.setProductAddon(this.productAddonService.findOne(productParameterGroupLink.getProductAddon()));

        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateAddNewItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupLinkService.addNewItem(productParameterGroupLink)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ProductParameterGroupLinkEditDTO
            productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(Long.valueOf(id));
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        if (productParameterGroupLink.getProduct() == null) {
            productParameterGroupLink.setProduct(null);
            productParameterGroupLink.setProductAddon(this.productAddonService.findOne(productParameterGroupLink.getProductAddon()));
        } else {
            productParameterGroupLink.setProduct(this.productService.findOne(productParameterGroupLink.getProduct()));
            productParameterGroupLink.setProductAddon(null);
        }
        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateUpdateItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.productParameterGroupLinkService.updateItem(productParameterGroupLink).convert2Object()))
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
    public Object updateOneWithProduct(@PathVariable("id") String id, @RequestBody ProductParameterGroupLinkEditDTO
            productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(Long.valueOf(id));
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        productParameterGroupLink.setProduct(this.productService.findOne(productParameterGroupLink.getProduct()));
        productParameterGroupLink.setProductAddon(null);
        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateUpdateItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.productParameterGroupLinkService.updateItem(productParameterGroupLink).convert2Object()))
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
    public Object updateOneWithProductAddon(@PathVariable("id") String id, @RequestBody ProductParameterGroupLinkEditDTO
            productParameterGroupLinkDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ProductParameterGroupLink")) {
            return new ApiResponse("Error", 101, Arrays.asList("ProductParameterGroupLink - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ProductParameterGroupLink productParameterGroupLink = productParameterGroupLinkDTO.convert2Object();
        productParameterGroupLink.setProductParameterGroupLinkId(Long.valueOf(id));
        productParameterGroupLink.setProductParameterGroup(this.productParameterGroupService.findOne(productParameterGroupLink.getProductParameterGroup()));
        productParameterGroupLink.setProduct(null);
        productParameterGroupLink.setProductAddon(this.productAddonService.findOne(productParameterGroupLink.getProductAddon()));

        ValidateObject validateObject = this.productParameterGroupLinkValidate.validateUpdateItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.productParameterGroupLinkService.updateItem(productParameterGroupLink).convert2Object()))
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

        ProductParameterGroupLink productParameterGroupLink = new ProductParameterGroupLink();
        productParameterGroupLink.setProductParameterGroupLinkId(Long.valueOf(id));
        ValidateObject validateObject = this.productParameterGroupLinkValidate.deleteItem(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success",
                        Arrays.asList(this.productParameterGroupLinkService.deleteItem(productParameterGroupLink).convert2Object()))
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

        ProductParameterGroupLink productParameterGroupLink = new ProductParameterGroupLink();
        productParameterGroupLink.setProductParameterGroupLinkId(Long.valueOf(id));
        ValidateObject validateObject = this.productParameterGroupLinkValidate.findOne(productParameterGroupLink);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.productParameterGroupLinkService.findOne(productParameterGroupLink)
                    .convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
