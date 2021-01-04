package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductGroupValidate implements ValidateInterface<ProductGroup> {
    private ProductGroupService productGroupService;

    @Autowired
    public ProductGroupValidate(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductGroup productGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productGroup == null) {
            errorList.add("object is null");
        } else {
            if (productGroup.getProductGroup() != null
                    && !this.productGroupService.existsById(productGroup.getProductGroup().getProductGroupId())) {
                errorList.add("Parent ProductGroup not defined");
            }

            if (productGroup.getTitle() == null || productGroup.getTitle().isEmpty() || productGroup.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(ProductGroup productGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productGroupService.existsById(productGroup.getProductGroupId())) {
                errorList.add("ProductGroup not defined");
            } else {

                if (productGroup.getProductGroup() != null && !this.productGroupService.existsById(productGroup.getProductGroup().getProductGroupId())) {
                    errorList.add("Parent ProductGroup not defined");
                }

                if (productGroup.getTitle() != null && (productGroup.getTitle().isEmpty() || productGroup.getTitle().trim().isEmpty())) {
                    errorList.add("Title is required");
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
    public ValidateObject deleteItem(ProductGroup productGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productGroupService.existsById(productGroup.getProductGroupId())) {
                errorList.add("ProductGroup not defined");
            } else {
                List<ProductGroup> productGroupExists = this.productGroupService.findByProductGroup(productGroup);
                if (productGroupExists.size() > 0 ) {
                    errorList.add("ProductGroup is parent");
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
    public ValidateObject findOne(ProductGroup productGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productGroup == null) {
            errorList.add("object is null");
        } else {
            if (!this.productGroupService.existsById(productGroup.getProductGroupId())) {
                errorList.add("ProductGroup not defined");
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
    public ValidateObject findById(ProductGroup productGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productGroup == null) {
            errorList.add("object is null");
        } else {
            if (!this.productGroupService.existsById(productGroup.getProductGroupId())) {
                errorList.add("ProductGroup not defined");
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
