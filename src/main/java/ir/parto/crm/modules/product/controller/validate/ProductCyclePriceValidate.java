package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.service.*;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCyclePriceValidate implements ValidateInterface<ProductCyclePrice> {
    private ProductCyclePriceService productCyclePriceService;
    private ProductCycleService productCycleService;
    private ProductService productService;
    private ProductAddonService productAddonService;

    @Autowired
    public ProductCyclePriceValidate(ProductCyclePriceService productCyclePriceService, ProductCycleService productCycleService, ProductService productService, ProductAddonService productAddonService) {
        this.productCyclePriceService = productCyclePriceService;
        this.productCycleService = productCycleService;
        this.productService = productService;
        this.productAddonService = productAddonService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice == null) {
            errorList.add("object is null");
        } else {
            if (productCyclePrice.getProductCycle() == null) {
                errorList.add("ProductCycle object is null");
            } else {
                if (productCyclePrice.getProduct() == null && productCyclePrice.getProductAddon() == null) {
                    errorList.add("Product and ProductAddon object is null");
                } else {
                    if (productCyclePrice.getProduct() != null &&
                            !this.productService.existsById(productCyclePrice.getProduct().getProductId())) {
                        errorList.add("Product not found");
                    }

                    if (productCyclePrice.getProductAddon() != null &&
                            !this.productAddonService.existsById(productCyclePrice.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon not found");
                    }

                    if (productCyclePrice.getPrice() == null || productCyclePrice.getPrice() <= 0) {
                        errorList.add("Price is required and must be bigger than zero");
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
    public ValidateObject validateUpdateItem(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice == null) {
            errorList.add("object is null");
        } else {
            if (productCyclePrice.getProductCycle() != null && (
                    productCyclePrice.getProductCycle().getProductCycleId() == null || productCyclePrice.getProductCycle().getProductCycleId() == 0)) {
                errorList.add("ProductCycle object is null");
            } else {
                if (productCyclePrice.getProduct() != null && (productCyclePrice.getProduct() == null
                        || productCyclePrice.getProduct().getProductId() == 0)) {
                    errorList.add("Product and ProductAddon object is null");
                } else {
                    if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
                        errorList.add("ProductCyclePrice not found");
                    }

                    if (productCyclePrice.getProduct() != null &&
                            !this.productService.existsById(productCyclePrice.getProduct().getProductId())) {
                        errorList.add("Product not found");
                    }

                    if (productCyclePrice.getProductAddon() != null &&
                            !this.productAddonService.existsById(productCyclePrice.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon not found");
                    }

                    if (productCyclePrice.getPrice() == null || productCyclePrice.getPrice() <= 0) {
                        errorList.add("Price is required and must be bigger than zero");
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
    public ValidateObject deleteItem(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
                errorList.add("ProductCyclePrice not found");
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
    public ValidateObject findOne(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
                errorList.add("ProductCyclePrice not found");
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
    public ValidateObject findById(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice == null) {
            errorList.add("object is null");
        } else {
            if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
                errorList.add("ProductCyclePrice not found");
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
