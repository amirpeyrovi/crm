package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.service.ProductAddonService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductAddonValidate implements ValidateInterface<ProductAddon> {
    private ProductAddonService productAddonService;

    @Autowired
    public ProductAddonValidate(ProductAddonService productAddonService) {
        this.productAddonService = productAddonService;
    }

    @Override
    public ValidateObject validateAddNewItem(ProductAddon productAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(productAddon == null || productAddon.getTitle() == null || productAddon.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(productAddon == null || productAddon.getProductGroup() == null || productAddon.getProductGroup().getProductGroupId() == 0){
            errorList.add("Product Group is required");
        }

        ProductAddon exist = this.productAddonService.findByTitleAndProductGroup(productAddon.getTitle(),productAddon.getProductGroup());
        if(productAddon != null && exist != null){
            errorList.add("Title is repetetive in Product Group");
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(ProductAddon productAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        ProductAddon exist = this.productAddonService.findByTitleAndProductGroup(productAddon.getTitle(),productAddon.getProductGroup());

        if(productAddon != null || productAddon.getTitle() == null || productAddon.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(productAddon != null || productAddon.getProductGroup() == null || productAddon.getProductGroup().getProductGroupId() == 0){
            errorList.add("Product Group is required");
        }
        if(productAddon != null && exist != null && exist.getProductAddonId() !=productAddon.getProductAddonId()){
            errorList.add("Title is repetetive in Product Group");
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(ProductAddon productAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productAddonService.existsById(productAddon.getProductAddonId())){
            errorList.add("Product Addon Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(ProductAddon productAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productAddonService.existsById(productAddon.getProductAddonId())){
            errorList.add("Product Addon Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(ProductAddon productAddon) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productAddonService.existsById(productAddon.getProductAddonId())){
            errorList.add("Product Addon Id not defined");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if(errorList.size()>0){
            validateObject.setResult("error");
        }else{
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
