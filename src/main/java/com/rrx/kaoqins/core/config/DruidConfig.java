package com.rrx.kaoqins.core.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    //    @Bean
    //    public ServletRegistrationBean statViewServlet(){
    //        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    //        Map<String,String> initParams = new HashMap<>();
    //        initParams.put("loginUsername","admin");
    //        initParams.put("loginPassword","123456");
    //        //initParams.put("allow","");//默认就是允许所有访问
    //        //initParams.put("deny","192.168.15.21");
    //        bean.setInitParameters(initParams);
    //        return bean;
    //    }
    //
    //    //2、配置一个web监控的filter
    //    @Bean
    //    public FilterRegistrationBean webStatFilter(){
    //        FilterRegistrationBean bean = new FilterRegistrationBean();
    //        bean.setFilter(new WebStatFilter());
    //        Map<String,String> initParams = new HashMap<>();
    //        initParams.put("exclusions","*.js,*.css,/druid/*");
    //        bean.setInitParameters(initParams);
    //        bean.setUrlPatterns(Arrays.asList("/*"));
    //        return  bean;
    //    }
    //
    //    @Primary
    //    @Bean
    //    @ConfigurationProperties("spring.datasource.druid.one")
    //    public DataSource dataSourceOne(){
    //        return DruidDataSourceBuilder.create().build();
    //    }
    //
    //    @Bean
    //    @ConfigurationProperties("spring.datasource.druid.two")
    //    public DataSource dataSourceTwo(){
    //        return DruidDataSourceBuilder.create().build();
    //    }

}
