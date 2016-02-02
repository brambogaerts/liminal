package nl.brambogaerts.liminal.config;

import java.util.HashMap;

public abstract class Configuration {
	private static HashMap<String, String> config = new HashMap<String, String>();

	public static void add(String key, String defaultValue){
		if(!config.keySet().contains(key)){
			config.put(key, defaultValue);
		}
	}

	public static void set(String key, String value){
		if(config.keySet().contains(key)){
			config.put(key, value);
		}
	}

	public static String get(String key){
		return config.get(key);
	}

	public static double getDouble(String key){
		return Double.parseDouble(config.get(key));
	}

	public static void use(HashMap<String, String> options){
		options.forEach((String key, String value) -> set(key, value));
	}

	public static String stringify(){
		String[] arr = new String[config.size()];

		int i = 0;
		for(String key: config.keySet()){
			arr[i] = key + "=" + config.get(key);
			i++;
		}

		return String.join(",", arr);
	}

	public static void print(){
		System.out.println("Configuration:");

		config.forEach((String key, String value) -> {
			System.out.println("  " + key + "=" + value);
		});

		System.out.println();
	}
}
