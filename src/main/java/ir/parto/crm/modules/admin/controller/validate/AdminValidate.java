package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminValidate implements ValidateInterface<Admin> {
    private AdminService adminService;

    @Autowired
    public AdminValidate(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public ValidateObject validateAddNewItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(admin == null){
            errorList.add("Admin information is required");
        }else{
            if(admin.getFirstName() == null || admin.getFirstName().isEmpty() ){
                errorList.add("First Name is required");
            }

            if(admin.getLastName() == null || admin.getLastName().isEmpty() ){
                errorList.add("Last Name is required");
            }

            if(admin.getUsername() == null || admin.getUsername().isEmpty()){
                errorList.add("Username is required");
            }

            if(admin.getPassword() == null || admin.getPassword().isEmpty()){
                errorList.add("Password is required");
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
    public ValidateObject validateUpdateItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(admin == null){
            errorList.add("Admin information is required");
        }else{
            if(this.adminService.existsById(admin.getAdminId())){
                errorList.add("Admin is not defined!");
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
    public ValidateObject deleteItem(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(admin == null){
            errorList.add("Admin information is required");
        }else{
            if(this.adminService.existsById(admin.getAdminId())){
                errorList.add("Admin is not defined!");
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
    public ValidateObject findOne(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(admin == null){
            errorList.add("Admin information is required");
        }else{
            if(this.adminService.existsById(admin.getAdminId())){
                errorList.add("Admin is not defined!");
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
    public ValidateObject findById(Admin admin) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(admin == null){
            errorList.add("Admin information is required");
        }else{
            if(this.adminService.existsById(admin.getAdminId())){
                errorList.add("Admin is not defined!");
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
