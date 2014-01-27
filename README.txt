# Make a deployment into DEVELOPMENT environment
mvn -DskipTests -Dn2o.deployment -Dn2o.profile=development clean deploy

# Make a deployment into QA environment
mvn -DskipTests -Dn2o.deployment -Dn2o.profile=test clean deploy

# Make a deployment into QA-LIVE environment
mvn -DskipTests -Dn2o.deployment -Dn2o.profile=test-live clean deploy

# Make a package for LIVE environment
mvn -DskipTests -Dn2o.deployment -Dn2o.profile=production clean package
