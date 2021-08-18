package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    @Test
    public void mybatisDemo() throws IOException {
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 创建SqlSeeion
        SqlSession sqlSession = build.openSession();
        // 执行sql
        List<User> userList = sqlSession.selectList("user.findAll");
        // 遍历
        for (User user : userList) {
            System.out.println(user);
        }
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testSave() throws IOException {
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSeeionFactory
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sf.openSession();
        // 创建User
        User user = new User();
        user.setUsername("小明");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京东城");
        sqlSession.insert("user.saveUser",user);
        // 增删改手动提交
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() throws IOException {
        // 加载配置文件
        InputStream rs = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory
        SqlSessionFactory s = new SqlSessionFactoryBuilder().build(rs);
        SqlSession sqlSession = s.openSession();
        User user = new User();
        user.setId(4);
        user.setUsername("小明2");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京朝阳");
        //执行sql
        sqlSession.update("user.updateUser",user);

        // 手动提交
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void deleteUser() throws IOException {
        // 加载配置文件
        InputStream rs = Resources.getResourceAsStream("SqlMapConfig.xml");
        // SqlSessionFactory
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(rs);
        SqlSession sqlSession = sf.openSession();
        // 执行sql
        sqlSession.delete("user.deleteUser",4);

        //手动提交
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

}
