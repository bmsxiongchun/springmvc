package controller;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import pojo.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemController1 implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
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

        httpServletRequest.setAttribute("itemList", list);
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
