#/home/jessy/apache/apache-tomcat-8.5.81/lib
#/home/jessy/apache/apache-tomcat-8.5.81/webapps
#/home/jessy/NetBeansProjects/Framework2/build/classes
cd /home/jessy/NetBeansProjects/Framework2/build/classes
jar -cf /home/jessy/NetBeansProjects/temp/fw.jar *
cp /home/jessy/NetBeansProjects/temp/fw.jar /home/jessy/NetBeansProjects/Framework_test/build/web/WEB-INF/lib
cd /home/jessy/NetBeansProjects/Framework_test/build/web/
jar -cf /home/jessy/NetBeansProjects/temp/fw_test.war *
cp /home/jessy/NetBeansProjects/temp/fw_test.war /home/jessy/apache/apache-tomcat-8.5.81/webapps
