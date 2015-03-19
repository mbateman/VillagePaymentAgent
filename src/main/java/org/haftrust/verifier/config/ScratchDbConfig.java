package org.haftrust.verifier.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * This provides the database configuration for the 'scratch' (in-memory) profile.
 */
@Configuration
@ComponentScan(basePackages = { "org.haftrust.verifier.model", "org.haftrust.verifier.dao" })
@Profile(value = "scratch")
public class ScratchDbConfig {

    @Value(value = "${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    
    @Value(value = "${hibernate.hbm2ddl.import_files}")
    private String importFiles;
    
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("vpadb")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Primary
    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        
        System.out.println("Auto: " + hbm2ddlAuto);
        System.out.println("Imports: " + importFiles);
        
        HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setShowSql(true);
        // Set this to false and Hibernate will automatically run import.sql.
        jpaAdapter.setGenerateDdl(false);
        jpaAdapter.setDatabase(Database.H2);
        jpaAdapter.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        jpaAdapter.getJpaPropertyMap().put("hibernate.hbm2ddl.import_files", importFiles);
        return jpaAdapter;
    }

}
