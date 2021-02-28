package ir.parto.crm.modules.promotion.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductAddonValidate;
import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct.PromotionCodeProductAddDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct.PromotionCodeProductDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct.PromotionCodeProductEditDTO;
import ir.parto.crm.modules.promotion.controller.validate.PromotionCodeProductValidate;
import ir.parto.crm.modules.promotion.controller.validate.PromotionCodeValidate;
import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.entity.PromotionCodeProduct;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeProductService;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.PromotionAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@PromotionAnnotation
@RequestMapping("/v1/promotion/promotionCodeProduct")
public class PromotionCodeProductRestController implements RestControllerInterface {
    private PromotionCodeProductValidate promotionCodeProductValidate;
    private PromotionCodeValidate promotionCodeValidate;
    private PromotionCodeProductService promotionCodeProductService;
    private PromotionCodeService promotionCodeService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public PromotionCodeProductRestController(PromotionCodeProductValidate promotionCodeProductValidate, PromotionCodeValidate promotionCodeValidate, PromotionCodeProductService promotionCodeProductService, PromotionCodeService promotionCodeService, ProductAddonService productAddonService, ProductService productService) {
        this.promotionCodeProductValidate = promotionCodeProductValidate;
        this.promotionCodeValidate = promotionCodeValidate;
        this.promotionCodeProductService = promotionCodeProductService;
        this.promotionCodeService = promotionCodeService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.promotionCodeProductValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<PromotionCodeProduct> productPage = this.promotionCodeProductService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "PromotionCodeProduct", sortProperty, sortOrder));
            List<PromotionCodeProductDTO> returnDTO = new ArrayList();
            for (PromotionCodeProduct promotionCodeProduct : productPage.getContent()) {
                returnDTO.add(promotionCodeProduct.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/promotionCode/{id}", method = RequestMethod.GET)
    public Object findAllByPromotionCode(@PathVariable("id") String id,
                                         @RequestParam(required = false, defaultValue = "0") String page,
                                         @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                         @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.promotionCodeProductValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            PromotionCode promotionCode = new PromotionCode();
            promotionCode.setPromotionCodeId(Long.valueOf(id));
            ValidateObject validateObjectPromotionCode = this.promotionCodeValidate.findOne(promotionCode);
            if (validateObjectPromotionCode.getResult().equals("success")) {
                PromotionCode promotionCodeExist = this.promotionCodeService.findOne(promotionCode);
                Page<PromotionCodeProduct> productPage = this.promotionCodeProductService.findAllItemByPromotionCode(promotionCodeExist, PageableRequest.getInstance().createPageRequest(page, "PromotionCodeProduct", sortProperty, sortOrder));
                List<PromotionCodeProductDTO> returnDTO = new ArrayList();
                for (PromotionCodeProduct promotionCodeProduct : productPage.getContent()) {
                    returnDTO.add(promotionCodeProduct.convert2Object());
                }
                return new ApiResponse("Success",  PageHelper.getInstance().createResponse(productPage,returnDTO))
                        .getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectPromotionCode.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody PromotionCodeProductAddDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_add", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_add - access denied!"))
                    .getFaultResponse();
        }
        PromotionCodeProduct promotionCodeProduct = promotionCodeProductDTO.convert2Object();
        promotionCodeProduct.setPromotionCodeProductId(null);

        promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
        if (promotionCodeProduct.getProduct() == null) {
            promotionCodeProduct.setProduct(null);
            promotionCodeProduct.setProductAddon(this.productAddonService.findOne(promotionCodeProduct.getProductAddon()));
        } else {
            promotionCodeProduct.setProduct(this.productService.findOne(promotionCodeProduct.getProduct()));
            promotionCodeProduct.setProductAddon(null);
        }
        ValidateObject validateObject = this.promotionCodeProductValidate.validateAddNewItem(promotionCodeProduct);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.promotionCodeProductService.addNewItem(promotionCodeProduct).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Object addOneWithProduct(@RequestBody PromotionCodeProductAddDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_add", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_add - access denied!"))
                    .getFaultResponse();
        }
        PromotionCodeProduct promotionCodeProduct = promotionCodeProductDTO.convert2Object();
        promotionCodeProduct.setPromotionCodeProductId(null);

        promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
        promotionCodeProduct.setProduct(this.productService.findOne(promotionCodeProduct.getProduct()));
        promotionCodeProduct.setProductAddon(null);

        ValidateObject validateObject = this.promotionCodeProductValidate.validateAddNewItem(promotionCodeProduct);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.promotionCodeProductService.addNewItem(promotionCodeProduct)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/productAddon", method = RequestMethod.POST)
    public Object addOneWithProductAddon(@RequestBody PromotionCodeProductAddDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_add", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_add - access denied!"))
                    .getFaultResponse();
        }
        PromotionCodeProduct promotionCodeProduct = promotionCodeProductDTO.convert2Object();
        promotionCodeProduct.setPromotionCodeProductId(null);

        promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
        promotionCodeProduct.setProduct(null);
        promotionCodeProduct.setProductAddon(this.productAddonService.findOne(promotionCodeProduct.getProductAddon()));

        ValidateObject validateObject = this.promotionCodeProductValidate.validateAddNewItem(promotionCodeProduct);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.promotionCodeProductService.addNewItem(promotionCodeProduct)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long String, @RequestBody PromotionCodeProductEditDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_update", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_update - access denied!"))
                    .getFaultResponse();
        }

        return new ApiResponse("Error", 100, Arrays.asList("request not valid!"))
                .getFaultResponse();

