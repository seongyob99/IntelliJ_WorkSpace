package com.busanit501.helloworld.jdbcex.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Log4j2
public class TestListener implements ServletContextListener {
    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        log.info("contextInitialized : 초기화 중 init");

        //
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("appTestName", "helloworld");
    }


    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent sce) {
        log.info("contextDestroyed : 종료 destroy");

    }

}
