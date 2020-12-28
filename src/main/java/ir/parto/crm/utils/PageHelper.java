package ir.parto.crm.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageHelper {

    private static PageHelper pageHelper = new PageHelper();

    private PageHelper() {
    }

    public static PageHelper getInstance() {
        return pageHelper;
    }

    public Map<String, Object> createResponse(Page page, List content) {
        Map<String, Object> map = new HashMap<>();
        map.put("totalPages", page.getTotalPages());
        map.put("totalElements", page.getTotalElements());
        map.put("size", page.getSize());
        map.put("content", content);
        return map;
    }

    public Pageable createPageable(String page) {
        Pageable pageable = null;
        pageable = PageRequest.of(Integer.parseInt(page), 20);
        return pageable;
    }

    public Pageable createPageable(String page, String size) {
        Pageable pageable = null;
        pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        return pageable;
    }

}
