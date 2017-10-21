#------------------------------------------------------------------------------
# makefile for charType.c with Macros
# Camille Rogers
# carogers
# May 2, 2016
# lab4

FLAGS = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

check :
	 valgrind --leak-check=full charType in out

clean :
	rm $(EXEBIN) $(OBJECTS)

submit: 
	submit cmps012b-pt.s16 lab4 README Makefile $(SOURCES)
	
