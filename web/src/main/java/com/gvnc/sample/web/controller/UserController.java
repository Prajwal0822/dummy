package com.gvnc.sample.web.controller;

import com.google.common.base.Strings;
import com.gvnc.sample.web.model.response.OperationResponse;
import com.gvnc.sample.web.model.response.ResponseStatusEnum;
import com.gvnc.sample.web.model.user.User;
import com.gvnc.sample.web.model.user.UserResponse;
import com.gvnc.sample.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
	public UserResponse getUserInformation(@RequestParam(value = "name", required = false) String userIdParam, HttpServletRequest req) {

		String loggedInUserId = userService.getLoggedInUserId();

		User user;
		boolean provideUserDetails = false;

		if (Strings.isNullOrEmpty(userIdParam)) {
			provideUserDetails = true;
			user = userService.getLoggedInUser();
		} else if (loggedInUserId.equals(userIdParam)) {
			provideUserDetails = true;
			user = userService.getLoggedInUser();
		} else {
			//Check if the current user is superuser then provide the details of requested user
			provideUserDetails = true;
			user = userService.getUserInfoByUserId(userIdParam);
		}

		UserResponse resp = new UserResponse();
		if (provideUserDetails) {
			resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
		} else {
			resp.setOperationStatus(ResponseStatusEnum.NO_ACCESS);
			resp.setOperationMessage("No Access");
		}
		resp.setData(user);
		return resp;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = {"application/json"})
	public OperationResponse addNewUser(@RequestBody User user, HttpServletRequest req) {
		boolean userAddSuccess = userService.addNewUser(user);
		OperationResponse resp = new OperationResponse();
		if (userAddSuccess==true){
			resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
			resp.setOperationMessage("User Added");
		} else{
			resp.setOperationStatus(ResponseStatusEnum.ERROR);
			resp.setOperationMessage("Unable to add user");
		}
		return resp;
	}
}
