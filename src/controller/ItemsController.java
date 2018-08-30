package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    //注入Service
    @Autowired
    private ItemsService itemsService;

    //自定义属性编辑器

//    /**
//     * 第一种方式
//     * @param webDataBinder
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        //Date.class必须是与controller方法形参pojo属性一致的date类型
//        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
//    }

    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {
        //调用service来查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }

//    @RequestMapping(value = "/editItems", method = RequestMethod.GET)
//    public ModelAndView editItems() throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//
//        ItemsCustom itemsCustom = itemsService.findItemsById(1);
//        modelAndView.addObject("item", itemsCustom);
//        modelAndView.setViewName("editItem");
//        return modelAndView;
//    }

//    @RequestMapping(value = "/editItems", method = RequestMethod.GET)
//    public String editItems(Model model) throws Exception {
//
//
//        ItemsCustom itemsCustom = itemsService.findItemsById(1);
//        model.addAttribute("itemsCustom", itemsCustom);
//
//        return "editItem";
//    }

    @RequestMapping(value = "/editItems", method = RequestMethod.GET)
    public void editItems(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(required = false, defaultValue = "1") Integer id) throws Exception {

        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        httpServletRequest.setAttribute("item", itemsCustom);

        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/editItem2.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = "/editItemSubmit", method = RequestMethod.POST)
    public String editItemSubmit(Integer id, ItemsCustom itemsCustom) throws Exception {

        itemsService.updateItems(id, itemsCustom);
        /**
         * 请求转发，使用forward进行转发，request数据可以共享，url地址栏不会
         * return "forward:queryItems.action";
         */
        /**
         * 使用redirect进行请求重定向，request数据不会共享，url地址栏会发生变化
         */
        return "redirect:queryItems.action";
    }

//    @RequestMapping(value = "/editItemSubmit", method = RequestMethod.POST)
//    public String editItemSubmit(Integer id, ItemsCustom itemsCustom, ItemsQueryVo itemsQueryVo) throws Exception {
//
//        itemsService.updateItems(id, itemsCustom);
//        /**
//         * 请求转发，使用forward进行转发，request数据可以共享，url地址栏不会
//         * return "forward:queryItems.action";
//         */
//        /**
//         * 使用redirect进行请求重定向，request数据不会共享，url地址栏会发生变化
//         */
//        return "redirect:queryItems.action";
//    }
}
