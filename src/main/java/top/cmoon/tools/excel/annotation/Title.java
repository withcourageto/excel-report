package top.cmoon.tools.excel.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题单元格注解，用于第一行的标题行
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Title {

    /**
     * 单元格名称
     *
     * @return
     */
    String value();

}
