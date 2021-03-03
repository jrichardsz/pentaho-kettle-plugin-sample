/*     */ package org.pentaho.di.pdi.jobentry.dummy;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.pentaho.di.cluster.SlaveServer;
/*     */ import org.pentaho.di.core.Result;
/*     */ import org.pentaho.di.core.annotations.JobEntry;
/*     */ import org.pentaho.di.core.database.DatabaseMeta;
/*     */ import org.pentaho.di.core.exception.KettleDatabaseException;
/*     */ import org.pentaho.di.core.exception.KettleException;
/*     */ import org.pentaho.di.core.exception.KettleXMLException;
/*     */ import org.pentaho.di.core.xml.XMLHandler;
/*     */ import org.pentaho.di.job.entry.JobEntryBase;
/*     */ import org.pentaho.di.job.entry.JobEntryInterface;
/*     */ import org.pentaho.di.repository.ObjectId;
/*     */ import org.pentaho.di.repository.Repository;
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
/*     */ @JobEntry(id = "DummyJob", i18nPackageName = "pdi.jobentry.dummy", image = "DPL.svg", name = "DummyPlugin.Job.Name", description = "DummyPlugin.Job.Description", categoryDescription = "Deprecated")
/*     */ public class JobEntryDummy
/*     */   extends JobEntryBase
/*     */   implements Cloneable, JobEntryInterface
/*     */ {
/*     */   private static final String WILDCARD = "wildcard";
/*     */   private static final String TARGETDIRECTORY = "targetdirectory";
/*     */   private static final String SOURCEDIRECTORY = "sourcedirectory";
/*     */   private String sourceDirectory;
/*     */   private String targetDirectory;
/*     */   private String wildcard;
/*     */   
/*  66 */   public final String getSourceDirectory() { return this.sourceDirectory; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public final void setSourceDirectory(String sourceDirectory) { this.sourceDirectory = sourceDirectory; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public final String getWildcard() { return this.wildcard; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public final void setWildcard(String wildcard) { this.wildcard = wildcard; }
/*     */ 
/*     */   
/*     */   public JobEntryDummy(String n) {
/*  82 */     super(n, "");
/*  83 */     setID(-1L);
/*     */   }
/*     */ 
/*     */   
/*  87 */   public JobEntryDummy() { this(""); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public Object clone() { return (JobEntryDummy)super.clone(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXML() {
/*  98 */     StringBuffer retval = new StringBuffer();
/*     */     
/* 100 */     retval.append(super.getXML());
/*     */     
/* 102 */     retval.append("      " + XMLHandler.addTagValue("sourcedirectory", this.sourceDirectory));
/* 103 */     retval.append("      " + XMLHandler.addTagValue("targetdirectory", this.targetDirectory));
/* 104 */     retval.append("      " + XMLHandler.addTagValue("wildcard", this.wildcard));
/*     */     
/* 106 */     return retval.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadXML(Node entrynode, List<DatabaseMeta> databases, List<SlaveServer> slaveServers, Repository rep) throws KettleXMLException {
/*     */     try {
/* 112 */       loadXML(entrynode, databases, slaveServers);
/* 113 */       this.sourceDirectory = XMLHandler.getTagValue(entrynode, "sourcedirectory");
/* 114 */       this.targetDirectory = XMLHandler.getTagValue(entrynode, "targetdirectory");
/* 115 */       this.wildcard = XMLHandler.getTagValue(entrynode, "wildcard");
/* 116 */     } catch (KettleXMLException xe) {
/* 117 */       throw new KettleXMLException("Unable to load file exists job entry from XML node", xe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadRep(Repository rep, ObjectId id_jobentry, List<DatabaseMeta> databases, List<SlaveServer> slaveServers) throws KettleException {
/*     */     try {
/* 125 */       super.loadRep(rep, id_jobentry, databases, slaveServers);
/* 126 */       this.sourceDirectory = rep.getJobEntryAttributeString(id_jobentry, "sourcedirectory");
/* 127 */       this.targetDirectory = rep.getJobEntryAttributeString(id_jobentry, "targetdirectory");
/* 128 */       this.wildcard = rep.getJobEntryAttributeString(id_jobentry, "wildcard");
/* 129 */     } catch (KettleException dbe) {
/* 130 */       throw new KettleException("Unable to load job entry for type file exists from the repository for id_jobentry=" + id_jobentry, dbe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveRep(Repository rep, ObjectId id_job) throws KettleException {
/*     */     try {
/* 139 */       super.saveRep(rep, id_job);
/*     */       
/* 141 */       rep.saveJobEntryAttribute(id_job, getObjectId(), "sourcedirectory", this.sourceDirectory);
/* 142 */       rep.saveJobEntryAttribute(id_job, getObjectId(), "targetdirectory", this.targetDirectory);
/* 143 */       rep.saveJobEntryAttribute(id_job, getObjectId(), "wildcard", this.wildcard);
/* 144 */     } catch (KettleDatabaseException dbe) {
/* 145 */       throw new KettleException("unable to save jobentry of type 'file exists' to the repository for id_job=" + id_job, dbe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String getTargetDirectory() { return this.targetDirectory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setTargetDirectory(String targetDirectory) { this.targetDirectory = targetDirectory; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Result execute(Result prev_result, int nr) {
/* 168 */     Result result = new Result(nr);
/* 169 */     result.setResult(false);
/* 170 */     long filesRetrieved = 0L;
/*     */     
/* 172 */     logDetailed(toString(), new Object[] { "Start of processing" });
/*     */ 
/*     */     
/* 175 */     String realWildcard = environmentSubstitute(this.wildcard);
/* 176 */     String realTargetDirectory = environmentSubstitute(this.targetDirectory);
/* 177 */     String realSourceDirectory = environmentSubstitute(this.sourceDirectory);
/* 178 */     DummyJob proc = new DummyJob(realSourceDirectory, realTargetDirectory, realWildcard);
/*     */ 
/*     */     
/*     */     try {
/* 182 */       filesRetrieved = proc.process();
/* 183 */       result.setResult(true);
/* 184 */       result.setNrFilesRetrieved(filesRetrieved);
/* 185 */     } catch (Exception e) {
/* 186 */       result.setNrErrors(1L);
/* 187 */       e.printStackTrace();
/* 188 */       logError(toString(), new Object[] { "Error processing DummyJob : " + e.getMessage() });
/*     */     } 
/*     */     
/* 191 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public boolean evaluates() { return true; }
/*     */ }


/* Location:              /home/rleon/Downloads/data-integration/plugins/kettle-dummy-plugin/kettle-dummy-plugin-core-8.0.0.0-28.jar!/org/pentaho/di/pdi/jobentry/dummy/JobEntryDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */