package org.com.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 基于JXL实现的Excel工具类
 * @author binary
 *
 */
public class JxlExcelHelper extends ExcelHelper {

    private File file; // 操作文件

    /**
     * 构造方法
     * @param file 文件对象
     */
    public JxlExcelHelper(File file) {
        super();
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames, int sheetNo, boolean hasTitle) throws Exception {
        List<T> dataModels = new ArrayList<T>();
        // 获取excel工作簿
        Workbook workbook = Workbook.getWorkbook(file);
        try {
            Sheet sheet = workbook.getSheet(sheetNo);
            int start = hasTitle ? 1 : 0; // 如果有标题则从第二行开始
            for (int i = start; i < sheet.getRows(); i++) {
                // 生成实例并通过反射调用setter方法
                T target = clazz.newInstance();
                for (int j = 0; j < fieldNames.length; j++) {
                    String fieldName = fieldNames[j];
                    if (fieldName == null || UID.equals(fieldName)) {
                        continue; // 过滤serialVersionUID属性
                    }
                    // 获取excel单元格的内容
                    Cell cell = sheet.getCell(j, i);
                    if (cell == null) {
                        continue;
                    }
                    String content = cell.getContents();
                    // 如果属性是日期类型则将内容转换成日期对象
                    if (isDateType(clazz, fieldName)) {
                        // 如果属性是日期类型则将内容转换成日期对象
                        ReflectUtil.invokeSetter(target, fieldName,
                                DateUtil.parse(content));
                    } else {
                        Field field = clazz.getDeclaredField(fieldName);
                        ReflectUtil.invokeSetter(target, fieldName,
                                parseValueWithType(content, field.getType()));
                    }
                }
                dataModels.add(target);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return dataModels;
    }

}
