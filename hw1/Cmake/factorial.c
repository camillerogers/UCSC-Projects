# include <stdio.h>

int main()
{
	int i = 0;
	int num = 0;
	int j = 1;
	int array[5] = { 0 };
	
	while (i < 4) {
		array[i] = i;
		i++;
	}
	
	increment (array[1]); /*increment a[1] from 1 to 2*/
	
	printf ("Enter the number: ");
	scanf ("%d", &num );
	

	for (i=1; i<num; i++)
		j=j*i; 
	

	printf("The factorial of %d is %d\n",num,j);
}

int increment (int i) {
	i++;
	return i;
}

