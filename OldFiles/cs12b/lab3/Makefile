#Makefile
#12M
#carogers
#April 18,2016
#complies and submits FileReverse.c 
FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse

FileReverse.class: FileReverse.c
	javac -Xlint FileReverse.c

clean:
	rm -f FileReverse FileReverse.class

submit: README Makefile FileReverse.c
	submit cmps012b-pt.s16 lab3 README Makefile FileReverse.c
