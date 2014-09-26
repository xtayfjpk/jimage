package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.xtayfjpk.jimage.utils.InputStreamUtils;
import com.xtayfjpk.jimage.utils.ReadResult;

/**
 * 调色板数据块
 * PNG的调色板由3个字节组成，每个字节分别表示红、绿、蓝三色的颜色值。
 * @author zj
 *
 */
public class PLTE extends Chunk {
	private List<Pixel> pixels = new ArrayList<Pixel>();
	
	public PLTE() {
		this.setCritical(false);
		this.setChunkType(ChunkType.PLTE);
		this.setMultiple(false);
	}

	@Override
	public int doRead(InputStream input) throws IOException {
		int pixels = this.getLength()/3;
		int length = 0;
		for(int i=0; i<pixels; i++) {
			length += readPixel(input);
		}
		this.readCrc(input);
		return length;
	}
	
	
	public int readPixel(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		int red = readResult.getResult();
		readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		int green = readResult.getResult();
		readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		int blue = readResult.getResult();
		Pixel pixel = new Pixel(red, green, blue);
		this.pixels.add(pixel);
		return len * 3;
	}
	

	public List<Pixel> getPixels() {
		return pixels;
	}
	public void addPixel(Pixel pixel) {
		this.pixels.add(pixel);
	}
	public boolean removePixel(Pixel pixel) {
		return this.pixels.remove(pixel);
	}
	
	public int getColorCount() {
		return getLength() / 3;
	}
}
