package com.xtayfjpk.jimage.png;

import java.util.ArrayList;
import java.util.List;

import com.xtayfjpk.jimage.png.model.Chunk;
import com.xtayfjpk.jimage.png.model.ChunkType;
import com.xtayfjpk.jimage.png.model.IHDR;

public class ReadManager {
	private static ReadManager instance = null;
	private List<Chunk> chunks = new ArrayList<Chunk>();
	
	private ReadManager() {}
	
	public static ReadManager getInstance() {
		if(instance==null) {
			synchronized (ReadManager.class) {
				if(instance==null) {
					instance = new ReadManager();
				}
			}
		}
		return instance;
	}
	
	public IHDR getIHDR() {
		for(Chunk chunk : chunks) {
			if(chunk.getChunkType()==ChunkType.IHDR) {
				return (IHDR) chunk;
			}
		}
		return null;
	}
	
	public boolean addChunk(Chunk chunk) {
		return this.chunks.add(chunk);
	}
	public boolean removeChunk(Chunk chunk) {
		return this.chunks.remove(chunk);
	}
	
	public List<Chunk> getChunks() {
		return chunks;
	}
	public void setChunks(List<Chunk> chunks) {
		this.chunks = chunks;
	}
}
