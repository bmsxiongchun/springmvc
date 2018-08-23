package mapper;

import pojo.OrderCustom;
import pojo.Orders;

import java.util.List;

public interface OrdersMapperCustom {

    List<OrderCustom> findOrderUserList() throws Exception;

    List<Orders> findOrderUserListResultMap() throws Exception;
}
