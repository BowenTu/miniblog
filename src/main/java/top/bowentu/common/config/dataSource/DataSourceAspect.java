package top.bowentu.common.config.dataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Pointcut("@annotation(top.bowentu.common.config.dataSource.DataSourceAnn)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSourceAnn ds = method.getAnnotation(DataSourceAnn.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.MASTER);
            System.out.println("set datasource is " + DataSourceNames.MASTER);;
        }else {
            DynamicDataSource.setDataSource(ds.name());
            System.out.println("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            System.out.println("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
