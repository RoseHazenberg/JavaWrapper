# JavaWrapper
This is the repository that contains the Java Wrapper for theme 9. 
The Java Wrapper classifies new instances based on breast cancer cell features whether an instance is benign or malignant.

### What is this repository for?
* This repository contains the classes of the Java Wrapper with the corresponding data.
* © Copyright Rose Hazenberg, Hanze University of Applied Sciences. Bio-informatics, Life Science and Technology.

### Contents 
* The folder **src/main/java/nl/bioinf/wrapper** contains the classes that classify a new instance.
* The folder **data** contains three files, one file with the RandomForest model ***randomforest.model***, one where the class of the breast cancer data is known ***breastcancer.arff***, 
and one file where the class of the breast cancer data is unknown which provides the new instances ***unknown.arff***.

### Installation
* **Fork** this repository into your own account. 
* **Clone** the repository to your local computer.
* Open this project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) to set up and run.
* IntelliJ needs [java version 17.0.1](https://www.java.com/nl/download/) and [Java SE 17.0.1](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* The version can be specified in **File/Project Structure**
* For support about IntelliJ IDEA you can visit https://www.jetbrains.com/idea/features/

### Usage 
To run the program you need to build a jar and shadowJar file by in the right corner **Gradle/build/jar** or **Gradle/build/shadowJar**.
The program needs two arguments which are two **.arff** files and are provided in the data folder. ***breastcancer.arff*** and ***unknown.arff***. 

The command line is:   
java -jar .\build\libs\Wrapper-1.0-SNAPSHOT-all.jar -f .\data\breastcancer.arff -u .\data\unknown.arff

If one of the arguments is missing it gives you the next outcome:  
Parsing failed! Because something went wrong: No arff file is provided with unknown instances  
usage: java -jar Wrapper-1.0-SNAPSHOT-all.jar [options]  
-f,--file <arg>      The input file with the known classes of the instances  
-h,--help            Prints the help for the command line arguments  
-u,--unknown <arg>   The input file of the unknown classes of the instances
inputFile: .\data\breastcancer.arff
unknownInputFile = null

When the program works it should give the next example:  
ID: 1496472.0, predicted: Malignant  
ID: 1903842.0, predicted: Malignant  
ID: 8900211.0, predicted: Benign  
ID: 5405401.0, predicted: Benign  
ID: 4013729.0, predicted: Benign  
ID: 6093021.0, predicted: Malignant  
ID: 1109223.0, predicted: Malignant  
ID: 3094124.0, predicted: Malignant  

Another options to run the ***RunnerMain.java*** is with the Edit Configuration, program arguments:  
-f data/breastcancer.arff -u data/unknown.arff  
This provides the same outcome as shown in the example above.

### Support
For more information about the set-up please visit   
https://www.jetbrains.com/help/idea/compiling-applications.html#compile_module

For support please contact Rose Hazenberg.
E-mail: c.r.hazenberg@st.hanze.nl