package ir.parto.crm.modules.financial.controller.rest;

import ir.parto.crm.modules.financial.controller.validate.InvoiceValidate;
import ir.parto.crm.modules.financial.model.entity.Invoice;
import ir.parto.crm.modules.financial.model.service.InvoiceService;
import ir.parto.crm.modules.order.model.service.OrderService;
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
@RequestMapping("/v1/financial/invoice")
public class InvoiceRestController implements RestControllerInterface {
    private InvoiceService invoiceService;
    private InvoiceValidate invoiceValidate;
    private OrderService orderService;

    @Autowired
    public InvoiceRestController(InvoiceService invoiceService, InvoiceValidate invoiceValidate, OrderService orderService) {
        this.invoiceService = invoiceService;
        this.invoiceValidate = invoiceValidate;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Invoice")) {
            return new ApiResponse("Error", 101, Arrays.asList("Invoice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.invoiceValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Invoice> invoicePage = this.invoiceService.findAllItem(PageableRequest.getInstance().
                    createPageRequest(page, "Invoice", sortProperty, sortOrder));
            return new ApiResponse("Success", invoicePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Invoice invoice) {
        if (CheckPermission.getInstance().check("admin_add", "Invoice")) {
            invoice.setOrder(this.orderService.findOne(invoice.getOrder()));
            return new ApiResponse("Error", 101, Arrays.asList("Invoice - admin_add - access denied!"))
                    .getFaultResponse();
        }

        invoice.setInvoiceId(null);

        ValidateObject validateObject = this.invoiceValidate.validateAddNewItem(invoice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.invoiceService.addNewItem(invoice)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Invoice invoice) {
        if (CheckPermission.getInstance().check("admin_update", "Invoice")) {
            return new ApiResponse("Error", 101, Arrays.asList("Invoice - admin_update - access denied!"))
                    .getFaultResponse();
        }

        invoice.setInvoiceId(id);

        ValidateObject validateObject = this.invoiceValidate.validateUpdateItem(invoice);
        if (validateObject.getResult().equals("success")) {
            try {
                invoice.setOrder(this.orderService.findOne(invoice.getOrder()));
                return new ApiResponse("Success", Arrays.asList(this.invoiceService.updateItem(invoice)))
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
        if (CheckPermission.getInstance().check("admin_delete", "Invoice")) {
            return new ApiResponse("Error", 101, Arrays.asList("Invoice - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(id);
        ValidateObject validateObject = this.invoiceValidate.deleteItem(invoice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.invoiceService.deleteItem(invoice)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Invoice")) {
            return new ApiResponse("Error", 101, Arrays.asList("Invoice - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(id);
        ValidateObject validateObject = this.invoiceValidate.findOne(invoice);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.invoiceService.findOne(invoice)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
