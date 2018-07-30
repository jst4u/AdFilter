package com.loit.core.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.loit.core.exception.AppException;

public class Export2Word {

//    private Logger log=Logger.getLogger(this.getClass());  
	 private final String pageStyle = "<div style=\"page-break-before:always\"></div>";
    /**    
     * 打开文件    
     * @param documents     
     * @param inputDocPath    
     * @return    
     */     
    private Dispatch open(Dispatch documents,String inputDocPath){      
        return Dispatch.call(documents,"Open",inputDocPath).toDispatch();      
    }      
          
    /**    
     * 选定内容    
     * @param word    
     * @return    
     */     
    private Dispatch select(ActiveXComponent word){      
        return word.getProperty("Selection").toDispatch();      
    }      
          
    /**    
     * 把插入点移动到文件首位置    
     * @param selection    
     */     
    private void moveStart(Dispatch selection){      
        Dispatch.call(selection, "HomeKey",new Variant(6));      
    }      
          
    /**    
     * 从选定内容或插入点开始查找文本    
     * @param selection 选定内容    
     * @param toFindText    要查找的文本    
     * @return  true：查找到并选中该文本；false：未查找到文本。    
     */     
    private boolean find(Dispatch selection,String toFindText){      
        //从selection所在位置开始查询      
        Dispatch find = Dispatch.call(selection, "Find").toDispatch();      
        //设置要查找的内容      
        Dispatch.put(find, "Text", toFindText);      
        //向前查找      
        Dispatch.put(find, "Forward", "True");      
        //设置格式      
        Dispatch.put(find,"format","True");      
        //大小写匹配      
        Dispatch.put(find, "MatchCase", "True");      
        //全字匹配      
        Dispatch.put(find, "MatchWholeWord", "True");      
        //查找并选中      
        return Dispatch.call(find, "Execute").getBoolean();      
    }      
          
    /**    
     * 把选定内容替换为设定文本    
     * @param selection    
     * @param newText    
     */     
    private void replace(Dispatch selection,String newText){      
        Dispatch.put(selection, "Text", newText);      
    }      
          
