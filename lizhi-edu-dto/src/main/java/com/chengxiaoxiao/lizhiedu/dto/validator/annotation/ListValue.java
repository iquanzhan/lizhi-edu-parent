package com.chengxiaoxiao.lizhiedu.dto.validator.annotation;

import com.chengxiaoxiao.lizhiedu.dto.validator.validator.ListValueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否包含指定的值注解
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/21 16:51
 * @Version 1.0
 */
@Documented
@Constraint(
        validatedBy = {ListValueConstraintValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {
    String message() default "所传数据非法";

    /**
     * 分组校验
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 载荷信息
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 包含的值
     *
     * @return
     */
    int[] value() default {};
}
