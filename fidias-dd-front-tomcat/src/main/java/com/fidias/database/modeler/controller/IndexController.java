package com.fidias.database.modeler.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fidias.database.modeler.api.dto.FileMeta;
import com.fidias.database.modeler.api.dto.FilesDto;
import com.google.common.collect.Lists;

@Controller
@RequestMapping
public class IndexController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(IndexController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody List<FilesDto> upload(@RequestParam(value = "files[]", required = false) MultipartFile[] files,
			HttpSession session){
		LOGGER.debug("uploading...");
		List<FilesDto> res = Lists.newArrayList();
		for(MultipartFile file : files){
			if(file != null){
				String filename = file.getOriginalFilename();
				if(!StringUtils.endsWithIgnoreCase(filename, ".xml")){
					LOGGER.error("el archivo no posee la extension valida (XML).");
				}
				FilesDto d = new FilesDto(filename, file.getSize());
				res.add(d);
			}
		}
		
		return res;
    }
	
	@RequestMapping(value="/upload2", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileMeta> upload2(MultipartHttpServletRequest request, HttpServletResponse response) {
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	    FileMeta fileMeta = null;
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10)
                 files.pop();
 
             //2.3 create new fileMeta
             fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/"+mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
    /***************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
     public void get(HttpServletResponse response,@PathVariable String value){
    	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
         FileMeta getFile = files.get(Integer.parseInt(value));
         try {      
                response.setContentType(getFile.getFileType());
                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
                FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
         }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }
     }

	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
}