    /**    
     * 全局替换    
     * @param selection     
     * @param oldText    
     * @param replaceObj    
     */     
    private void replaceAll(Dispatch selection,String oldText,Object replaceObj){      
        moveStart(selection);      
//        log.info("replace--"+oldText);   
        if(oldText.startsWith("{table1}")){   
            createTable(selection,oldText,(List<String[]>) replaceObj);   
        }else if(oldText.startsWith("{zp}")){   
           // log.info("替换图片");   
            replaceImage(selection, oldText, replaceObj.toString());   
        }else{   
            String newText = (String) replaceObj;   
            while (find(selection, oldText)) {   
                replace(selection, newText);   
                Dispatch.call(selection, "MoveRight");   
            }   
        }    
    }      
    /**  
     * image  
     * @param selection  
     * @param toFindText  
     * @param imagePath  
     */  
    public void replaceImage(Dispatch selection,String toFindText, String imagePath) {    
        if(find(selection,toFindText)){   
         Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),   
                 "AddPicture", imagePath);   
               Dispatch.call(selection, "MoveRight");   
        }   
     }    
    /**  
     * table  
     */  
    public void createTable(Dispatch selection, String tableName, List<String[]> dataList) {   
        if(find(selection,tableName)){   
            int row=dataList.size();   
          Dispatch tables = Dispatch.get(selection, "Tables").toDispatch();    
          Dispatch range = Dispatch.get(selection, "Range").toDispatch();    
          Dispatch newTable = Dispatch.call(tables, "Add", range,    
                          new Variant(row), new Variant(5)).toDispatch();    
          for(int i=0 ;i<row;i++){   
              String[] str=dataList.get(i);   
              for(int j=0;j<str.length;j++){   
                 // log.info("行="+i+" 列="+j+"值="+str[j]);   
                  String s=str[j];   
                  Dispatch cell = Dispatch.call(newTable, "Cell", new Variant(i+1), new Variant(j+1)).toDispatch();    
                  Dispatch.call(cell, "Select");    
                  Dispatch.put(selection, "Text", s);   
              }   
          }   
          Dispatch.call(selection, "MoveRight");   
        }   
           
    }    
  
    /**    
     * 打印    
     * @param document    
     */     
    public void print(Dispatch document,String ober){    
    	if(StringUtils.isNotEmpty(ober) && ober.equals("print")){
    		  Dispatch.call(document, "PrintOut"); //打印
    	}else if(StringUtils.isNotEmpty(ober) && ober.equals("view")){
    		  Dispatch.call(document, "PrintPreView"); // 打印预览 
    	}
      
    }      
          
    /**    
     * 保存文件    
     * @param word    
     * @param outputPath    
     */     
    private void save(ActiveXComponent word,String outputPath){      
        Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs",outputPath);      
    }      
          
    /**    
     * 关闭文件    
     * @param doc    
     */     
    private void close(Dispatch doc){      
        Dispatch.call(doc, "Close",new Variant(false));      
    }      
          
    /**    
     * 保存打印doc文档    
     * @param inputDocPath    
     * @param outPutDocPath    
     * @param data    
     * @param isPrint    
     * @throws Exception 
     */     
    public synchronized void saveDoc(String inputDocPath,String outPutDocPath,String htmPath,HashMap<String, Object> data,boolean isPrint,String ober) throws Exception{      
    	 ActiveXComponent word =null;
    	 Dispatch document = null;
    	try{
    		
        	//初始化com的线程      
            ComThread.InitSTA();      
            //word运行程序对象      
            word = new ActiveXComponent("Word.Application");      
            //文档对象      
            Dispatch wordObject = (Dispatch) word.getObject();      
            //设置属性  Variant(true)表示word应用程序可见      
            Dispatch.put((Dispatch)wordObject,"Visible", new Variant(false));      
            //word所有文档      
            Dispatch documents = word.getProperty("Documents").toDispatch();      
            //打开文档      
            document = this.open(documents,inputDocPath);      
            Dispatch selection = this.select(word);      
            Iterator<?> keys = data.keySet().iterator();      
            String oldText;      
            Object newValue;      
            while(keys.hasNext()){      
                oldText = (String)keys.next();      
                newValue = data.get(oldText);      
                this.replaceAll(selection, oldText, newValue);      
            }      
            //是否打印      
//            if(isPrint){      
//                this.print(document,ober);      
//            }      
           //this.save(word,outPutDocPath);   
        /**    Dispatch pageSetup = Dispatch.get(document, "PageSetup").toDispatch();    

            if(StringUtils.isNotEmpty(ober)&& ober.equals("Orientation")){
            	System.out.println("---------横向打印----------");
            	Dispatch.put(pageSetup, "Orientation", 0);
            }*/
           Dispatch.invoke(document,"SaveAs",Dispatch.Method, new Object[]{htmPath,new Variant(8)}, new int[1]);

        }catch(Exception e){
        	 e.printStackTrace();
	    }finally{ 
	    	if(document!=null)
	    		close(document);
	         word.invoke("Quit", new Variant[0]);      
	         //关闭com的线程      
	      //   ComThread.Release();      

        }
    
    }    
    
    /**    
     * 保存打印doc文档    
     * @param inputDocPath    
     * @param outPutDocPath    
     * @param data    
     * @param isPrint    
     * @throws Exception 
     */     
    public  void saveDoc(String inputDocPath,String htmPath,HashMap<String, Object> data,boolean isPrint,String ober) throws Exception{      
    	 ActiveXComponent word =null;
    	 Dispatch document = null;
    	try{
    		synchronized (this) {
    			//初始化com的线程      
                ComThread.InitSTA();      
                //word运行程序对象      
                word = new ActiveXComponent("Word.Application");      
                //文档对象      
                Dispatch wordObject = (Dispatch) word.getObject();      
                //设置属性  Variant(true)表示word应用程序可见      
                Dispatch.put((Dispatch)wordObject,"Visible", new Variant(false));      
                //word所有文档      
                Dispatch documents = word.getProperty("Documents").toDispatch();      
                //打开文档      
                document = this.open(documents,inputDocPath);      
                Dispatch selection = this.select(word);      
                Iterator<?> keys = data.keySet().iterator();      
                String oldText;      
                Object newValue;      
                while(keys.hasNext()){      
                    oldText = (String)keys.next();      
                    newValue = data.get(oldText);      
                    this.replaceAll(selection, oldText, newValue);      
                }      
                //是否打印      
//                if(isPrint){      
//                    this.print(document,ober);      
//                }      
//               this.save(word,outPutDocPath);   
/**                Dispatch pageSetup = Dispatch.get(document, "PageSetup").toDispatch();    

                if(StringUtils.isNotEmpty(ober)&& ober.equals("Orientation")){
                	System.out.println("---------横向打印----------");
                	Dispatch.put(pageSetup, "Orientation", 0);
                }*/
               Dispatch.invoke(document,"SaveAs",Dispatch.Method, new Object[]{htmPath,new Variant(8)}, new int[1]);	
			}
        }catch(Exception e){
        	 e.printStackTrace();
	    }finally{ 
	    	if(document!=null)
	    		close(document);
	         word.invoke("Quit", new Variant[0]);      
	         //关闭com的线程      
	      //   ComThread.Release();      

        }
    
    }    
    
    public boolean ChageFormat (String DocFile){

        System.out.println(DocFile);

            System.out.println("word文件路径："+DocFile);
            //word文件的完整路径

            String HtmlFile = DocFile.substring(0, (DocFile.length() - 4)) + ".htm";

            System.out.println("htm文件路径："+HtmlFile);
            //html文件的完整路径

            ActiveXComponent app = new ActiveXComponent("Word.Application");
            //启动word

            try
            {
                app.setProperty("Visible", new Variant(false));
                //设置word程序非可视化运行

                Dispatch docs = app.getProperty("Documents").toDispatch();

                Dispatch doc = Dispatch.invoke(docs,"Open", Dispatch.Method, new Object[]{DocFile,new Variant(false), new Variant(true)}, new int[1]).toDispatch(); 
                //打开word文件

                Dispatch.invoke(doc,"SaveAs",Dispatch.Method, new Object[]{HtmlFile,new Variant(8)}, new int[1]);
                //作为htm格式保存文件

                Dispatch.call(doc, "Close",new Variant(false));
                //关闭文件

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                app.invoke("Quit", new Variant[] {});
                //退出word程序
            }
            //转化完毕
            return true;
       
    }


    
    public void printing(String outPutDocPath, String ober) {
		ActiveXComponent word = null;
		Dispatch document = null;
		try {
			word = new ActiveXComponent("Word.Application");
			// 文档对象
			Dispatch wordObject = (Dispatch) word.getObject();
			// 设置属性 Variant(true)表示word应用程序可见
			Dispatch.put((Dispatch) wordObject, "Visible", new Variant(false));
			Dispatch documents = word.getProperty("Documents").toDispatch();

			document = Dispatch.call(documents, "Open", outPutDocPath)
					.toDispatch();

			if (StringUtils.isNotEmpty(ober) && ober.equals("print")) {
				Dispatch.call(document, "PrintOut"); // 打印
			} else if (StringUtils.isNotEmpty(ober) && ober.equals("view")) {
				Dispatch.call(document, "PrintPreView"); // 打印预览
			}
			Dispatch.call(document, "Close", new Variant(false));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("打印失败");
		}finally{

			word.invoke("Quit", new Variant[0]);
		}
	}
   
    /**
     * 删除文件
     * @author denggr
     * @param uploadPath
     */
	public void deleteCopyFile(String uploadPath)
	{
		if (StringUtils.isNotEmpty(uploadPath)) {
			String htmPath = uploadPath.substring(0,uploadPath.lastIndexOf(".")+1);
			String filePath = htmPath+"files";
			try {
				File file = new File(filePath);
				if (!file.isDirectory()) {

					file.delete();
				} else if (file.isDirectory()) {

					String[] filelist = file.list();
					for (int i = 0; i < filelist.length; i++) {
						File delfile = new File(filePath + "\\" + filelist[i]);
						if (!delfile.isDirectory()) {
							delfile.delete();
						} 
					}
					file.delete();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－导出word直接打印－－－－－－－开始－－－－－－－－－－－－－
	 * 把要导出的文件读到流中
	 * @param realhtmPath
	 * @return
	 */
	public String readHtml(String realhtmPath){
		LineNumberReader lnr;
		try{
			lnr = new LineNumberReader( new FileReader(realhtmPath));
			StringBuffer htmlContent = new StringBuffer();
			String line = null;
			while((line = lnr.readLine())!=null){
				htmlContent.append(line).append("\n");
			}
			return htmlContent.toString();
		}catch(IOException e){
			throw new AppException(e);
		}
	}
	
	/**
	 * 重新拼接html文件
	 * @param htmlStr
	 * @param rsHtml
	 */
	public void addIntoHtmlBody(String htmlStr,StringBuffer rsHtml){
		rsHtml.append(this.parseStyle(htmlStr));
		rsHtml.append(this.parseBody(htmlStr));
		rsHtml.append(pageStyle);
	}
	
	/**
	 * 拼接样式
	 * @param htmlStr
	 * @return
	 */
	public String parseStyle(String htmlStr){
		int titleEnd = htmlStr.indexOf("</title>");
		int headEnd = htmlStr.indexOf("</head>");
		if(titleEnd > -1 && headEnd> -1){
			return htmlStr.substring(titleEnd + "</title>".length(),headEnd);
		}else{
			return "";
		}
	}
	
	/**
	 * 拼接body
	 * @param htmlStr
	 * @return
	 */
	public String parseBody(String htmlStr){
		int start = htmlStr.indexOf("<body");
		int end = htmlStr.indexOf("</body>");
		if(start > -1 && end > -1){
			return htmlStr.substring(htmlStr.indexOf(">", start)+1, end);
		}else{
			return "";
		}
	}
	
	
	
	public void readHtmlPrint(String realhtmPath,HttpServletResponse response,String flag){
		LineNumberReader lnr;
		try {
			lnr = new LineNumberReader( new FileReader(realhtmPath));
			StringBuffer htmlContent = new StringBuffer();
			String line = null;
			while((line = lnr.readLine())!=null){
				htmlContent.append(line).append("\n");
			}
//			htmlContent.append("<div style='page-break-after: always;'>");
//			htmlContent.append("</div>");
			//htmlContent.append("<br clear=all style='page-break-before:always'>");
			lnr.close();
			System.out.println("--------------"+htmlContent.toString());
			if(StringUtils.isNotEmpty(flag)&& flag.equals("1")){
				htmlContent.append("<script>").append("\n");
				htmlContent.append("parent.printIframe1();");
				htmlContent.append("</script>").append("\n");
				System.out.println("-------1-------");
			}else if(StringUtils.isNotEmpty(flag)&& flag.equals("2")){
				htmlContent.append("<script>").append("\n");
				htmlContent.append("parent.printIframe2();");
				htmlContent.append("</script>").append("\n");
				System.out.println("-------2-------");
			}
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write(htmlContent.toString());
			out.flush();
		//	out.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("--------文件没找到------");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("--------读取文件失败------");
			e.printStackTrace();
		}
	}
	/*
	 * 以下为打印html辅助文件开始
	 */
	public void rsHtml(StringBuffer rsHtml){
		rsHtml.append("<html>").append("\n");
		rsHtml.append("<head>").append("\n");
		rsHtml.append("</head>").append("\n");
		rsHtml.append("<body>").append("\n");
//		rsHtml.append("<script  type='text/javascript'>").append("\n");
//		removeHead(rsHtml);
//		rsHtml.append("</script>").append("\n");
	}

	public StringBuffer removeHead(StringBuffer rsHtml){
		//	rsHtml.append("<style>@media print {.Noprint{DISPLAY:none;}.PageNext{PAGE-BREAK-AFTER:always}}</style> ").append("\n");
			
//		rsHtml.append("var HKEY_Root,HKEY_Path,HKEY_Key;").append("\n");
//			rsHtml.append("HKEY_Root=\"HKEY_CURRENT_USER\";").append("\n");
//			rsHtml.append("HKEY_Path=\"\\\\Software\\\\Microsoft\\\\Internet Explorer\\\\PageSetup\\\\\"").append("\n");
//			;
			rsHtml.append("function pagesetup_null(){").append("\n");
//			rsHtml.append("try{").append("\n");
//			rsHtml.append("var Wsh=new ActiveXObject('WScript.Shell')").append("\n");
//			rsHtml.append("HKEY_Key='header'; ").append("\n");
//			rsHtml.append("Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');").append("\n");
//			rsHtml.append("HKEY_Key='footer' ").append("\n");
//			rsHtml.append("Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');").append("\n");
//
//			//			rsHtml.append("HKEY_Key='margin_bottom'").append("\n");
////			rsHtml.append("Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'0');").append("\n");
////			rsHtml.append("HKEY_Key='margin_left';").append("\n");
////			rsHtml.append("Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'0'); ").append("\n");
////			rsHtml.append("HKEY_Key='margin_right';").append("\n");
////			rsHtml.append("Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'0');").append("\n");
////			rsHtml.append("HKEY_Key='margin_top';").append("\n");
////			rsHtml.append(" Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'8');").append("\n");
//			rsHtml.append("}catch(e){ }").append("\n");
			rsHtml.append("}").append("\n");
			return rsHtml;
		}

	public void rsHtmlPe(StringBuffer rsHtml){
		rsHtml.append("<script>").append("\n");
		rsHtml.append("alert('请选择打印')").append("\n");
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete1(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe1();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete2(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe2();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete3(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe3();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete4(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe4();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete5(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe5();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete6(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe6();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}
	public void rsHtmlDelete7(StringBuffer rsHtml){
		rsHtml.delete(rsHtml.length()-pageStyle.length(), rsHtml.length());			
		rsHtml.append("<script>").append("\n");
		rsHtml.append("parent.printIframe7();").append("\n");
		rsHtml=this.removeHead(rsHtml);
		rsHtml.append("</script>").append("\n");
	}

	public void rsHtmlEnd(StringBuffer rsHtml){
		
		rsHtml.append("</body>").append("\n");
		rsHtml.append("</html>").append("\n");
	}
	/*
	 * －－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－导出word直接打印－－－－－－－结束－－－－－－－－－－－－－
	 */
} 

