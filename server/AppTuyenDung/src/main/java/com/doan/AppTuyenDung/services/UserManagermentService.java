package com.doan.AppTuyenDung.Services;



import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.doan.AppTuyenDung.DTO.ReqRes;
// import com.doan.AppTuyenDung.entity.User;
// import com.doan.AppTuyenDung.repository.UserRepo;



// @Service
// public class UserManagermentService {
// 	@Autowired
// 	private UserRepo usersRepo;
// 	@Autowired
// 	private JWTUtils jwtUtils;
// 	@Autowired
// 	private AuthenticationManager authenticationManager;
// 	@Autowired
// 	private PasswordEncoder passwordEncoder;
	
// 	public ReqRes register(ReqRes registrationRequest) {
// 		ReqRes resp = new ReqRes();
		
// 		try {
// 			User ourUser = new User();
// 			ourUser.setUsername(registrationRequest.getUsername());
//             ourUser.setEmail(registrationRequest.getEmail());
//             ourUser.setCity(registrationRequest.getCity());
//             ourUser.setUser_type(registrationRequest.getUser_type());
//             ourUser.setFull_name(registrationRequest.getFull_name());
//             ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
//             User UserResult = usersRepo.save(ourUser);
//             if (UserResult.getUser_id()>0) {
//                 resp.setUser(UserResult);
//                 resp.setMessage("User Saved Successfully");
//                 resp.setStatusCode(200);
//             }
// 		}catch(Exception e) {
// 			resp.setStatusCode(500);
// 			resp.setError(e.getMessage());
// 		}
// 		return resp;
// 	}
// 	public ReqRes login(ReqRes loginRequest) {
// 		ReqRes resp = new ReqRes();
		
// 		try {
// 			authenticationManager
// 							.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
// 									, loginRequest.getPassword()));
// 			var user = usersRepo.findByUsername(loginRequest.getUsername()).orElseThrow();
// 			var jwt = jwtUtils.generateToken(user);
//             var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
// 			resp.setStatusCode(200);
// 			resp.setUser_type(user.getUser_type());
// 			resp.setToken(jwt);
// 			resp.setRefreshToken(refreshToken);
// 			resp.setExpirationTime("24Hrs");
// 	        resp.setMessage("Successfully Logged In");
// 		}catch(Exception e) {
// 			resp.setStatusCode(500);
// 			resp.setError(e.getMessage());
// 		}
// 		return resp;
// 	}
// 	public ReqRes refreshToken(ReqRes refreshTokenReqiest){
//         ReqRes response = new ReqRes();
//         try{
//             String userName = jwtUtils.extractUserName(refreshTokenReqiest.getToken());
//             User users = usersRepo.findByUsername(userName).orElseThrow();
//             if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
//                 var jwt = jwtUtils.generateToken(users);
//                 response.setStatusCode(200);
//                 response.setToken(jwt);
//                 response.setRefreshToken(refreshTokenReqiest.getToken());
//                 response.setExpirationTime("24Hr");
//                 response.setMessage("Successfully Refreshed Token");
//             }
//             response.setStatusCode(200);
//             return response;

//         }catch (Exception e){
//             response.setStatusCode(500);
//             response.setMessage(e.getMessage());
//             return response;
//         }
//     }


//     public ReqRes getAllUsers() {
//         ReqRes reqRes = new ReqRes();

//         try {
//             List<User> result = usersRepo.findAll();
//             if (!result.isEmpty()) {
//                 reqRes.setUsersList(result);
//                 reqRes.setStatusCode(200);
//                 reqRes.setMessage("Successful");
//             } else {
//                 reqRes.setStatusCode(404);
//                 reqRes.setMessage("No users found");
//             }
//             return reqRes;
//         } catch (Exception e) {
//             reqRes.setStatusCode(500);
//             reqRes.setMessage("Error occurred: " + e.getMessage());
//             return reqRes;
//         }
//     }


//     public ReqRes getUsersById(Integer id) {
//         ReqRes reqRes = new ReqRes();
//         try {
//             User usersById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
//             reqRes.setUser(usersById);
//             reqRes.setStatusCode(200);
//             reqRes.setMessage("Users with id '" + id + "' found successfully");
//         } catch (Exception e) {
//             reqRes.setStatusCode(500);
//             reqRes.setMessage("Error occurred: " + e.getMessage());
//         }
//         return reqRes;
//     }


//     public ReqRes deleteUser(Integer userId) {
//         ReqRes reqRes = new ReqRes();
//         try {
//             Optional<User> userOptional = usersRepo.findById(userId);
//             if (userOptional.isPresent()) {
//                 usersRepo.deleteById(userId);
//                 reqRes.setStatusCode(200);
//                 reqRes.setMessage("User deleted successfully");
//             } else {
//                 reqRes.setStatusCode(404);
//                 reqRes.setMessage("User not found for deletion");
//             }
//         } catch (Exception e) {
//             reqRes.setStatusCode(500);
//             reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
//         }
//         return reqRes;
//     }

//     public ReqRes updateUser(Integer userId, User updatedUser) {
//         ReqRes reqRes = new ReqRes();
//         try {
//             Optional<User> userOptional = usersRepo.findById(userId);
//             if (userOptional.isPresent()) {
//                 User existingUser = userOptional.get();
//                 existingUser.setEmail(updatedUser.getEmail());
//                 existingUser.setFull_name(updatedUser.getFull_name());
//                 existingUser.setCity(updatedUser.getCity()); 
//                 existingUser.setUser_type(updatedUser.getUser_type());

//                 // Check if password is present in the request
//                 if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
//                     // Encode the password and update it
//                     existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//                 }

//                 User savedUser = usersRepo.save(existingUser);
//                 reqRes.setUser(savedUser);
//                 reqRes.setStatusCode(200);
//                 reqRes.setMessage("User updated successfully");
//             } else {
//                 reqRes.setStatusCode(404);
//                 reqRes.setMessage("User not found for update");
//             }
//         } catch (Exception e) {
//             reqRes.setStatusCode(500);
//             reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
//         }
//         return reqRes;
//     }


//     public ReqRes getMyInfo(String user){
//         ReqRes reqRes = new ReqRes();
//         try {
//             Optional<User> userOptional = usersRepo.findByUsername(user);
//             if (userOptional.isPresent()) {
//                 reqRes.setUser(userOptional.get());
//                 reqRes.setStatusCode(200);
//                 reqRes.setMessage("successful");
//             } else {
//                 reqRes.setStatusCode(404);
//                 reqRes.setMessage("User not found for update");
//             }

//         }catch (Exception e){
//             reqRes.setStatusCode(500);
//             reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
//         }
//         return reqRes;

//     }
// }
