package jp.co.dgic.testing.common.asm;

import jp.co.dgic.djunit.asm.ClassWriter;

public class AsmClassWriter extends ClassWriter {

	public AsmClassWriter() {
		super(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	}

}
