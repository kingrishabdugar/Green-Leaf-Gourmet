That's really easy to package every dependent library (*.jar) into one single myProject.jar.

Just follow these steps and you will finally pack every dependent library into single jar. If you are using NetBeans then you can follow exactly or else you need to find your build.xml file in project files.

Follow these steps to edit build.xml

1) Click on Files tab on the left side of the project panel in NetBeans.

2) Double click on the build.xml file and add these lines in it just before </project> line

 <target name="package-for-store" depends="jar">
    <property name="store.jar.name" value="myProject"/>
    <property name="store.dir" value="store"/>
    <property name="store.jar" value="${store.dir}/${store.jar.name}.jar"/>
    <echo message="Packaging ${application.title} into a single JAR at ${store.jar}"/>
    <delete dir="${store.dir}"/>
    <mkdir dir="${store.dir}"/>
    <jar destfile="${store.dir}/temp_final.jar" filesetmanifest="skip">
        <zipgroupfileset dir="dist" includes="*.jar"/>
        <zipgroupfileset dir="dist/lib" includes="*.jar"/>
        <manifest>
            <attribute name="Main-Class" value="${main.class}"/>
        </manifest>
    </jar>
    <zip destfile="${store.jar}">
        <zipfileset src="${store.dir}/temp_final.jar"
        excludes="META-INF/*.SF, META-INF/*.DSA, META-INF/*.RSA"/>
    </zip>
    <delete file="${store.dir}/temp_final.jar"/>
</target>
3) Change value in second line of the code as per your project name which is

<property name="store.jar.name" value="myProject"/> //<---Just value not name
4) Save it and right click on build.xml and choose Run Target and then Other Targets and finally click on Package-for-store

5) And here you done. Now you can go and check just like dist folder there will be a store folder which will be containing your final complete jar including all of your dependent libraries. Now whenever you want to change / add more libraries or so, just follow step 4.

Picture for step 4

enter image description here
