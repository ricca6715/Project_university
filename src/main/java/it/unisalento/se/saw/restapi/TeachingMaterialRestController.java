package it.unisalento.se.saw.restapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.unisalento.se.saw.Iservices.ITeachingMaterialService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.models.TeachingMaterialModel;


@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/teachingmaterial")
@CrossOrigin
public class TeachingMaterialRestController {
	@Autowired
	ITeachingMaterialService teachingMaterialService;

	public TeachingMaterialRestController(ITeachingMaterialService teachingMaterialService) {
		super();
		this.teachingMaterialService = teachingMaterialService;
	}
	
	@GetMapping(
			value = "/getTeachingMaterialByIdLecture/{idLecture}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Teachingmaterial> getTeachingMaterialByIdLecture(@PathVariable("idLecture") int idLecture){
		return teachingMaterialService.getTeachingMaterialByIdLecture(idLecture);
	}
	
	@PostMapping(
			value = "/saveFile",
			consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
	public Teachingmaterial saveFile(@RequestParam("file") MultipartFile file,
			@RequestParam("lectureid") String lectureid,
			@RequestParam("userid") String userid) {

		String link = "C:\\Users\\Andrea\\Desktop\\Progetto\\temp\\"+file.getOriginalFilename();
		try {
			 File convertedFile = new File(file.getOriginalFilename());
			 file.transferTo(convertedFile);
			 System.out.println(convertedFile.getAbsolutePath());
		     FileInputStream in = new FileInputStream(convertedFile);
		     FileOutputStream out = new FileOutputStream(link);
		     int newByte = 0;
		     while(newByte!=-1){
		    	  newByte = in.read();
		    	  out.write(newByte);
		     }
		     in.close();
		     out.close();
		    } catch (Exception e) {
		     //e.printStackTrace();
		    }
		
		Teachingmaterial tm = new Teachingmaterial();
		Lecture l = new Lecture();
		User u = new User();
		l.setIdLecture(Integer.parseInt(lectureid));
		u.setIdUser(Integer.parseInt(userid));
		tm.setLecture(l);
		tm.setUser(u);
		tm.setLink(link);
		tm.setName(file.getOriginalFilename());
		tm.setType(file.getContentType());
		return teachingMaterialService.saveMaterial(tm);
	}
	
	@PostMapping(
			value = "/saveLink",
			consumes =  MediaType.APPLICATION_JSON_VALUE,
			produces =  MediaType.APPLICATION_JSON_VALUE
			)
	public Teachingmaterial saveLink(@RequestBody TeachingMaterialModel tmm) {
		Teachingmaterial tm = new Teachingmaterial();
		Lecture l = new Lecture();
		l.setIdLecture(tmm.getLecture().getIdLecture());
		tm.setLecture(l);
		tm.setLink(tmm.getLink());
		tm.setName(tmm.getName());
		tm.setType(tmm.getType());
		User u = new User();
		u.setIdUser(tmm.getUser().getIdUser());
		tm.setUser(u);
		return teachingMaterialService.saveMaterial(tm);
	}
	
	@GetMapping(
			value="/delete/{idTeachingmaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean removeMaterialById(@PathVariable("idTeachingmaterial") int idTeachingmaterial) {
		Teachingmaterial tm = getTeachingMaterialById(idTeachingmaterial);
		if(!(tm.getType().equalsIgnoreCase("link"))) {
			File file = new File(tm.getLink());
			if(file.delete()) {
	            System.out.println("File deleted successfully");
	            return teachingMaterialService.removeMaterial(idTeachingmaterial);
	        } else {
	            System.out.println("Failed to delete the file");
	            return false;
	        }
		}
		return teachingMaterialService.removeMaterial(idTeachingmaterial);
	}
	
	@GetMapping(
			value="/getTeachingMaterialById/{idTeachingmaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Teachingmaterial getTeachingMaterialById(@PathVariable("idTeachingmaterial") int idTeachingmaterial) {
		return teachingMaterialService.getTeachingMaterialById(idTeachingmaterial);
	}
		
	@PostMapping(value="/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestBody String filepath, HttpServletRequest request) throws MalformedURLException {
		Path filePath = Paths.get(filepath);
		String filename = filepath.substring(filepath.lastIndexOf(File.separator) + 1);
		System.out.println(filepath);
		System.out.println(filename);
        Resource resource = new UrlResource(filePath.toUri());
        
        String contentType = null;
        contentType = request.getServletContext().getMimeType(filepath);
        System.out.println(contentType);
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
		return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
					.contentType(MediaType.parseMediaType(contentType))
					.body(resource);	
	}

}
