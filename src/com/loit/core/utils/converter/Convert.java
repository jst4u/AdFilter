package com.loit.core.utils.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loit.core.utils.ClassUtil;


public class Convert {

    private static FastHashMap converters;
    private static Log log;

    public Convert() {
    }
//
///**
// * 从request对象中取得实体
// * @param req
// * @param entity
// * @return
// * @throws InvocationTargetException
// * @throws InstantiationException
// * @throws IllegalAccessException
// * modify:一次批量提交多个数据窗口的数据,此时每个窗口中表单元素的命名规则是 改 formName+"_"+字段名,以防止多个数据窗口
// *  间表单元素名称重复,取得实体时,要根据formName从request对象中取得相应form提交的数据的实体
// *  synchronized
// */
//public static Collection getCollection(HttpServletRequest req,
//          Class entity, String formName) throws InvocationTargetException, InstantiationException,
//          IllegalAccessException {
//
//          String propName = null;
//          String subPropName = null;
//          int length = 0;//实体个数
//          String tmp[] = (String[])null;
//
//          Object dto = entity.newInstance();
//          PropertyDescriptor origDescriptors[] = PropertyUtils.
//              getPropertyDescriptors(dto);
//
//          //解析一共有多少个实体
//          for (int i = 0; i < origDescriptors.length; i++) {
//              propName = origDescriptors[i].getName();
//              if("class".equals(propName))
//                  continue;
//              tmp = req.getParameterValues( renderProName(propName, formName) );
//              if(tmp == null)
//                  continue;
//              length = tmp.length;
//              break;
//          }
//          //复制
//          Collection result = new ArrayList();
//          for (int j = 0; j < length; j++) {
//              Object item = entity.newInstance();
//              for (int i = 0; i < origDescriptors.length; i++)
//                  if (origDescriptors[i].getReadMethod() == null) {
//                      if (log.isTraceEnabled())
//                          log.trace("-->No getter on JavaBean for " +
//                                    origDescriptors[i].getName() + ", skipping");
//                  } else {
//                      String name = origDescriptors[i].getName();
//                      if ("class".equals(name)) {
//                          continue;
//                      }
//                      //如果是普通类型
//                      Object value = null;
//                      Class subclass = origDescriptors[i].getPropertyType();
//                      String classname =subclass.getName();
//                      if (classname.equals("java.lang.String") ||
//                            classname.equals("java.lang.Double") ||classname.equals("java.lang.Long")||
//                            classname.equals("java.lang.Integer") || classname.equals("java.lang.Number")||
//                            classname.equals("java.math.BigDecimal") ||
//                            classname.equals("java.lang.Float") || classname.equals("java.lang.Boolean")) {
//                          tmp = req.getParameterValues( renderProName(name, formName) );
//                          if (tmp != null)
//                              value = tmp[j];
//                          BeanUtils.copyProperty(item, name, value);
//                      }else if (classname.equals("java.util.Date")||
//                            classname.equals("java.sql.Date")){
//                          Object d = null;
//                        tmp = req.getParameterValues( renderProName(name, formName) );
//                        if (tmp != null){
//                            value = tmp[j];
//                            if (value.getClass().getName().equals("java.lang.String")){
//                                value = ((String)value).replaceAll("-","");
////                                try {
//                                    //TODO 缺省是util.date
////                                    d = PubTool.stringToDate((String)value);
//                                    //如果是要用sql.Date
//                                    if (d!=null && classname.equals("java.sql.Date")){
//                                        d= new java.sql.Date(((Date)d).getTime());
//                                    }
////                                } catch (SysException e) {
////                                    d = null;
////                                }
//                            }
//                        }
//                        ClassUtil.copyProperty(item, name, d);
//
//                      } else{
//                        //如果是属性类
//                        //子类实例
//                        Object subins = subclass.newInstance();
//                        PropertyDescriptor subDescriptors[] = PropertyUtils.getPropertyDescriptors(subclass);
//                        for (int k = 0; k < subDescriptors.length; k++) {
//                            //复制子类属性
//                            subPropName = subDescriptors[k].getName();
//                            if ("class".equals(subPropName)) {
//                                continue;
//                            }
//                            String attrname = name + "." + subPropName;
//                            tmp = req.getParameterValues( renderProName(attrname, formName) );
//                            if (tmp != null)
//                                value = tmp[j];
//                            BeanUtils.copyProperty(subins, subPropName, value);
//                        }
//                        BeanUtils.copyProperty(item, name, subins);
//
//                      }
//                  }
//
//              result.add(item);
//          }
//
//          return result;
//      }
//
//
//    public static Object convert(String value, Class clazz) {
//        Converter converter = (Converter) converters.get(clazz);
//        if (converter == null)
//            converter = (Converter) converters.get(java.lang.String.class);
//        return converter.convert(clazz, value);
//    }
//
//    public static Object convert(String values[], Class clazz) {
//        Class type = clazz;
//        if (clazz.isArray())
//            type = clazz.getComponentType();
//        Converter converter = (Converter) converters.get(type);
//        if (converter == null)
//            converter = (Converter) converters.get(java.lang.String.class);
//        Object array = Array.newInstance(type, values.length);
//        for (int i = 0; i < values.length; i++)
//            Array.set(array, i, converter.convert(type, values[i]));
//
//        return array;
//    }
//
//    private static void register() {
//        converters.put(java.math.BigDecimal.class, new BigDecimalConverter(null));
//        converters.put(java.math.BigInteger.class, new BigIntegerConverter(null));
//        converters.put(Boolean.TYPE, new BooleanConverter(null));
//        converters.put(java.lang.Boolean.class, new BooleanConverter(null));
//        converters.put(Byte.TYPE, new ByteConverter(null));
//        converters.put(java.lang.Byte.class, new ByteConverter(null));
//        converters.put(Character.TYPE, new CharacterConverter(null));
//        converters.put(java.lang.Character.class, new CharacterConverter(null));
//        converters.put(Double.TYPE, new DoubleConverter(null));
//        converters.put(java.lang.Double.class, new DoubleConverter(null));
//        converters.put(Float.TYPE, new FloatConverter(null));
//        converters.put(java.lang.Float.class, new FloatConverter(null));
//        converters.put(Integer.TYPE, new IntegerConverter(null));
//        converters.put(java.lang.Integer.class, new IntegerConverter(null));
//        converters.put(Long.TYPE, new LongConverter(null));
//        converters.put(java.lang.Long.class, new LongConverter(null));
//        converters.put(Short.TYPE, new ShortConverter(null));
//        converters.put(java.lang.Short.class, new ShortConverter(null));
//        converters.put(java.lang.String.class, new StringConverter());
//        converters.put(java.sql.Date.class, new DateConverter(null));
//    }
//    
//    /**
//     * 重新组装proName
//     * @param proName
//     * @param formName
//     * @return
//     */
//    private static String renderProName(String proName, String formName) {
//    	proName = formName==null?proName:formName+"_"+proName ;
//    	return proName ;
//    }
//
//    static {
//        converters = new FastHashMap();
//        converters.setFast(false);
//        register();
//        converters.setFast(true);
//        log = LogFactory.getLog(Convert.class);
//    }
}
