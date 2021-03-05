package jp.co.dgic.testing.common.asm;

import java.io.IOException;

import jp.co.dgic.testing.common.asm3x.AsmClassReader3x;
import jp.co.dgic.testing.common.asm3x.AsmClassWriter3x;
import jp.co.dgic.testing.common.util.DJUnitUtil;
import jp.co.dgic.testing.common.virtualmock.asm.AsmClassChecker;
import jp.co.dgic.testing.common.virtualmock.asm3x.AsmClassVisitor3x;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public abstract class AsmClassReader extends ClassReader {
	
	private static final String CLASS_READER_ASM2X = "jp.co.dgic.testing.common.asm2x.AsmClassReader2x";
	private static final String CLASS_WRITER_ASM2X = "jp.co.dgic.testing.common.asm2x.AsmClassWriter2x";
	private static final String CLASS_VISITOR_ASM2X = "jp.co.dgic.testing.common.virtualmock.asm2x.AsmClassVisitor2x";

	public static AsmClassReader createAsmClassReader(String name) throws IOException {
		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
			Class asmClassReader2x;
			try {
				asmClassReader2x = Class.forName(CLASS_READER_ASM2X);
				Class[] argTypes = {String.class};
				Object[] args = {name};
				return (AsmClassReader) asmClassReader2x.getConstructor(argTypes).newInstance(args);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
//			return new AsmClassReader2x(name);
		}
		return new AsmClassReader3x(name);
	}

	public static AsmClassReader createAsmClassReader(byte[] bytecodes) throws IOException {
		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
			try {
				Class asmClassReader2x = Class.forName(CLASS_READER_ASM2X);
				Class[] argTypes = {byte[].class};
				Object[] args = {bytecodes};
				return (AsmClassReader) asmClassReader2x.getConstructor(argTypes).newInstance(args);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
//			return new AsmClassReader2x(bytecodes);
		}
		return new AsmClassReader3x(bytecodes);
	}
	
	public static ClassWriter createClassWriter() {
		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
			try {
				Class asmClassWriter2x = Class.forName(CLASS_WRITER_ASM2X);
				return (ClassWriter) asmClassWriter2x.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
//			return new AsmClassWriter2x();
		}
		return new AsmClassWriter3x();
	}

	public static ClassWriter createClassVisitor(AsmClassChecker acc) {
		if (DJUnitUtil.BYTECODE_LIBRARY_ASM2.equals(getAsmType())) {
			try {
				Class asmClassVisitor2x = Class.forName(CLASS_VISITOR_ASM2X);
				Class[] argTypes = {AsmClassChecker.class};
				Object[] args = {acc};
				return (ClassWriter) asmClassVisitor2x.getConstructor(argTypes).newInstance(args);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
//			return new AsmClassVisitor2x(acc);
		}
		return new AsmClassVisitor3x(acc);
	}
	private static String getAsmType() {
		return System.getProperty(DJUnitUtil.BYTECODE_LIBRARY_KEY);
	}

	public AsmClassReader(String name) throws IOException {
		super(name);
	}

	public AsmClassReader(byte[] bytecodes) {
		super(bytecodes);
	}

	public abstract void accept(ClassVisitor cv);
	
}
