package com.healthpro.customer.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class BeanUtilsCustom extends BeanUtils {


    /**
     * Copy the property values of the given source bean into the target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full {@link BeanWrapper}.
     * <p>As of Spring Framework 5.3, this method honors generic type information
     * when matching properties in the source and target objects.
     * <p>The following table provides a non-exhaustive set of examples of source
     * and target property types that can be copied as well as source and target
     * property types that cannot be copied.
     * <table border="1">
     * <tr><th>source property type</th><th>target property type</th><th>copy supported</th></tr>
     * <tr><td>{@code Integer}</td><td>{@code Integer}</td><td>yes</td></tr>
     * <tr><td>{@code Integer}</td><td>{@code Number}</td><td>yes</td></tr>
     * <tr><td>{@code List<Integer>}</td><td>{@code List<Integer>}</td><td>yes</td></tr>
     * <tr><td>{@code List<?>}</td><td>{@code List<?>}</td><td>yes</td></tr>
     * <tr><td>{@code List<Integer>}</td><td>{@code List<?>}</td><td>yes</td></tr>
     * <tr><td>{@code List<Integer>}</td><td>{@code List<? extends Number>}</td><td>yes</td></tr>
     * <tr><td>{@code String}</td><td>{@code Integer}</td><td>no</td></tr>
     * <tr><td>{@code Number}</td><td>{@code Integer}</td><td>no</td></tr>
     * <tr><td>{@code List<Integer>}</td><td>{@code List<Long>}</td><td>no</td></tr>
     * <tr><td>{@code List<Integer>}</td><td>{@code List<Number>}</td><td>no</td></tr>
     * </table>
     *
     * @param source the source bean
     * @param target the target bean
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyPropertiesIfNotNull(Object source, Object target) throws BeansException {
        copyPropertiesIfNotNull(source, target, null, (String[]) null);
    }


    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>As of Spring Framework 5.3, this method honors generic type information
     * when matching properties in the source and target objects. See the
     * documentation for {@link #copyProperties(Object, Object)} for details.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param editable         the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    private static void copyPropertiesIfNotNull(Object source, Object target, @Nullable Class<?> editable,
                                                @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null) {
                        ResolvableType sourceResolvableType = ResolvableType.forMethodReturnType(readMethod);
                        ResolvableType targetResolvableType = ResolvableType.forMethodParameter(writeMethod, 0);

                        // Ignore generic types in assignable check if either ResolvableType has unresolvable generics.
                        boolean isAssignable =
                                (sourceResolvableType.hasUnresolvableGenerics() || targetResolvableType.hasUnresolvableGenerics() ?
                                        ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType()) :
                                        targetResolvableType.isAssignableFrom(sourceResolvableType));

                        if (isAssignable) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value = readMethod.invoke(source);
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                if (value != null) {
                                    writeMethod.invoke(target, value);
                                }
                            } catch (Throwable ex) {
                                throw new FatalBeanException(
                                        "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                            }
                        }
                    }
                }
            }
        }
    }
}
