package com.jcoverage.coverage.asm;

import jp.co.dgic.djunit.asm.Label;
import jp.co.dgic.djunit.asm.MethodVisitor;
import jp.co.dgic.djunit.asm.Opcodes;

public class AsmMethodCoverageTargetLineCollector extends MethodVisitor {

	private AsmClassCoverageTargetLineCollector classLineCollector;

	public AsmMethodCoverageTargetLineCollector(AsmClassCoverageTargetLineCollector classLineCollector) {
		super(Opcodes.ASM5);
//		super(new AsmEmptyVisitor());
		this.classLineCollector = classLineCollector;
	}

	public void visitLineNumber(final int line, final Label start) {
		classLineCollector.addLineLabel(line, start);
	}

	public void visitTryCatchBlock(final Label start, final Label end, final Label handler, final String type) {
		if (type == null) {
			classLineCollector.addFinallyBlock(handler);
		}
	}
}
