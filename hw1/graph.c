#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int arg, char* argv[]){
		int m, n;
		intList[] adjVertices;
		
		if(argc == 0){
			printf("Usage: java graph input.data");
			exit(EXIT_SUCCESS);
		}
		char infile = argv[0];
		BufferedReader inbuf = fopen(infile, "r"):
		printf("Opened %s for input.\n", infile); 
		fgets(line,len,inbuf);
		n = LoadGraph->atoi(line);
		printf("n = %d\n", n);
		
		adjVertices = LoadGraph->initEdges(n);
		m = LoadGraph->loadEdge(inbuf, adjVertices);
		InputLib->fclose(inbuf);
		printf("m = %d\n", m);
		
		for(int i=1; i<=n; i++){
			printf("%d\t%d", i, adjVertices[i+1]);
			return;
		} 
	}