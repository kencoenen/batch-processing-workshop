package be.ordina.springbatch.application;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class MyPartitioner implements Partitioner {

	static private Log log = LogFactory.getLog(MyPartitioner.class);

	public Map<String, ExecutionContext> partition(int gridSize) {
		log.debug("START: Partition");
		Map<String, ExecutionContext> partitionMap = new HashMap<String, ExecutionContext>();
		int startingIndex = 0;
		int endingIndex = 5;

		for (int i = 0; i < gridSize; i++) {
			ExecutionContext ctxMap = new ExecutionContext();
			ctxMap.putInt("startingIndex", startingIndex);
			ctxMap.putInt("endingIndex", endingIndex);

			startingIndex = endingIndex + 1;
			endingIndex += 5;

			partitionMap.put("Thread:-" + i, ctxMap);
		}
		log.debug("END: Created Partitions of size: " + partitionMap.size());
		return partitionMap;
	}

}
