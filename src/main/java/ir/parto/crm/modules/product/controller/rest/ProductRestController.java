package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product/product")
public class ProductRestController implements RestControllerInterface {
    private ProductValidate productValidate;

    @Autowired
    public ProductRestController(ProductValidate productValidate) {
        this.productValidate = productValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllProduct(@RequestParam(required = false, defaultValue = "0") String page,
                                 @RequestParam(required = false, defaultValue = "0") String sort){
        ValidateObject validateObject = this.productValidate.findAll();
        if(validateObject.getResult().equals("success")){
            return null;
        }else{
            return null;
        }
    }

}
