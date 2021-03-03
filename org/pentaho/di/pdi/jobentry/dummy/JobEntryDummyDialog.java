/*     */ package org.pentaho.di.pdi.jobentry.dummy;
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
/*     */ import org.pentaho.di.core.Const;
/*     */ import org.pentaho.di.job.JobMeta;
/*     */ import org.pentaho.di.job.entry.JobEntryDialogInterface;
/*     */ import org.pentaho.di.job.entry.JobEntryInterface;
/*     */ import org.pentaho.di.repository.Repository;
/*     */ import org.pentaho.di.ui.core.PropsUI;
/*     */ import org.pentaho.di.ui.core.gui.WindowProperty;
/*     */ import org.pentaho.di.ui.core.widget.TextVar;
/*     */ import org.pentaho.di.ui.job.dialog.JobDialog;
/*     */ import org.pentaho.di.ui.job.entry.JobEntryDialog;
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
/*     */ public class JobEntryDummyDialog
/*     */   extends JobEntryDialog
/*     */   implements JobEntryDialogInterface
/*     */ {
/*     */   private Label wlName;
/*     */   private Text wName;
/*     */   private FormData fdlName;
/*     */   private FormData fdName;
/*     */   private Label wlSourceDirectory;
/*     */   private TextVar wSourceDirectory;
/*     */   private FormData fdlSourceDirectory;
/*     */   private FormData fdSourceDirectory;
/*     */   private Label wlTargetDirectory;
/*     */   private TextVar wTargetDirectory;
/*     */   private FormData fdlTargetDirectory;
/*     */   private FormData fdTargetDirectory;
/*     */   private Label wlWildcard;
/*     */   private TextVar wWildcard;
/*     */   private FormData fdlWildcard;
/*     */   private FormData fdWildcard;
/*     */   private Button wOK;
/*     */   private Button wCancel;
/*     */   private Listener lsOK;
/*     */   private Listener lsCancel;
/*     */   private JobEntryDummy jobEntry;
/*     */   private Shell shell;
/*     */   private PropsUI props;
/*     */   private SelectionAdapter lsDef;
/*     */   private boolean changed;
/*     */   
/*     */   public JobEntryDummyDialog(Shell parent, JobEntryInterface jobEntryInt, Repository rep, JobMeta jobMeta) {
/*  97 */     super(parent, jobEntryInt, rep, jobMeta);
/*  98 */     this.props = PropsUI.getInstance();
/*  99 */     this.jobEntry = (JobEntryDummy)jobEntryInt;
/*     */     
/* 101 */     if (this.jobEntry.getName() == null) {
/* 102 */       this.jobEntry.setName(jobEntryInt.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JobEntryInterface open() {
/* 108 */     Shell parent = getParent();
/* 109 */     Display display = parent.getDisplay();
/*     */     
/* 111 */     this.shell = new Shell(parent, 3312);
/* 112 */     this.props.setLook(this.shell);
/* 113 */     JobDialog.setShellImage(this.shell, this.jobEntry);
/*     */     
/* 115 */     ModifyListener lsMod = new ModifyListener()
/*     */       {
/*     */         public void modifyText(ModifyEvent e) {
/* 118 */           JobEntryDummyDialog.this.jobEntry.setChanged();
/*     */         }
/*     */       };
/* 121 */     this.changed = this.jobEntry.hasChanged();
/*     */     
/* 123 */     FormLayout formLayout = new FormLayout();
/* 124 */     formLayout.marginWidth = 5;
/* 125 */     formLayout.marginHeight = 5;
/*     */     
/* 127 */     this.shell.setLayout(formLayout);
/* 128 */     this.shell.setText(Messages.getString("DummyPluginDialog.Shell.Title"));
/*     */     
/* 130 */     int middle = this.props.getMiddlePct();
/* 131 */     int margin = 4;
/*     */ 
/*     */     
/* 134 */     this.wlName = new Label(this.shell, 131072);
/* 135 */     this.wlName.setText("Job entry name ");
/* 136 */     this.props.setLook(this.wlName);
/* 137 */     this.fdlName = new FormData();
/* 138 */     this.fdlName.left = new FormAttachment(0, 0);
/* 139 */     this.fdlName.right = new FormAttachment(middle, -margin);
/* 140 */     this.fdlName.top = new FormAttachment(0, margin);
/* 141 */     this.wlName.setLayoutData(this.fdlName);
/* 142 */     this.wName = new Text(this.shell, 18436);
/* 143 */     this.props.setLook(this.wName);
/* 144 */     this.wName.addModifyListener(lsMod);
/* 145 */     this.fdName = new FormData();
/* 146 */     this.fdName.left = new FormAttachment(middle, 0);
/* 147 */     this.fdName.top = new FormAttachment(0, margin);
/* 148 */     this.fdName.right = new FormAttachment(100, 0);
/* 149 */     this.wName.setLayoutData(this.fdName);
/*     */ 
/*     */     
/* 152 */     this.wlSourceDirectory = new Label(this.shell, 131072);
/* 153 */     this.wlSourceDirectory.setText("Source directory");
/* 154 */     this.props.setLook(this.wlSourceDirectory);
/* 155 */     this.fdlSourceDirectory = new FormData();
/* 156 */     this.fdlSourceDirectory.left = new FormAttachment(0, 0);
/* 157 */     this.fdlSourceDirectory.top = new FormAttachment(this.wName, margin);
/* 158 */     this.fdlSourceDirectory.right = new FormAttachment(middle, -margin);
/* 159 */     this.wlSourceDirectory.setLayoutData(this.fdlSourceDirectory);
/* 160 */     this.wSourceDirectory = new TextVar(this.jobMeta, this.shell, 18436);
/* 161 */     this.props.setLook(this.wSourceDirectory);
/* 162 */     this.wSourceDirectory.addModifyListener(lsMod);
/* 163 */     this.fdSourceDirectory = new FormData();
/* 164 */     this.fdSourceDirectory.left = new FormAttachment(middle, 0);
/* 165 */     this.fdSourceDirectory.top = new FormAttachment(this.wName, margin);
/* 166 */     this.fdSourceDirectory.right = new FormAttachment(100, 0);
/* 167 */     this.wSourceDirectory.setLayoutData(this.fdSourceDirectory);
/*     */ 
/*     */     
/* 170 */     this.wlTargetDirectory = new Label(this.shell, 131072);
/* 171 */     this.wlTargetDirectory.setText("Target directory");
/* 172 */     this.props.setLook(this.wlTargetDirectory);
/* 173 */     this.fdlTargetDirectory = new FormData();
/* 174 */     this.fdlTargetDirectory.left = new FormAttachment(0, 0);
/* 175 */     this.fdlTargetDirectory.top = new FormAttachment(this.wSourceDirectory, margin);
/* 176 */     this.fdlTargetDirectory.right = new FormAttachment(middle, -margin);
/* 177 */     this.wlTargetDirectory.setLayoutData(this.fdlTargetDirectory);
/* 178 */     this.wTargetDirectory = new TextVar(this.jobMeta, this.shell, 18436);
/* 179 */     this.props.setLook(this.wTargetDirectory);
/* 180 */     this.wTargetDirectory.addModifyListener(lsMod);
/* 181 */     this.fdTargetDirectory = new FormData();
/* 182 */     this.fdTargetDirectory.left = new FormAttachment(middle, 0);
/* 183 */     this.fdTargetDirectory.top = new FormAttachment(this.wSourceDirectory, margin);
/* 184 */     this.fdTargetDirectory.right = new FormAttachment(100, 0);
/* 185 */     this.wTargetDirectory.setLayoutData(this.fdTargetDirectory);
/*     */ 
/*     */     
/* 188 */     this.wlWildcard = new Label(this.shell, 131072);
/* 189 */     this.wlWildcard.setText("Wildcard (regular expression)");
/* 190 */     this.props.setLook(this.wlWildcard);
/* 191 */     this.fdlWildcard = new FormData();
/* 192 */     this.fdlWildcard.left = new FormAttachment(0, 0);
/* 193 */     this.fdlWildcard.top = new FormAttachment(this.wTargetDirectory, margin);
/* 194 */     this.fdlWildcard.right = new FormAttachment(middle, -margin);
/* 195 */     this.wlWildcard.setLayoutData(this.fdlWildcard);
/* 196 */     this.wWildcard = new TextVar(this.jobMeta, this.shell, 18436);
/* 197 */     this.props.setLook(this.wWildcard);
/* 198 */     this.wWildcard.addModifyListener(lsMod);
/* 199 */     this.fdWildcard = new FormData();
/* 200 */     this.fdWildcard.left = new FormAttachment(middle, 0);
/* 201 */     this.fdWildcard.top = new FormAttachment(this.wTargetDirectory, margin);
/* 202 */     this.fdWildcard.right = new FormAttachment(100, 0);
/* 203 */     this.wWildcard.setLayoutData(this.fdWildcard);
/*     */     
/* 205 */     this.wOK = new Button(this.shell, 8);
/* 206 */     this.wOK.setText(" &OK ");
/* 207 */     this.wCancel = new Button(this.shell, 8);
/* 208 */     this.wCancel.setText(" &Cancel ");
/*     */     
/* 210 */     BaseStepDialog.positionBottomButtons(this.shell, new Button[] { this.wOK, this.wCancel }, margin, this.wWildcard);
/*     */ 
/*     */     
/* 213 */     this.lsCancel = new Listener()
/*     */       {
/*     */         public void handleEvent(Event e) {
/* 216 */           JobEntryDummyDialog.this.cancel();
/*     */         }
/*     */       };
/* 219 */     this.lsOK = new Listener()
/*     */       {
/*     */         public void handleEvent(Event e) {
/* 222 */           JobEntryDummyDialog.this.ok();
/*     */         }
/*     */       };
/*     */     
/* 226 */     this.wCancel.addListener(13, this.lsCancel);
/* 227 */     this.wOK.addListener(13, this.lsOK);
/*     */     
/* 229 */     this.lsDef = new SelectionAdapter()
/*     */       {
/*     */         public void widgetDefaultSelected(SelectionEvent e) {
/* 232 */           JobEntryDummyDialog.this.ok();
/*     */         }
/*     */       };
/*     */     
/* 236 */     this.wSourceDirectory.addSelectionListener(this.lsDef);
/* 237 */     this.wTargetDirectory.addSelectionListener(this.lsDef);
/* 238 */     this.wWildcard.addSelectionListener(this.lsDef);
/*     */ 
/*     */     
/* 241 */     this.shell.addShellListener(new ShellAdapter()
/*     */         {
/*     */           public void shellClosed(ShellEvent e) {
/* 244 */             JobEntryDummyDialog.this.cancel();
/*     */           }
/*     */         });
/*     */     
/* 248 */     getData();
/*     */     
/* 250 */     BaseStepDialog.setSize(this.shell);
/*     */     
/* 252 */     this.shell.open();
/* 253 */     while (!this.shell.isDisposed()) {
/* 254 */       if (!display.readAndDispatch()) {
/* 255 */         display.sleep();
/*     */       }
/*     */     } 
/* 258 */     return this.jobEntry;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 262 */     WindowProperty winprop = new WindowProperty(this.shell);
/* 263 */     this.props.setScreen(winprop);
/* 264 */     this.shell.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getData() {
/* 271 */     if (this.jobEntry.getName() != null) {
/* 272 */       this.wName.setText(this.jobEntry.getName());
/*     */     }
/* 274 */     this.wName.selectAll();
/*     */     
/* 276 */     this.wSourceDirectory.setText(Const.NVL(this.jobEntry.getSourceDirectory(), ""));
/* 277 */     this.wTargetDirectory.setText(Const.NVL(this.jobEntry.getTargetDirectory(), ""));
/* 278 */     this.wWildcard.setText(Const.NVL(this.jobEntry.getWildcard(), ""));
/*     */   }
/*     */   
/*     */   private void cancel() {
/* 282 */     this.jobEntry.setChanged(this.changed);
/* 283 */     this.jobEntry = null;
/* 284 */     dispose();
/*     */   }
/*     */   
/*     */   private void ok() {
/* 288 */     this.jobEntry.setName(this.wName.getText());
/* 289 */     this.jobEntry.setSourceDirectory(this.wSourceDirectory.getText());
/* 290 */     this.jobEntry.setTargetDirectory(this.wTargetDirectory.getText());
/* 291 */     this.jobEntry.setWildcard(this.wWildcard.getText());
/*     */     
/* 293 */     dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 298 */   public String toString() { return getClass().getName(); }
/*     */ 
/*     */ 
/*     */   
/* 302 */   public boolean evaluates() { return true; }
/*     */ 
/*     */ 
/*     */   
/* 306 */   public boolean isUnconditional() { return false; }
/*     */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/pdi/jobentry/dummy/JobEntryDummyDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */