/* readline.c   to demonstrate separate compilation of C programs */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "readline.h"

int	read1(FILE* infile, char buf[], int len)
{
int retn;

char* result = fgets(buf, len, infile);
if (result == NULL)
	{
	buf[0] = '\0';
	retn = -1;
	}
else
	{
	retn = strlen(buf);
	}
return retn;
}

