package be.ordina.springbatch.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class SpringBatchWorkshopChunkListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext context) {
		System.out.println("Starting new Chunk");
	}

	@Override
	public void afterChunk(ChunkContext context) {
		System.out.println("Finished Chunk");
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		System.out.println("Chunk error!");
	}

}
