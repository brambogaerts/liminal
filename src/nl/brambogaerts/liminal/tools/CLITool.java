package nl.brambogaerts.liminal.tools;

import java.util.HashMap;

public abstract class CLITool {
	public static HashMap<String, String> parseArguments(String[] args){
		HashMap<String, String> parsed = new HashMap<String, String>();

		for(String a: args){
			String[] pair = a.split("=");
			if(pair.length == 2) parsed.put(pair[0], pair[1]);
		}

		return parsed;
	}
}
