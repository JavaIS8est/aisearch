package com.example.aisearch.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 作者：韦作旭
 * 时间：2024-05-07 下午5:54
 * 描述：配置支持http协议
 **/
@Component
public class TomcatSeverCustomer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {


    @Override
    public void customize(TomcatServletWebServerFactory factory){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(9201);
        factory.addAdditionalTomcatConnectors(connector);
    }
}
