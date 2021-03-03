/*    */ package org.pentaho.di.pdi.jobentry.dummy;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.regex.Pattern;
/*    */ import org.pentaho.di.core.exception.KettleJobException;
/*    */ import org.pentaho.di.core.logging.LogChannel;
/*    */ import org.pentaho.di.core.util.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DummyJob
/*    */ {
/*    */   private String _wildcard;
/*    */   private String _targetDir;
/*    */   private String _sourceDir;
/*    */   
/*    */   public DummyJob(String source, String target, String wildcard) {
/* 44 */     this._sourceDir = source;
/* 45 */     this._targetDir = target;
/* 46 */     this._wildcard = wildcard;
/*    */   }
/*    */   
/*    */   public long process() throws KettleJobException, FileNotFoundException {
/* 50 */     LogChannel logChannel = new LogChannel(this);
/* 51 */     File srcDir = getDir(this._sourceDir);
/* 52 */     Pattern pattern = null;
/* 53 */     if (!Utils.isEmpty(this._wildcard)) {
/* 54 */       pattern = Pattern.compile(this._wildcard);
/*    */     }
/* 56 */     final Pattern fpat = pattern;
/* 57 */     FileFilter regexFiler = new FileFilter()
/*    */       {
/*    */         public boolean accept(File pathname) {
/* 60 */           if (fpat == null) {
/* 61 */             return true;
/*    */           }
/* 63 */           if (fpat.matcher(pathname.getName()).matches()) {
/* 64 */             return true;
/*    */           }
/* 66 */           return false;
/*    */         }
/*    */       };
/* 69 */     long files = 0L;
/* 70 */     File[] allFiles = srcDir.listFiles(regexFiler);
/* 71 */     File outDir = new File(this._targetDir);
/* 72 */     outDir.mkdirs();
/* 73 */     for (int i = 0; i < allFiles.length; i++) {
/* 74 */       File cFile = allFiles[i];
/* 75 */       logChannel.logDetailed(toString(), new Object[] { "processing file '" + cFile + "'" });
/* 76 */       processFile(cFile, outDir);
/*    */     } 
/* 78 */     return files;
/*    */   }
/*    */   
/*    */   public File getDir(String dirname) throws KettleJobException {
/* 82 */     File fl = new File(dirname);
/* 83 */     if (!fl.isDirectory()) {
/* 84 */       throw new KettleJobException("'" + dirname + "' is not a directory");
/*    */     }
/* 86 */     return fl;
/*    */   }
/*    */   
/*    */   public void processFile(File fl, File outDir) throws FileNotFoundException {}
/*    */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/pdi/jobentry/dummy/DummyJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */