package service;

import po.ItemsCustom;
import po.ItemsQueryVo;

import java.util.List;

/**
 * Spring将Service对象注入到IOC容器中，有几种不同的方式
 * 第一种是通过@Service注解的方式
 * 另外一种是通过在配置文件中配置bean的方式注入到IOC容器中
 */
//@Service
public interface ItemsService {

    //查询商品信息
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    //更新商品信息
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;

    //根据id查找商品
    public ItemsCustom findItemsById(Integer id) throws Exception;

    //根据id删除商品
    public void deleteItemsById(Integer[] delete_id) throws Exception;
}
