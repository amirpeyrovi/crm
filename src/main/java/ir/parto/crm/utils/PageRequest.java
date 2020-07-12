package ir.parto.crm.utils;

import org.springframework.stereotype.Component;

@Component
public class PageRequest {
    private static PageRequest pageRequest = new PageRequest();

    private PageRequest() {}

    public static PageRequest getInstance() {
        return pageRequest;
    }
}
