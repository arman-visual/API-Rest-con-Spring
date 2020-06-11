package con.aquispe.backend.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);
			
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		request.setAttribute("tiempoInicio", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		logger.info("--REQUEST URL: '" + request.getRequestURL().toString() + "' --TOTAL TIME: '" + (System.currentTimeMillis() - tiempoInicio) + "'ms");
	}

}
