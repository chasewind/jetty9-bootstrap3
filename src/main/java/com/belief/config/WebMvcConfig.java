package com.belief.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.belief.Scanned;

/**
 * 基于 WebMvcConfigurationSupport，需要去掉@EnableWebMvc
 * http://stackoverflow.com/questions/17898606/difference
 * -between-webmvcconfigurationsupport-and-webmvcconfigureradapter
 * 
 * @author YuDongwei
 *
 */
@Configuration
@ComponentScan(basePackageClasses = Scanned.class, includeFilters = @Filter(Controller.class), useDefaultFilters = false)
public class WebMvcConfig extends WebMvcConfigurationSupport {
	@Controller
	static class FaviconController {
		@RequestMapping("favicon.ico")
		String favicon() {
			return "forward:/resources/images/favicon.ico.png";
		}
	}

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
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
		templateResolver.setPrefix("/WEB-INF/view/");
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
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setCharacterEncoding("UTF-8");
		return thymeleafViewResolver;
	}

	/**
	 * <code>
	<bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		<property name="exceptionMappings">
			<props>
				<prop key="java.lang.Exception">errors/error</prop>
				<prop key="java.lang.Throwable">errors/err</prop>
			</props>
		</property>
		<property name="statusCodes">
			<props>
				<prop key="errors/error">500</prop>
				<prop key="errors/404">404</prop>
			</props>
		</property>
		<!-- 设置日志输出级别，不定义则默认不输出警告等错误日志信息 -->
		<property name="warnLogCategory" value="WARN"></property>
		<!-- 默认错误页面，当找不到上面mappings中指定的异常对应视图时，使用本默认配置 -->
		<property name="defaultErrorView" value="errors/error"></property>
		<!-- 默认HTTP状态码 -->
		<property name="defaultStatusCode" value="500"></property>
	</bean>
	</code>
	 **/

	/**
	 * <code>
	<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
	<!-- 定义默认的异常处理页面，当该异常类型的注册时使用 -->
	<property name="defaultErrorView" value="error"></property>
	<!-- 定义异常处理页面用来获取异常信息的变量名，默认名为exception -->
	<property name="exceptionAttribute" value="ex"></property>
	<!-- 定义需要特殊处理的异常，用类名或完全路径名作为key，异常也页名作为值 -->
	<property name="exceptionMappings">
		<props>
			<prop key="cn.basttg.core.exception.BusinessException">error-business</prop>
			<prop key="cn.basttg.core.exception.ParameterException">error-parameter</prop>

			<!-- 这里还可以继续扩展对不同异常类型的处理 -->
		</props>
	</property>
</bean>
</code>
	 **/

	// 全局异常处理页面
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("java.lang.Exception", "errors/exception");
		mappings.setProperty("java.lang.Throwable", "errors/error");
		simpleMappingExceptionResolver.setExceptionMappings(mappings);
		Properties statusCodes = new Properties();
		// 500
		statusCodes.setProperty("errors/exception", "500");
		// 404
		statusCodes.setProperty("errors/error", "404");
		simpleMappingExceptionResolver.setStatusCodes(statusCodes);
		simpleMappingExceptionResolver.setDefaultStatusCode(HttpServletResponse.SC_NOT_FOUND);
		return simpleMappingExceptionResolver;
	}

}
