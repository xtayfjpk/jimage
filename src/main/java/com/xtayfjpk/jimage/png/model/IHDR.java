package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.xtayfjpk.jimage.utils.InputStreamUtils;
import com.xtayfjpk.jimage.utils.ReadResult;

/**
 * 文件头数据块
 * @author zj
 *
 */
public class IHDR extends Chunk {
	private static final Logger LOGGER = Logger.getLogger(IHDR.class);
	/**
	 * 图像宽度，以像素为单位，占4个字节
	 */
	private int width = UNINIT_VALUE;
	/**
	 * 图像高度，以像素为单位，占4个字节
	 */
	private int height = UNINIT_VALUE;
	/**
	 * 图像深度： 索引彩色图像：1，2，4或8; 灰度图像：1，2，4，8或16; 真彩色图像：8或16.
	 * 占1个字节
	 */
	private int bitWidth = UNINIT_VALUE;
	/**
	 * 颜色类型：
	 *	0：灰度图像，1，2，4，8或16
	 *	2：真彩色图像，8或16
	 *	3：索引彩色图像，1，2，4或8
	 *	4：带α通道数据的灰度图像，8或16
	 *	6：带α通道数据的真彩色图像，8或16
	 *	占1个字节
	 */
	private int colorType = UNINIT_VALUE;
	
	/**
	 * 压缩方法（LZ77变种算法）
	 * 占1个字节
	 */
	private int compressionMethod = UNINIT_VALUE;
	
	/**
	 * 滤波器方法, 占一个字节
	 */
	private int filterMethod = UNINIT_VALUE;
	/**
	 * 隔行扫描方法：0：非隔行扫描   1： Adam7（由Adam M.Costello开发的7遍隔行扫描方法）
	 * 占一个字节
	 */
	private int interlaceMethod = UNINIT_VALUE;
	
	
	public IHDR() {
		this.setCritical(true);
		this.setMultiple(false);
		this.setChunkType(ChunkType.IHDR);
	}
	

	@Override
	public int read(InputStream input) {
		if(getLength()==UNINIT_VALUE) {
			throw new RuntimeException("数据长度值未初始化");
		}
		try {
			readWidth(input);
			readHeight(input);
			readBitWidth(input);
			readColorType(input);
			readCompressionMethod(input);
			readFilterMethod(input);
			readInterlaceMethod(input);
			readCrc(input);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return 0;
	}

	private void readWidth(InputStream input) throws IOException {
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input);
		if(readResult.getLength()!=4) {
			throw new RuntimeException("数据读取错误");
		}
		this.width = readResult.getResult();
	}
	private void readHeight(InputStream input) throws IOException {
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input);
		if(readResult.getLength()!=4) {
			throw new RuntimeException("数据读取错误");
		}
		this.height = readResult.getResult();
	}
	private void readBitWidth(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.bitWidth = readResult.getResult();		
	}
	private void readColorType(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.colorType = readResult.getResult();
	}
	private void readCompressionMethod(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.compressionMethod = readResult.getResult();
	}
	private void readFilterMethod(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.filterMethod = readResult.getResult();
	}
	private void readInterlaceMethod(InputStream input) throws IOException {
		final int len = 1;
		ReadResult<Integer> readResult = InputStreamUtils.readInteger(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.interlaceMethod = readResult.getResult();		
	}
	private void readCrc(InputStream input) throws IOException {
		final int len = 4;
		ReadResult<byte[]> readResult = InputStreamUtils.read(input, len);
		if(readResult.getLength()!=len) {
			throw new RuntimeException("数据读取错误");
		}
		this.setCrc(readResult.getResult());		
	}
	

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getBitWidth() {
		return bitWidth;
	}
	public void setBitWidth(int bitWidth) {
		this.bitWidth = bitWidth;
	}
	public int getColorType() {
		return colorType;
	}
	public void setColorType(int colorType) {
		this.colorType = colorType;
	}
	public int getCompressionMethod() {
		return compressionMethod;
	}
	public void setCompressionMethod(int compressionMethod) {
		this.compressionMethod = compressionMethod;
	}
	public int getFilterMethod() {
		return filterMethod;
	}
	public void setFilterMethod(int filterMethod) {
		this.filterMethod = filterMethod;
	}
	public int getInterlaceMethod() {
		return interlaceMethod;
	}
	public void setInterlaceMethod(int interlaceMethod) {
		this.interlaceMethod = interlaceMethod;
	}
}
