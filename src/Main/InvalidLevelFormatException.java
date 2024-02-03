package Main;

public class InvalidLevelFormatException extends Exception{
	 String line;
		
		
		public InvalidLevelFormatException(String line) {
	        this.line=line;
		}
		@Override
		public String getMessage() {
			
			return "Invalid format: "+line+ "  you should have input(Name x y angle).";
		}
}
