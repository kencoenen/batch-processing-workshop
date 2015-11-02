# Spring batch

## Getting started

In order to save bandwidth you can use the `InputGeneratorTest` to generate a correct inputfile.
You can choose the size of the file by adapting the for-loop, 10 000 000 records are about 400 megabytes worth of inputdata.

Don't forget to refresh your src/main/resources folder after generating the data.

After you've generated the data you can start the `SpringBatchWorkshopApplication`, it will start treating your inputfile and print some statistics after the batch is done.



## Running a job

To run a job you can use Postman/curl/anything that can send HTTP post requests.
After you've started `SpringBatchWorkshopApplication` you can navigate to the `/api/batch/jobs` endpoint to see the registered jobs.  
Start a job by sending a **POST** request to `/api/batch/jobs/{jobName}` you can then track the jobs' status using `/api/batch/jobs/{jobName}/{executionId}`