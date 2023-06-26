package telran.text;


public class Strings {
	public static String javaVariableName() {
		
		return "[a-zA-Z$][\\w$]*|_[\\w$]+";
	}
	public static String zero_300() {
		return "[1-9]\\d?|[1-2]\\d\\d|300|0";
	}
	public static String ipV4Octet() {
		
		// positive number from 0 to 255 and leading zeros are aloowed
		return "\\d\\d?|[0-1]\\d\\d|2[0-4]\\d|25[0-5]";
	}
	public static String ipV4() {
		
		// four ipV4 octets separated by dot 123.123.255.01
		
		String octet = "("+ipV4Octet()+")";
	
		return "("+octet+"\\."+")"+"{3}"+octet;
	}

}
