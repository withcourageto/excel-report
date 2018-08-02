package top.cmoon.tools.excel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

abstract class AbstractExcelWriter<T> implements ExcelWriter<T> {

    Class<?> elementClass;


    AbstractExcelWriter() {

        Type typeClass1 = getClass().getGenericSuperclass();

        if (typeClass1 instanceof ParameterizedType) {
            Type actualType1 = ((ParameterizedType) typeClass1).getActualTypeArguments()[0];


            if(actualType1 instanceof TypeVariable){
                ((TypeVariable)actualType1).getGenericDeclaration();
            }

            elementClass = (Class<?>) actualType1;
        } else {
            throw new IllegalStateException("class :" + getClass() + "不是泛型 class");
        }
    }


}
