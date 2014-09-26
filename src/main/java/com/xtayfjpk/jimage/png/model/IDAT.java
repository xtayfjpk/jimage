package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;

import com.xtayfjpk.jimage.png.ReadManager;
import com.xtayfjpk.jimage.utils.LZ77Utils;
import com.xtayfjpk.jimage.utils.ReadResult;

public class IDAT extends Chunk {
	
	public IDAT() {
		this.setChunkType(ChunkType.IDAT);
		this.setMultiple(true);
		this.setCritical(true);
	}

	@Override
	public int doRead(InputStream input) throws IOException {
		ReadResult<byte[]> readResult = this.readData(input);
		this.readCrc(input);
		return readResult.getLength();
	}

	
	public byte[] inflateData() {
		IHDR ihdr = ReadManager.getInstance().getIHDR();
		if(null==ihdr) {
			throw new RuntimeException("未获取到文件头信息[IHDR]");
		}
		byte[] bytes = LZ77Utils.inflate(getData(), ihdr.getDataLengthAfterInflate());
		return bytes;
	}
}
