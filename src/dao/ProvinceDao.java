package dao;

import domain.City;
import domain.Province;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.TxQueryRunner;

import java.sql.*;
import java.util.List;

public class ProvinceDao {

    QueryRunner queryRunner = new TxQueryRunner();

    public List<Province> findAllProvince() {
        List<Province> provinces;

        try {
            String sql = "select * from province";
            provinces = queryRunner.query(sql, new BeanListHandler<Province>(Province.class));

            return provinces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<City> findByProvinceId(int pid) {
        try {
            String sql= "select * from City where pid=?";
            return queryRunner.query(sql, new BeanListHandler<>(City.class), pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
