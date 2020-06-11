package con.aquispe.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import con.aquispe.backend.interceptors.TiempoTranscurridoInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	private TiempoTranscurridoInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}

}
