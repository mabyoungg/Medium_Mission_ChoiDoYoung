package com.ll.likelionspringboottestmedium.domain.base.genFile.controller;

import com.ll.likelionspringboottestmedium.domain.base.genFile.entity.GenFile;
import com.ll.likelionspringboottestmedium.domain.base.genFile.service.GenFileService;
import com.ll.likelionspringboottestmedium.global.rq.Rq;
import com.ll.likelionspringboottestmedium.global.rsData.RsData;
import com.ll.likelionspringboottestmedium.standard.util.Ut.Ut;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/genFile")
@Validated
public class GenFileController {
    private final Rq rq;
    private final GenFileService genFileService;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable long id, HttpServletRequest request) throws FileNotFoundException {
        GenFile genFile = genFileService.findById(id).get();
        String filePath = genFile.getFilePath();

        Resource resource = new InputStreamResource(new FileInputStream(filePath));

        String contentType = request.getServletContext().getMimeType(new File(filePath).getAbsolutePath());

        if (contentType == null) contentType = "application/octet-stream";

        String fileName = Ut.url.encode(genFile.getOriginFileName()).replace("%20", " ");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

    @PostMapping("/upload")
    @ResponseBody
    public RsData<String> upload(
            String refererUrl,
            @RequestParam("file") MultipartFile file
    ) {
        String relTypeCode = null;
        long relId = 0;
        String typeCode = null;
        String type2Code = null;
        long fileNo = 0;

        System.out.println("referer : " + rq.getReferer());

        if (refererUrl.startsWith("/post/") && refererUrl.contains("/edit")) {
            relTypeCode = "post";
            relId = Long.parseLong(
                    refererUrl.split("/", 4)[2]
            );
            typeCode = "common";
            type2Code = "inBody";
        }

        GenFile savedFile = genFileService.saveFile(relTypeCode, relId, typeCode, type2Code, fileNo, file);

        return RsData.of("200-1", "파일이 생성되었습니다.", savedFile.getUrl());
    }
}
