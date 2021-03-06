package jp.co.dgic.testing.common.asm;

import java.io.IOException;

import jp.co.dgic.djunit.asm.ClassVisitor;
import jp.co.dgic.djunit.asm.ClassWriter;
import jp.co.dgic.testing.common.util.DJUnitUtil;
import jp.co.dgic.testing.common.virtualmock.asm.AsmClassChecker;

public abstract class AsmClassUtils {
	
//	private static final String CLASS_READER_ASM2X = "jp.co.dgic.testing.common.asm2x.AsmClassReader2x";
//	private static final String CLASS_WRITER_ASM2X = "jp.co.dgic.testing.common.asm2x.AsmClassWriter2x";
//	private static final String CLASS_VISITOR_ASM2X = "jp.co.dgic.testing.common.virtualmock.asm2x.AsmClassVisitor2x";

	public static AsmClassReader createAsmClassReader(String name) throws IOException {
//		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
//			Class asmClassReader2x;
//			try {
//				asmClassReader2x = Class.forName(CLASS_READER_ASM2X);
//				Class[] argTypes = {String.class};
//				Object[] args = {name};
//				return (AsmClassReader) asmClassReader2x.getConstructor(argTypes).newInstance(args);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}
		return new AsmClassReader(name);
	}

	public static AsmClassReader createAsmClassReader(byte[] bytecodes) throws IOException {
//		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
//			try {
//				Class asmClassReader2x = Class.forName(CLASS_READER_ASM2X);
//				Class[] argTypes = {byte[].class};
//				Object[] args = {bytecodes};
//				return (AsmClassReader) asmClassReader2x.getConstructor(argTypes).newInstance(args);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}
		return new AsmClassReader(bytecodes);
	}
	
	public static AsmClassWriter createClassWriter() {
//		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
//			try {
//				Class asmClassWriter2x = Class.forName(CLASS_WRITER_ASM2X);
//				return (AsmClassWriter) asmClassWriter2x.newInstance();
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}
		return new AsmClassWriter();
	}

	public static AsmClassVisitor createClassVisitor(AsmClassChecker acc) {
//		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
//			try {
//				Class asmClassVisitor2x = Class.forName(CLASS_VISITOR_ASM2X);
//				Class[] argTypes = {AsmClassChecker.class};
//				Object[] args = {acc};
//				return (AsmClassVisitor) asmClassVisitor2x.getConstructor(argTypes).newInstance(args);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}
		return new AsmClassVisitor(acc);
	}
	private static String getAsmType() {
		return System.getProperty(DJUnitUtil.BYTECODE_LIBRARY_KEY);
	}

}
