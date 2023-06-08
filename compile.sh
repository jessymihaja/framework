#/home/jessy/apache/apache-tomcat-8.5.81/lib
#/home/jessy/apache/apache-tomcat-8.5.81/webapps
#$pathFramework2/build/classes
#cd $pathFramework2/build/classes
#jar -cf $pathtemp/fw.jar *
#cp $pathtemp/fw.jar $path$testbuild/web/WEB-INF/lib
#cd $path$testbuild/web/
#jar -cf $pathtemp/fw_test.war *
#cp $pathtemp/fw_test.war /home/jessy/apache/apache-tomcat-8.5.81/webapps
path='/home/jessy/NetBeansProjects/'
framework='Framework2/src/'
test='Framework_test/'
webaps='/home/jessy/apache/apache-tomcat-8.5.81/webapps'
mkdir temp_classes
mkdir temp
cd $path'temp'
mkdir WEB-INF
cd WEB-INF
mkdir lib
mkdir classes

cd $path'temp_classes'
javac -d . $path$framework'annotation/Annotation.java'
javac -d . $path$framework'etu2046/framework/Mapping.java'
javac -d . $path$framework'etu2046/framework/Modelview.java'
javac -d . $path$framework'etu2046/framework/servlet/FrontServlet.java'
javac -d . $path$test'/src/java/test/Test.java'
javac -d . $path$test'/src/java/test/Test2.java'

cp $path'temp_classes/test/Test.class' $path'temp/WEB-INF/classes'
cp $path'temp_classes/test/Test2.class' $path'temp/WEB-INF/classes'

jar -cf /home/jessy/NetBeansProjects/temp/WEB-INF/lib/fw.jar *
rm -R $path'temp_classes'

cp $path$test'web/formulaire.jsp' $path'temp'
cp $path$test'web/test_view.jsp' $path'temp'
cp $path$test'web/WEB-INF/web.xml' $path'temp/WEB-INF'	

cd $path'temp'
jar -cf /home/jessy/NetBeansProjects/temp/fw_test.war *
cp $path'temp/fw_test.war' $webaps

rm -R $path'temp'
