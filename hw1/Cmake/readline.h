/* readline.h   to demonstrate separate compilation of C programs */

/* include stdio.h before including readline.h */

/** read1() Preconds: infile is open for input; len > 0; buf array has
 *           at least len chars of space allocated.
 *  Postconds:
 *      If \return >= 0, contents of next line up to len-1 chars
 *          is stored into buf including the '\n' and '\0' is appended.
 *      \return gives the number of stored chars, not counting the final '\0'.
 *      If \return == -1, end of file occurred.
 *      If \return == -2, some error occurred.
 */
int	read1(FILE* infile, char buf[], int len);

