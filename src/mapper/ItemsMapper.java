//package mapper;
//
//import java.util.List;
//import org.apache.ibatis.annotations.Param;
//import pojo.Items;
//import pojo.ItemsExample;
//
//public interface ItemsMapper {
//    int countByExample(ItemsExample example);
//
//    int deleteByExample(ItemsExample example);
//
//    int insert(Items record);
//
//    int insertSelective(Items record);
//
//    List<Items> selectByExampleWithBLOBs(ItemsExample example);
//
//    List<Items> selectByExample(ItemsExample example);
//
//    int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") Items record, @Param("example") ItemsExample example);
//
//    int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);
//}