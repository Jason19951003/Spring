package session01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// SpringJavaConfig 是一個傳統 .xml 配置檔的替代品
@Configuration
@ComponentScan(basePackages = {"session01.bean"})
public class SpringJavaConfig2 {

}
