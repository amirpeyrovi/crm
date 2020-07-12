package ir.parto.crm.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageableRequest {
    private static PageableRequest pageableRequest = new PageableRequest();

    private PageableRequest() {}

    public static PageableRequest getInstance() {
        return pageableRequest;
    }

    public Pageable createPageRequest(String page, String sortProperty, String sortOrder){
//        List<Sort.Order> orderArrayList = new ArrayList<>();
//        orderArrayList.add(new Sort.Order(Sort.Direction.ASC, ""));
//        Sort sort = new Sort(orderArrayList);
        return PageRequest.of(Integer.parseInt(page), 25);
    }

}
