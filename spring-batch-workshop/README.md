# Spring batch

## Getting started

In order to save bandwidth you can use the `InputGeneratorTest` to generate a correct inputfile.
You can choose the size of the file by adapting the for-loop, 10 000 000 records are about 400 megabytes worth of inputdata.

Don't forget to refresh your src/main/resources folder after generating the data.

After you've generated the data you can start the `SpringBatchWorkshopApplication`, it will start treating your inputfile and print some statistics after the batch is done.