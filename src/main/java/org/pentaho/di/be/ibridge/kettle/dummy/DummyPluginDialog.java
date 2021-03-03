/*     */ package org.pentaho.di.be.ibridge.kettle.dummy;
/*     */ 
/*     */ import org.eclipse.swt.events.ModifyEvent;
/*     */ import org.eclipse.swt.events.ModifyListener;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.events.ShellAdapter;
/*     */ import org.eclipse.swt.events.ShellEvent;
/*     */ import org.eclipse.swt.layout.FormAttachment;
/*     */ import org.eclipse.swt.layout.FormData;
/*     */ import org.eclipse.swt.layout.FormLayout;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Event;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Listener;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Text;
/*     */ import org.pentaho.di.core.row.ValueMetaAndData;
/*     */ import org.pentaho.di.trans.TransMeta;
/*     */ import org.pentaho.di.trans.step.BaseStepMeta;
/*     */ import org.pentaho.di.trans.step.StepDialogInterface;
/*     */ import org.pentaho.di.ui.core.dialog.EnterValueDialog;
/*     */ import org.pentaho.di.ui.trans.step.BaseStepDialog;
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
/*     */ public class DummyPluginDialog
/*     */   extends BaseStepDialog
/*     */   implements StepDialogInterface
/*     */ {
/*     */   private DummyPluginMeta input;
/*     */   private ValueMetaAndData value;
/*     */   private Label wlValName;
/*     */   private Text wValName;
/*     */   private FormData fdlValName;
/*     */   private FormData fdValName;
/*     */   private Label wlValue;
/*     */   private Button wbValue;
/*     */   private Text wValue;
/*     */   private FormData fdlValue;
/*     */   private FormData fdbValue;
/*     */   private FormData fdValue;
/*     */   
/*     */   public DummyPluginDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
/*  65 */     super(parent, (BaseStepMeta)in, transMeta, sname);
/*  66 */     this.input = (DummyPluginMeta)in;
/*  67 */     this.value = this.input.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String open() {
/*  72 */     Shell parent = getParent();
/*  73 */     Display display = parent.getDisplay();
/*     */     
/*  75 */     this.shell = new Shell(parent, 3312);
/*  76 */     this.props.setLook(this.shell);
/*  77 */     setShellImage(this.shell, this.input);
/*     */     
/*  79 */     ModifyListener lsMod = new ModifyListener()
/*     */       {
/*     */         public void modifyText(ModifyEvent e) {
/*  82 */           DummyPluginDialog.this.input.setChanged();
/*     */         }
/*     */       };
/*  85 */     this.changed = this.input.hasChanged();
/*     */     
/*  87 */     FormLayout formLayout = new FormLayout();
/*  88 */     formLayout.marginWidth = 5;
/*  89 */     formLayout.marginHeight = 5;
/*     */     
/*  91 */     this.shell.setLayout(formLayout);
/*  92 */     this.shell.setText(Messages.getString("DummyPluginDialog.Shell.Title"));
/*     */     
/*  94 */     int middle = this.props.getMiddlePct();
/*  95 */     int margin = 4;
/*     */ 
/*     */     
/*  98 */     this.wlStepname = new Label(this.shell, 131072);
/*  99 */     this.wlStepname.setText(Messages.getString("DummyPluginDialog.StepName.Label"));
/* 100 */     this.props.setLook(this.wlStepname);
/* 101 */     this.fdlStepname = new FormData();
/* 102 */     this.fdlStepname.left = new FormAttachment(0, 0);
/* 103 */     this.fdlStepname.right = new FormAttachment(middle, -margin);
/* 104 */     this.fdlStepname.top = new FormAttachment(0, margin);
/* 105 */     this.wlStepname.setLayoutData(this.fdlStepname);
/* 106 */     this.wStepname = new Text(this.shell, 18436);
/* 107 */     this.wStepname.setText(this.stepname);
/* 108 */     this.props.setLook(this.wStepname);
/* 109 */     this.wStepname.addModifyListener(lsMod);
/* 110 */     this.fdStepname = new FormData();
/* 111 */     this.fdStepname.left = new FormAttachment(middle, 0);
/* 112 */     this.fdStepname.top = new FormAttachment(0, margin);
/* 113 */     this.fdStepname.right = new FormAttachment(100, 0);
/* 114 */     this.wStepname.setLayoutData(this.fdStepname);
/*     */ 
/*     */     
/* 117 */     this.wlValName = new Label(this.shell, 131072);
/* 118 */     this.wlValName.setText(Messages.getString("DummyPluginDialog.ValueName.Label"));
/* 119 */     this.props.setLook(this.wlValName);
/* 120 */     this.fdlValName = new FormData();
/* 121 */     this.fdlValName.left = new FormAttachment(0, 0);
/* 122 */     this.fdlValName.right = new FormAttachment(middle, -margin);
/* 123 */     this.fdlValName.top = new FormAttachment(this.wStepname, margin);
/* 124 */     this.wlValName.setLayoutData(this.fdlValName);
/* 125 */     this.wValName = new Text(this.shell, 18436);
/* 126 */     this.props.setLook(this.wValName);
/* 127 */     this.wValName.addModifyListener(lsMod);
/* 128 */     this.fdValName = new FormData();
/* 129 */     this.fdValName.left = new FormAttachment(middle, 0);
/* 130 */     this.fdValName.right = new FormAttachment(100, 0);
/* 131 */     this.fdValName.top = new FormAttachment(this.wStepname, margin);
/* 132 */     this.wValName.setLayoutData(this.fdValName);
/*     */ 
/*     */     
/* 135 */     this.wlValue = new Label(this.shell, 131072);
/* 136 */     this.wlValue.setText(Messages.getString("DummyPluginDialog.ValueToAdd.Label"));
/* 137 */     this.props.setLook(this.wlValue);
/* 138 */     this.fdlValue = new FormData();
/* 139 */     this.fdlValue.left = new FormAttachment(0, 0);
/* 140 */     this.fdlValue.right = new FormAttachment(middle, -margin);
/* 141 */     this.fdlValue.top = new FormAttachment(this.wValName, margin);
/* 142 */     this.wlValue.setLayoutData(this.fdlValue);
/*     */     
/* 144 */     this.wbValue = new Button(this.shell, 16777224);
/* 145 */     this.props.setLook(this.wbValue);
/* 146 */     this.wbValue.setText(Messages.getString("System.Button.Edit"));
/* 147 */     this.fdbValue = new FormData();
/* 148 */     this.fdbValue.right = new FormAttachment(100, 0);
/* 149 */     this.fdbValue.top = new FormAttachment(this.wValName, margin);
/* 150 */     this.wbValue.setLayoutData(this.fdbValue);
/*     */     
/* 152 */     this.wValue = new Text(this.shell, 18436);
/* 153 */     this.props.setLook(this.wValue);
/* 154 */     this.wValue.addModifyListener(lsMod);
/* 155 */     this.fdValue = new FormData();
/* 156 */     this.fdValue.left = new FormAttachment(middle, 0);
/* 157 */     this.fdValue.right = new FormAttachment(this.wbValue, -margin);
/* 158 */     this.fdValue.top = new FormAttachment(this.wValName, margin);
/* 159 */     this.wValue.setLayoutData(this.fdValue);
/*     */     
/* 161 */     this.wbValue.addSelectionListener(new SelectionAdapter()
/*     */         {
/*     */           public void widgetSelected(SelectionEvent arg0) {
/* 164 */             ValueMetaAndData v = (ValueMetaAndData)DummyPluginDialog.this.value.clone();
/* 165 */             EnterValueDialog evd = new EnterValueDialog(DummyPluginDialog.this.shell, 0, v.getValueMeta(), v.getValueData());
/* 166 */             ValueMetaAndData newval = evd.open();
/* 167 */             if (newval != null) {
/* 168 */               DummyPluginDialog.this.value = newval;
/* 169 */               DummyPluginDialog.this.getData();
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 175 */     this.wOK = new Button(this.shell, 8);
/* 176 */     this.wOK.setText(Messages.getString("System.Button.OK"));
/* 177 */     this.wCancel = new Button(this.shell, 8);
/* 178 */     this.wCancel.setText(Messages.getString("System.Button.Cancel"));
/*     */     
/* 180 */     BaseStepDialog.positionBottomButtons(this.shell, new Button[] { this.wOK, this.wCancel }, margin, this.wValue);
/*     */ 
/*     */     
/* 183 */     this.lsCancel = new Listener()
/*     */       {
/*     */         public void handleEvent(Event e) {
/* 186 */           DummyPluginDialog.this.cancel();
/*     */         }
/*     */       };
/* 189 */     this.lsOK = new Listener()
/*     */       {
/*     */         public void handleEvent(Event e) {
/* 192 */           DummyPluginDialog.this.ok();
/*     */         }
/*     */       };
/*     */     
/* 196 */     this.wCancel.addListener(13, this.lsCancel);
/* 197 */     this.wOK.addListener(13, this.lsOK);
/*     */     
/* 199 */     this.lsDef = new SelectionAdapter()
/*     */       {
/*     */         public void widgetDefaultSelected(SelectionEvent e) {
/* 202 */           DummyPluginDialog.this.ok();
/*     */         }
/*     */       };
/*     */     
/* 206 */     this.wStepname.addSelectionListener(this.lsDef);
/* 207 */     this.wValName.addSelectionListener(this.lsDef);
/*     */ 
/*     */     
/* 210 */     this.shell.addShellListener(new ShellAdapter()
/*     */         {
/*     */           public void shellClosed(ShellEvent e) {
/* 213 */             DummyPluginDialog.this.cancel();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 218 */     setSize();
/*     */     
/* 220 */     getData();
/* 221 */     this.input.setChanged(this.changed);
/*     */     
/* 223 */     this.shell.open();
/* 224 */     while (!this.shell.isDisposed()) {
/* 225 */       if (!display.readAndDispatch()) {
/* 226 */         display.sleep();
/*     */       }
/*     */     } 
/* 229 */     return this.stepname;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getData() {
/* 234 */     this.wStepname.selectAll();
/* 235 */     if (this.value != null) {
/* 236 */       this.wValName.setText(this.value.getValueMeta().getName());
/* 237 */       this.wValue.setText(this.value.toString() + " (" + this.value.toStringMeta() + ")");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cancel() {
/* 242 */     this.stepname = null;
/* 243 */     this.input.setChanged(this.changed);
/* 244 */     dispose();
/*     */   }
/*     */   
/*     */   private void ok() {
/* 248 */     this.stepname = this.wStepname.getText();
/* 249 */     this.value.getValueMeta().setName(this.wValName.getText());
/* 250 */     this.input.setValue(this.value);
/* 251 */     dispose();
/*     */   }
/*     */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/be/ibridge/kettle/dummy/DummyPluginDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */