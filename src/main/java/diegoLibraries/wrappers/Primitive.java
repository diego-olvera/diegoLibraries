package diegoLibraries.wrappers;

import java.util.HashSet;
import java.util.Set;

public class Primitive {
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
	private static final Set<Class<?>> PRIMITIVE_TYPES = getPrimitiveTypes();
	
	public static boolean isWrapperOrPrimitiveType(Object o){
		Class<? extends Object> cl=o.getClass();
		return isWrapperType(cl)|| isPrimitiveType(cl);
	}
	public static boolean isWrapperOrPrimitiveType(Class<? extends Object> cl){
		return isWrapperType(cl)|| isPrimitiveType(cl);
	}
	public static boolean isWrapperType(Object o){
		return isWrapperType(o.getClass());
	}
	public static boolean isPrimiveType(Object o){
		return isPrimitiveType(o.getClass());
	}
    private static boolean isPrimitiveType(Class<? extends Object> class1) {
		return PRIMITIVE_TYPES.contains(class1);
	}
	private static Set<Class<?>> getPrimitiveTypes() {
    	Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
	}
	public static boolean isWrapperType(Class<?> clazz){
        return WRAPPER_TYPES.contains(clazz);
    }
    private static Set<Class<?>> getWrapperTypes(){
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(WrapperBigDecimal.class);
        ret.add(WrapperBigInteger.class);
        ret.add(WrapperByte.class);
        ret.add(WrapperShort.class);
        ret.add(WrapperBoolean.class);
        ret.add(WrapperFloat.class);
        ret.add(WrapperInt.class);
        ret.add(WrapperLong.class);
        ret.add(WrapperDouble.class);
        ret.add(WrapperChar.class);
        return ret;
    }
}
