package me.wcy.learnspring.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by hzwangchenyan on 2017/9/5.
 */
public class DBHelper {

    private void test() {
        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.得到连接
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/wcy", "root", "wcy19930921");
            // 3.创建PreparedStatement 用于传送sql查询语句
            ps = ct.prepareStatement("select * from user where user_name =?");
            //给?赋值
            ps.setObject(1, "wangchenyan");

            // 4.执行操作
            rs = ps.executeQuery();
            // 5.根据结果做处理
            if (rs.next()) {//合法
                int nameIndex = rs.findColumn("password");
                String name = rs.getString(nameIndex);
                System.out.println(name);
            } else {
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            close(rs);
            close(ps);
            close(ct);
        }
    }

    private static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
