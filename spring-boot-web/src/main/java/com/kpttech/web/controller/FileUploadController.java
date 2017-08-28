package com.kpttech.web.controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kpttech.common.pojo.Json;
import com.kpttech.common.utils.UUIDUtil;

import net.coobird.thumbnailator.Thumbnails;
//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FileUploadController {

	private static final Logger logger = Logger.getLogger(FileUploadController.class);

	public static final int SMALLTHUMBWIDTH = 50 ;//缩略图大小
    public static final int SMALLTHUMBHEIGHT = 50 ;
    public static final int MEDIUMTHUMBWIDTH = 500 ;//缩略图大小
    public static final int MEDIUMTHUMBHEIGHT = 500 ;
    public static final int LARGETHUMBWIDTH = 1000 ;//缩略图大小
    public static final int LARGETHUMBHEIGHT = 1000 ;
    
	/**
	 * 使用SpringMVC封装好的方法进行文件上传
	 * 
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
    //@CrossOrigin(origins = "http://172.16.224.2", maxAge = 3600)
	@RequestMapping(value="/fileupload/upload.action")
	@ResponseBody
	public Json upload(HttpServletRequest request, HttpServletResponse response) {
		Json j = new Json();
		List<String> listFiles = new ArrayList<String>();
		try {
			// 获取解析器
			CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 判断是否是文件
			if (resolver.isMultipart(request)) {

				// 保存路径：upload/2016/8/23/{guid}.{扩展名}
				String baseDir = request.getSession().getServletContext().getRealPath("/upload");

				// 进行转换
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
				// 获取所有文件名称
				Iterator<String> it = multiRequest.getFileNames();
				while (it.hasNext()) {
					// 根据文件名称取文件
					MultipartFile file = multiRequest.getFile(it.next());
					String fileName = file.getOriginalFilename();

					// logger.info(baseDir);
					Calendar ca = Calendar.getInstance();

					String y, m, d;

					y = String.valueOf(ca.get(Calendar.YEAR));

					m = String.valueOf(ca.get(Calendar.MONTH) + 1);

					d = String.valueOf(ca.get(Calendar.DATE));

					File fileDir = new File(baseDir + "/" + y + "/" + m + "/" + d + "/");

					// logger.info(fileDir);
					// 如果文件夹不存在则创建
					if (!fileDir.exists() && !fileDir.isDirectory()) {
						// System.out.println("//不存在");
						fileDir.mkdirs();
					} else {
						// System.out.println("//目录存在");
					}
					String fileNewName = UUIDUtil.gernerateUUID() + getExtensionName(fileName);
					String filePath = baseDir + "/" + y + "/" + m + "/" + d + "/" + fileNewName;

					// String localPath = "D:/temp/" + fileName;
					File newFile = new File(filePath);
					// 上传的文件写入到指定的文件中
					file.transferTo(newFile);

					listFiles.add("/upload/" + y + "/" + m + "/" + d + "/" + fileNewName);// 返回新路径

				}
			}
			j.setObj(listFiles);
			j.setSuccess(true);
			j.setMsg("保存成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 上传后自动创建缩略图，适合产品图片上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/fileupload/uploadthumb.action")
	@ResponseBody
	public Json uploadThumb(HttpServletRequest request, HttpServletResponse response) {
		Json j = new Json();
		List<String> listFiles = new ArrayList<String>();
		try {
			// 获取解析器
			CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 判断是否是文件
			if (resolver.isMultipart(request)) {

				// 保存路径：upload/2016/8/23/{guid}.{扩展名}
				String baseDir = request.getSession().getServletContext().getRealPath("/upload");

				// 进行转换
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
				// 获取所有文件名称
				Iterator<String> it = multiRequest.getFileNames();
				while (it.hasNext()) {
					// 根据文件名称取文件
					MultipartFile file = multiRequest.getFile(it.next());
					String fileName = file.getOriginalFilename();

					// logger.info(baseDir);
					Calendar ca = Calendar.getInstance();

					String y, m, d;

					y = String.valueOf(ca.get(Calendar.YEAR));

					m = String.valueOf(ca.get(Calendar.MONTH) + 1);

					d = String.valueOf(ca.get(Calendar.DATE));

					File fileDir = new File(baseDir + "/" + y + "/" + m + "/" + d + "/");

					// logger.info(fileDir);
					// 如果文件夹不存在则创建
					if (!fileDir.exists() && !fileDir.isDirectory()) {
						// System.out.println("//不存在");
						fileDir.mkdirs();
					} else {
						// System.out.println("//目录存在");
					}
					
					String extName=getExtensionName(fileName);
					if(!checkImageExt(extName)){
						j.setSuccess(false);
						j.setMsg("图片格式不正确！");
						return j;
					}
					
					String id=UUIDUtil.gernerateUUID();
					//缩略图处理
					//前端取缩略图时可以把.替换成_smallthumb.即可,考虑大中小三种图，原图不要了
					String fileNewNameThumb = id + "_smallthumb" + extName;
					String filePathThumb = baseDir + "/" + y + "/" + m + "/" + d + "/" + fileNewNameThumb;
					Thumbnails.of(file.getInputStream()).size(SMALLTHUMBWIDTH, SMALLTHUMBHEIGHT).toFile(filePathThumb);
					
					fileNewNameThumb = id + "_mediumthumb" + extName;
					filePathThumb = baseDir + "/" + y + "/" + m + "/" + d + "/" + fileNewNameThumb;
					Thumbnails.of(file.getInputStream()).size(MEDIUMTHUMBWIDTH, MEDIUMTHUMBHEIGHT).toFile(filePathThumb);
					
					fileNewNameThumb = id + "_largethumb" + extName;
					filePathThumb = baseDir + "/" + y + "/" + m + "/" + d + "/" + fileNewNameThumb;
					Thumbnails.of(file.getInputStream()).size(LARGETHUMBWIDTH, LARGETHUMBHEIGHT).toFile(filePathThumb);
					
					String fileNewName = id + extName;
					String filePath = baseDir + "/" + y + "/" + m + "/" + d + "/" + fileNewName;
					// String localPath = "D:/temp/" + fileName;
					File newFile = new File(filePath);
					// 上传的文件写入到指定的文件中
					file.transferTo(newFile);									

					listFiles.add("/upload/" + y + "/" + m + "/" + d + "/" + fileNewName);// 返回新路径
					

				}
			}
			j.setObj(listFiles);
			j.setSuccess(true);
			j.setMsg("保存成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	private String getExtensionName(String filename) {

		if ((filename != null) && (filename.length() > 0)) {

			int dot = filename.lastIndexOf('.');

			if ((dot > -1) && (dot < (filename.length() - 1))) {

				return filename.substring(dot);

			}

		}

		return filename;

	}
	
	private Boolean checkImageExt(String ext){
		Boolean flag=false;
		String[] extArray={".jpg",".jpeg",".jpe",".gif",".png",".bmp",".tiff",".tif",".jfif",".dib",".ico"};
		for(String s:extArray){
			if(s.equals(ext.toLowerCase())){
				flag=true;
				break;
			}
		}
		return flag;
	}
	


}
