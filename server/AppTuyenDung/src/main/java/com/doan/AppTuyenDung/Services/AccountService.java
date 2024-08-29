package com.doan.AppTuyenDung.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.DTO.Request.ChangePasswordRequest;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.User;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository uRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String changePassword(int idUser, ChangePasswordRequest changePass) {
		Optional<User> uOptional = uRepository.findById(idUser);
		User user = uOptional.get();
		if(uOptional.get()==null) {
			return "Không tìm thấy user";
		}
		
		String oldPassEncode = passwordEncoder.encode(changePass.oldPassword);

		String newPassEncode = passwordEncoder.encode(changePass.newPassword);
		Account account = accountRepository.findByUserId(idUser);
		boolean isPasswordMatch = passwordEncoder.matches(changePass.oldPassword, account.getPassword());
      if (isPasswordMatch && !newPassEncode.isEmpty()) {
      	account.setPassword(newPassEncode);
      	Account accountSave = accountRepository.save(account);
      }
      else { 
    	  return "Password củ không đúng";
      }
		return "Thay đổi password thành công";
	}
}
