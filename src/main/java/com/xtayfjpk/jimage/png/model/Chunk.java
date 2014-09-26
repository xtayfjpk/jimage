package com.xtayfjpk.jimage.png.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.xtayfjpk.jimage.utils.InputStreamUtils;
import com.xtayfjpk.jimage.utils.ReadResult;

/**
 * PNG图像数据块基类
 * @author zj
 *
 */
public abstract class Chunk {
	private static final Logger LOGGER = Logger.getLogger(Chunk.class);
	public static final int UNINIT_VALUE = -1;
	/** 数据长度 **/
	private int length;
	/** 类型码 **/
	private String typeCode;
	/** 数据 **/
	private byte[] data;
	/** 冗余码 **/
	private byte[] crc = new byte[4];
	/** 是否为关键数据块 **/
	private boolean critical = false;
	/** 是否可以有多个 **/
	private boolean multiple = false;
	private ChunkType chunkType;
	
	
	public abstract int read(InputStream input);
	
	public int read(File png) {
		try {
			return read(new FileInputStream(png));
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		throw new RuntimeException(String.format("PNG image file[%s] does not exists.", png.getAbsoluteFile()));
	}
	
	
	public int readCrc(InputStream input) throws IOException {
		final int len = 4;
		ReadResult<byte[]> readResult = InputStreamUtils.read(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.setCrc(readResult.getResult());
		return readResult.getLength();
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getTypeCode() {
		if(typeCode==null) {
			typeCode = this.chunkType.toString();
		}
		return typeCode;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getCrc() {
		return crc;
	}
	public void setCrc(byte[] crc) {
		this.crc = crc;
	}

	public boolean isCritical() {
		return this.critical;
	}
	
	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	public boolean isMultiple() {
		return this.multiple;
	}
	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public ChunkType getChunkType() {
		return chunkType;
	}
	public void setChunkType(ChunkType chunkType) {
		this.chunkType = chunkType;
	}
}
