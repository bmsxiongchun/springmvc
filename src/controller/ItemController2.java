package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Item;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController2 {

    // 商品列表：@RequestMapping中的url建议和方法名一样，便于维护
    @RequestMapping("/queryItems")
    public ModelAndView queryItens() {
        //使用静态数据将商品信息显示在jsp页面上
        List<Item> arrayList = new ArrayList<>();

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

        arrayList.add(item_1);
        arrayList.add(item_2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", arrayList);
        modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
        return modelAndView;
    }
}
