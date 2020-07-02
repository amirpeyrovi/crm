package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.repository.ProductRepository;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidate implements ValidateInterface<Product> {
    private ProductService productService;

    @Autowired
    public ProductValidate(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ValidateObject validateAddNewItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(product == null || product.getTitle() == null || product.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(product == null || product.getProductGroup() == null || product.getProductGroup().getProductGroupId() == 0){
            errorList.add("Product Group is required");
        }

        Product exist = this.productService.findByTitleAndProductGroup(product.getTitle(),product.getProductGroup());
        if(product != null && exist != null){
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
    public ValidateObject validateUpdateItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        Product exist = this.productService.findByTitleAndProductGroup(product.getTitle(),product.getProductGroup());

        if(product != null || product.getTitle() == null || product.getTitle().isEmpty()){
            errorList.add("Title is required");
        }
        if(product != null || product.getProductGroup() == null || product.getProductGroup().getProductGroupId() == 0){
            errorList.add("Product Group is required");
        }
        if(product != null && exist != null && exist.getProductId() != product.getProductId()){
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
    public ValidateObject deleteItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productService.existsById(product.getProductId())){
            errorList.add("Product Id not defined");
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
    public ValidateObject findOne(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productService.existsById(product.getProductId())){
            errorList.add("Product Id not defined");
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
    public ValidateObject findById(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(!this.productService.existsById(product.getProductId())){
            errorList.add("Product Id not defined");
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
