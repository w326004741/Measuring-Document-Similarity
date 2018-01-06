# Measuring-Document-Similarity
> Module: Advanced Object-Oriented / 4th Year  
> Lecturer: Dr John Healy  
> Project 2017 : A JEE Application for Measuring Document Similarity

> Student : Weichen Wang

## Overview
  You are required to develop a Java web application that enables two or more text documents to be compared for similarity. An overview of the system is given below:

[Project Detail](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/oodppAssignment2017.pdf)

![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2201515242990_.pic.jpg)

## How to use this repository:
1. Click Clone or download and Copy to clipboard
2. Enter your Terminal(for mac) or cmd(for windows), and following below:
```
# Change directory to anywhere just you like put
cd anywhere.....

# Clone this repository
git clone https://github.com/w326004741/Measuring-Document-Similarity.git
&
cd your folder(Measuring-Document-Similarity)

# You can import jaccard-server to eclipse

```

## You should be have components:
- [Tomcat v7.0](https://tomcat.apache.org/download-70.cgi)
- [servlet-api.jar](https://stackoverflow.com/questions/8521851/where-do-i-get-servlet-api-jar-from)
- [Db4O V.8.02(AN Object Oriented Database)](https://github.com/w326004741/Measuring-Document-Similarity)
- [Db4O XTea Crypto Library](https://github.com/w326004741/Measuring-Document-Similarity)

## How to run:

#### Right Click jaccard-server project --> Run As --> Run on Server ####

## About Project

- First of all, after the project is started, the first upload file to access `UploadFileServlet` will load initialization class `DB4Oinit`. The constructor of this class will load the local sample document `test.txt` from the `ReadFile1 ()` function in the calling tool `KShingle` and save it to the `DB4O` database. The `DB4O` database locally creates a data document named `document.yap` in which all document objects are stored.

- Document file object model exists in the project `Document.java`, 

- `DocumentDao.java`: document object Operation of the underlying database interface, used to abstract the Document object, abstraction of the database method. 

- `DocumentDaoImpl.java`: The document object on the underlying database operation of the underlying instance, used to provide operation DB4O database function interface. 

- `DocumentService.java` document object Abstraction of the service interface provided. 

- `DocumentServiceImpl.java` document object provides the service interface externally. 

##### Before you start the project, you need to first add Tomcat server to your Eclipse Project:
```
1. Go to the “Servers” tab in Eclipse, and click the link to create a new server. 

2. Select “Tomcat v7.0 Server” and click Next.

3. Browse to the Tomcat directory from Step 1 and then click Finish – “Tomcat v7.0 Server at localhost” 
   should be displayed on the “Servers” tab.
```

#### Web Application Main Page:
![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2021515177610_.pic.jpg)

![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2031515177643_.pic.jpg)

#### Result:
![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2001515177538_.pic.jpg)

![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2041515177790_.pic_hd.jpg)

#### Project UML:
![image](https://github.com/w326004741/Measuring-Document-Similarity/blob/master/image/2131515235352_.pic_hd.jpg)
