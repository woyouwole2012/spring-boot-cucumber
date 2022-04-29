package com.woyou.springcucumber.utils;

import com.woyou.springcucumber.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DBUtils {

    private Connection getConnection(String url, String username, String passwword) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, passwword);
            return connection;
        } catch (ClassNotFoundException e) {
            log.warn("get mysql connection error! ", e);
        } catch (SQLException e) {
            log.warn("get mysql connection error !", e);
        }
        return null;
    }


    private Connection getSvcConnection(String dbHostPath, String dbNamePath, String dbUserNamePath, String dbPasswordPath, String dbPortPath) {

        YamlReader yamlReader = new YamlReader();
        String dbHost = yamlReader.getYamlValue(dbHostPath).toString();
        String dbName = yamlReader.getYamlValue(dbNamePath).toString();
        String dbUserName = yamlReader.getYamlValue(dbUserNamePath).toString();
        String dbPassword = yamlReader.getYamlValue(dbPasswordPath).toString();
        String dbPort = yamlReader.getYamlValue(dbPortPath).toString();

        return getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=utf8"
                , dbUserName
                , dbPassword);
    }

    public List<String> doQueryList(Connection connection, String sql) {
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    list.add(resultSet.getString(i + 1));
                }
            }
        } catch (SQLException e) {
            log.warn("do query list error ! ", e);
        } finally {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                log.warn("connection close error ! ", e);
            }
        }
        return list;

    }


    public List<Map<String, String>> doQueryMap(Connection connection, String sql) {
        if (!sql.contains("limit") && !sql.contains("LIMIT")) {
            throw new MyException("sql not contains limit !");
        }
        List<Map<String, String>> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(upcaseColumnName(metaData.getColumnName(i)), resultSet.getString(metaData.getColumnName(i)));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            log.warn("do query list map error ! ", e);
        } finally {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                log.warn("connection close error ! ", e);
            }
        }
        return list;
    }

    private String upcaseColumnName(String column) {

        if (column.contains("_")) {
            String[] s = column.split("_");
            StringBuilder stringBuilder = new StringBuilder(s[0]);
            for (int i = 1; i < s.length; i++) {
                stringBuilder.append(s[i].substring(0, 1).toUpperCase() + s[i].substring(1));
            }
            return stringBuilder.toString();
        }
        return column;
    }


    /**
     * ResultSet.TYPE_FORWORD_ONLY 结果集的游标只能向下滚动。
     * ResultSet.TYPE_SCROLL_INSENSITIVE 结果集的游标可以上下移动，当数据库变化时，当前结果集不变。
     * ResultSet.TYPE_SCROLL_SENSITIVE 返回可滚动的结果集，当数据库变化时，当前结果集同步改变。
     * 参数 int concurrency
     * ResultSet.CONCUR_READ_ONLY 不能用结果集更新数据库中的表。
     * ResultSet.CONCUR_UPDATETABLE 能用结果集更新数据库中的表。
     *
     * @param sql
     */
    public int getTotalResultRows(Connection connection, String sql) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            log.warn("query total rows error ! ", e);
        }finally {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                log.warn("connection close error ! ", e);
            }
        }
        return 0;

    }


    public int doDelete(Connection connection, String sql) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int deleteCount = preparedStatement.executeUpdate();
            return deleteCount;
        } catch (SQLException e) {
            log.warn("do db delete error !", e);
        }finally {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                log.warn("connection close error ! ", e);
            }
        }
        return 0;
    }


}
