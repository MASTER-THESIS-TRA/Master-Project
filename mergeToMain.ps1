$testFolder = 'C:\DIKU\Thesis\MSc Thesis\Java API\TRA Project\TRA-Project\src\test\java\dk\diku\TRA\Project\ResourceTests.java'
$rootFolder = 'C:\DIKU\Thesis\MSc Thesis\Java API\TRA Project\TRA-Project'
$projectFolder = 'C:\DIKU\Thesis\MSc Thesis'

#javac $rootFolder -d tmp 

#javac -d target -cp target:junit-platform-console-standalone-1.7.2.jar -source tmp $testFolder

#java -jar junit-platform-console-standalone-1.7.2.jar --class-path target --select-class com.baeldung.commandline.ResourceTests

cd $rootFolder

mvn test

cd $projectFolder