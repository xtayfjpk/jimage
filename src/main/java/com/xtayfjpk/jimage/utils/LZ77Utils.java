package com.xtayfjpk.jimage.utils;

import org.apache.log4j.Logger;

import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZStream;

@SuppressWarnings("deprecation")
public class LZ77Utils {
	private static final Logger LOGGER = Logger.getLogger(LZ77Utils.class);
	
	public static byte[] inflate(byte[] sources, int outlen) {
		byte[] out = new byte[outlen];
		int err;
		Inflater inflater = new Inflater();

		inflater.setInput(sources);
		inflater.setOutput(out);

		err = inflater.init();
		checkError(inflater, err, "inflateInit");
		
		while (inflater.total_out < out.length && inflater.total_in < sources.length) {
			inflater.avail_in = inflater.avail_out = 1; /* force small buffers */
			err = inflater.inflate(JZlib.Z_NO_FLUSH);
			if (err == JZlib.Z_STREAM_END)
				break;
			checkError(inflater, err, "inflate");
		}

		err = inflater.end();
		checkError(inflater, err, "inflateEnd");
		return out;
	}
	
	
	private static void checkError(ZStream z, int err, String msg) {
		if (err != JZlib.Z_OK) {
			if (z.msg != null) {
				LOGGER.error(z.msg);
			}
			LOGGER.error(msg + " error: " + err);
		}
	}
}
