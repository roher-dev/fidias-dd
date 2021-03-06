package com.fidias.database.modeler.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fidias.database.modeler.api.dto.AutoCompleteResponseDto;
import com.fidias.database.modeler.api.dto.BasicOutputDto;
import com.fidias.database.modeler.api.dto.FileMeta;
import com.fidias.database.modeler.api.dto.FileResponseDto;
import com.fidias.database.modeler.api.dto.ProjectDto;
import com.fidias.database.modeler.api.dto.ProjectResponseDto;
import com.fidias.database.modeler.service.ProjectService;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.processor.ProjectXmlProcessor;

@Controller
@RequestMapping
public class IndexController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(IndexController.class);
	
	@Inject
	private ProjectXmlProcessor projectXmlProcessor;
	@Inject
	private ProjectService projectService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
		
        return "index";
    }
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ProjectDto> getAllProjects(HttpServletResponse response){
		List<ProjectDto> projects = this.projectService.searchAll();
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		return projects;
	}
	
	/*
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
	*/
	@RequestMapping(value="/upload2", method = RequestMethod.POST)
    public @ResponseBody FileResponseDto upload2(MultipartHttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("uploading...");
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	    FileMeta fileMeta = null;
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             LOGGER.debug(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
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
//                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/" + mpf.getOriginalFilename()));
                ProjectElement projectElement = this.projectXmlProcessor.process(mpf.getInputStream());
                if(projectElement != null){
                	this.projectService.insert(projectElement);
                } else {
                	throw new RuntimeException("an error has ocurred when it tried to parse the file ...");
                }
            } catch (IOException e) {
            	throw new RuntimeException(e);
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return new FileResponseDto(files);
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

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProjectResponseDto getProjects(@PathVariable String id, HttpServletResponse response){
		ProjectResponseDto dto = new ProjectResponseDto();
		
		if(StringUtils.isNotBlank(id)){
			String[] parts = StringUtils.split(id, "_");
			String type = parts[0];
			Long identifier = Long.valueOf(parts[1]);
			List<ProjectDto> projects = this.projectService.searchByidType(type, identifier);
			if(projects != null){
				dto.setProjectList(projects);
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
		
		return dto;
	}
	
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody BasicOutputDto deleteProject(@PathVariable String id, 
			HttpServletResponse response){
		
		if(StringUtils.isNotBlank(id)){
			String[] parts = StringUtils.split(id, "_");
			Long identifier = Long.valueOf(parts[1]);
			this.projectService.remove(identifier);
		}
		
		return new BasicOutputDto(0);
	}

	@RequestMapping(value = "/projects/filter/{query}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AutoCompleteResponseDto filterProjects(@PathVariable String query, HttpServletResponse response){
		AutoCompleteResponseDto dto = new AutoCompleteResponseDto();
		
		if(StringUtils.isNotBlank(query)){
			List<ProjectDto> projects = this.projectService.searchByNameLike(query);
			if(projects != null){
				for(ProjectDto d : projects){
					dto.addElement(d.getName());
				}
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
		
		return dto;
	}
	
	@RequestMapping(value = "/projects/get/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProjectResponseDto getProjectsByName(@PathVariable String name, HttpServletResponse response){
		ProjectResponseDto dto = new ProjectResponseDto();
		
		if(StringUtils.isNotBlank(name)){
			List<ProjectDto> projects = this.projectService.searchByName(name);
			if(projects != null){
				for(ProjectDto d : projects){
					dto.addProject(d);
				}
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
		
		return dto;
	}
	
}
