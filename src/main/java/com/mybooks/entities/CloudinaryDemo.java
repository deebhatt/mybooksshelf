package com.mybooks.entities;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;



public class CloudinaryDemo {

	/*public static void main(String[] args) {
		
		Map config = new HashMap();
		config.put("cloud_name", "mybooksshelf");
		config.put("api_key", "685194311551847");
		config.put("api_secret", "RA98UGk5ECMgkGlG2AAHlNTI3sY");
		config.put("cdn_subdomain", true);
		
		File file = new File("C:/Users/DEEPAK/Downloads/MyBooksShelf-Books/Modified_Pics/New folder4/hc verma.jpg");
		
		Cloudinary cloudinary = new Cloudinary(config);
		
		try {
			Map uploadresult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			Set us = uploadresult.entrySet();
			
			Iterator itr = us.iterator();
			
			while(itr.hasNext())
			{
				Map.Entry mp = (Map.Entry) itr.next();
				System.out.println(mp.getKey() +" "+ mp.getValue());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

}
