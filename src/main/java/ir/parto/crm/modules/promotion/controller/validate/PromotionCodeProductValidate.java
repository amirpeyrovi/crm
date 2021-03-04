package ir.parto.crm.modules.promotion.controller.validate;

import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.promotion.model.entity.PromotionCodeProduct;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeProductService;
import ir.parto.crm.modules.promotion.model.service.PromotionCodeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class PromotionCodeProductValidate implements ValidateInterface<PromotionCodeProduct> {
    private PromotionCodeProductService promotionCodeProductService;
    private PromotionCodeService promotionCodeService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public PromotionCodeProductValidate(PromotionCodeProductService promotionCodeProductService, PromotionCodeService promotionCodeService, ProductAddonService productAddonService, ProductService productService) {
        this.promotionCodeProductService = promotionCodeProductService;
        this.promotionCodeService = promotionCodeService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @Override
    public ValidateObject validateAddNewItem(PromotionCodeProduct promotionCodeProduct) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCodeProduct == null) {
            errorList.add("PromotionCodeProduct object is null");
        } else {
            if (promotionCodeProduct.getProduct() == null && promotionCodeProduct.getProductAddon() == null) {
                errorList.add("Product and ProductAddon object is null");
            } else {
                if (promotionCodeProduct.getPromotionCode() == null) {
                    errorList.add("PromotionCode object is null");
                } else {
                    if (!this.promotionCodeService.existsById(promotionCodeProduct.getPromotionCode().getPromotionCodeId())) {
                        errorList.add("PromotionCode Id not defined");
                    }

                    if (promotionCodeProduct.getProductAddon() != null &&
                            !this.productAddonService.existsById(promotionCodeProduct.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon Id not defined");
                    }

                    if (promotionCodeProduct.getProduct() != null &&
                            !this.productService.existsById(promotionCodeProduct.getProduct().getProductId())) {
                        errorList.add("Product Id not defined");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(PromotionCodeProduct promotionCodeProduct) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCodeProduct == null) {
            errorList.add("PromotionCodeProduct object is null");
        } else {
            if (promotionCodeProduct.getProduct() == null && promotionCodeProduct.getProductAddon() == null) {
                errorList.add("Product and ProductAddon object is null");
            } else {
                if (promotionCodeProduct.getPromotionCode() == null) {
                    errorList.add("PromotionCode object is null");
                } else {
                    if (!this.promotionCodeProductService.existsById(promotionCodeProduct.getPromotionCodeProductId())) {
                        errorList.add("PromotionCodeProduct Id not defined");
                    }
                    if (!this.promotionCodeService.existsById(promotionCodeProduct.getPromotionCode().getPromotionCodeId())) {
                        errorList.add("PromotionCode Id not defined");
                    }

                    if (promotionCodeProduct.getProductAddon() != null &&
                            !this.productAddonService.existsById(promotionCodeProduct.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon Id not defined");
                    }

                    if (promotionCodeProduct.getProduct() != null &&
                            !this.productService.existsById(promotionCodeProduct.getProduct().getProductId())) {
                        errorList.add("Product Id not defined");
                    }
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(PromotionCodeProduct promotionCodeProduct) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCodeProduct == null) {
            errorList.add("PromotionCodeProduct object is null");
        } else {
            if (!this.promotionCodeProductService.existsById(promotionCodeProduct.getPromotionCodeProductId())) {
                errorList.add("PromotionCodeProduct Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(PromotionCodeProduct promotionCodeProduct) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCodeProduct == null) {
            errorList.add("PromotionCodeProduct object is null");
        } else {
            if (!this.promotionCodeProductService.existsById(promotionCodeProduct.getPromotionCodeProductId())) {
                errorList.add("PromotionCodeProduct Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(PromotionCodeProduct promotionCodeProduct) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (promotionCodeProduct == null) {
            errorList.add("PromotionCodeProduct object is null");
        } else {
            if (!this.promotionCodeProductService.existsById(promotionCodeProduct.getPromotionCodeProductId())) {
                errorList.add("PromotionCodeProduct Id not defined");
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
