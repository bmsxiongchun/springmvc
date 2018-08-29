package service.impl;

import mappers.ItemsCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    //注入mapper
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsCustomMapper.findItemsList(itemsQueryVo);
    }
}
