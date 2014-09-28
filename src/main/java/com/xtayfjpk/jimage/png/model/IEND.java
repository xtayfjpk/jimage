package com.xtayfjpk.jimage.png.model;

import java.io.IOException;
import java.io.InputStream;

public class IEND extends Chunk {

	@Override
	public int doRead(InputStream input) throws IOException {
		readCrc(input);
		return 0;
	}

}
