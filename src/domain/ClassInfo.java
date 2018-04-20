package domain;

import java.io.Serializable;

public class ClassInfo implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String className;//°à¼¶Ãû
	private String classId;//id
	
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String id) {
		this.classId = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
