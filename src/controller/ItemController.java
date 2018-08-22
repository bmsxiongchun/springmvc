package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import pojo.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements Controller {


    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        List<Item> list = new ArrayList<>();

        Item item_1 = new Item();
        item_1.setName("Hello");
        item_1.setDetail("This is Detail");
        item_1.setId(1);
        item_1.setPrice(1000f);

        Item item_2 = new Item();
        item_1.setName("Hello...");
        item_1.setDetail("This is second Detail");
        item_1.setId(2);
        item_1.setPrice(5000f);

        list.add(item_1);
        list.add(item_2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", list);
        modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
        return modelAndView;
    }
}
