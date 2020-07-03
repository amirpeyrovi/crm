package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.service.ProductCyclePriceService;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCyclePriceValidate implements ValidateInterface<ProductCyclePrice> {
    private ProductCyclePriceService productCyclePriceService;

    @Autowired
    public ProductCyclePriceValidate(ProductCyclePriceService productCyclePriceService) {
        this.productCyclePriceService = productCyclePriceService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductCyclePrice productCyclePrice) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productCyclePrice != null || productCyclePrice.getProductCycle() == null
                || productCyclePrice.getProductCycle().getProductCycleId() == 0) {
            errorList.add("Product Cycle Id is required");
        }

        if (productCyclePrice != null || productCyclePrice.getProduct() == null
                || productCyclePrice.getProduct().getProductId() == 0) {
            errorList.add("Product Id is required");
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

        if (productCyclePrice != null || productCyclePrice.getProductCycle() == null
                || productCyclePrice.getProductCycle().getProductCycleId() == 0) {
            errorList.add("Product Cycle Id is required");
        }

        if (productCyclePrice != null || productCyclePrice.getProduct() == null
                || productCyclePrice.getProduct().getProductId() == 0) {
            errorList.add("Product Id is required");
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
        if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
            errorList.add("Product Cycle Price Id not defined");
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
        if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
            errorList.add("Product Cycle Price Id not defined");
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
        if (!this.productCyclePriceService.existsById(productCyclePrice.getProductCyclePriceId())) {
            errorList.add("Product Cycle Price Id not defined");
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
