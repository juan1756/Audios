package ar.com.ice.brasil.model.beans;

import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = 1L;

	private String input1;

	private boolean input2;

	private String input3;

	private String select1;

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public boolean getInput2() {
		return input2;
	}

	public void setInput2(boolean input2) {
		this.input2 = input2;
	}

	public String getInput3() {
		return input3;
	}

	public void setInput3(String input3) {
		this.input3 = input3;
	}

	public String getSelect1() {
		return select1;
	}

	public void setSelect1(String select1) {
		this.select1 = select1;
	}
}
