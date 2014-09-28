package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;

public class IEND extends Chunk {
	
	public IEND() {
		this.setChunkType(ChunkType.IEND);
		this.setMultiple(false);
		this.setCritical(true);
	}

	@Override
	public int doRead(InputStream input) throws IOException {
		return 0;
	}

}
