package com.loit.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.StringUtils;

import com.loit.core.spring.SpringContext;

public class SpringUtil {

	  public static TreeSet<String> getProjectPackageNames()
	  {
	    SessionFactory sessionFactory = SpringContext.getBeanOfType(SessionFactory.class);
	    Map<Object,ClassMetadata> d = sessionFactory.getAllClassMetadata();
	    TreeSet c = new TreeSet();
	    for (ClassMetadata b : d.values()) {
	      String a = b.getMappedClass(EntityMode.POJO).getPackage().getName();
	      if (a.endsWith(".model")) {
	        c.add(a.substring(0, a.length() - 6));
	      }
	    }
	    return c;
	  }

}
