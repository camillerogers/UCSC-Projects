#Camille Rogers
#carogers
#12B
#April 7, 2016
#creates an executable jar file called Recursion, removes all.class files and the executable jar file from the current directory.

Recursion: Recursion.class
	echo Main-class: Recursion > Manifest
	jar cvfm Recursion Manifest Recursion.class
	rm Manifest
	chmod +x Recursion
Recursion.class: Recursion.java
	javac -Xlint Recursion.java
clean:
	rm -f Recursion Recursion.class
submit: README Makefile Recursion.java
	submit cmps012b-pt.s16 pa1 README Makefile Recursion.java 
