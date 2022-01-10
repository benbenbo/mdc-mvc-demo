package com.zzb.example.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengbingyuan
 * @date 2022/1/10
 */
public class TimeTotalInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TimeTotalInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("startTime",String.valueOf(System.currentTimeMillis()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String startTimeStr = MDC.get("startTime");
        long startTime = Long.parseLong(startTimeStr);
        long endTime = System.currentTimeMillis();
        long timePeriod = endTime - startTime;
        log.info("此次请求耗时：{} 毫秒",timePeriod);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
