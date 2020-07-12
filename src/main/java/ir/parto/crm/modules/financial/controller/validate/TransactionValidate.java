package ir.parto.crm.modules.financial.controller.validate;

import ir.parto.crm.modules.financial.model.entity.Transaction;
import ir.parto.crm.modules.financial.model.service.TransactionService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TransactionValidate implements ValidateInterface<Transaction> {

    private TransactionService transactionService;

    @Autowired
    public TransactionValidate(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ValidateObject validateAddNewItem(Transaction transaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(transaction == null || transaction.getGateway() == null || transaction.getGateway().isEmpty()){
            errorList.add("Gateway is required");
        }
        if(transaction == null || transaction.getAmountIn() == null ){
            errorList.add("AmoutIn is required");
        }
        if(transaction == null || transaction.getAmountOut() == null ){
            errorList.add("AmoutOut is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(Transaction transaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(transaction != null && transaction.getGateway() == null && transaction.getGateway().isEmpty()){
            errorList.add("Gateway is required");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(Transaction transaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(transaction == null || !this.transactionService.existsById(transaction.getTransactionId())){
            errorList.add("Transaction not defined");
        }

        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findOne(Transaction transaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(transaction == null || !this.transactionService.existsById(transaction.getTransactionId())){
            errorList.add("Transaction not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findById(Transaction transaction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if(transaction == null || !this.transactionService.existsById(transaction.getTransactionId())){
            errorList.add("Transaction not defined");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        return validateObject;
    }
}
