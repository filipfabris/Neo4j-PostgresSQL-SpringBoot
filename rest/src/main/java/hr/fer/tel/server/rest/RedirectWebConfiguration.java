package hr.fer.tel.server.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class RedirectWebConfiguration implements WebMvcConfigurer {

  private static final Logger log = LoggerFactory.getLogger(RedirectWebConfiguration.class);

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // All resources go to where they should go
    registry
      .addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.jsx", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.ttf", "/**/*.woff", "/**/*.woff2", "/**/*.map", "/**/*.ico", "/**/*.eot")
      .setCachePeriod(0)
      .addResourceLocations("classpath:/static/");

    // redirecting to index.html all except api calls
    registry.addResourceHandler("/", "/**")
      .setCachePeriod(0)
      .addResourceLocations("classpath:/static/rest2/ui/index.html")
      .resourceChain(true)
      .addResolver(new PathResourceResolver() {
        @Override
        protected Resource getResource(String resourcePath, Resource location) throws IOException {
          if (resourcePath.startsWith("/rest2/ui") || resourcePath.startsWith("rest2/ui")) {
            return location.exists() && location.isReadable() ? location : null;
          }
          
          return null;
        }
      });
  }
}
