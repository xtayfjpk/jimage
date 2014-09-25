package com.xtayfjpk.jimage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public abstract class InputStreamUtils {
	
	public static ReadResult<byte[]> read(InputStream input, int length) throws IOException {
		byte[] bytes = new byte[length];
		int len = input.read(bytes);
		return new ReadResult<byte[]>(bytes, len);
	}
	
	public static ReadResult<String> readString(InputStream input, int length) throws IOException {
		return readString(input, length, null);
	}
	
	public static ReadResult<String> readString(InputStream input, int length, Charset charset) throws IOException {
		byte[] bytes = new byte[length];
		int len = input.read(bytes);
		String str = null;
		if(charset==null) {
			str = new String(bytes, 0, len);
		} else {
			str = new String(bytes, 0, len, charset);
		}
		return new ReadResult<String>(str, len);
	}
	
	public static ReadResult<Integer> readInteger(InputStream input) throws IOException {
		byte[] bytes = new byte[4];
		int len = input.read(bytes);
		int result = (bytes[0]&0xff) | (bytes[1]<<8)&0xff00 | ((bytes[2]<<16&0xffff00)) | ((bytes[3]<<24)&0xff000000);
		
		return new ReadResult<Integer>(result, len);
	}
	
	public static ReadResult<Integer> readInteger(InputStream input, int length) throws IOException {
		byte[] bytes = new byte[length];
		int len = input.read(bytes);
		int result = 0;
		for(int i=0; i<len; i++) {
			result = result | (bytes[i]&0xff)<<(8*i);
		}
		return new ReadResult<Integer>(result, len);
	}
	
	/*public static void main(String[] args) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{100,-1, 127, -8});
		System.out.println(readInteger(in).getResult());
	}*/ 
	
	
}
