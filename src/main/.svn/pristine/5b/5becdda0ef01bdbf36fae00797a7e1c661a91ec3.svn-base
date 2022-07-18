package com.tes.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tes.utilities.Constant;

/**
 * This class used to view third party monitoring report file including stack,ambient,
 * water effluent and sewage.
 * 
 * @author Tushar Chougule
 */
@Controller
public class CommonController
{

	/**
	 * This method used to view third party report file including stack,ambient,water effluent and sewage.
	 * 
	 * @param request the request object of the HttpServletRequest
	 * @param httpSession the object of the HttpSession
	 * @param fileName the name of file
	 * @param fileType the type of file
	 * @return view-file
	 * @throws IOException if an input/output error occurs
	 */
	@GetMapping(value = "/view-file")
	public ResponseEntity<byte[]> getConsentPdf(HttpServletRequest request,
			HttpSession httpSession, @RequestParam(value = "fileName", required = false) String fileName, @RequestParam(value = "fileType", required = false) String fileType) throws IOException
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));

		String myFile = "";
		switch (fileType)
		{
			case "consent":
				myFile = Constant.consent_file_path;
				break;

			case "stack":
				myFile = Constant.ThirdParty_file_path + "stack//";
				break;

			case "ambient":
				myFile = Constant.ThirdParty_file_path + "ambient//";
				break;

			case "eff":
				myFile = Constant.ThirdParty_file_path + "eff//";
				break;

			case "sew":
				myFile = Constant.ThirdParty_file_path + "sew//";
				break;
		}
		myFile = myFile + fileName;
		Path path = Paths.get(myFile);
		byte[] data = Files.readAllBytes(path);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}

}
