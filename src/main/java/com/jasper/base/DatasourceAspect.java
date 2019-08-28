package com.jasper.base;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatasourceAspect implements Ordered {

    private int order;

    @Pointcut("execution(* com.jasper.base..*.*(..))")
    public void serviceMethod() {}

    @Before("serviceMethod()")
    public void dataSourceSwitch() {
        boolean flag = ApolloConfigUtils.getDataSourceSwitch;
        if (!flag) {
            DBContextHolder.setDataSource("dataSource1");
        } else {
            DBContextHolder.setDataSource("dataSource2");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Value("20")
    public void setOrder(int order) {
        this.order = order;
    }
}
