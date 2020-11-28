package entry;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/24 9:20 下午
 */
@Data
public class ExcelStudentData {
    @ExcelProperty(value = "姓名",index = 2)
    private String name;

    @DateTimeFormat("yyyy-mm-dd hh:mm:ss")
    @ExcelProperty(value = "生日",index = 3)
    private Date birthday;

    @NumberFormat("#.##%")
    @ExcelProperty("薪资")
    private Double salary;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String password;
}
