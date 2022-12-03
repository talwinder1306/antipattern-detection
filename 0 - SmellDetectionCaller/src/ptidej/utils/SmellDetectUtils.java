package ptidej.utils;

import padl.kernel.IFirstClassEntity;

public class SmellDetectUtils {
	
	public static boolean isJavaClass(IFirstClassEntity entity) {
		String path = entity.getDisplayPath();
		String[] pathSplit = path.split("\\|");
		
		if(pathSplit.length > 1 && pathSplit[1].equals("java")) {
			return true;
		}
		
		return false;
	}

}
