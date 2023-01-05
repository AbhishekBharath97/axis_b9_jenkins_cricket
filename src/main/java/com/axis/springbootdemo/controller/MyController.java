package com.axis.springbootdemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class MyController {
	
	private static ArrayList<Cricketer> crickList;
	static {
		crickList=new ArrayList<>();
		crickList.add(new Cricketer(1001, "Suryakumar Yadav", 111, 51, 8,6,217.2 ));
		crickList.add(new Cricketer(1002, "Virat Kohli", 82, 60, 6,5,130.8 ));
		crickList.add(new Cricketer(1003, "Mithali Raj", 62, 62, 4,2,100.0 ));
		crickList.add(new Cricketer(1004, "Smrithi Mandhana", 52, 40, 3,3,130 ));

	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello.. First SpringBoot Project Good Afternoon.";
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome .Happy to start Spring boot";
	}
	// get all cricketers
	@GetMapping("/cricketer")
	public ArrayList<Cricketer> getCricketer() {
		return crickList;
	}
	
	//get cricketer by id
	@GetMapping("/cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for (Cricketer ck:crickList) {
			if (ck.getCricketerId()==cricketerId) {
				return ck;// return Cricketer if Id is found
			}
		}return null;//return null if cricketer id not found
	}
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer) {
		crickList.add(cricketer);
		return new ResponseEntity<String>("Cricketer Added Successfully.....",HttpStatus.OK);
	}
	
	//update request
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer(@PathVariable int cricketerId, @RequestBody Cricketer updatedCricketer){
	if (cricketerId!=updatedCricketer.getCricketerId()) {
		return new ResponseEntity<String>("Cricketer id is does not match !!!",HttpStatus.BAD_REQUEST);
		
	}
	int index=crickList.indexOf(updatedCricketer);
	if (index==-1) {
		return new ResponseEntity<String> ("Cricketer with id : "+cricketerId+ "is not found ...!!!",HttpStatus.NOT_FOUND);
	}else {
		crickList.get(index).setBalls(updatedCricketer.getBalls());
		crickList.get(index).setRunScored(updatedCricketer.getRunScored());
		crickList.get(index).setFours(updatedCricketer.getFours());
		crickList.get(index).setSixes(updatedCricketer.getSixes());
		crickList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
		return new ResponseEntity<String>("Cricketer Data updated successfully !!!",HttpStatus.OK);
		

	}
	}
	///Delete Mapping
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId){
		
		Cricketer cricketer= getCricketerById(cricketerId);
		if(cricketer==null) {
			return new ResponseEntity<String> ("Cricketer with id : "+cricketerId+ "is not found ...!!!",HttpStatus.NOT_FOUND);
		}else {
			crickList.remove(cricketer);
			return new ResponseEntity<String>("Cricketer with id :"+cricketerId+"deleted Successfully !!!",HttpStatus.OK);
		}
	}
	

}
