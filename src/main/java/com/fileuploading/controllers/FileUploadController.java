package com.fileuploading.controllers;

import com.fileuploading.entities.FileUpload;
import com.fileuploading.repositories.FileRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Controller
public class FileUploadController {


    @Autowired
    private FileRepository fileRepository;


    @GetMapping ("/")
    public String homeHandler(Model m){

        List<FileUpload> list = fileRepository.findAll();
        m.addAttribute("list",list);

        return "homePage";
    }
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam  MultipartFile file, HttpSession session) {

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(file.getOriginalFilename());

        FileUpload fileUp = fileRepository.save(fileUpload);

        if (fileUp!=null){

            try {

                File saveFile= new ClassPathResource("/static/img").getFile();

                Path path= Paths.get(saveFile.getAbsolutePath()+File.separator + file.getOriginalFilename() );
                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            }catch (Exception e){
                e.printStackTrace();
            }
        }


        session.setAttribute("msg","File Upload Successfully");

            return "redirect:/";
        }


    }











//    @GetMapping ("/home")
//    public String get(Model m){
//        List<FileUp> filesUps = fileStorageService.getAllFile();
//        m.addAttribute("filesUps",filesUps);
//        return "homePage";
//    }

//
//    @PostMapping ("/viewFiles")
//    public String viewFiles(@RequestParam("files") MultipartFile[] files){
//        for (MultipartFile file: files){
//            fileStorageService.saveFile(file);
//        }
//
//        return "viewFiles";
//    }
//
//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
//        FileUp fileUp = fileStorageService.getFile(fileId).get();
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(fileUp.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+fileUp.getFileName()+"\"")
//                .body(new ByteArrayResource(fileUp.getData()));
//    }




