package jp.co.dgic.testing.common.asm;

import jp.co.dgic.djunit.asm.ClassVisitor;
import jp.co.dgic.djunit.asm.MethodVisitor;
import jp.co.dgic.djunit.asm.Opcodes;
import jp.co.dgic.testing.common.util.DJUnitUtil;
import jp.co.dgic.testing.common.virtualmock.InternalMockObjectManager;
import jp.co.dgic.testing.common.virtualmock.asm.AsmClassChecker;
import jp.co.dgic.testing.common.virtualmock.asm.AsmConstractorVisitor;
import jp.co.dgic.testing.common.virtualmock.asm.AsmMethodVisitor;

public class AsmClassVisitor extends ClassVisitor {

	protected AsmClassChecker acc;
	protected String className;
	protected AsmClassWriter classWriter;

	public AsmClassVisitor(AsmClassChecker acc) {
		super(DJUnitUtil.ASM_API_VERSION);
		this.acc = acc;
		this.classWriter = new AsmClassWriter();
		this.cv = this.classWriter;
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		InternalMockObjectManager.printConsole("[modify class] " + name + " " + signature);
		InternalMockObjectManager.printConsole("[class version] " + version);
		this.className = name.replace('/', '.');
		super.visit(version, access, name, signature, superName, interfaces);
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		boolean isStatic = false;
		if ((access & Opcodes.ACC_STATIC) > 0) {
			isStatic = true;
		}

		InternalMockObjectManager.printConsole("#################################################################");
		InternalMockObjectManager.printConsole("#################################################################");
		InternalMockObjectManager.printConsole("### [AsmClassVisitor.visitMethod] " + access + (isStatic ? " static " : " ") + name + " " + signature);
		InternalMockObjectManager.printConsole("#################################################################");
		InternalMockObjectManager.printConsole("#################################################################");

		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

		// is abstract or native
		if ((access & Opcodes.ACC_ABSTRACT) > 0) return mv;
		if ((access & Opcodes.ACC_NATIVE) > 0) return mv;
		if ((access & Opcodes.ACC_BRIDGE) > 0) return mv;

		int maxLocals = acc.getMaxLocals(name, desc);

		return createMethodVisitor(mv, name, desc, signature, isStatic, exceptions, maxLocals);
	}

	protected MethodVisitor createMethodVisitor(MethodVisitor mv, String name, String desc, String signature, boolean isStatic, String[] exceptions, int maxLocals) {
		if ("<init>".equalsIgnoreCase(name)) return new AsmConstractorVisitor(mv, this.className, name, desc, signature, exceptions, maxLocals, acc.getSuperClassNames());
		return new AsmMethodVisitor(mv, this.className, name, desc, signature, isStatic, exceptions, maxLocals, acc.getSuperClassNames());
	}
	
	public byte[] toByteArray() {
		return this.classWriter.toByteArray();
	}

}
