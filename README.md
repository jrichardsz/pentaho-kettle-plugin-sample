# neo4j-kettle-plugin

export PDI_HOME=/home/rleon/Downloads/data-integration

cd ./offline-libs

mvn install:install-file -Dfile=metastore-8.0.0.0-28.jar -DgroupId=pentaho -DartifactId=metastore -Dversion=8.0.0.0-28 -Dpackaging=jar

cd /home/rleon/Downloads/data-integration/plugins

mvn install:install-file -Dfile=kettle-core-8.0.0.0-28.jar -DgroupId=pentaho-kettle -DartifactId=kettle-core -Dversion=8.0.0.0-28 -Dpackaging=jar

mvn install:install-file -Dfile=kettle-engine-8.0.0.0-28.jar -DgroupId=pentaho-kettle -DartifactId=kettle-engine -Dversion=8.0.0.0-28 -Dpackaging=jar

mvn install:install-file -Dfile=kettle-ui-swt-8.0.0.0-28.jar -DgroupId=pentaho-kettle -DartifactId=kettle-ui-swt -Dversion=8.0.0.0-28 -Dpackaging=jar

cd /home/rleon/Downloads/data-integration/libswt/linux/x86_64

mvn install:install-file -Dfile=swt.jar -DgroupId=org.eclipse.platform -DartifactId=org.eclipse.swt -Dversion=4.335 -Dpackaging=jar




