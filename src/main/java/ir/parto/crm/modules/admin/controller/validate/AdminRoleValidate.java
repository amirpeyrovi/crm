package ir.parto.crm.modules.admin.controller.validate;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class AdminRoleValidate implements ValidateInterface<AdminRole> {
    private AdminRoleService adminRoleService;

    @Autowired
    public AdminRoleValidate(AdminRoleService adminRoleService) {
        this.adminRoleService = adminRoleService;
    }

    @Override
    public ValidateObject validateAddNewItem(AdminRole adminRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRole == null){
            errorList.add("Admin Role information is required");
        }else{
            if(adminRole.getTitle() == null || adminRole.getTitle().isEmpty() ){
                errorList.add("Title is required");
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
    public ValidateObject validateUpdateItem(AdminRole adminRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRole == null){
            errorList.add("Admin Role information is required");
        }else{
            if(this.adminRoleService.existsById(adminRole.getAdminRoleId())){
                errorList.add("Admin Role is not defined!");
            }

            if(adminRole.getTitle() == null || adminRole.getTitle().isEmpty() ){
                errorList.add("Title is required");
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
    public ValidateObject deleteItem(AdminRole adminRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRole == null){
            errorList.add("Role information is required");
        }else{
            if(!this.adminRoleService.existsById(adminRole.getAdminRoleId())){
                errorList.add("Role is not defined!");
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
    public ValidateObject findOne(AdminRole adminRole) {

        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRole == null){
            errorList.add("Role information is required");
        }else{
            if(!this.adminRoleService.existsById(adminRole.getAdminRoleId())){
                errorList.add("Role is not defined!");
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
    public ValidateObject findById(AdminRole adminRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        if(adminRole == null){
            errorList.add("Role information is required");
        }else{
            if(!this.adminRoleService.existsById(adminRole.getAdminRoleId())){
                errorList.add("Role is not defined!");
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
