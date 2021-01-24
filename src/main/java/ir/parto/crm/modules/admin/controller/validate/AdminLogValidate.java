package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminLog;
import ir.parto.crm.modules.admin.model.service.AdminLogService;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminLogValidate implements ValidateInterface<AdminLog> {
    private AdminLogService adminLogService;
    private AdminService adminService;

    @Autowired
    public AdminLogValidate(AdminLogService adminLogService,  AdminService adminService) {
        this.adminLogService = adminLogService;
        this.adminService = adminService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminLog adminLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminLog == null){
            errorList.add("Admin Log information is required");
        }else{
            if(adminLog.getIpAddress() == null || adminLog.getIpAddress().trim().isEmpty() ){
                errorList.add("Ip Address is required");
            }
            if(adminLog.getLog() == null || adminLog.getLog().trim().isEmpty() ){
                errorList.add("Log is required");
            }
            if(adminLog.getUsername() == null || adminLog.getUsername().trim().isEmpty() ){
                errorList.add("Username is required");
            }
            if(adminLog.getAdmin() == null || adminLog.getAdmin().getAdminId() == 0 || adminLog.getAdmin().getAdminId() == null){
                errorList.add("Admin is required");
            }else{
                if (!this.adminService.existsById(adminLog.getAdmin().getAdminId())) {
                    errorList.add("Admin not found");
                }
            }
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
    public ValidateObject validateUpdateItem(AdminLog adminLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminLog == null){
            errorList.add("Admin Log information is required");
        }else{
            if(!this.adminLogService.existsById(adminLog.getAdminLogId())){
                errorList.add("Admin Log is not defined!");
            }
            if(adminLog.getIpAddress() != null && adminLog.getIpAddress().trim().isEmpty() ){
                errorList.add("Ip Address is required");
            }
            if(adminLog.getLog() != null && adminLog.getLog().trim().isEmpty() ){
                errorList.add("Log is required");
            }
            if(adminLog.getUsername() != null && adminLog.getUsername().trim().isEmpty() ){
                errorList.add("Username is required");
            }
            if(adminLog.getAdmin() != null && (adminLog.getAdmin().getAdminId() == 0 || adminLog.getAdmin().getAdminId() == null)){
                errorList.add("Admin is required");
            }else if(adminLog.getAdmin() != null){
                if (!this.adminService.existsById(adminLog.getAdmin().getAdminId())) {
                    errorList.add("Admin not found");
                }
            }
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
    public ValidateObject deleteItem(AdminLog adminLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminLog == null){
            errorList.add("Admin Log information is required");
        }else{
            if(!this.adminLogService.existsById(adminLog.getAdminLogId())){
                errorList.add("Admin Log is not defined!");
            }
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
    public ValidateObject findOne(AdminLog adminLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminLog == null){
            errorList.add("Admin Log information is required");
        }else{
            if(!this.adminLogService.existsById(adminLog.getAdminLogId())){
                errorList.add("Admin Log is not defined!");
            }
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
    public ValidateObject findById(AdminLog adminLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminLog == null){
            errorList.add("Admin Log information is required");
        }else{
            if(!this.adminLogService.existsById(adminLog.getAdminLogId())){
                errorList.add("Admin Log is not defined!");
            }
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
