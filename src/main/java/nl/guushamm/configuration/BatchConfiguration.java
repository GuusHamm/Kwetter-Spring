// package nl.guushamm.configuration;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.support.ResourcePatternResolver;
// import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
//
// /**
//  * Created by guushamm on 22-3-17.
//  */
// @Configuration
// public class BatchConfiguration {
// 	@Autowired
// 	ObjectMapper jsonMapper;
//
// 	@Autowired
// 	ResourcePatternResolver resourceResolver;
//
// 	@Bean
// 	public Jackson2RepositoryPopulatorFactoryBean batch(){
// 		Jackson2RepositoryPopulatorFactoryBean factoryBean  = new Jackson2RepositoryPopulatorFactoryBean();
// 		try {
// 			factoryBean.setMapper(jsonMapper);
// 			factoryBean.setResources(resourceResolver.getResources("classpath:kwetter.json"));
// 			factoryBean.afterPropertiesSet();
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 		return factoryBean;
// 	}
// }
