package ir.parto.crm.modules.product.controller.validate;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.product.model.repository.ProductRepository;
import ir.parto.crm.modules.product.model.service.ProductGroupService;
import ir.parto.crm.modules.product.model.service.ProductService;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidate implements ValidateInterface<Product> {
    private ProductService productService;
    private ProductGroupService productGroupService;
    private ServerGroupService serverGroupService;
    private TicketStageService ticketStageService;
    private TicketStateService ticketStateService;

    @Autowired
    public ProductValidate(ProductService productService, ProductGroupService productGroupService,
                           ServerGroupService serverGroupService, TicketStageService ticketStageService,
                           TicketStateService ticketStateService) {
        this.productService = productService;
        this.productGroupService = productGroupService;
        this.serverGroupService = serverGroupService;
        this.ticketStageService = ticketStageService;
        this.ticketStateService = ticketStateService;
    }

    @Override
    public ValidateObject validateAddNewItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (product == null) {
            errorList.add("Object is null");
        } else {
            if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            }

            if (product.getProductGroup() == null || product.getProductGroup().getProductGroupId() == 0) {
                errorList.add("Product Group is required");
            }
            /*else {
                ProductGroup productGroup = this.productGroupService.findById(product.getProductGroup().getProductGroupId());
                if (productGroup == null || productGroup.getProductGroupId() == 0) {
                    errorList.add("Product Group not defined");
                }
            }

             */

            if (product.getServerGroup() == null || product.getServerGroup().getServerGroupId() == 0) {
                errorList.add("Server Group is required");
            }

            /*else {
                ServerGroup serverGroup = this.serverGroupService.findById(product.getServerGroup().getServerGroupId());
                if (serverGroup == null || serverGroup.getServerGroupId() == 0) {
                    errorList.add("Server Group not defined");
                }
            }

             */

            if (product.getTicketStage() == null || product.getTicketStage().getTicketStageId() == 0) {
                errorList.add("Ticket Stage is required");
            }
            /*else {
                TicketStage ticketStage = this.ticketStageService.findById(product.getTicketStage().getTicketStageId());
                if (ticketStage == null || ticketStage.getTicketStageId() == 0) {
                    errorList.add("Ticket Stage not defined");
                }
            }

             */

            if (product.getTicketState() == null || product.getTicketState().getTicketStateId() == 0) {
                errorList.add("Ticket State is required");
            }
            /*else {
                TicketState ticketState = this.ticketStateService.findById(product.getTicketState().getTicketStateId());
                if (ticketState == null || ticketState.getTicketStateId() == 0) {
                    errorList.add("Ticket Stage not defined");
                }
            }

             */
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
    public ValidateObject validateUpdateItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (product == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productService.existsById(product.getProductId())) {
                errorList.add("Product Id not defined");
            }

            if (product.getTitle() == null || product.getTitle().isEmpty()) {
                errorList.add("Title is required");
            }

            if (product.getProductGroup() == null || product.getProductGroup().getProductGroupId() == 0) {
                errorList.add("Product Group is required");
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
    public ValidateObject deleteItem(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (product == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productService.existsById(product.getProductId())) {
                errorList.add("Product Id not defined");
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
    public ValidateObject findOne(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (product == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productService.existsById(product.getProductId())) {
                errorList.add("Product Id not defined");
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
    public ValidateObject findById(Product product) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if (product == null) {
            errorList.add("Object is null");
        } else {
            if (!this.productService.existsById(product.getProductId())) {
                errorList.add("Product Id not defined");
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
