#include <stdlib.h>
#include <stdio.h>
/**
*数组越界问题，编译器版本不同，可能发生死循环。
*/
int main(int argc, char* argv[]){
    int i = 0;
    int arr[3] = {0};
    for(; i<=3; i++){
        arr[i] = 0;
        printf("hello world\n");
    }
    return 0;
}
