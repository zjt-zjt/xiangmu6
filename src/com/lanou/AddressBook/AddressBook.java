package com.lanou.AddressBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class AddressBook {
	private TreeMap<String, List<Contact>> map = new TreeMap<>();
	private Pinyin4j py = new Pinyin4j();
	
	public AddressBook() {
		super();
		initData();
	}
	
	public void initData() {
		File file = new File("src/contacts.txt");
		if(file.exists() == true) {
			ObjectInputStream ois = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				map = (TreeMap<String, List<Contact>>) ois.readObject();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void saveToFile(){
		ObjectOutputStream ois = null;
		try {
			FileOutputStream fis = new FileOutputStream("src/contacts.txt");
			ois = new ObjectOutputStream(fis);
			ois.writeObject(map);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void addContact(Contact c) {
		String group = c.getGroup();//获取联系人所在的分区。
		List<Contact> list = map.get(group);
		if(list == null) {
			//说明这个分区现在还没有。
			list = new ArrayList<Contact>();
			list.add(c);
			map.put(group, list);
		}else {
			list.add(c);
		}
	}

	public void deleteContact(String name) throws BadHanyuPinyinOutputFormatCombination {
		String group = py.toPinYinUppercaseInitials(name);
		List<Contact> list = map.get(group);
		if(list != null) {
			//如果有这个分区。
			Iterator<Contact> it = list.iterator();
			while(it.hasNext()) {
				Contact c = it.next();
				if(c.getName().equals(name)) {
					it.remove();
				}
			}
			if(list.size() == 0) {
				//如果分区空了，删除这个分区。
				map.remove(group);
			}
		}
	}
	
	public void modifyContact(String name, Contact c) throws BadHanyuPinyinOutputFormatCombination {
		deleteContact(name);
		addContact(c);
	}
	
	public List<Contact> findContactsByGroup(String group){
		return map.get(group);
	}

	public List<Contact> findCotactsByName(String name){
		List<Contact> result = new ArrayList<>();
		Set<Entry<String, List<Contact>>> s = map.entrySet();	
		Iterator<Entry<String,List<Contact>>> it = s.iterator();
		while(it.hasNext()) {
			Entry<String,List<Contact>> entry = it.next();
			List<Contact> list = entry.getValue();
			for(Contact c : list) {
				if(c.getName().contains(name) == true) {
					result.add(c);
				}
			}
		}
		return result.size() == 0 ? null : result;
	}
	public List<Contact> findCotactsByPhone(String phone){
		List<Contact> result = new ArrayList<>();
		Set<Entry<String, List<Contact>>> s = map.entrySet();	
		Iterator<Entry<String,List<Contact>>> it = s.iterator();
		while(it.hasNext()) {
			Entry<String,List<Contact>> entry = it.next();
			List<Contact> list = entry.getValue();
			for(Contact c : list) {
				if(c.getPhone().contains(phone) == true) {
					result.add(c);
				}
			}
		}
		return result.size() == 0 ? null : result;
	}
	
	public void showAllCotacts() {
		System.out.println("-----------------------");
		Set<String> groups = map.keySet();
		for(String group : groups) {
			System.out.println(group);
			List<Contact> list = map.get(group);
			for(Contact c : list) {
				System.out.println("    "+ c.toString());
			}
		}
	}
	
	public void showCotacts(List<Contact> list) {
		if(list == null) {
			System.out.println("没有匹配的结果！");
			return;
		}
		System.out.println("-----------------------");
		for(Contact c : list) {
			System.out.println("    " + c.toString());
		}
	}
	
	
	@Override
	public String toString() {
		return map.toString();
	}
}
