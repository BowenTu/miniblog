package top.bowentu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import top.bowentu.common.config.dataSource.DynamicDataSourceConfig;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("top.bowentu.dao")
public class MiniBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniBlogApplication.class, args);
    }
}
