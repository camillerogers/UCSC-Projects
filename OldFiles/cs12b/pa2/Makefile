#Makefile
#Camille Rogers
#carogers, 12B
#17 April, 2016
#creates an executable jar file called Search, and includes a clean utility that removes all .class files as well as Search itself.  
 
Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search

Search.class: Search.java
	javac -Xlint Search.java

clean:
	rm -f Search Search.class

submit: README Makefile Search.java
	submit cmps012b-pt.s16 pa2 README Makefile Search.java

