package epam.com.springBoot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("/n----------- UserInterceptor.preHandle-----------");
        log.info("/n Request ulr:" + request.getRequestURL());
        log.info("start time: " + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("/n----------- UserInterceptor.postHandle-----------");
        log.info("/n Request ulr:" + request.getRequestURL());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("\n-------- LogInterception.afterCompletion --- ");

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("Request URL: " + request.getRequestURL());
        log.info("End Time: " + endTime);
        log.info("Time Taken: " + (endTime - startTime));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
