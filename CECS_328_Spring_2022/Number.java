package shangchi;

public class Number {

	Number partner;
	int num;
	Number leftParent;
	Number rightParent;
	Number son;
	
	public Number(int num) {
		this.num=num;
	}
	
	public void setParents(Number leftParent, Number rightParent) {
		this.leftParent=leftParent;
		this.rightParent=rightParent;
	}
	
	public void setSon(Number number) {
		son = number;
	}
	
	public Number getSon() {
		return son;
	}
	
	public void setPartner(Number partner) {
		this.partner = partner;
	}
	
	public boolean hasParents() {
		if(leftParent!=null)
			return true;
		return false;
	}
}
