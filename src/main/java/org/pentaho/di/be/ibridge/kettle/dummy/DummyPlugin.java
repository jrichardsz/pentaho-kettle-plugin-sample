/*     */ package org.pentaho.di.be.ibridge.kettle.dummy;
/*     */ 
/*     */ import org.pentaho.di.core.Const;
/*     */ import org.pentaho.di.core.exception.KettleException;
/*     */ import org.pentaho.di.core.row.RowDataUtil;
/*     */ import org.pentaho.di.trans.Trans;
/*     */ import org.pentaho.di.trans.TransMeta;
/*     */ import org.pentaho.di.trans.step.BaseStep;
/*     */ import org.pentaho.di.trans.step.StepDataInterface;
/*     */ import org.pentaho.di.trans.step.StepInterface;
/*     */ import org.pentaho.di.trans.step.StepMeta;
/*     */ import org.pentaho.di.trans.step.StepMetaInterface;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DummyPlugin
/*     */   extends BaseStep
/*     */   implements StepInterface
/*     */ {
/*     */   private DummyPluginData data;
/*     */   private DummyPluginMeta meta;
/*     */   
/*  49 */   public DummyPlugin(StepMeta s, StepDataInterface stepDataInterface, int c, TransMeta t, Trans dis) { super(s, stepDataInterface, c, t, dis); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
/*  54 */     this.meta = (DummyPluginMeta)smi;
/*  55 */     this.data = (DummyPluginData)sdi;
/*     */     
/*  57 */     Object[] r = getRow();
/*  58 */     if (r == null) {
/*  59 */       setOutputDone();
/*  60 */       return false;
/*     */     } 
/*     */     
/*  63 */     if (this.first) {
/*  64 */       this.first = false;
/*     */       
/*  66 */       this.data.outputRowMeta = getInputRowMeta().clone();
/*  67 */       this.meta.getFields(this.data.outputRowMeta, getStepname(), null, null, this);
/*     */     } 
/*     */     
/*  70 */     Object extraValue = this.meta.getValue().getValueData();
/*     */     
/*  72 */     Object[] outputRow = RowDataUtil.addValueData(r, this.data.outputRowMeta.size() - 1, extraValue);
/*     */     
/*  74 */     putRow(this.data.outputRowMeta, outputRow);
/*     */     
/*  76 */     if (checkFeedback(getLinesRead())) {
/*  77 */       logBasic("Linenr " + getLinesRead());
/*     */     }
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean init(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
/*  85 */     this.meta = (DummyPluginMeta)smi;
/*  86 */     this.data = (DummyPluginData)sdi;
/*     */     
/*  88 */     return super.init(smi, sdi);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose(StepMetaInterface smi, StepDataInterface sdi) {
/*  93 */     this.meta = (DummyPluginMeta)smi;
/*  94 */     this.data = (DummyPluginData)sdi;
/*     */     
/*  96 */     super.dispose(smi, sdi);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 102 */     logBasic("Starting to run...");
/*     */     try {
/* 104 */       while (processRow(this.meta, this.data) && !isStopped());
/*     */     
/*     */     }
/* 107 */     catch (Exception e) {
/* 108 */       logError("Unexpected error : " + e.toString());
/* 109 */       logError(Const.getStackTracker(e));
/* 110 */       setErrors(1L);
/* 111 */       stopAll();
/*     */     } finally {
/* 113 */       dispose(this.meta, this.data);
/* 114 */       logBasic("Finished, processing " + getLinesRead() + " rows");
/* 115 */       markStop();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/be/ibridge/kettle/dummy/DummyPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */