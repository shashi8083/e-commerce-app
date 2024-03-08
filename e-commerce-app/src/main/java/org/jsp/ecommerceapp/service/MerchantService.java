package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.DataNotFoundException;
import org.jsp.ecommerceapp.exception.IdNotFoundException;
import org.jsp.ecommerceapp.exception.InvalidCredentialsException;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setMessage("Merchant saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if(recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			
			structure.setMessage("Merchant Updated");
			structure.setData(merchantDao.saveMerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<String>> deleteById(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if(recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData("Merchant Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			merchantDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	public ResponseStructure<List<Merchant>> findAll(){
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setMessage("Merchant List");
		structure.setData(merchantDao.findAll());
		structure.setStatusCode(HttpStatus.FOUND.value());
		return structure;
	}
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone,String password){
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			structure.setMessage("Verified");
			structure.setData(merchantDao.verifyMerchant(phone, password).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
			
		}
		throw new InvalidCredentialsException("Invalid Merchant phone or password");
	}
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email,String password){
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			structure.setMessage("Verified");
			structure.setData(merchantDao.verifyMerchant(email, password).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
			
		}
		throw new InvalidCredentialsException("Invalid Merchant email or password");
	}
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.findByName(name);
		structure.setData(merchants);
		if(merchants.size()>0) {
			structure.setMessage("List of Merchants with entered name");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}
		throw new DataNotFoundException("Name not belongs to Merchant");
	}
	public ResponseEntity<ResponseStructure<Merchant>> findByPhone(long phone){
		return null;
		
	}
}
