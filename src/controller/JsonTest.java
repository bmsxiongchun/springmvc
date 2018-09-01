package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.ItemsCustom;

@Controller
public class JsonTest {

    @RequestMapping(value = "/requestJson", method = RequestMethod.POST)
    @ResponseBody
    public ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
        return itemsCustom;
    }

    @RequestMapping(value = "/responseJson", method = RequestMethod.POST)
    @ResponseBody
    public ItemsCustom responseJson(ItemsCustom itemsCustom) {
        return itemsCustom;
    }
 }
