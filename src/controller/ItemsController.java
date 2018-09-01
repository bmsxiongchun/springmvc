package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;
import utils.ValidGroup1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @ModelAttribute("itemsType")
    public Map<String, String> getItemsType() {
        HashMap<String, String> itemsType = new HashMap<>();
        itemsType.put("001", "data type");
        itemsType.put("002", "cloths");

        return itemsType;
    }

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
    public String editItemSubmit(Model model, Integer id, @Validated(value = {ValidGroup1.class}) @ModelAttribute(value = "itemsCustom") ItemsCustom itemsCustom, BindingResult bindingResult, MultipartFile pictureFile) throws Exception {

        //输出校验错误信息
        //如果参数绑定时出单
        if (bindingResult.hasErrors()) {
            //获取错误
            List<ObjectError> allErrors = bindingResult.getAllErrors();

            model.addAttribute("errors", allErrors);

            for (ObjectError error : allErrors) {
                System.out.println(error.getDefaultMessage());
            }

            //如果校验错误，仍然返回到修改信息页面
            return "editItem";
        }

        //进行数据回显
        model.addAttribute("id", id);
//        model.addAttribute("item", itemsCustom);

        //上传图片
        if (pictureFile != null && pictureFile.getOriginalFilename() != null && pictureFile.getOriginalFilename().length() > 0) {
            //图片上传成功之后将地址上传至数据库
            String filePath = "C:\\Users\\bonree\\Pictures";
            String originalFilename = pictureFile.getOriginalFilename();

            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

            //新文件
            File file = new File(filePath + newFileName);

            //将内存中的文件存入磁盘
            pictureFile.transferTo(file);

            itemsCustom.setPic(newFileName);
        }

        itemsService.updateItems(id, itemsCustom);
        /**
         * 请求转发，使用forward进行转发，request数据可以共享，url地址栏不会
         * return "forward:queryItems.action";
         */
        /**
         * 使用redirect进行请求重定向，request数据不会共享，url地址栏会发生变化
         */
        return "editItem";
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

    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] delete_id) throws Exception {
        itemsService.deleteItemsById(delete_id);
        return "success";
    }

    //批量修改商品
    @RequestMapping("/editItemsList")
    public ModelAndView editItemList() throws Exception {
        //调用service查询商品信息
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("editItemsList");

        return modelAndView;
    }

    //批量修改商品的提交
    @RequestMapping("/editItemsListSubmit")
    public String editItemsListSubmit(ItemsQueryVo itemsQueryVo) {
        return "success";
    }
}
