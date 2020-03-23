package top.bowentu.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.bowentu.common.config.dataSource.DataSourceNames;
import top.bowentu.common.config.dataSource.DynamicDataSource;

/**
 * 用于实现MySQL主从架构下数据库的选择，主一从一，写在master上，读在master和salve之间轮询
 */
@Aspect
@Component
public class ChooseDataSourceAspect {
    @Autowired
    private DynamicDataSource dynamicDataSource;
    private static int count=0;

    @Before("execution(* top.bowentu.service.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        if(count>10000) count=0;
        String methodName = jp.getSignature().getName();
        if(methodName.contains("find")){
            if(count%2==0){
                DynamicDataSource.setDataSource(DataSourceNames.MASTER);
                System.out.println("当前使用master");
            }else{
                DynamicDataSource.setDataSource(DataSourceNames.SLAVE);
                System.out.println("当前使用slave");
            }
            count++;
        }else if(methodName.contains("insert")||methodName.contains("del")){
            DynamicDataSource.setDataSource(DataSourceNames.MASTER);
            System.out.println("当前使用master");
        }else{
        }
    }

    @After("execution(* top.bowentu.service.*.*(..))")
    public void doAfter(JoinPoint jp) throws NoSuchMethodException {
        String methodName = jp.getSignature().getName();
        if(methodName.contains("find")||methodName.contains("insert")||methodName.contains("del")) {
            DynamicDataSource.clearDataSource();
        }
    }

}
