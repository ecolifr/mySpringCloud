package sample;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import sample.entity.Customer;

@SpringBootApplication
@EnableConfigurationProperties(value = Customer.class)
@MapperScan("sample.mapper")
public class SampleWebJspApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleWebJspApplication.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebJspApplication.class);
	}

	public static void main(String[] args) {
		LOGGER.debug("开始测试");
		LOGGER.info("开始测试");
		LOGGER.error("开始测试");
		SpringApplication.run(SampleWebJspApplication.class, args);
	}

}
