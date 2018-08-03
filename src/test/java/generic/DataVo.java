package generic;

import top.cmoon.tools.excel.annotation.Title;

import java.util.Date;

public class DataVo {

    @Title("名称")
    private String name;

    @Title("年龄")
    private Integer age;

    @Title("生日")
    private Date birthDay;


    public DataVo(String name, int age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
