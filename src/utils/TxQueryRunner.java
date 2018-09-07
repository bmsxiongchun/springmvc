package util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TxQueryRunner extends QueryRunner
{
    /**
     * 1.批处理
     */
    @Override
    public int[] batch(String sql,Object[][] params)throws SQLException
    {
        //获取连接
        Connection con = JDBCUtils.getConnection();
        //操作
        int[] result = super.batch(con, sql, params);
        //释放连接
        JDBCUtils.releaseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
            throws SQLException {
        Connection conn=JDBCUtils.getConnection();
        T result=super.query(conn,sql,param,rsh);
        JDBCUtils.releaseConnection(conn);
        return result;
    }

    @Override
    public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
            throws SQLException {
        Connection conn=JDBCUtils.getConnection();
        T result=super.query(conn,sql,params,rsh);
        JDBCUtils.releaseConnection(conn);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
            throws SQLException {
        Connection conn=JDBCUtils.getConnection();
        T result=super.query(conn,sql,rsh,params);
        JDBCUtils.releaseConnection(conn);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection conn=JDBCUtils.getConnection();
        T result=super.query(conn,sql,rsh);
        JDBCUtils.releaseConnection(conn);
        return result;
    }

    /**
     * 4.不带参数的update()
     */
    @Override
    public int update(String sql)throws SQLException
    {
        //获取连接
        Connection con = JDBCUtils.getConnection();
        //操作
        int result = super.update(con, sql);
        //释放连接
        JDBCUtils.releaseConnection(con);
        return result;
    }

    /**
     * 带有一个参数的uodate()
     */
    @Override
    public int update(String sql,Object param)throws SQLException
    {
        //获取连接
        Connection con = JDBCUtils.getConnection();
        //操作
        int result = super.update(con, sql, param);
        //释放连接
        JDBCUtils.releaseConnection(con);
        return result;
    }

    @Override
    public int update(String sql,Object... params)throws SQLException
    {
        //获取连接
        Connection con = JDBCUtils.getConnection();
        //操作
        int result = super.update(con, sql, params);
        //释放连接
        JDBCUtils.releaseConnection(con);
        return result;
    }

}
