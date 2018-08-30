package service.impl;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import mappers.ItemsCustomMapper;
import mappers.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.ItemsCustom;
import po.ItemsQueryVo;
import service.ItemsService;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    //注入mapper
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsCustomMapper.findItemsList(itemsQueryVo);
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        if (id == null) {
            return;
        }

        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        ItemsCustom itemsCustom = itemsCustomMapper.findItemById(id);
        return itemsCustom;
    }

    @Override
    public void deleteItemsById(Integer[] delete_id) throws Exception {
        itemsMapper.deleteByIds(delete_id);
    }
}
