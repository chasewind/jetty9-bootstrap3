package com.belief.module;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = Scanned.class, includeFilters = @Filter(Controller.class), useDefaultFilters = false)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("static resources");
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(10485760000L);
		multipartResolver.setMaxInMemorySize(40960);
		return multipartResolver;
	}

	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	/**
	 * 支持JSON格式的数据，如果没有配置该对象，会被拦截到并返回406错误
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter jsonConvert() {
		MappingJackson2HttpMessageConverter jsonConvert = new MappingJackson2HttpMessageConverter();
		List<MediaType> typeList = new ArrayList<MediaType>();
		typeList.add(MediaType.APPLICATION_JSON);
		jsonConvert.setSupportedMediaTypes(typeList);
		return jsonConvert;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		logger.info("viewResolver");
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setCharacterEncoding("UTF-8");
		return thymeleafViewResolver;
	}
}