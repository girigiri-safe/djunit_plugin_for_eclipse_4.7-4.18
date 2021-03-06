package jp.co.dgic.testing.common.asm;

import java.io.IOException;

import jp.co.dgic.djunit.asm.ClassReader;
import jp.co.dgic.djunit.asm.ClassVisitor;

public class AsmClassReader extends ClassReader {

	public AsmClassReader(byte[] bytecodes) {
		super(bytecodes);
	}

	public AsmClassReader(String name) throws IOException {
		super(name);
	}

	public void accept(ClassVisitor cv) {
		super.accept(cv, ClassReader.SKIP_FRAMES);
	}

}
