package com.project.factory.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.factory.dept.human.admin.NewMembers;



public class NewMemberData {
	public static ArrayList<NewMembers> NewMemberList = new ArrayList<NewMembers>();

	public static void load() {
        try {
            NewMemberList.clear();

            BufferedReader reader = new BufferedReader(new FileReader(Path.NEWMEMBER));

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("■");

                	
                    NewMembers newMember = new NewMembers(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],temp[6],temp[7]);
                    NewMemberList.add(newMember);
               
                    // 유효하지 않은 데이터 처리
               
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Data.load");
            e.printStackTrace();
        }
    }
		
		
	
	
	public static void save() {
	    try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter(Path.NEWMEMBER));

	        for (NewMembers newMember : NewMemberData.NewMemberList) {
	            String line = String.format("%s■%s■%s■%s■%s■%s■%s■%s\r\n", newMember.getId(),newMember.getPw(), newMember.getName(),
	                    newMember.getPhoneNum(), newMember.getBirth(), newMember.getAddress(), newMember.getDept(),
	                    newMember.getLevel());
	            writer.write(line);
	        }

	        writer.close();
	    } catch (Exception e) {
	        System.out.println("Data.save");
	        e.printStackTrace();
	    }
	}
}
