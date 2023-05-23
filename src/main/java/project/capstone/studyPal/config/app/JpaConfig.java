//package project.capstone.studyPal.config.app;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//public class JpaConfig {
//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setPackagesToScan("project.capstone.studyPal.data.models");
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//}
