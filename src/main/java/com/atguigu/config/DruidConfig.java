package com.atguigu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource setDruid() throws SQLException {
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setFilters("stat");
        return dataSource;
    }
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean sb=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");//设置进入druid监控 用户名
        initParams.put("loginPassword", "123456");//设置进入druid监控 密码
        initParams.put("allow", "");// 默认就是允许所有访问
        initParams.put("deny", "192.168.15.21");//禁止 ip访问

        sb.setInitParameters(initParams);

        return sb;
    }
    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean sb = new FilterRegistrationBean();
        sb.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        sb.setInitParameters(initParams);
        sb.setUrlPatterns(Arrays.asList("/*"));

        return sb;
    }

}