//        promotionCodeProduct.setPromotionCodeProductId(id);
//        ValidateObject validateObject = this.promotionCodeProductValidate.validateUpdateItem(promotionCodeProduct);
//        if (validateObject.getResult().equals("success")) {
//            try {
//                promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
//                if (promotionCodeProduct.getProduct() == null) {
//                    promotionCodeProduct.setProduct(null);
//                    promotionCodeProduct.setProductAddon(this.productAddonService.findOne(promotionCodeProduct.getProductAddon()));
//                } else {
//                    promotionCodeProduct.setProduct(this.productService.findOne(promotionCodeProduct.getProduct()));
//                    promotionCodeProduct.setProductAddon(null);
//                }
//                return new ApiResponse("Success", Arrays.asList(this.promotionCodeProductService.updateItem(promotionCodeProduct)))
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

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public Object updateOneWithProduct(@PathVariable("id") String id, @RequestBody PromotionCodeProductEditDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_update", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_update - access denied!"))
                    .getFaultResponse();
        }

        return new ApiResponse("Error", 100, Arrays.asList("request not valid!"))
                .getFaultResponse();

//        promotionCodeProduct.setPromotionCodeProductId(id);
//        ValidateObject validateObject = this.promotionCodeProductValidate.validateUpdateItem(promotionCodeProduct);
//        if (validateObject.getResult().equals("success")) {
//            try {
//                promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
//                promotionCodeProduct.setProduct(this.productService.findOne(promotionCodeProduct.getProduct()));
//                promotionCodeProduct.setProductAddon(null);
//                return new ApiResponse("Success", Arrays.asList(this.promotionCodeProductService.updateItem(promotionCodeProduct)))
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

    @RequestMapping(value = "/productAddon/{id}", method = RequestMethod.PUT)
    public Object updateOneWithProductAddon(@PathVariable("id") Long id,
                                            @RequestBody PromotionCodeProductEditDTO promotionCodeProductDTO) {
        if (CheckPermission.getInstance().check("admin_update", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_update - access denied!"))
                    .getFaultResponse();
        }

        return new ApiResponse("Error", 100, Arrays.asList("request not valid!"))
                .getFaultResponse();

//        promotionCodeProduct.setPromotionCodeProductId(id);
//        ValidateObject validateObject = this.promotionCodeProductValidate.validateUpdateItem(promotionCodeProduct);
//        if (validateObject.getResult().equals("success")) {
//            try {
//                promotionCodeProduct.setPromotionCode(this.promotionCodeService.findOne(promotionCodeProduct.getPromotionCode()));
//                promotionCodeProduct.setProduct(null);
//                promotionCodeProduct.setProductAddon(this.productAddonService.findOne(promotionCodeProduct.getProductAddon()));
//                return new ApiResponse("Success", Arrays.asList(this.promotionCodeProductService.updateItem(promotionCodeProduct)))
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
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        PromotionCodeProduct promotionCodeProduct = new PromotionCodeProduct();
        promotionCodeProduct.setPromotionCodeProductId(Long.valueOf(id));
        ValidateObject validateObject = this.promotionCodeProductValidate.deleteItem(promotionCodeProduct);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.promotionCodeProductService.deleteItem(promotionCodeProduct).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("" +
                            "Integrity constraint violated - child record"))).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("An error occurred during the Delete")))
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
        if (CheckPermission.getInstance().check("admin_show", "PromotionCodeProduct")) {
            return new ApiResponse("Error", 101, Arrays.asList("PromotionCodeProduct - admin_show - access denied!"))
                    .getFaultResponse();
        }

        PromotionCodeProduct promotionCodeProduct = new PromotionCodeProduct();
        promotionCodeProduct.setPromotionCodeProductId(Long.valueOf(id));
        ValidateObject validateObject = this.promotionCodeProductValidate.findOne(promotionCodeProduct);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(
                    this.promotionCodeProductService.findOne(promotionCodeProduct).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
