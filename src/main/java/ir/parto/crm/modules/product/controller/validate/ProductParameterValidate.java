package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.service.ProductParameterGroupService;
import ir.parto.crm.modules.product.model.service.ProductParameterService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductParameterValidate implements ValidateInterface<ProductParameter> {
    private ProductParameterService productParameterService;
    private ProductParameterGroupService productParameterGroupService;

    @Autowired
    public ProductParameterValidate(ProductParameterService productParameterService, ProductParameterGroupService productParameterGroupService) {
        this.productParameterService = productParameterService;
        this.productParameterGroupService = productParameterGroupService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductParameter productParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameter == null) {
            errorList.add("ProductParameter object is null");
        } else {
            if (productParameter.getProductParameterGroup() == null) {
                errorList.add("ProductParameterGroup object is null");
            } else {
                if (!this.productParameterGroupService.existsById(productParameter.getProductParameterGroup().getProductParameterGroupId())) {
                    errorList.add("ProductParameterGroup not defined");
                }

                if (productParameter.getTitle() == null || productParameter.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (productParameter.getType() == null || productParameter.getType().isEmpty()) {
                    errorList.add("Type is required");
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
    public ValidateObject validateUpdateItem(ProductParameter productParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameter == null) {
            errorList.add("ProductParameter object is null");
        } else {
            if (productParameter.getProductParameterGroup() == null) {
                errorList.add("ProductParameterGroup object is null");
            } else {
                if (!this.productParameterService.existsById(productParameter.getProductParameterId())) {
                    errorList.add("ProductParameter not defined");
                }

                if (!this.productParameterGroupService.existsById(productParameter.getProductParameterGroup().getProductParameterGroupId())) {
                    errorList.add("ProductParameterGroup not defined");
                }

                if (productParameter.getTitle() == null || productParameter.getTitle().isEmpty()) {
                    errorList.add("Title is required");
                }

                if (productParameter.getType() == null || productParameter.getType().isEmpty()) {
                    errorList.add("Type is required");
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
    public ValidateObject deleteItem(ProductParameter productParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameter == null) {
            errorList.add("ProductParameter object is null");
        } else {
            if (!this.productParameterService.existsById(productParameter.getProductParameterId())) {
                errorList.add("ProductParameter not defined");
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
    public ValidateObject findOne(ProductParameter productParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameter == null) {
            errorList.add("ProductParameter object is null");
        } else {
            if (!this.productParameterService.existsById(productParameter.getProductParameterId())) {
                errorList.add("ProductParameter not defined");
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
    public ValidateObject findById(ProductParameter productParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productParameter == null) {
            errorList.add("ProductParameter object is null");
        } else {
            if (!this.productParameterService.existsById(productParameter.getProductParameterId())) {
                errorList.add("ProductParameter not defined");
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
