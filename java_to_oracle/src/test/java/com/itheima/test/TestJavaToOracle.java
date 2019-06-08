package com.itheima.test;

import oracle.jdbc.driver.OracleTypes;
import org.junit.Test;

import java.sql.*;

/**
 * @author Johnny
 * @date 2019/6/5 19:49
 */
public class TestJavaToOracle {
    @Test
    public void testJdbc(){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.85.10:1521:orcl";
        String username = "scott";
        String password = "scott";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("empno")+","+resultSet.getString("ename"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProcedure(){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.85.10:1521:orcl";
        String username = "scott";
        String password = "scott";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            CallableStatement statement = connection.prepareCall("{call p_yearsal(?, ?)}");
            statement.setInt(1, 7788);
            statement.registerOutParameter(2, OracleTypes.NUMBER);
            statement.execute();
            System.out.println(statement.getObject(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
