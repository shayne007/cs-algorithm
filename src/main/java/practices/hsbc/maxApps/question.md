# question
Allie is working on a system that can allocate resources to the applications in a manner efficient enough to allow the maximum number of applications to be executed. There are N number of applications and each application is identified by a unique integer ID (1 to N). Only M types of resources are available with a unique resourceD. Each application sends a request message to the system. The request message includes the information regarding the request time, the execution ending time, and the type of resource required for execution. Time is in the MMSS format where MM is minutes and SS is seconds.

If more than one application sends a reques the same time then only one application will be approved by the system. The denied requests are automatically destroyed by the system. When approving the request, the system ensures that the request will be granted to the application in a way that will maximize the number of executions. The system can execute only one application at a time with a given resource. It will deny all other requests for the resource until the previous application has finished. Allie wants to know the maximum number of applications that have been executed successfully.

Write an algorithm to help Allie calculate the maximum number of applications that are executed successfully by the system.

## Input
The first line of the input consists of two space-separated integers num and constX, representing the number of applications (N) and constX is always 3. The next N lines consist of constX space-separated integers representing the request time, the execution ending time, and the resourceD of the resource required by each application for successful execution.

## Output
Print an integer representing the maximum number of applications that are executed successfully by the system.

## Constraints
1<= num <= 10^3

## Example
### Input:
6 3
1200 1250 1
1210 1220 1
1225 1230 1
1330 1345 2
1330 1340 2
1340 1345 2

### Output:
4

### Explanation:
For the resourceD 1: If the 2nd application is selected for execution, then the 3rd application also gets a chance for execution. However, if the 1st application will be selected then no other application can be executed so 2 applications(2nd and 3rd) will be selected to maximize the execution.
For the resourcelD 2: Similarly, the 5th and 6th application are selected so that maximum applications can be executed. So, the maximum number of applications that can be executed is 4.