package mappers;

import po.ItemsCustom;
import po.ItemsQueryVo;

import java.util.List;

public interface ItemsCustomMapper {

    //查询商品列表
    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
