
public class LinkStrand implements IDnaStrand {
	private class Node {
		String info;
		Node next;
		public Node (String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	
	public int myIndex;
	public int myLocalIndex;
	public Node myCurrent;
	
	public LinkStrand() {
		this("");
	}
	
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myFirst.next = myLast;
		myLast = new Node("");
		mySize = source.length();
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = null;
	}
	
	@Override 
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	
	@Override
	public IDnaStrand append(String dna) {
		Node theUseful = new Node(dna);
		theUseful.next = null;
		myLast.next = theUseful;
		mySize += dna.length();
		myAppends++;
		myLast = myLast.next;
		return this;
	}
	
	@Override
	public long size() {
		return mySize;
	}
	
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public String toString() {
		Node myList = myFirst;
		StringBuilder mySB = new StringBuilder();
		mySB.append(myFirst.info);
		while (myList.next != null) {
			myList = myList.next;
			mySB.append(myList.info);
		}
		String out = mySB.toString();
		return out;
	}
	
	@Override
	public IDnaStrand reverse() {
		Node myNewFirst = myLast;
		myNewFirst.next = null;
		Node myNewLast;
		Node theCurrent = myFirst;
		theCurrent.next = myLast;
		myNewLast = myFirst;
		
		StringBuilder copy = new StringBuilder(myNewLast.info);
		copy.reverse();
		LinkStrand myLS = new LinkStrand(copy.toString());
		myNewLast.info = myLS.strandInfo();
		myNewLast.next = null;
		
		
		
		theCurrent = myFirst.next;
		
		myNewFirst = theCurrent;
		myNewFirst.next = myNewLast;
		theCurrent = theCurrent.next;
		
		StringBuilder newCopy = new StringBuilder(myNewFirst.info);
		newCopy.reverse();
		LinkStrand thisLS = new LinkStrand(newCopy.toString());
		myNewFirst.info = thisLS.strandInfo();
		
		
		
		
		while (theCurrent != null) {
			Node holder = myNewFirst;
			myNewFirst = theCurrent;
			myNewFirst.next = holder;
			theCurrent = theCurrent.next;
			
			StringBuilder usefuk = new StringBuilder(myNewFirst.info);
			usefuk.reverse();
			LinkStrand realLS = new LinkStrand(usefuk.toString());
			myNewFirst.info = realLS.strandInfo();
			
		}
		
		
		myFirst = myNewFirst;
		myLast = myNewLast;
		
		
		return this;
	}

	
	@Override
	public char charAt(int index) {
		if (index >= myIndex) {
			int count = myIndex;
			int dex = count;
			Node list = myFirst;
			while (count != index) {
				count++;
				dex++;
				if (dex >= list.info.length()) {
					dex = 0;
					list = list.next;
				}
			}
			myCurrent = list;
			myLocalIndex = dex;
			myIndex = count;
			return list.info.charAt(dex);
		}
		
		else if (index < myIndex) {
			int count = 0;
			int dex = 0;
			Node list = myFirst;
			while (count != index) {
				count++;
				dex++;
				if (dex >= list.info.length()) {
					dex = 0;
					list = list.next;
				}
			}
			myCurrent = list;
			myLocalIndex = dex;
			myIndex = count;
			return list.info.charAt(dex);
		}
		return 0;
	}
	
	

	
}