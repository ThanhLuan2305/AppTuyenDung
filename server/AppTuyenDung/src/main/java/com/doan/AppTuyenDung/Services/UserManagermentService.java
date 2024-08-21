package com.doan.AppTuyenDung.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CodeGenderRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeGender;
import com.doan.AppTuyenDung.entity.CodeRule;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.doan.AppTuyenDung.DTO.InfoPostDetailDto;
import com.doan.AppTuyenDung.DTO.ReqRes;
import com.doan.AppTuyenDung.DTO.UserAccountDTO;
import com.doan.AppTuyenDung.DTO.UserUpdateRequest;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.entity.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Service
public class UserManagermentService {
	@Autowired
	private UserRepository usersRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private CodeGenderRepository codeGenderRepo;
    @Autowired
    private CodeRuleRepository ruleRepo;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ReqRes register(ReqRes registrationRequest) {
		ReqRes resp = new ReqRes();

		try {
            boolean accountExists = checkAccountExist(registrationRequest.getPhonenumber());
            if(accountExists) {
                resp.setMessage("Phone number is existed!");
                resp.setStatusCode(500);
                return resp;
            }
            CodeGender gender = codeGenderRepo.findByCode(registrationRequest.getGenderCode());
            if(gender == null) {
            	resp.setMessage("Gender is null!");
                resp.setStatusCode(500);
                return resp;
            }
			User user = new User();
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setEmail(registrationRequest.getEmail());
            user.setGenderCode(gender);
            User UserResult = usersRepo.save(user);
            if (user!=null) {
            	CodeRule rule = ruleRepo.findByCode(registrationRequest.getRoleCode());
                Account account = new Account();
                account.setPhonenumber(registrationRequest.getPhonenumber());
                account.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
                account.setRoleCode(rule);
                account.setUser(user);
                Account accountResult = accountRepo.save(account);
                if (account.getId()>0) {
                    resp.setUser(UserResult);
                    resp.setMessage("User Saved Successfully");
                    resp.setStatusCode(200);
                }
            }
		}catch(Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}
	public ReqRes login(ReqRes loginRequest) {
		ReqRes resp = new ReqRes();

		try {
            // Kiểm tra thông tin đăng nhập
            if (loginRequest.getPhonenumber() == null || loginRequest.getPassword() == null) {
                resp.setStatusCode(400);
                resp.setError("Các trường phải đảm bảo đầy đủ!");
                return resp;
            }
            // check tài khoản tồn tại = số điện thoại
            var account = accountRepo.findByPhonenumber(loginRequest.getPhonenumber());
            if (account == null) {
                resp.setStatusCode(404);
                resp.setError("Không tìm thấy số điện thoại! ");
                return resp;
            }
            // xác thực user
			authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPhonenumber()
									, loginRequest.getPassword()));
            // check acc bị ban
            if ("S1".equals(account.getStatusCode())) {
                resp.setStatusCode(403);
                resp.setError("Tài khoản của bạn đã bị khoá. Vui lòng liên hệ hỗ trợ!");
                return resp;
            }
			var jwt = jwtUtils.generateToken(account);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), account);
			CodeRule rule = ruleRepo.findByCode(account.getRoleCode().getCode());
            // done
			resp.setStatusCode(200);
			resp.setRoleCode(rule.getCode());
			resp.setToken(jwt);
			resp.setRefreshToken(refreshToken);
			resp.setExpirationTime("24Hrs");
	        resp.setMessage("Successfully Logged In");
		}catch(Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}
	public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            String phoneNumber = jwtUtils.extractUserName(refreshTokenReqiest.getToken());
            Account account = accountRepo.findByPhonenumber(phoneNumber);
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), account)) {
                var jwt = jwtUtils.generateToken(account);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = usersRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getUsersById(Integer id) {
        ReqRes reqRes = new ReqRes();
        try {
            User usersById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUser(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, UserUpdateRequest updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setAddress(updatedUser.getAddress());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setEmail(updatedUser.getEmail());
                CodeGender gender = codeGenderRepo.findByCode(updatedUser.getGenderCode());
                existingUser.setGenderCode(gender);
                existingUser.setImage(null); // Cần chỉnh lại sau
                existingUser.setDob(updatedUser.getDob());
//                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
//                    // Encode the password and update it
//                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//                }
                User savedUser = usersRepo.save(existingUser);
                reqRes.setUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    private boolean checkAccountExist(String phoneNumber) {
        return accountRepo.existsByPhonenumber(phoneNumber);
    }
	public ReqRes updateUser( UserUpdateRequest reqres) {
		return null;
	}
    public Account getUserFromToken(String token) {
        String phonenumber = jwtUtils.extractUserName(token);
        return accountRepo.findByPhonenumber(phonenumber);
    }

}

