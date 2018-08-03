package top.cmoon.tools.excel.titile;

import top.cmoon.tools.excel.annotation.Title;

import java.lang.reflect.Field;

public class TitleParserUtil {

    /**
     * 从 field 中获取标题信息，
     * <p>
     * <pre>
     *  1. 从 @Title 注解获取 value 值作为 title
     *  2. 如果没有 @Title,或者title 值为空白, 将 field 名称作为 title
     * </pre>
     *
     * @param field
     * @return
     */
    public static TitleCellDef parseField(Field field) {

        Title title = field.getAnnotation(Title.class);

        TitleCellDef titleDef = new TitleCellDef();
        if (title != null && !"".equals(title.value().trim())) { // prefer to annotation define
            titleDef.setValue(title.value());
            return titleDef;
        }

        titleDef.setValue(field.getName());
        return titleDef;
    }
}
