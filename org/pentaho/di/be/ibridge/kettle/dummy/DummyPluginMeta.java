/*     */ package org.pentaho.di.be.ibridge.kettle.dummy;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.pentaho.di.core.CheckResult;
/*     */ import org.pentaho.di.core.CheckResultInterface;
/*     */ import org.pentaho.di.core.Const;
/*     */ import org.pentaho.di.core.Counter;
/*     */ import org.pentaho.di.core.annotations.Step;
/*     */ import org.pentaho.di.core.database.DatabaseMeta;
/*     */ import org.pentaho.di.core.exception.KettleDatabaseException;
/*     */ import org.pentaho.di.core.exception.KettleException;
/*     */ import org.pentaho.di.core.exception.KettleXMLException;
/*     */ import org.pentaho.di.core.row.RowMetaInterface;
/*     */ import org.pentaho.di.core.row.ValueMeta;
/*     */ import org.pentaho.di.core.row.ValueMetaAndData;
/*     */ import org.pentaho.di.core.row.ValueMetaInterface;
/*     */ import org.pentaho.di.core.row.value.ValueMetaFactory;
/*     */ import org.pentaho.di.core.row.value.ValueMetaNumber;
/*     */ import org.pentaho.di.core.row.value.ValueMetaString;
/*     */ import org.pentaho.di.core.variables.VariableSpace;
/*     */ import org.pentaho.di.core.xml.XMLHandler;
/*     */ import org.pentaho.di.repository.ObjectId;
/*     */ import org.pentaho.di.repository.Repository;
/*     */ import org.pentaho.di.trans.Trans;
/*     */ import org.pentaho.di.trans.TransMeta;
/*     */ import org.pentaho.di.trans.step.BaseStepMeta;
/*     */ import org.pentaho.di.trans.step.StepDataInterface;
/*     */ import org.pentaho.di.trans.step.StepDialogInterface;
/*     */ import org.pentaho.di.trans.step.StepInterface;
/*     */ import org.pentaho.di.trans.step.StepMeta;
/*     */ import org.pentaho.di.trans.step.StepMetaInterface;
/*     */ import org.w3c.dom.Node;
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
/*     */ @Step(id = "DummyStep", image = "DPL.svg", i18nPackageName = "be.ibridge.kettle.dummy", name = "DummyPlugin.Step.Name", description = "DummyPlugin.Step.Description", categoryDescription = "Deprecated")
/*     */ public class DummyPluginMeta
/*     */   extends BaseStepMeta
/*     */   implements StepMetaInterface
/*     */ {
/*     */   private ValueMetaAndData value;
/*     */   
/*  64 */   public ValueMetaAndData getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setValue(ValueMetaAndData value) { this.value = value; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXML() throws KettleException {
/*  76 */     retval = "";
/*     */     
/*  78 */     retval = retval + "    <values>" + Const.CR;
/*  79 */     if (this.value != null) {
/*  80 */       retval = retval + this.value.getXML();
/*     */     }
/*  82 */     return retval + "      </values>" + Const.CR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getFields(RowMetaInterface r, String origin, RowMetaInterface[] info, StepMeta nextStep, VariableSpace space) {
/*  89 */     if (this.value != null) {
/*  90 */       ValueMetaInterface v = this.value.getValueMeta();
/*  91 */       v.setOrigin(origin);
/*     */       
/*  93 */       r.addValueMeta(v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Object clone() { return super.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadXML(Node stepnode, List<DatabaseMeta> databases, Map<String, Counter> counters) throws KettleXMLException {
/*     */     try {
/* 106 */       this.value = new ValueMetaAndData();
/*     */       
/* 108 */       Node valnode = XMLHandler.getSubNode(stepnode, "values", "value");
/* 109 */       if (valnode != null) {
/* 110 */         System.out.println("reading value in " + valnode);
/* 111 */         this.value.loadXML(valnode);
/*     */       } 
/* 113 */     } catch (Exception e) {
/* 114 */       throw new KettleXMLException("Unable to read step info from XML node", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefault() {
/* 120 */     this.value = new ValueMetaAndData(new ValueMetaNumber("valuename"), new Double(123.456D));
/* 121 */     this.value.getValueMeta().setLength(12);
/* 122 */     this.value.getValueMeta().setPrecision(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readRep(Repository rep, ObjectId id_step, List<DatabaseMeta> databases, Map<String, Counter> counters) throws KettleException {
/*     */     try {
/* 128 */       String name = rep.getStepAttributeString(id_step, 0, "value_name");
/* 129 */       String typedesc = rep.getStepAttributeString(id_step, 0, "value_type");
/* 130 */       String text = rep.getStepAttributeString(id_step, 0, "value_text");
/* 131 */       boolean isnull = rep.getStepAttributeBoolean(id_step, 0, "value_null");
/* 132 */       int length = (int)rep.getStepAttributeInteger(id_step, 0, "value_length");
/* 133 */       int precision = (int)rep.getStepAttributeInteger(id_step, 0, "value_precision");
/*     */       
/* 135 */       int type = ValueMetaFactory.getIdForValueMeta(typedesc);
/* 136 */       this.value = new ValueMetaAndData(new ValueMeta(name, type), null);
/* 137 */       this.value.getValueMeta().setLength(length);
/* 138 */       this.value.getValueMeta().setPrecision(precision);
/*     */       
/* 140 */       if (isnull) {
/* 141 */         this.value.setValueData(null);
/*     */       } else {
/* 143 */         ValueMetaString valueMetaString = new ValueMetaString(name);
/* 144 */         if (type != 2) {
/* 145 */           text = Const.trim(text);
/*     */         }
/* 147 */         this.value.setValueData(this.value.getValueMeta().convertData(valueMetaString, text));
/*     */       } 
/* 149 */     } catch (KettleDatabaseException dbe) {
/* 150 */       throw new KettleException("error reading step with id_step=" + id_step + " from the repository", dbe);
/* 151 */     } catch (Exception e) {
/* 152 */       throw new KettleException("Unexpected error reading step with id_step=" + id_step + " from the repository", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveRep(Repository rep, ObjectId id_transformation, ObjectId id_step) throws KettleException {
/*     */     try {
/* 159 */       rep.saveStepAttribute(id_transformation, id_step, "value_name", this.value.getValueMeta().getName());
/* 160 */       rep.saveStepAttribute(id_transformation, id_step, 0, "value_type", this.value.getValueMeta().getTypeDesc());
/* 161 */       rep.saveStepAttribute(id_transformation, id_step, 0, "value_text", this.value.getValueMeta().getString(this.value.getValueData()));
/* 162 */       rep.saveStepAttribute(id_transformation, id_step, 0, "value_null", this.value.getValueMeta().isNull(this.value.getValueData()));
/* 163 */       rep.saveStepAttribute(id_transformation, id_step, 0, "value_length", this.value.getValueMeta().getLength());
/* 164 */       rep.saveStepAttribute(id_transformation, id_step, 0, "value_precision", this.value.getValueMeta().getPrecision());
/* 165 */     } catch (KettleDatabaseException dbe) {
/* 166 */       throw new KettleException("Unable to save step information to the repository, id_step=" + id_step, dbe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void check(List<CheckResultInterface> remarks, TransMeta transmeta, StepMeta stepMeta, RowMetaInterface prev, String[] input, String[] output, RowMetaInterface info) {
/* 173 */     if (prev == null || prev.size() == 0) {
/* 174 */       CheckResult cr = new CheckResult(3, "Not receiving any fields from previous steps!", stepMeta);
/* 175 */       remarks.add(cr);
/*     */     } else {
/* 177 */       CheckResult cr = new CheckResult(1, "Step is connected to previous one, receiving " + prev.size() + " fields", stepMeta);
/* 178 */       remarks.add(cr);
/*     */     } 
/*     */ 
/*     */     
/* 182 */     if (input.length > 0) {
/* 183 */       CheckResult checkResult = new CheckResult(1, "Step is receiving info from other steps.", stepMeta);
/* 184 */       remarks.add(checkResult);
/*     */     } else {
/* 186 */       CheckResult checkResult = new CheckResult(4, "No input received from other steps!", stepMeta);
/* 187 */       remarks.add(checkResult);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 192 */   public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta, TransMeta transMeta, String name) { return new DummyPluginDialog(shell, meta, transMeta, name); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta, Trans disp) { return new DummyPlugin(stepMeta, stepDataInterface, cnr, transMeta, disp); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public StepDataInterface getStepData() { return new DummyPluginData(); }
/*     */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/be/ibridge/kettle/dummy/DummyPluginMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */