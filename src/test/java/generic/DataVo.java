package generic;

import top.cmoon.tools.excel.annotation.DatePattern;
import top.cmoon.tools.excel.annotation.ExportFields;
import top.cmoon.tools.excel.annotation.NumberPattern;
import top.cmoon.tools.excel.annotation.Title;

import java.util.Date;

@ExportFields
public class DataVo {

    @Title("名称")
    private String name;

    @Title("年龄")
    private Integer age;

    @Title("生日")
    @DatePattern
    private Date birthDay;

    @Title("银行余额")
    @NumberPattern(maxFractionDigits = 2)
    private Double balance;


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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
