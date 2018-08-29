package mappers;

import po.ItemsCustom;
import po.ItemsQueryVo;

import java.util.List;

public interface ItemsCustomMapper {

    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
