package br.org.mongoose.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class ReflectionUtils {
	private ReflectionUtils(){}

	public static <T> T instantiateFromClass(Class<T> clazz) {
		if(clazz == null) {
			return null;
		}
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void copyAttributes(Object source, Object destination) {
		if(source == null || destination == null) {
			return;
		}

        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        for (Field sourceField : sourceClass.getDeclaredFields()) {
            try {
                sourceField.setAccessible(true);

                Field destinationField = destinationClass.getDeclaredField(sourceField.getName());
				if(destinationField == null || destinationField.getType() != sourceField.getType()) {
					continue;
				}
                destinationField.setAccessible(true);

                Object value = sourceField.get(source);
                destinationField.set(destination, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                continue;
            }
        }
    }

	public static <T> T createFromObject(Object source, Class<T> clazz) {
		if(source == null) {
			return null;
		}
		T destination = instantiateFromClass(clazz);
		copyAttributes(source, destination);
		return destination;
	}
}
