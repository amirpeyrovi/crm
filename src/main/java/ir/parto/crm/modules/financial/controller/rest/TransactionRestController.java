package ir.parto.crm.modules.financial.controller.rest;

import ir.parto.crm.modules.financial.controller.validate.TransactionValidate;
import ir.parto.crm.modules.financial.model.entity.Transaction;
import ir.parto.crm.modules.financial.model.service.TransactionService;
import ir.parto.crm.modules.payment.model.service.GatewayService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/financial/transaction")
public class TransactionRestController implements RestControllerInterface {
    private TransactionValidate transactionValidate;
    private TransactionService transactionService;
    private GatewayService gatewayService;

    @Autowired
    public TransactionRestController(TransactionValidate transactionValidate, TransactionService transactionService) {
        this.transactionValidate = transactionValidate;
        this.transactionService = transactionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Transaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("Transaction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.transactionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Transaction> productPage = this.transactionService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "Transaction", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Transaction transaction) {
        if (CheckPermission.getInstance().check("admin_add", "Transaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("Transaction - admin_add - access denied!"))
                    .getFaultResponse();
        }

        transaction.setTransactionId(null);

        ValidateObject validateObject = this.transactionValidate.validateAddNewItem(transaction);
        if (validateObject.getResult().equals("success")) {
            transaction.setGateway(this.gatewayService.findOne(transaction.getGateway()));
            return new ApiResponse("Success", Arrays.asList(this.transactionService.addNewItem(transaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
        if (CheckPermission.getInstance().check("admin_update", "Transaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("Transaction - admin_update - access denied!"))
                    .getFaultResponse();
        }

        transaction.setTransactionId(id);

        ValidateObject validateObject = this.transactionValidate.validateUpdateItem(transaction);
        if (validateObject.getResult().equals("success")) {
            try {
                transaction.setGateway(this.gatewayService.findOne(transaction.getGateway()));
                return new ApiResponse("Success", Arrays.asList(this.transactionService.updateItem(transaction)))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "Transaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("Transaction - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(id);
        ValidateObject validateObject = this.transactionValidate.deleteItem(transaction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.transactionService.deleteItem(transaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Transaction")) {
            return new ApiResponse("Error", 101, Arrays.asList("Transaction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(id);
        ValidateObject validateObject = this.transactionValidate.findOne(transaction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.transactionService.findOne(transaction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
