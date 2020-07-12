package ir.parto.crm.modules.product.controller.rest;

import ir.parto.crm.modules.product.controller.validate.ProductValidate;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product/product")
public class ProductRestController implements RestControllerInterface {
    private ProductValidate productValidate;
    private ProductService productService;

    @Autowired
    public ProductRestController(ProductValidate productValidate, ProductService productService) {
        this.productValidate = productValidate;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllProduct(@RequestParam(required = false, defaultValue = "0") String page,
                                 @RequestParam(required = false, defaultValue = "default") String sort,
                                 @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Product")) {
            return "access denied";
        }
        ValidateObject validateObject = this.productValidate.findAll();
        if (validateObject.getResult().equals("success")) {
//            Pageable pageable = PageRequest.of(Integer.parseInt(page), 25, new Sort(Sort.Direction.ASC));
            return this.productService.findAllItem();
        } else {
            return null;
        }
    }

}
