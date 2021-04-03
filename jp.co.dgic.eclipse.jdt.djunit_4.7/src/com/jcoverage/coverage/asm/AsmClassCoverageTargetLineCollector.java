package com.jcoverage.coverage.asm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jp.co.dgic.djunit.asm.ClassVisitor;
import jp.co.dgic.djunit.asm.Label;
import jp.co.dgic.djunit.asm.MethodVisitor;
import jp.co.dgic.djunit.asm.Opcodes;
import jp.co.dgic.testing.common.util.DJUnitUtil;

public class AsmClassCoverageTargetLineCollector extends ClassVisitor {

	private boolean isEnum = false;
	private Map lineNumberLabels = new HashMap();
	private Set finallyBlocks = new HashSet();

	public AsmClassCoverageTargetLineCollector() {
		super(DJUnitUtil.ASM_API_VERSION);
//		super(new AsmEmptyVisitor());
	}

	public boolean isEnum() {
		return isEnum;
	}
	
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		isEnum = (access & Opcodes.ACC_ENUM) > 0;
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		return new AsmMethodCoverageTargetLineCollector(this);
	}

	public void addLineLabel(int line, Label start) {
		lineNumberLabels.put(start.toString(), new Integer(line));
	}

	public void addFinallyBlock(Label handler) {
		finallyBlocks.add(handler.toString());
	}

	public Set getFinallyLines() {
		Set lines = new HashSet();
		String[] blocks = (String[]) finallyBlocks.toArray(new String[finallyBlocks.size()]);
		for (int i = 0; i < blocks.length; i++) {
			Integer line = (Integer) lineNumberLabels.get(blocks[i]);
			if (line != null) {
				lines.add(line);
			}
		}
		return lines;
	}
}
