/*
 * package com.tes.controller.api;
 * import java.util.ArrayList;
 * import java.util.HashMap;
 * import javax.servlet.http.HttpServletRequest;
 * import javax.validation.Valid;
 * import org.apache.logging.log4j.LogManager;
 * import org.apache.logging.log4j.Logger;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.BadCredentialsException;
 * import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.Authentication;
 * import org.springframework.security.core.AuthenticationException;
 * import org.springframework.security.core.context.SecurityContextHolder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.web.bind.annotation.CrossOrigin;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * import com.tes.jwt.JwtAuthTokenFilter;
 * import com.tes.jwt.JwtProvider;
 * import com.tes.jwt.LoginForm;
 * import com.tes.model.EmpData;
 * import com.tes.model.JwtResponse;
 * import com.tes.services.UsersServices;
 * import com.tes.services.admin.EmpDataServices;
 * import com.tes.utilities.Constant;
 * import com.tes.utilities.Validator;
 * @CrossOrigin(origins = "*", maxAge = 3600)
 * @RestController
 * @RequestMapping("/api/auth")
 * public class LoginRestController {
 * @Autowired
 * AuthenticationManager authenticationManager;
 * @Autowired
 * UsersServices usersServices;
 * @Autowired
 * EmpDataServices empDataServices;
 * @Autowired
 * JwtProvider jwtProvider;
 * @Autowired
 * PasswordEncoder encoder;
 * @Autowired
 * JwtAuthTokenFilter jwtAuthTokenFilter;
 * private static final Logger LOGGER = LogManager.getLogger(LoginRestController.class);
 * @GetMapping("/")
 * public String getIndexPage() {
 * return "hello world";
 * }
 * @PostMapping("/login")
 * public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 * try {
 * Authentication authentication = authenticationManager.authenticate(
 * new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 * SecurityContextHolder.getContext().setAuthentication(authentication);
 * String jwt = jwtProvider.generateJwtToken(authentication);
 * return ResponseEntity.ok(new JwtResponse(jwt));
 * } catch (AuthenticationException e) {
 * throw new BadCredentialsException("Invalid username/password supplied");
 * }
 * }
 * @GetMapping("/user/logindetails")
 * public ArrayList<Object> getUsersDetails(HttpServletRequest request, Authentication authentication) {
 * HashMap<String, Object> userDetailsMap=new HashMap<>();
 * HashMap<String, Object> userDetailMap=new HashMap<>();
 * ArrayList<Object> loginDetailsArray = new ArrayList<>();
 * String userName=authentication.getName();
 * String jwt=null;
 * EmpData empDataDetails=new EmpData();
 * try {
 * if(!Validator.isEmpty(authentication))
 * {
 * jwt=jwtAuthTokenFilter.getJwt(request);
 * }
 * int usersId=usersServices.findUserIdByUserName(userName);
 * if(!Validator.isEmpty(usersId)||!Validator.isEmpty(jwt))
 * {
 * empDataDetails=empDataServices.findByUserId(usersId);
 * userDetailMap.put("usersId",empDataDetails.getUsers().getUsersId());
 * userDetailMap.put("employeeName",empDataDetails.getEmployeeName());
 * userDetailMap.put("designation",empDataDetails.getContPerDesig());
 * userDetailMap.put("compName", empDataDetails.getCompanyProfile().getCompName());
 * userDetailMap.put("companyProfileId", empDataDetails.getCompanyProfile().getCompanyProfileId());
 * userDetailMap.put("JwtToken",jwt);
 * userDetailsMap.put("userDetails", userDetailMap);
 * userDetailsMap.put("result",Constant.SUCCESS);
 * }else
 * {
 * userDetailsMap.put("result",Constant.FAILURE);
 * }
 * } catch (Exception e) {
 * LOGGER.error(e);
 * userDetailsMap.put("result",Constant.FAILURE);
 * }
 * loginDetailsArray.add(userDetailsMap);
 * return loginDetailsArray;
 * }
 * }
 */
