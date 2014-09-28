package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;

import com.xtayfjpk.jimage.utils.InputStreamUtils;
import com.xtayfjpk.jimage.utils.ReadResult;

/**
 * 物理像素尺寸数据块
 * @author zj
 *
 */
public class PHYS extends Chunk {
	public static final int UNIT_UNKNOWN = 0;
	public static final int UNIT_METER = 1;
	/** 每单位多少像素，x轴, 占4字节 **/
	private int pixelPerUnitX = -1;
	/** 每单位多少像素，y轴, 占4字节 **/
	private int pixelPerUnitY = -1;
	/**
	 * 	UnkownUnit=0，表示只定义了像素显示的比例，未定义实际像素大小。
	 *	枚举类型，Meter=1，表示定义单位为米, 占1字节
	 */
	private int pixelUnit = -1;
	
	
	public PHYS() {
		this.setChunkType(ChunkType.pHYs);
		this.setCritical(false);
		this.setMultiple(false);
	}

	@Override
	public int doRead(InputStream input) throws IOException {
		int lenght = 0;
		lenght += readPixelPerUnitX(input);
		lenght += readPixelPerUnitY(input);
		lenght += readPixelUnit(input);
		return lenght;
	}


	private int readPixelPerUnitX(InputStream input) throws IOException {
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input);
		if(readResult.getLength()!=4) {
			throw new RuntimeException("数据读取错误");
		}
		this.pixelPerUnitX = readResult.getResult();
		return readResult.getLength();
	}
	
	private int readPixelPerUnitY(InputStream input) throws IOException {
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input);
		if(readResult.getLength()!=4) {
			throw new RuntimeException("数据读取错误");
		}
		this.pixelPerUnitY = readResult.getResult();
		return readResult.getLength();
	}
	
	private int readPixelUnit(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.pixelUnit = readResult.getResult();
		return readResult.getLength();
	}

	public int getPixelPerUnitX() {
		return pixelPerUnitX;
	}
	public int getPixelPerUnitY() {
		return pixelPerUnitY;
	}
	public int getPixelUnit() {
		return pixelUnit;
	}
	
	
}
