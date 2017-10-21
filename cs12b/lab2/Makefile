#Makefile
#Camille Rogers
#carogers
#12M
#April 10, 2016
#Makefile for FileReverse.java

HelloUser: FileReverse.class
	echo Main-class: HelloUser > Manifest
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse

FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java
clean:
	rm -f FileReverse FileReverse.class

submit: README Makefile FileReverse.java
	submit cmps012b-pt.s16 lab2 README Makefile FileReverse.java
