package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.modules.product.model.service.ProductAddonsLinkService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class ProductAddonsLinkValidate implements ValidateInterface<ProductAddonsLink> {
    private ProductAddonsLinkService productAddonsLinkService;
    private ProductAddonService productAddonService;
    private ProductService productService;

    @Autowired
    public ProductAddonsLinkValidate(ProductAddonsLinkService productAddonsLinkService, ProductAddonService productAddonService, ProductService productService) {
        this.productAddonsLinkService = productAddonsLinkService;
        this.productAddonService = productAddonService;
        this.productService = productService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductAddonsLink productAddonsLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productAddonsLink == null) {
            errorList.add("object is nul");
        } else {
            if (productAddonsLink.getProductAddon() == null) {
                errorList.add("ProductAddon object is null");
            } else {
                if (productAddonsLink.getProduct() == null) {
                    errorList.add("Product object is null");
                } else {
                    if (!this.productAddonService.existsById(productAddonsLink.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon not found");
                    }

                    if (!this.productService.existsById(productAddonsLink.getProduct().getProductId())) {
                        errorList.add("Product not found");
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
    public ValidateObject validateUpdateItem(ProductAddonsLink productAddonsLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productAddonsLink == null) {
            errorList.add("object is nul");
        } else {
            if (productAddonsLink.getProductAddon() == null) {
                errorList.add("ProductAddon object is null");
            } else {
                if (productAddonsLink.getProduct() == null) {
                    errorList.add("Product object is null");
                } else {
                    if (!this.productAddonsLinkService.existsById(productAddonsLink.getProductAddonLinkId())) {
                        errorList.add("ProductAddonLink not found");
                    }

                    if (!this.productAddonService.existsById(productAddonsLink.getProductAddon().getProductAddonId())) {
                        errorList.add("ProductAddon not found");
                    }

                    if (!this.productService.existsById(productAddonsLink.getProduct().getProductId())) {
                        errorList.add("Product not found");
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
    public ValidateObject deleteItem(ProductAddonsLink productAddonsLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productAddonsLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.productAddonsLinkService.existsById(productAddonsLink.getProductAddonLinkId())) {
                errorList.add("ProductAddonLink not found");
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
    public ValidateObject findOne(ProductAddonsLink productAddonsLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productAddonsLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.productAddonsLinkService.existsById(productAddonsLink.getProductAddonLinkId())) {
                errorList.add("ProductAddonLink not found");
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
    public ValidateObject findById(ProductAddonsLink productAddonsLink) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (productAddonsLink == null) {
            errorList.add("object is null");
        } else {
            if (!this.productAddonsLinkService.existsById(productAddonsLink.getProductAddonLinkId())) {
                errorList.add("ProductAddonLink not found");
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
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
