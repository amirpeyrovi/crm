package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductParameterGroupValidate implements ValidateInterface<ProductParameterGroup> {
    private ProductParameterGroupService productParameterGroupService;

    @Autowired
    public ProductParameterGroupValidate(ProductParameterGroupService productParameterGroupService) {
        this.productParameterGroupService = productParameterGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductParameterGroup productParameterGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroup == null) {
            errorList.add("Object is null");
        } else {
            if (productParameterGroup.getTitle() == null || productParameterGroup.getTitle().isEmpty() || productParameterGroup.getTitle().trim().isEmpty()) {
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
    public ValidateObject validateUpdateItem(ProductParameterGroup productParameterGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productParameterGroupService.existsById(productParameterGroup.getProductParameterGroupId())) {
                errorList.add("ProductParameterGroup not defined");
            }

            if (productParameterGroup.getTitle() != null && (productParameterGroup.getTitle().isEmpty()
                    || productParameterGroup.getTitle().trim().isEmpty())) {
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
    public ValidateObject deleteItem(ProductParameterGroup productParameterGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productParameterGroupService.existsById(productParameterGroup.getProductParameterGroupId())) {
                errorList.add("ProductParameterGroup not defined");
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
    public ValidateObject findOne(ProductParameterGroup productParameterGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productParameterGroupService.existsById(productParameterGroup.getProductParameterGroupId())) {
                errorList.add("ProductParameterGroup not defined");
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
    public ValidateObject findById(ProductParameterGroup productParameterGroup) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameterGroup == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productParameterGroupService.existsById(productParameterGroup.getProductParameterGroupId())) {
                errorList.add("ProductParameterGroup not defined");
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
