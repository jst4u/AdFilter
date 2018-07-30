package com.loit.tools.generator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loit.core.commom.SysConfig;
import com.loit.core.utils.TemplateUtils;
import com.loit.tools.utils.UIUtils;

public abstract class Generator
{
  protected final Log log = LogFactory.getLog(getClass());

  protected Map<String, Object> dataModel = new HashMap();

  private boolean A = true;

  private int a = 0;

  protected void generateFile(String fileName, String templateName) throws Exception {
    this.log.info("Generating " + fileName + "...");
    File b;
    if ((b = new File(fileName))
      .exists()) {
      if ((this.A) && 
        (UIUtils.showConfirmYesNo("Confirm", "File " + fileName + " 已存在, 是否覆盖?") != 0))
      {
        this.log.info("Exists. Skipped");
        return;
      }

      FileUtils.copyFile(b, new File("bak/" + fileName + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
      this.log.info("Exists. Overwrite");
    }

    String a = TemplateUtils.process(templateName, this.dataModel);

    FileUtils.writeStringToFile(b, a, "UTF-8");

    this.log.info("Done");
    this.a += 1;
  }

  protected void generateJava(String packageName, String className, String templateName) throws Exception {
    String b = packageName + "." + className;
    String a = SysConfig.SOURCE_DIR + "/" + b.replace(".", "/") + ".java";

    generateFile(a, templateName);
  }

  public void reset()
  {
    this.a = 0;
    this.dataModel.clear();
  }

  public int getCount() {
    return this.a;
  }

  public Map<String, Object> getDataModel() {
    return this.dataModel;
  }

  public void setDataModel(Map<String, Object> dataModel) {
    this.dataModel = dataModel;
  }

  public boolean isPromptOverwrite() {
    return this.A;
  }

  public void setPromptOverwrite(boolean promptOverwrite) {
    this.A = promptOverwrite;
  }
}