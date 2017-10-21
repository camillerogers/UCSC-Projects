/* hex.c   demonstration program for separate compilation, etc. */
/*         May also be good to practice gdb and looking at call stacks. */

/*	Usage: hex
	Then enter one unsigned decimal number per line; ^D to end.
	The response is the hexadecimal equivalent.  No prompts.
		% hex
		10
		a
		20
		14
********/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include "readline.h"

void	hexConvFile(int status);
void	hexConv(int decNum, char hexNum[]);
int	hexConvR(int decNum, int start, char hexNum[]);

FILE*	infile;
char	hexigit[16];
int	len= 2000;
char*	inBuf;

int	main(int argc, char* argv[])
	{
	int i;

	inBuf = calloc(len+1, sizeof(char));
	assert(inBuf != NULL);
	infile = stdin;

	/* initialize hexigit array */
	for (i = 0; i < 16; i++)
		{
		hexigit[i] = (i < 10)	? ('0' + i)
					: ('a' + i - 10);
		}

	hexConvFile(0);
	return 0;
	}

void	hexConvFile(int status)
	{
	/* OBJECTIVE: read decimal numbers, one per line, convert them
	*  to hexadecimal strings, and print them.
	*/
	char	hexNum[10];

	if (status < 0)
		return;
	else
		{
		int  decNum;
		int  retn;

		retn = read1(infile, inBuf, len);
		if (retn > 0)
			{
			decNum = atoi(inBuf);
			hexConv(decNum, hexNum);
			fprintf(stdout, "%s\n", hexNum);
			}
		hexConvFile(retn);
		}
	}

void	hexConv(int decNum, char hexNum[])
	{
	/* OBJECTIVE: convert decNum to a null-terminated hexadecimal
	*  string in hexNum[], beginning with '-' if negative.
	*/

	if (decNum == 0)
		{
		hexNum[0] = '0';
		hexNum[1] = 0;
		}
	else if (decNum > 0)
		{
		int	len = hexConvR(decNum, 0, hexNum);

		hexNum[len] = 0;
		}
	else
		{
		int	len = hexConvR(-decNum, 1, hexNum);

		hexNum[0] = '-';
		hexNum[len] = 0;
		}
	}

int	hexConvR(int decNum, int start, char hexNum[])
	{
	/* OBJECTIVE: convert nonnegative decNum to a sequence of
	*  hexadecimal characters '0' ... 'f',
	*  beginning at hexNum[start].
	*   Return len as the next unused position of hexNum[].
	*/

	int	len;

	if (decNum == 0)
		{
		len = start;
		}
	else
		{
		int	quotient = decNum / 16;
		int	remainder = decNum % 16;
		int	lenQuotient = hexConvR(quotient, start, hexNum);

		hexNum[lenQuotient] = hexigit[remainder];
		len = lenQuotient + 1;
		}
	return len;
	}
