package com.doan.AppTuyenDung.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.doan.AppTuyenDung.DTO.Request.ProfileUserRequest;
import com.doan.AppTuyenDung.DTO.Request.ReqRes;
import com.doan.AppTuyenDung.DTO.Request.UserSettingDTO;
import com.doan.AppTuyenDung.DTO.Request.UserUpdateRequest;
import com.doan.AppTuyenDung.Exception.AppException;
import com.doan.AppTuyenDung.Exception.ErrorCode;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CodeGenderRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeExpType;
import com.doan.AppTuyenDung.entity.CodeGender;
import com.doan.AppTuyenDung.entity.CodeJobType;
import com.doan.AppTuyenDung.entity.CodeProvince;
import com.doan.AppTuyenDung.entity.CodeRule;
import com.doan.AppTuyenDung.entity.CodeSalaryType;

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
import com.doan.AppTuyenDung.Repositories.UserSettingRepository;
import com.doan.AppTuyenDung.Repositories.UserSkillRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeExpTypeRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeJobTypeRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeProvinceRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeSalaryTypeRepository;
import com.doan.AppTuyenDung.entity.User;
import com.doan.AppTuyenDung.entity.UserSetting;
import com.doan.AppTuyenDung.entity.UserSkill;



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
    private UserSettingRepository userSettingRepository;
    @Autowired
    private UserSkillRepository userSkillRepository;
    @Autowired
    private CodeJobTypeRepository codeJobTypeRepository;
    @Autowired
    private CodeSalaryTypeRepository codeSalaryTypeRepository;
    @Autowired
    private CodeProvinceRepository codeProvinceRepository;
    @Autowired
    private CodeExpTypeRepository codeExpTypeRepository;
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
                existingUser.setAddress(updatedUser.getAddress());
                existingUser.setLastName(updatedUser.getLastName());
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
    public ProfileUserRequest getProfile(String token) {
    	String phoneNumber = jwtUtils.extractUserName(token);
    	Account account = accountRepo.findByPhonenumber(phoneNumber);
    	User user = account.getUser();
    	ProfileUserRequest profile = new ProfileUserRequest();
    	profile.setFirstName(user.getFirstName());
    	profile.setLastName(user.getLastName());
    	profile.setAddress(user.getAddress());
    	profile.setDob(user.getDob());
    	profile.setEmail(user.getEmail());
    	profile.setGender(user.getGenderCode().getValue());
    	profile.setImage(user.getImage());
    	profile.setPhonenumber(account.getPhonenumber());
    	//profile.setStatus(account.getStatusCode().getValue()); chưa có status tam thoi de active
    	profile.setStatus("active");
    	return profile;
    }

    private boolean checkAccountExist(String phoneNumber) {
        return accountRepo.existsByPhonenumber(phoneNumber);
    }
	public ReqRes updateUser( UserUpdateRequest reqres) {
		return null;
	}
	public String setDataUserSetting(UserSettingDTO data) {
	    try {
	    	if (data.getIdUser() == null) {
		        return "Missing required parameters!";
		    }

		    User user = usersRepo.findById(data.getIdUser()).orElse(null);
		    System.out.print(data.getCategoryJobCode());
		    if (user == null) {
		        return "Không tồn tại người dùng này";
		    }

		    createOrUpdateUserSetting(data, user);

		    if (data.getSkill() != null && !data.getSkill().isEmpty()) {
		        userSkillRepository.deleteByUserId(user.getId());
		        List<UserSkill> userSkills = data.getSkill().stream().map(skillId -> {
		            UserSkill userSkill = new UserSkill();
		            userSkill.setUserId(user.getId());
		            userSkill.setSkillId(skillId);
		            return userSkill;
		        }).toList();
		        userSkillRepository.saveAll(userSkills);
		    }
		} catch (Exception e) {
			throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
		}

	    return "Hệ thống đã ghi nhận lựa chọn";
	}

	private void createOrUpdateUserSetting(UserSettingDTO data, User user) {
	    UserSetting userSetting = userSettingRepository.findByUserId(user.getId());
	    if (userSetting == null) {
	        userSetting = new UserSetting();
	        userSetting.setUser(user);
	    }
	    CodeJobType categoryJobCode = codeJobTypeRepository.findByCode(data.getCategoryJobCode());
	    CodeSalaryType salaryJobCode = codeSalaryTypeRepository.findByCode(data.getSalaryJobCode());
	    CodeProvince addressCode = codeProvinceRepository.findByCode(data.getAddressCode());
	    CodeExpType experienceJobCode = codeExpTypeRepository.findByCode(data.getExperienceJobCode());
	    userSetting.setCategoryJobCode(categoryJobCode);
	    userSetting.setSalaryJobCode(salaryJobCode);
	    userSetting.setAddressCode(addressCode);
	    userSetting.setExperienceJobCode(experienceJobCode);
	    userSetting.setFile(data.getFile());
	    userSetting.setIsTakeMail(data.getIsTakeMail());
	    userSetting.setIsFindJob(data.getIsFindJob());

	    userSettingRepository.save(userSetting);
	}
    public Account getUserFromToken(String token) {
        String phonenumber = jwtUtils.extractUserName(token);
        return accountRepo.findByPhonenumber(phonenumber);
    }

}

