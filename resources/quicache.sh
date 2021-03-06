if [ -z "$JAVA_HOME" ]
then
    echo "ERROR: Failed to find JAVA_HOME. Please, check your path."
    exit 1 
else
    exec "java -jar quicache.jar"
fi