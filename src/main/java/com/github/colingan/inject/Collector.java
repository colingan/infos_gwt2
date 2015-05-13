
package com.github.colingan.inject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Module;

public class Collector {

  public static List<Module> start(Class<? extends Module> clazz) {
    Set<Class<? extends Module>> modules = new HashSet<Class<? extends Module>>();
    addModule(clazz, modules);
    List<Module> list = new ArrayList<Module>();
    for (Class<? extends Module> module : modules) {
      try {
        list.add((Module) module.newInstance());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return list;
  }

  private static void addModule(Class<? extends Module> clazz, Set<Class<? extends Module>> modules) {
    if (modules.add(clazz)) {
      // 添加成功后，继续添加继承的Module
      Inherits anno = clazz.getAnnotation(Inherits.class);
      if (anno != null) {
        Class<? extends Module>[] inherits = anno.value();
        for (Class<? extends Module> module : inherits) {
          addModule(module, modules);
        }
      }
    }
  }

}

